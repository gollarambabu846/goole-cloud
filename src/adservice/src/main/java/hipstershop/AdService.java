/*
 * Copyright 2018, Google LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hipstershop;

import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Iterables;
import hipstershop.Demo.Ad;
import hipstershop.Demo.AdRequest;
import hipstershop.Demo.AdResponse;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.StatusRuntimeException;
import io.grpc.health.v1.HealthCheckResponse.ServingStatus;
import io.grpc.services.HealthStatusManager;
import io.grpc.stub.StreamObserver;
import io.opentelemetry.metrics.DoubleMeasure;
import io.opentelemetry.metrics.LongCounter;
import io.opentelemetry.metrics.Meter;
import io.opentelemetry.metrics.MeterProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.net.InetAddress;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public final class AdService {

  private static final Logger logger = LogManager.getLogger(AdService.class);

  private static final int MAX_ADS_TO_SERVE = 2;
  private final MeterProvider meterProvider;

  private Server server;
  private HealthStatusManager healthMgr;

  public AdService(MeterProvider meterProvider) {
    this.meterProvider = meterProvider;
  }

  private static String getHost() {
    try {
      return InetAddress.getLocalHost().getHostName();
    } catch (java.net.UnknownHostException e) {
      return "";
    }
  }

  private void start() throws IOException {
    int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "9555"));
    healthMgr = new HealthStatusManager();

    server =
        ServerBuilder.forPort(port)
            .addService(new AdServiceImpl(this))
            .addService(healthMgr.getHealthService())
            .build()
            .start();
    logger.info("Ad Service started, listening on " + port);
    Runtime.getRuntime()
        .addShutdownHook(
            new Thread(
                () -> {
                  // Use stderr here since the logger may have been reset by its JVM shutdown hook.
                  System.err.println(
                      "*** shutting down gRPC ads server since JVM is shutting down");
                  AdService.this.stop();
                  System.err.println("*** server shut down");
                }));
    healthMgr.setStatus("", ServingStatus.SERVING);
  }

  private void stop() {
    if (server != null) {
      healthMgr.clearStatus("");
      server.shutdown();
    }
  }

  private static class AdServiceImpl extends hipstershop.AdServiceGrpc.AdServiceImplBase {
    private final AdService service;

    // note: these two instruments should be updated to match semantic conventions, when they are
    // defined.
    private final LongCounter requestCount;
    private final DoubleMeasure requestLatency;
    private final LongCounter errorCount;
    private final LongCounter numberOfAdsRequested;

    public AdServiceImpl(AdService service) {
      this.service = service;
      //    private final Tracer tracer = OpenTelemetry.getTracerProvider().get("AdService");
      Meter meter = service.meterProvider.get("AdService");
      ImmutableMap<String, String> hostLabels = ImmutableMap.of(
          "host",
          AdService.getHost()
      );
      requestCount = meter
          .longCounterBuilder("rpc_request_count")
          .setDescription("Number of gRPC requests to a service")
          .setConstantLabels(hostLabels)
          .setUnit("1")
          .build();
      requestLatency = meter
          .doubleMeasureBuilder("rpc_request_latency")
          .setDescription("Timings of gRPC requests to a service")
          .setConstantLabels(hostLabels)
          .setUnit("ms")
          .build();
      errorCount = meter
          .longCounterBuilder("rpc_error_count")
          .setDescription("Number of gRPC requests to a service which resulted in an error")
          .setConstantLabels(hostLabels)
          .setUnit("1")
          .build();
      numberOfAdsRequested = meter
          .longCounterBuilder("ads_requested")
          .setMonotonic(false)
          .setConstantLabels(hostLabels)
          .setUnit("one")
          .setDescription("Number of Ads Requested per Request")
          .build();
    }

    /**
     * Retrieves ads based on context provided in the request {@code AdRequest}.
     *
     * @param req the request containing context.
     * @param responseObserver the stream observer which gets notified with the value of {@code
     * AdResponse}
     */
    @Override
    public void getAds(AdRequest req, StreamObserver<AdResponse> responseObserver) {
      String methodName = "hipstershop.AdService/getAds";
      requestCount.add(1, "method.name", methodName);
      long startTime = System.currentTimeMillis();
      numberOfAdsRequested.add(req.getContextKeysCount());

      try {
        List<Ad> allAds = new ArrayList<>();
        logger.info("received ad request (context_words=" + req.getContextKeysCount() + ")");
        if (req.getContextKeysCount() > 0) {
          for (int i = 0; i < req.getContextKeysCount(); i++) {
            Collection<Ad> ads = service.getAdsByCategory(req.getContextKeys(i));
            allAds.addAll(ads);
          }
        } else {
          allAds = service.getRandomAds();
        }
        if (allAds.isEmpty()) {
          // Serve random ads.
          allAds = service.getRandomAds();
        }
        AdResponse reply = AdResponse.newBuilder().addAllAds(allAds).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
        requestLatency.record(
            (System.currentTimeMillis() - startTime), "method.name", methodName, "error", "false");
      } catch (StatusRuntimeException e) {
        logger.log(Level.WARN, "GetAds Failed with status {}", e.getStatus());
        errorCount.add(1, "method.name", methodName);
        responseObserver.onError(e);
        requestLatency.record(
            (System.currentTimeMillis() - startTime), "method.name", methodName, "error", "true");
      }
    }
  }

  private static final ImmutableListMultimap<String, Ad> adsMap = createAdsMap();

  private Collection<Ad> getAdsByCategory(String category) {
    return adsMap.get(category);
  }

  private static final Random random = new Random();

  private List<Ad> getRandomAds() {
    List<Ad> ads = new ArrayList<>(MAX_ADS_TO_SERVE);
    Collection<Ad> allAds = adsMap.values();
    for (int i = 0; i < MAX_ADS_TO_SERVE; i++) {
      ads.add(Iterables.get(allAds, random.nextInt(allAds.size())));
    }
    return ads;
  }

  /** Await termination on the main thread since the grpc library uses daemon threads. */
  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  private static ImmutableListMultimap<String, Ad> createAdsMap() {
    Ad camera =
        Ad.newBuilder()
            .setRedirectUrl("/product/2ZYFJ3GM2N")
            .setText("Film camera for sale. 50% off.")
            .build();
    Ad lens =
        Ad.newBuilder()
            .setRedirectUrl("/product/66VCHSJNUP")
            .setText("Vintage camera lens for sale. 20% off.")
            .build();
    Ad recordPlayer =
        Ad.newBuilder()
            .setRedirectUrl("/product/0PUK6V6EV0")
            .setText("Vintage record player for sale. 30% off.")
            .build();
    Ad bike =
        Ad.newBuilder()
            .setRedirectUrl("/product/9SIQT8TOJO")
            .setText("City Bike for sale. 10% off.")
            .build();
    Ad baristaKit =
        Ad.newBuilder()
            .setRedirectUrl("/product/1YMWWN1N4O")
            .setText("Home Barista kitchen kit for sale. Buy one, get second kit for free")
            .build();
    Ad airPlant =
        Ad.newBuilder()
            .setRedirectUrl("/product/6E92ZMYYFZ")
            .setText("Air plants for sale. Buy two, get third one for free")
            .build();
    Ad terrarium =
        Ad.newBuilder()
            .setRedirectUrl("/product/L9ECAV7KIM")
            .setText("Terrarium for sale. Buy one, get second one for free")
            .build();
    return ImmutableListMultimap.<String, Ad>builder()
        .putAll("photography", camera, lens)
        .putAll("vintage", camera, lens, recordPlayer)
        .put("cycling", bike)
        .put("cookware", baristaKit)
        .putAll("gardening", airPlant, terrarium)
        .build();
  }

  /** Main launches the server from the command line. */
  public static void main(String[] args) throws IOException, InterruptedException {
    MeterProvider meterProvider = OpenTelemetryUtils.initializeForNewRelic();

    // Start the RPC server. You shouldn't see any output from gRPC before this.
    logger.info("AdService starting.");
    AdService adService = new AdService(meterProvider);
    adService.start();
    adService.blockUntilShutdown();
  }
}
