// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: ResultMessage.proto

package com.HyChat.server.Message;

public final class ResultMessageOuterClass {
  private ResultMessageOuterClass() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ResultMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:ResultMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>bool Result = 1;</code>
     * @return The result.
     */
    boolean getResult();

    /**
     * <code>.google.protobuf.Timestamp sendTime = 4;</code>
     * @return Whether the sendTime field is set.
     */
    boolean hasSendTime();
    /**
     * <code>.google.protobuf.Timestamp sendTime = 4;</code>
     * @return The sendTime.
     */
    com.google.protobuf.Timestamp getSendTime();
    /**
     * <code>.google.protobuf.Timestamp sendTime = 4;</code>
     */
    com.google.protobuf.TimestampOrBuilder getSendTimeOrBuilder();

    /**
     * <pre>
     *消息类型
     * </pre>
     *
     * <code>int32 MessType = 3;</code>
     * @return The messType.
     */
    int getMessType();

    /**
     * <code>bytes Data = 2;</code>
     * @return The data.
     */
    com.google.protobuf.ByteString getData();
  }
  /**
   * <pre>
   * 服务端返回客户端的消息
   * </pre>
   *
   * Protobuf type {@code ResultMessage}
   */
  public  static final class ResultMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:ResultMessage)
      ResultMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ResultMessage.newBuilder() to construct.
    private ResultMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ResultMessage() {
      data_ = com.google.protobuf.ByteString.EMPTY;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ResultMessage();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ResultMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
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
            case 8: {

              result_ = input.readBool();
              break;
            }
            case 18: {

              data_ = input.readBytes();
              break;
            }
            case 24: {

              messType_ = input.readInt32();
              break;
            }
            case 34: {
              com.google.protobuf.Timestamp.Builder subBuilder = null;
              if (sendTime_ != null) {
                subBuilder = sendTime_.toBuilder();
              }
              sendTime_ = input.readMessage(com.google.protobuf.Timestamp.parser(), extensionRegistry);
              if (subBuilder != null) {
                subBuilder.mergeFrom(sendTime_);
                sendTime_ = subBuilder.buildPartial();
              }

              break;
            }
            default: {
              if (!parseUnknownField(
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
      return com.HyChat.server.Message.ResultMessageOuterClass.internal_static_ResultMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.HyChat.server.Message.ResultMessageOuterClass.internal_static_ResultMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage.class, com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage.Builder.class);
    }

    public static final int RESULT_FIELD_NUMBER = 1;
    private boolean result_;
    /**
     * <code>bool Result = 1;</code>
     * @return The result.
     */
    public boolean getResult() {
      return result_;
    }

    public static final int SENDTIME_FIELD_NUMBER = 4;
    private com.google.protobuf.Timestamp sendTime_;
    /**
     * <code>.google.protobuf.Timestamp sendTime = 4;</code>
     * @return Whether the sendTime field is set.
     */
    public boolean hasSendTime() {
      return sendTime_ != null;
    }
    /**
     * <code>.google.protobuf.Timestamp sendTime = 4;</code>
     * @return The sendTime.
     */
    public com.google.protobuf.Timestamp getSendTime() {
      return sendTime_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : sendTime_;
    }
    /**
     * <code>.google.protobuf.Timestamp sendTime = 4;</code>
     */
    public com.google.protobuf.TimestampOrBuilder getSendTimeOrBuilder() {
      return getSendTime();
    }

    public static final int MESSTYPE_FIELD_NUMBER = 3;
    private int messType_;
    /**
     * <pre>
     *消息类型
     * </pre>
     *
     * <code>int32 MessType = 3;</code>
     * @return The messType.
     */
    public int getMessType() {
      return messType_;
    }

    public static final int DATA_FIELD_NUMBER = 2;
    private com.google.protobuf.ByteString data_;
    /**
     * <code>bytes Data = 2;</code>
     * @return The data.
     */
    public com.google.protobuf.ByteString getData() {
      return data_;
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
      if (result_ != false) {
        output.writeBool(1, result_);
      }
      if (!data_.isEmpty()) {
        output.writeBytes(2, data_);
      }
      if (messType_ != 0) {
        output.writeInt32(3, messType_);
      }
      if (sendTime_ != null) {
        output.writeMessage(4, getSendTime());
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (result_ != false) {
        size += com.google.protobuf.CodedOutputStream
          .computeBoolSize(1, result_);
      }
      if (!data_.isEmpty()) {
        size += com.google.protobuf.CodedOutputStream
          .computeBytesSize(2, data_);
      }
      if (messType_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, messType_);
      }
      if (sendTime_ != null) {
        size += com.google.protobuf.CodedOutputStream
          .computeMessageSize(4, getSendTime());
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
      if (!(obj instanceof com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage)) {
        return super.equals(obj);
      }
      com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage other = (com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage) obj;

      if (getResult()
          != other.getResult()) return false;
      if (hasSendTime() != other.hasSendTime()) return false;
      if (hasSendTime()) {
        if (!getSendTime()
            .equals(other.getSendTime())) return false;
      }
      if (getMessType()
          != other.getMessType()) return false;
      if (!getData()
          .equals(other.getData())) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + RESULT_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashBoolean(
          getResult());
      if (hasSendTime()) {
        hash = (37 * hash) + SENDTIME_FIELD_NUMBER;
        hash = (53 * hash) + getSendTime().hashCode();
      }
      hash = (37 * hash) + MESSTYPE_FIELD_NUMBER;
      hash = (53 * hash) + getMessType();
      hash = (37 * hash) + DATA_FIELD_NUMBER;
      hash = (53 * hash) + getData().hashCode();
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parseFrom(
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
    public static Builder newBuilder(com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage prototype) {
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
     * <pre>
     * 服务端返回客户端的消息
     * </pre>
     *
     * Protobuf type {@code ResultMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:ResultMessage)
        com.HyChat.server.Message.ResultMessageOuterClass.ResultMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return com.HyChat.server.Message.ResultMessageOuterClass.internal_static_ResultMessage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return com.HyChat.server.Message.ResultMessageOuterClass.internal_static_ResultMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage.class, com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage.Builder.class);
      }

      // Construct using com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage.newBuilder()
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
        result_ = false;

        if (sendTimeBuilder_ == null) {
          sendTime_ = null;
        } else {
          sendTime_ = null;
          sendTimeBuilder_ = null;
        }
        messType_ = 0;

        data_ = com.google.protobuf.ByteString.EMPTY;

        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return com.HyChat.server.Message.ResultMessageOuterClass.internal_static_ResultMessage_descriptor;
      }

      @java.lang.Override
      public com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage getDefaultInstanceForType() {
        return com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage.getDefaultInstance();
      }

      @java.lang.Override
      public com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage build() {
        com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage buildPartial() {
        com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage result = new com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage(this);
        result.result_ = result_;
        if (sendTimeBuilder_ == null) {
          result.sendTime_ = sendTime_;
        } else {
          result.sendTime_ = sendTimeBuilder_.build();
        }
        result.messType_ = messType_;
        result.data_ = data_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage) {
          return mergeFrom((com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage other) {
        if (other == com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage.getDefaultInstance()) return this;
        if (other.getResult() != false) {
          setResult(other.getResult());
        }
        if (other.hasSendTime()) {
          mergeSendTime(other.getSendTime());
        }
        if (other.getMessType() != 0) {
          setMessType(other.getMessType());
        }
        if (other.getData() != com.google.protobuf.ByteString.EMPTY) {
          setData(other.getData());
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
        com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private boolean result_ ;
      /**
       * <code>bool Result = 1;</code>
       * @return The result.
       */
      public boolean getResult() {
        return result_;
      }
      /**
       * <code>bool Result = 1;</code>
       * @param value The result to set.
       * @return This builder for chaining.
       */
      public Builder setResult(boolean value) {
        
        result_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bool Result = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearResult() {
        
        result_ = false;
        onChanged();
        return this;
      }

      private com.google.protobuf.Timestamp sendTime_;
      private com.google.protobuf.SingleFieldBuilderV3<
          com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> sendTimeBuilder_;
      /**
       * <code>.google.protobuf.Timestamp sendTime = 4;</code>
       * @return Whether the sendTime field is set.
       */
      public boolean hasSendTime() {
        return sendTimeBuilder_ != null || sendTime_ != null;
      }
      /**
       * <code>.google.protobuf.Timestamp sendTime = 4;</code>
       * @return The sendTime.
       */
      public com.google.protobuf.Timestamp getSendTime() {
        if (sendTimeBuilder_ == null) {
          return sendTime_ == null ? com.google.protobuf.Timestamp.getDefaultInstance() : sendTime_;
        } else {
          return sendTimeBuilder_.getMessage();
        }
      }
      /**
       * <code>.google.protobuf.Timestamp sendTime = 4;</code>
       */
      public Builder setSendTime(com.google.protobuf.Timestamp value) {
        if (sendTimeBuilder_ == null) {
          if (value == null) {
            throw new NullPointerException();
          }
          sendTime_ = value;
          onChanged();
        } else {
          sendTimeBuilder_.setMessage(value);
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Timestamp sendTime = 4;</code>
       */
      public Builder setSendTime(
          com.google.protobuf.Timestamp.Builder builderForValue) {
        if (sendTimeBuilder_ == null) {
          sendTime_ = builderForValue.build();
          onChanged();
        } else {
          sendTimeBuilder_.setMessage(builderForValue.build());
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Timestamp sendTime = 4;</code>
       */
      public Builder mergeSendTime(com.google.protobuf.Timestamp value) {
        if (sendTimeBuilder_ == null) {
          if (sendTime_ != null) {
            sendTime_ =
              com.google.protobuf.Timestamp.newBuilder(sendTime_).mergeFrom(value).buildPartial();
          } else {
            sendTime_ = value;
          }
          onChanged();
        } else {
          sendTimeBuilder_.mergeFrom(value);
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Timestamp sendTime = 4;</code>
       */
      public Builder clearSendTime() {
        if (sendTimeBuilder_ == null) {
          sendTime_ = null;
          onChanged();
        } else {
          sendTime_ = null;
          sendTimeBuilder_ = null;
        }

        return this;
      }
      /**
       * <code>.google.protobuf.Timestamp sendTime = 4;</code>
       */
      public com.google.protobuf.Timestamp.Builder getSendTimeBuilder() {
        
        onChanged();
        return getSendTimeFieldBuilder().getBuilder();
      }
      /**
       * <code>.google.protobuf.Timestamp sendTime = 4;</code>
       */
      public com.google.protobuf.TimestampOrBuilder getSendTimeOrBuilder() {
        if (sendTimeBuilder_ != null) {
          return sendTimeBuilder_.getMessageOrBuilder();
        } else {
          return sendTime_ == null ?
              com.google.protobuf.Timestamp.getDefaultInstance() : sendTime_;
        }
      }
      /**
       * <code>.google.protobuf.Timestamp sendTime = 4;</code>
       */
      private com.google.protobuf.SingleFieldBuilderV3<
          com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder> 
          getSendTimeFieldBuilder() {
        if (sendTimeBuilder_ == null) {
          sendTimeBuilder_ = new com.google.protobuf.SingleFieldBuilderV3<
              com.google.protobuf.Timestamp, com.google.protobuf.Timestamp.Builder, com.google.protobuf.TimestampOrBuilder>(
                  getSendTime(),
                  getParentForChildren(),
                  isClean());
          sendTime_ = null;
        }
        return sendTimeBuilder_;
      }

      private int messType_ ;
      /**
       * <pre>
       *消息类型
       * </pre>
       *
       * <code>int32 MessType = 3;</code>
       * @return The messType.
       */
      public int getMessType() {
        return messType_;
      }
      /**
       * <pre>
       *消息类型
       * </pre>
       *
       * <code>int32 MessType = 3;</code>
       * @param value The messType to set.
       * @return This builder for chaining.
       */
      public Builder setMessType(int value) {
        
        messType_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       *消息类型
       * </pre>
       *
       * <code>int32 MessType = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessType() {
        
        messType_ = 0;
        onChanged();
        return this;
      }

      private com.google.protobuf.ByteString data_ = com.google.protobuf.ByteString.EMPTY;
      /**
       * <code>bytes Data = 2;</code>
       * @return The data.
       */
      public com.google.protobuf.ByteString getData() {
        return data_;
      }
      /**
       * <code>bytes Data = 2;</code>
       * @param value The data to set.
       * @return This builder for chaining.
       */
      public Builder setData(com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        data_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>bytes Data = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearData() {
        
        data_ = getDefaultInstance().getData();
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:ResultMessage)
    }

    // @@protoc_insertion_point(class_scope:ResultMessage)
    private static final com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage();
    }

    public static com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<ResultMessage>
        PARSER = new com.google.protobuf.AbstractParser<ResultMessage>() {
      @java.lang.Override
      public ResultMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ResultMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ResultMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ResultMessage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public com.HyChat.server.Message.ResultMessageOuterClass.ResultMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_ResultMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_ResultMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023ResultMessage.proto\032\037google/protobuf/t" +
      "imestamp.proto\"m\n\rResultMessage\022\016\n\006Resul" +
      "t\030\001 \001(\010\022,\n\010sendTime\030\004 \001(\0132\032.google.proto" +
      "buf.Timestamp\022\020\n\010MessType\030\003 \001(\005\022\014\n\004Data\030" +
      "\002 \001(\014B\033\n\031com.HyChat.server.Messageb\006prot" +
      "o3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.TimestampProto.getDescriptor(),
        });
    internal_static_ResultMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_ResultMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_ResultMessage_descriptor,
        new java.lang.String[] { "Result", "SendTime", "MessType", "Data", });
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
