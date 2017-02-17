// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: lacuna.proto

package cx.corp.lacuna.server.data;

/**
 * Protobuf type {@code ReadRequest}
 */
public  final class ReadRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:ReadRequest)
    ReadRequestOrBuilder {
  // Use ReadRequest.newBuilder() to construct.
  private ReadRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ReadRequest() {
    pid_ = 0;
    requestType_ = 0;
    offset_ = 0;
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet
  getUnknownFields() {
    return com.google.protobuf.UnknownFieldSet.getDefaultInstance();
  }
  private ReadRequest(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    this();
    int mutable_bitField0_ = 0;
    try {
      boolean done = false;
      while (!done) {
        int tag = input.readTag();
        switch (tag) {
          case 0:
            done = true;
            break;
          default: {
            if (!input.skipField(tag)) {
              done = true;
            }
            break;
          }
          case 8: {

            pid_ = input.readInt32();
            break;
          }
          case 16: {
            int rawValue = input.readEnum();

            requestType_ = rawValue;
            break;
          }
          case 24: {

            offset_ = input.readInt32();
            break;
          }
          case 34: {
            com.google.protobuf.Any.Builder subBuilder = null;
            if (data_ != null) {
              subBuilder = data_.toBuilder();
            }
            data_ = input.readMessage(com.google.protobuf.Any.parser(), extensionRegistry);
            if (subBuilder != null) {
              subBuilder.mergeFrom(data_);
              data_ = subBuilder.buildPartial();
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
      makeExtensionsImmutable();
    }
  }
  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return cx.corp.lacuna.server.data.Lacuna.internal_static_ReadRequest_descriptor;
  }

  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return cx.corp.lacuna.server.data.Lacuna.internal_static_ReadRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            cx.corp.lacuna.server.data.ReadRequest.class, cx.corp.lacuna.server.data.ReadRequest.Builder.class);
  }

  public static final int PID_FIELD_NUMBER = 1;
  private int pid_;
  /**
   * <code>int32 pid = 1;</code>
   */
  public int getPid() {
    return pid_;
  }

  public static final int REQUEST_TYPE_FIELD_NUMBER = 2;
  private int requestType_;
  /**
   * <code>.DataType request_type = 2;</code>
   */
  public int getRequestTypeValue() {
    return requestType_;
  }
  /**
   * <code>.DataType request_type = 2;</code>
   */
  public cx.corp.lacuna.server.data.DataType getRequestType() {
    cx.corp.lacuna.server.data.DataType result = cx.corp.lacuna.server.data.DataType.valueOf(requestType_);
    return result == null ? cx.corp.lacuna.server.data.DataType.UNRECOGNIZED : result;
  }

  public static final int OFFSET_FIELD_NUMBER = 3;
  private int offset_;
  /**
   * <code>int32 offset = 3;</code>
   */
  public int getOffset() {
    return offset_;
  }

  public static final int DATA_FIELD_NUMBER = 4;
  private com.google.protobuf.Any data_;
  /**
   * <code>.google.protobuf.Any data = 4;</code>
   */
  public boolean hasData() {
    return data_ != null;
  }
  /**
   * <code>.google.protobuf.Any data = 4;</code>
   */
  public com.google.protobuf.Any getData() {
    return data_ == null ? com.google.protobuf.Any.getDefaultInstance() : data_;
  }
  /**
   * <code>.google.protobuf.Any data = 4;</code>
   */
  public com.google.protobuf.AnyOrBuilder getDataOrBuilder() {
    return getData();
  }

  private byte memoizedIsInitialized = -1;
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (pid_ != 0) {
      output.writeInt32(1, pid_);
    }
    if (requestType_ != cx.corp.lacuna.server.data.DataType.BUFFER.getNumber()) {
      output.writeEnum(2, requestType_);
    }
    if (offset_ != 0) {
      output.writeInt32(3, offset_);
    }
    if (data_ != null) {
      output.writeMessage(4, getData());
    }
  }

  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (pid_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(1, pid_);
    }
    if (requestType_ != cx.corp.lacuna.server.data.DataType.BUFFER.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(2, requestType_);
    }
    if (offset_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(3, offset_);
    }
    if (data_ != null) {
      size += com.google.protobuf.CodedOutputStream
        .computeMessageSize(4, getData());
    }
    memoizedSize = size;
    return size;
  }

  private static final long serialVersionUID = 0L;
  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
     return true;
    }
    if (!(obj instanceof cx.corp.lacuna.server.data.ReadRequest)) {
      return super.equals(obj);
    }
    cx.corp.lacuna.server.data.ReadRequest other = (cx.corp.lacuna.server.data.ReadRequest) obj;

    boolean result = true;
    result = result && (getPid()
        == other.getPid());
    result = result && requestType_ == other.requestType_;
    result = result && (getOffset()
        == other.getOffset());
    result = result && (hasData() == other.hasData());
    if (hasData()) {
      result = result && getData()
          .equals(other.getData());
    }
    return result;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    hash = (37 * hash) + PID_FIELD_NUMBER;
    hash = (53 * hash) + getPid();
    hash = (37 * hash) + REQUEST_TYPE_FIELD_NUMBER;
    hash = (53 * hash) + requestType_;
    hash = (37 * hash) + OFFSET_FIELD_NUMBER;
    hash = (53 * hash) + getOffset();
    if (hasData()) {
      hash = (37 * hash) + DATA_FIELD_NUMBER;
      hash = (53 * hash) + getData().hashCode();
    }
    hash = (29 * hash) + unknownFields.hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static cx.corp.lacuna.server.data.ReadRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cx.corp.lacuna.server.data.ReadRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cx.corp.lacuna.server.data.ReadRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static cx.corp.lacuna.server.data.ReadRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static cx.corp.lacuna.server.data.ReadRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static cx.corp.lacuna.server.data.ReadRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }
  public static cx.corp.lacuna.server.data.ReadRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }
  public static cx.corp.lacuna.server.data.ReadRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static cx.corp.lacuna.server.data.ReadRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static cx.corp.lacuna.server.data.ReadRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(cx.corp.lacuna.server.data.ReadRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
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
   * Protobuf type {@code ReadRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:ReadRequest)
      cx.corp.lacuna.server.data.ReadRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return cx.corp.lacuna.server.data.Lacuna.internal_static_ReadRequest_descriptor;
    }

    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return cx.corp.lacuna.server.data.Lacuna.internal_static_ReadRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              cx.corp.lacuna.server.data.ReadRequest.class, cx.corp.lacuna.server.data.ReadRequest.Builder.class);
    }

    // Construct using cx.corp.lacuna.server.data.ReadRequest.newBuilder()
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
    public Builder clear() {
      super.clear();
      pid_ = 0;

      requestType_ = 0;

      offset_ = 0;

      if (dataBuilder_ == null) {
        data_ = null;
      } else {
        data_ = null;
        dataBuilder_ = null;
      }
      return this;
    }

    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return cx.corp.lacuna.server.data.Lacuna.internal_static_ReadRequest_descriptor;
    }

    public cx.corp.lacuna.server.data.ReadRequest getDefaultInstanceForType() {
      return cx.corp.lacuna.server.data.ReadRequest.getDefaultInstance();
    }

    public cx.corp.lacuna.server.data.ReadRequest build() {
      cx.corp.lacuna.server.data.ReadRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    public cx.corp.lacuna.server.data.ReadRequest buildPartial() {
      cx.corp.lacuna.server.data.ReadRequest result = new cx.corp.lacuna.server.data.ReadRequest(this);
      result.pid_ = pid_;
      result.requestType_ = requestType_;
      result.offset_ = offset_;
      if (dataBuilder_ == null) {
        result.data_ = data_;
      } else {
        result.data_ = dataBuilder_.build();
      }
      onBuilt();
      return result;
    }

    public Builder clone() {
      return (Builder) super.clone();
    }
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.setField(field, value);
    }
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return (Builder) super.clearField(field);
    }
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return (Builder) super.clearOneof(oneof);
    }
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, Object value) {
      return (Builder) super.setRepeatedField(field, index, value);
    }
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        Object value) {
      return (Builder) super.addRepeatedField(field, value);
    }
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof cx.corp.lacuna.server.data.ReadRequest) {
        return mergeFrom((cx.corp.lacuna.server.data.ReadRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(cx.corp.lacuna.server.data.ReadRequest other) {
      if (other == cx.corp.lacuna.server.data.ReadRequest.getDefaultInstance()) return this;
      if (other.getPid() != 0) {
        setPid(other.getPid());
      }
      if (other.requestType_ != 0) {
        setRequestTypeValue(other.getRequestTypeValue());
      }
      if (other.getOffset() != 0) {
        setOffset(other.getOffset());
      }
      if (other.hasData()) {
        mergeData(other.getData());
      }
      onChanged();
      return this;
    }

    public final boolean isInitialized() {
      return true;
    }

    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      cx.corp.lacuna.server.data.ReadRequest parsedMessage = null;
      try {
        parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        parsedMessage = (cx.corp.lacuna.server.data.ReadRequest) e.getUnfinishedMessage();
        throw e.unwrapIOException();
      } finally {
        if (parsedMessage != null) {
          mergeFrom(parsedMessage);
        }
      }
      return this;
    }

    private int pid_ ;
    /**
     * <code>int32 pid = 1;</code>
     */
    public int getPid() {
      return pid_;
    }
    /**
     * <code>int32 pid = 1;</code>
     */
    public Builder setPid(int value) {
      
      pid_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 pid = 1;</code>
     */
    public Builder clearPid() {
      
      pid_ = 0;
      onChanged();
      return this;
    }

    private int requestType_ = 0;
    /**
     * <code>.DataType request_type = 2;</code>
     */
    public int getRequestTypeValue() {
      return requestType_;
    }
    /**
     * <code>.DataType request_type = 2;</code>
     */
    public Builder setRequestTypeValue(int value) {
      requestType_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>.DataType request_type = 2;</code>
     */
    public cx.corp.lacuna.server.data.DataType getRequestType() {
      cx.corp.lacuna.server.data.DataType result = cx.corp.lacuna.server.data.DataType.valueOf(requestType_);
      return result == null ? cx.corp.lacuna.server.data.DataType.UNRECOGNIZED : result;
    }
    /**
     * <code>.DataType request_type = 2;</code>
     */
    public Builder setRequestType(cx.corp.lacuna.server.data.DataType value) {
      if (value == null) {
        throw new NullPointerException();
      }
      
      requestType_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <code>.DataType request_type = 2;</code>
     */
    public Builder clearRequestType() {
      
      requestType_ = 0;
      onChanged();
      return this;
    }

    private int offset_ ;
    /**
     * <code>int32 offset = 3;</code>
     */
    public int getOffset() {
      return offset_;
    }
    /**
     * <code>int32 offset = 3;</code>
     */
    public Builder setOffset(int value) {
      
      offset_ = value;
      onChanged();
      return this;
    }
    /**
     * <code>int32 offset = 3;</code>
     */
    public Builder clearOffset() {
      
      offset_ = 0;
      onChanged();
      return this;
    }

    private com.google.protobuf.Any data_ = null;
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> dataBuilder_;
    /**
     * <code>.google.protobuf.Any data = 4;</code>
     */
    public boolean hasData() {
      return dataBuilder_ != null || data_ != null;
    }
    /**
     * <code>.google.protobuf.Any data = 4;</code>
     */
    public com.google.protobuf.Any getData() {
      if (dataBuilder_ == null) {
        return data_ == null ? com.google.protobuf.Any.getDefaultInstance() : data_;
      } else {
        return dataBuilder_.getMessage();
      }
    }
    /**
     * <code>.google.protobuf.Any data = 4;</code>
     */
    public Builder setData(com.google.protobuf.Any value) {
      if (dataBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        data_ = value;
        onChanged();
      } else {
        dataBuilder_.setMessage(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any data = 4;</code>
     */
    public Builder setData(
        com.google.protobuf.Any.Builder builderForValue) {
      if (dataBuilder_ == null) {
        data_ = builderForValue.build();
        onChanged();
      } else {
        dataBuilder_.setMessage(builderForValue.build());
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any data = 4;</code>
     */
    public Builder mergeData(com.google.protobuf.Any value) {
      if (dataBuilder_ == null) {
        if (data_ != null) {
          data_ =
            com.google.protobuf.Any.newBuilder(data_).mergeFrom(value).buildPartial();
        } else {
          data_ = value;
        }
        onChanged();
      } else {
        dataBuilder_.mergeFrom(value);
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any data = 4;</code>
     */
    public Builder clearData() {
      if (dataBuilder_ == null) {
        data_ = null;
        onChanged();
      } else {
        data_ = null;
        dataBuilder_ = null;
      }

      return this;
    }
    /**
     * <code>.google.protobuf.Any data = 4;</code>
     */
    public com.google.protobuf.Any.Builder getDataBuilder() {
      
      onChanged();
      return getDataFieldBuilder().getBuilder();
    }
    /**
     * <code>.google.protobuf.Any data = 4;</code>
     */
    public com.google.protobuf.AnyOrBuilder getDataOrBuilder() {
      if (dataBuilder_ != null) {
        return dataBuilder_.getMessageOrBuilder();
      } else {
        return data_ == null ?
            com.google.protobuf.Any.getDefaultInstance() : data_;
      }
    }
    /**
     * <code>.google.protobuf.Any data = 4;</code>
     */
    private com.google.protobuf.SingleFieldBuilderV3<
        com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder> 
        getDataFieldBuilder() {
      if (dataBuilder_ == null) {
        dataBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
            com.google.protobuf.Any, com.google.protobuf.Any.Builder, com.google.protobuf.AnyOrBuilder>(
                getData(),
                getParentForChildren(),
                isClean());
        data_ = null;
      }
      return dataBuilder_;
    }
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }

    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return this;
    }


    // @@protoc_insertion_point(builder_scope:ReadRequest)
  }

  // @@protoc_insertion_point(class_scope:ReadRequest)
  private static final cx.corp.lacuna.server.data.ReadRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new cx.corp.lacuna.server.data.ReadRequest();
  }

  public static cx.corp.lacuna.server.data.ReadRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ReadRequest>
      PARSER = new com.google.protobuf.AbstractParser<ReadRequest>() {
    public ReadRequest parsePartialFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
        return new ReadRequest(input, extensionRegistry);
    }
  };

  public static com.google.protobuf.Parser<ReadRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ReadRequest> getParserForType() {
    return PARSER;
  }

  public cx.corp.lacuna.server.data.ReadRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}

