'use strict';

const { NodeTracerProvider } = require('@opentelemetry/sdk-trace-node');
const { registerInstrumentations } = require('@opentelemetry/instrumentation');
const { BatchSpanProcessor } = require('@opentelemetry/sdk-trace-base');
const { CollectorTraceExporter } = require('@opentelemetry/exporter-collector-grpc');
const { GrpcInstrumentation } = require('@opentelemetry/instrumentation-grpc');
const { PinoInstrumentation } = require('@opentelemetry/instrumentation-pino');
const { Resource } = require('@opentelemetry/resources');
const { SemanticResourceAttributes } = require('@opentelemetry/semantic-conventions');
const os = require('os');

const identifier = process.env.HOSTNAME || os.hostname();
const resource = new Resource({
  [SemanticResourceAttributes.SERVICE_INSTANCE_ID]: identifier,
  [SemanticResourceAttributes.SERVICE_NAME]: 'PaymentService'
});

const traceProvider = new NodeTracerProvider({
  resource,
});

let url = process.env.OTEL_EXPORTER_OTLP_ENDPOINT;

const collectorOptions = {
  url,
};

const traceExporter = new CollectorTraceExporter(collectorOptions);

traceProvider.addSpanProcessor(new BatchSpanProcessor(traceExporter));

traceProvider.register();

registerInstrumentations({
  instrumentations: [
    new PinoInstrumentation({
      logHook: (_span, record) => {
        record['service.name'] =
          traceProvider.resource.attributes['service.name'];
      },
    }),
    new GrpcInstrumentation(),
  ],
});