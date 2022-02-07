// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ratingService.proto

package cse.project.grpc;

/**
 * Protobuf type {@code ShopRatingResponse}
 */
public  final class ShopRatingResponse extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ShopRatingResponse)
    ShopRatingResponseOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ShopRatingResponse.newBuilder() to construct.
  private ShopRatingResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ShopRatingResponse() {
    rating_ = 0D;
    ratingCount_ = 0L;
    responseMessage_ = "";
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return this.unknownFields;
  }
  private ShopRatingResponse(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    if (extensionRegistry == null) {
      throw new java.lang.NullPointerException();
    }
    int mutable_bitField0_ = 0;
    com.google.protobuf.UnknownFieldSet.Builder unknownFields =
        com.google.protobuf.UnknownFieldSet.newBuilder();
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          case 9: {

            rating_ = input.readDouble();
            break;
          }
          case 16: {

            ratingCount_ = input.readInt64();
            break;
          }
          case 26: {
            java.lang.String s = input.readStringRequireUtf8();

            responseMessage_ = s;
            break;
          }
          default: {
            if (!parseUnknownFieldProto3(
                input, unknownFields, extensionRegistry, tag)) {
              done = true;
            }
            break;
          }
        }
      }
    } catch (com.google.protobuf.InvalidProtocolBufferException e) {
      throw e.setUnfinishedMessage(this);
    } catch (java.io.IOException e) {
      throw new com.google.protobuf.InvalidProtocolBufferException(
          e).setUnfinishedMessage(this);
    } finally {
      this.unknownFields = unknownFields.build();
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return cse.project.grpc.RatingServiceOuterClass.internal_static_ShopRatingResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return cse.project.grpc.RatingServiceOuterClass.internal_static_ShopRatingResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            cse.project.grpc.ShopRatingResponse.class, cse.project.grpc.ShopRatingResponse.Builder.class);
  }

  public static final int RATING_FIELD_NUMBER = 1;
  private double rating_;
  /**
   * <code>double rating = 1;</code>
   */
  public double getRating() {
    return rating_;
  }

  public static final int RATINGCOUNT_FIELD_NUMBER = 2;
  private long ratingCount_;
  /**
   * <code>int64 ratingCount = 2;</code>
   */
  public long getRatingCount() {
    return ratingCount_;
  }

  public static final int RESPONSEMESSAGE_FIELD_NUMBER = 3;
  private volatile java.lang.Object responseMessage_;
  /**
   * <code>string responseMessage = 3;</code>
   */
  public java.lang.String getResponseMessage() {
    java.lang.Object ref = responseMessage_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      responseMessage_ = s;
      return s;
    }
  }
  /**
   * <code>string responseMessage = 3;</code>
   */
  public com.google.protobuf.ByteString
      getResponseMessageBytes() {
    java.lang.Object ref = responseMessage_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      responseMessage_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  private byte memoizedIsInitialized = -1;
  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (rating_ != 0D) {
      output.writeDouble(1, rating_);
    }
    if (ratingCount_ != 0L) {
      output.writeInt64(2, ratingCount_);
    }
    if (!getResponseMessageBytes().isEmpty()) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, responseMessage_);
    }
    unknownFields.writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (rating_ != 0D) {
      size += com.google.protobuf.CodedOutputStream
        .computeDoubleSize(1, rating_);
    }
    if (ratingCount_ != 0L) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt64Size(2, ratingCount_);
    }
    if (!getResponseMessageBytes().isEmpty()) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, responseMessage_);
    }
    size += unknownFields.getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof cse.project.grpc.ShopRatingResponse)) {
      return super.equals(obj);
    }
    cse.project.grpc.ShopRatingResponse other = (cse.project.grpc.ShopRatingResponse) obj;

    boolean result = true;
    result = result && (
        java.lang.Double.doubleToLongBits(getRating())
        == java.lang.Double.doubleToLongBits(
            other.getRating()));
    result = result && (getRatingCount()
        == other.getRatingCount());
    result = result && getResponseMessage()
        .equals(other.getResponseMessage());
    result = result && unknownFields.equals(other.unknownFields);
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + RATING_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        java.lang.Double.doubleToLongBits(getRating()));
    hash = (37 * hash) + RATINGCOUNT_FIELD_NUMBER;
    hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
        getRatingCount());
    hash = (37 * hash) + RESPONSEMESSAGE_FIELD_NUMBER;
    hash = (53 * hash) + getResponseMessage().hashCode();
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static cse.project.grpc.ShopRatingResponse parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cse.project.grpc.ShopRatingResponse parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cse.project.grpc.ShopRatingResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cse.project.grpc.ShopRatingResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cse.project.grpc.ShopRatingResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cse.project.grpc.ShopRatingResponse parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cse.project.grpc.ShopRatingResponse parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static cse.project.grpc.ShopRatingResponse parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static cse.project.grpc.ShopRatingResponse parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static cse.project.grpc.ShopRatingResponse parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static cse.project.grpc.ShopRatingResponse parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static cse.project.grpc.ShopRatingResponse parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(cse.project.grpc.ShopRatingResponse prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * Protobuf type {@code ShopRatingResponse}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ShopRatingResponse)
      cse.project.grpc.ShopRatingResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return cse.project.grpc.RatingServiceOuterClass.internal_static_ShopRatingResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cse.project.grpc.RatingServiceOuterClass.internal_static_ShopRatingResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              cse.project.grpc.ShopRatingResponse.class, cse.project.grpc.ShopRatingResponse.Builder.class);
    }

    // Construct using cse.project.grpc.ShopRatingResponse.newBuilder()
    private Builder() {
      maybeForceBuilderInitialization();
    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
      maybeForceBuilderInitialization();
    }
    private void maybeForceBuilderInitialization() {
      if (com.google.protobuf.GeneratedMessageV3
              .alwaysUseFieldBuilders) {
      }
    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      rating_ = 0D;

      ratingCount_ = 0L;

      responseMessage_ = "";

      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return cse.project.grpc.RatingServiceOuterClass.internal_static_ShopRatingResponse_descriptor;
    }

    @java.lang.Override
    public cse.project.grpc.ShopRatingResponse getDefaultInstanceForType() {
      return cse.project.grpc.ShopRatingResponse.getDefaultInstance();
    }

    @java.lang.Override
    public cse.project.grpc.ShopRatingResponse build() {
      cse.project.grpc.ShopRatingResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public cse.project.grpc.ShopRatingResponse buildPartial() {
      cse.project.grpc.ShopRatingResponse result = new cse.project.grpc.ShopRatingResponse(this);
      result.rating_ = rating_;
      result.ratingCount_ = ratingCount_;
      result.responseMessage_ = responseMessage_;
      onBuilt();
      return result;
    }

    @java.lang.Override
    public Builder clone() {
      return (Builder) super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof cse.project.grpc.ShopRatingResponse) {
        return mergeFrom((cse.project.grpc.ShopRatingResponse)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(cse.project.grpc.ShopRatingResponse other) {
      if (other == cse.project.grpc.ShopRatingResponse.getDefaultInstance()) return this;
      if (other.getRating() != 0D) {
        setRating(other.getRating());
      }
      if (other.getRatingCount() != 0L) {
        setRatingCount(other.getRatingCount());
      }
      if (!other.getResponseMessage().isEmpty()) {
        responseMessage_ = other.responseMessage_;
        onChanged();
      }
      this.mergeUnknownFields(other.unknownFields);
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      cse.project.grpc.ShopRatingResponse parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (cse.project.grpc.ShopRatingResponse) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private double rating_ ;
    /**
     * <code>double rating = 1;</code>
     */
    public double getRating() {
      return rating_;
    }
    /**
     * <code>double rating = 1;</code>
     */
    public Builder setRating(double value) {
      
      rating_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>double rating = 1;</code>
     */
    public Builder clearRating() {
      
      rating_ = 0D;
      onChanged();
      return this;
    }

    private long ratingCount_ ;
    /**
     * <code>int64 ratingCount = 2;</code>
     */
    public long getRatingCount() {
      return ratingCount_;
    }
    /**
     * <code>int64 ratingCount = 2;</code>
     */
    public Builder setRatingCount(long value) {
      
      ratingCount_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int64 ratingCount = 2;</code>
     */
    public Builder clearRatingCount() {
      
      ratingCount_ = 0L;
      onChanged();
      return this;
    }

    private java.lang.Object responseMessage_ = "";
    /**
     * <code>string responseMessage = 3;</code>
     */
    public java.lang.String getResponseMessage() {
      java.lang.Object ref = responseMessage_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        responseMessage_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <code>string responseMessage = 3;</code>
     */
    public com.google.protobuf.ByteString
        getResponseMessageBytes() {
      java.lang.Object ref = responseMessage_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        responseMessage_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <code>string responseMessage = 3;</code>
     */
    public Builder setResponseMessage(
        java.lang.String value) {
      if (value == null) {
    throw new NullPointerException();
  }
  
      responseMessage_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>string responseMessage = 3;</code>
     */
    public Builder clearResponseMessage() {
      
      responseMessage_ = getDefaultInstance().getResponseMessage();
      onChanged();
      return this;
    }
    /**
     * <code>string responseMessage = 3;</code>
     */
    public Builder setResponseMessageBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
      
      responseMessage_ = value;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFieldsProto3(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:ShopRatingResponse)
  }

  // @@protoc_insertion_point(class_scope:ShopRatingResponse)
  private static final cse.project.grpc.ShopRatingResponse DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new cse.project.grpc.ShopRatingResponse();
  }

  public static cse.project.grpc.ShopRatingResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ShopRatingResponse>
      PARSER = new com.google.protobuf.AbstractParser<ShopRatingResponse>() {
    @java.lang.Override
    public ShopRatingResponse parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return new ShopRatingResponse(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ShopRatingResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ShopRatingResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public cse.project.grpc.ShopRatingResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

