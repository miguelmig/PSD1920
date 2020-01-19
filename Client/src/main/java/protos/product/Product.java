// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: product.proto

package protos.product;

public final class Product {
  private Product() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface ProductMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:product.ProductMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>required string name = 1;</code>
     * @return Whether the name field is set.
     */
    boolean hasName();
    /**
     * <code>required string name = 1;</code>
     * @return The name.
     */
    java.lang.String getName();
    /**
     * <code>required string name = 1;</code>
     * @return The bytes for name.
     */
    com.google.protobuf.ByteString
        getNameBytes();

    /**
     * <code>required int32 minimum_quantity = 2;</code>
     * @return Whether the minimumQuantity field is set.
     */
    boolean hasMinimumQuantity();
    /**
     * <code>required int32 minimum_quantity = 2;</code>
     * @return The minimumQuantity.
     */
    int getMinimumQuantity();

    /**
     * <code>required int32 maximum_quantity = 3;</code>
     * @return Whether the maximumQuantity field is set.
     */
    boolean hasMaximumQuantity();
    /**
     * <code>required int32 maximum_quantity = 3;</code>
     * @return The maximumQuantity.
     */
    int getMaximumQuantity();

    /**
     * <code>required int32 unitary_price = 4;</code>
     * @return Whether the unitaryPrice field is set.
     */
    boolean hasUnitaryPrice();
    /**
     * <code>required int32 unitary_price = 4;</code>
     * @return The unitaryPrice.
     */
    int getUnitaryPrice();

    /**
     * <code>required int32 negotiation_time = 5;</code>
     * @return Whether the negotiationTime field is set.
     */
    boolean hasNegotiationTime();
    /**
     * <code>required int32 negotiation_time = 5;</code>
     * @return The negotiationTime.
     */
    int getNegotiationTime();
  }
  /**
   * Protobuf type {@code product.ProductMessage}
   */
  public  static final class ProductMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:product.ProductMessage)
      ProductMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use ProductMessage.newBuilder() to construct.
    private ProductMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private ProductMessage() {
      name_ = "";
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new ProductMessage();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private ProductMessage(
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
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              name_ = bs;
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              minimumQuantity_ = input.readInt32();
              break;
            }
            case 24: {
              bitField0_ |= 0x00000004;
              maximumQuantity_ = input.readInt32();
              break;
            }
            case 32: {
              bitField0_ |= 0x00000008;
              unitaryPrice_ = input.readInt32();
              break;
            }
            case 40: {
              bitField0_ |= 0x00000010;
              negotiationTime_ = input.readInt32();
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
      return protos.product.Product.internal_static_product_ProductMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return protos.product.Product.internal_static_product_ProductMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              protos.product.Product.ProductMessage.class, protos.product.Product.ProductMessage.Builder.class);
    }

    private int bitField0_;
    public static final int NAME_FIELD_NUMBER = 1;
    private volatile java.lang.Object name_;
    /**
     * <code>required string name = 1;</code>
     * @return Whether the name field is set.
     */
    public boolean hasName() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <code>required string name = 1;</code>
     * @return The name.
     */
    public java.lang.String getName() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          name_ = s;
        }
        return s;
      }
    }
    /**
     * <code>required string name = 1;</code>
     * @return The bytes for name.
     */
    public com.google.protobuf.ByteString
        getNameBytes() {
      java.lang.Object ref = name_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        name_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int MINIMUM_QUANTITY_FIELD_NUMBER = 2;
    private int minimumQuantity_;
    /**
     * <code>required int32 minimum_quantity = 2;</code>
     * @return Whether the minimumQuantity field is set.
     */
    public boolean hasMinimumQuantity() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <code>required int32 minimum_quantity = 2;</code>
     * @return The minimumQuantity.
     */
    public int getMinimumQuantity() {
      return minimumQuantity_;
    }

    public static final int MAXIMUM_QUANTITY_FIELD_NUMBER = 3;
    private int maximumQuantity_;
    /**
     * <code>required int32 maximum_quantity = 3;</code>
     * @return Whether the maximumQuantity field is set.
     */
    public boolean hasMaximumQuantity() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <code>required int32 maximum_quantity = 3;</code>
     * @return The maximumQuantity.
     */
    public int getMaximumQuantity() {
      return maximumQuantity_;
    }

    public static final int UNITARY_PRICE_FIELD_NUMBER = 4;
    private int unitaryPrice_;
    /**
     * <code>required int32 unitary_price = 4;</code>
     * @return Whether the unitaryPrice field is set.
     */
    public boolean hasUnitaryPrice() {
      return ((bitField0_ & 0x00000008) != 0);
    }
    /**
     * <code>required int32 unitary_price = 4;</code>
     * @return The unitaryPrice.
     */
    public int getUnitaryPrice() {
      return unitaryPrice_;
    }

    public static final int NEGOTIATION_TIME_FIELD_NUMBER = 5;
    private int negotiationTime_;
    /**
     * <code>required int32 negotiation_time = 5;</code>
     * @return Whether the negotiationTime field is set.
     */
    public boolean hasNegotiationTime() {
      return ((bitField0_ & 0x00000010) != 0);
    }
    /**
     * <code>required int32 negotiation_time = 5;</code>
     * @return The negotiationTime.
     */
    public int getNegotiationTime() {
      return negotiationTime_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasName()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMinimumQuantity()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasMaximumQuantity()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasUnitaryPrice()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasNegotiationTime()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) != 0)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, name_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        output.writeInt32(2, minimumQuantity_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        output.writeInt32(3, maximumQuantity_);
      }
      if (((bitField0_ & 0x00000008) != 0)) {
        output.writeInt32(4, unitaryPrice_);
      }
      if (((bitField0_ & 0x00000010) != 0)) {
        output.writeInt32(5, negotiationTime_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, name_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(2, minimumQuantity_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, maximumQuantity_);
      }
      if (((bitField0_ & 0x00000008) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(4, unitaryPrice_);
      }
      if (((bitField0_ & 0x00000010) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(5, negotiationTime_);
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
      if (!(obj instanceof protos.product.Product.ProductMessage)) {
        return super.equals(obj);
      }
      protos.product.Product.ProductMessage other = (protos.product.Product.ProductMessage) obj;

      if (hasName() != other.hasName()) return false;
      if (hasName()) {
        if (!getName()
            .equals(other.getName())) return false;
      }
      if (hasMinimumQuantity() != other.hasMinimumQuantity()) return false;
      if (hasMinimumQuantity()) {
        if (getMinimumQuantity()
            != other.getMinimumQuantity()) return false;
      }
      if (hasMaximumQuantity() != other.hasMaximumQuantity()) return false;
      if (hasMaximumQuantity()) {
        if (getMaximumQuantity()
            != other.getMaximumQuantity()) return false;
      }
      if (hasUnitaryPrice() != other.hasUnitaryPrice()) return false;
      if (hasUnitaryPrice()) {
        if (getUnitaryPrice()
            != other.getUnitaryPrice()) return false;
      }
      if (hasNegotiationTime() != other.hasNegotiationTime()) return false;
      if (hasNegotiationTime()) {
        if (getNegotiationTime()
            != other.getNegotiationTime()) return false;
      }
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
      if (hasName()) {
        hash = (37 * hash) + NAME_FIELD_NUMBER;
        hash = (53 * hash) + getName().hashCode();
      }
      if (hasMinimumQuantity()) {
        hash = (37 * hash) + MINIMUM_QUANTITY_FIELD_NUMBER;
        hash = (53 * hash) + getMinimumQuantity();
      }
      if (hasMaximumQuantity()) {
        hash = (37 * hash) + MAXIMUM_QUANTITY_FIELD_NUMBER;
        hash = (53 * hash) + getMaximumQuantity();
      }
      if (hasUnitaryPrice()) {
        hash = (37 * hash) + UNITARY_PRICE_FIELD_NUMBER;
        hash = (53 * hash) + getUnitaryPrice();
      }
      if (hasNegotiationTime()) {
        hash = (37 * hash) + NEGOTIATION_TIME_FIELD_NUMBER;
        hash = (53 * hash) + getNegotiationTime();
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static protos.product.Product.ProductMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.product.Product.ProductMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.product.Product.ProductMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.product.Product.ProductMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.product.Product.ProductMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static protos.product.Product.ProductMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static protos.product.Product.ProductMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protos.product.Product.ProductMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static protos.product.Product.ProductMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static protos.product.Product.ProductMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static protos.product.Product.ProductMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static protos.product.Product.ProductMessage parseFrom(
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
    public static Builder newBuilder(protos.product.Product.ProductMessage prototype) {
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
     * Protobuf type {@code product.ProductMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:product.ProductMessage)
        protos.product.Product.ProductMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return protos.product.Product.internal_static_product_ProductMessage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return protos.product.Product.internal_static_product_ProductMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                protos.product.Product.ProductMessage.class, protos.product.Product.ProductMessage.Builder.class);
      }

      // Construct using protos.product.Product.ProductMessage.newBuilder()
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
        name_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        minimumQuantity_ = 0;
        bitField0_ = (bitField0_ & ~0x00000002);
        maximumQuantity_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        unitaryPrice_ = 0;
        bitField0_ = (bitField0_ & ~0x00000008);
        negotiationTime_ = 0;
        bitField0_ = (bitField0_ & ~0x00000010);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return protos.product.Product.internal_static_product_ProductMessage_descriptor;
      }

      @java.lang.Override
      public protos.product.Product.ProductMessage getDefaultInstanceForType() {
        return protos.product.Product.ProductMessage.getDefaultInstance();
      }

      @java.lang.Override
      public protos.product.Product.ProductMessage build() {
        protos.product.Product.ProductMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public protos.product.Product.ProductMessage buildPartial() {
        protos.product.Product.ProductMessage result = new protos.product.Product.ProductMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          to_bitField0_ |= 0x00000001;
        }
        result.name_ = name_;
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.minimumQuantity_ = minimumQuantity_;
          to_bitField0_ |= 0x00000002;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          result.maximumQuantity_ = maximumQuantity_;
          to_bitField0_ |= 0x00000004;
        }
        if (((from_bitField0_ & 0x00000008) != 0)) {
          result.unitaryPrice_ = unitaryPrice_;
          to_bitField0_ |= 0x00000008;
        }
        if (((from_bitField0_ & 0x00000010) != 0)) {
          result.negotiationTime_ = negotiationTime_;
          to_bitField0_ |= 0x00000010;
        }
        result.bitField0_ = to_bitField0_;
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
        if (other instanceof protos.product.Product.ProductMessage) {
          return mergeFrom((protos.product.Product.ProductMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(protos.product.Product.ProductMessage other) {
        if (other == protos.product.Product.ProductMessage.getDefaultInstance()) return this;
        if (other.hasName()) {
          bitField0_ |= 0x00000001;
          name_ = other.name_;
          onChanged();
        }
        if (other.hasMinimumQuantity()) {
          setMinimumQuantity(other.getMinimumQuantity());
        }
        if (other.hasMaximumQuantity()) {
          setMaximumQuantity(other.getMaximumQuantity());
        }
        if (other.hasUnitaryPrice()) {
          setUnitaryPrice(other.getUnitaryPrice());
        }
        if (other.hasNegotiationTime()) {
          setNegotiationTime(other.getNegotiationTime());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        if (!hasName()) {
          return false;
        }
        if (!hasMinimumQuantity()) {
          return false;
        }
        if (!hasMaximumQuantity()) {
          return false;
        }
        if (!hasUnitaryPrice()) {
          return false;
        }
        if (!hasNegotiationTime()) {
          return false;
        }
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        protos.product.Product.ProductMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (protos.product.Product.ProductMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object name_ = "";
      /**
       * <code>required string name = 1;</code>
       * @return Whether the name field is set.
       */
      public boolean hasName() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <code>required string name = 1;</code>
       * @return The name.
       */
      public java.lang.String getName() {
        java.lang.Object ref = name_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            name_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <code>required string name = 1;</code>
       * @return The bytes for name.
       */
      public com.google.protobuf.ByteString
          getNameBytes() {
        java.lang.Object ref = name_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          name_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>required string name = 1;</code>
       * @param value The name to set.
       * @return This builder for chaining.
       */
      public Builder setName(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearName() {
        bitField0_ = (bitField0_ & ~0x00000001);
        name_ = getDefaultInstance().getName();
        onChanged();
        return this;
      }
      /**
       * <code>required string name = 1;</code>
       * @param value The bytes for name to set.
       * @return This builder for chaining.
       */
      public Builder setNameBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        name_ = value;
        onChanged();
        return this;
      }

      private int minimumQuantity_ ;
      /**
       * <code>required int32 minimum_quantity = 2;</code>
       * @return Whether the minimumQuantity field is set.
       */
      public boolean hasMinimumQuantity() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <code>required int32 minimum_quantity = 2;</code>
       * @return The minimumQuantity.
       */
      public int getMinimumQuantity() {
        return minimumQuantity_;
      }
      /**
       * <code>required int32 minimum_quantity = 2;</code>
       * @param value The minimumQuantity to set.
       * @return This builder for chaining.
       */
      public Builder setMinimumQuantity(int value) {
        bitField0_ |= 0x00000002;
        minimumQuantity_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 minimum_quantity = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearMinimumQuantity() {
        bitField0_ = (bitField0_ & ~0x00000002);
        minimumQuantity_ = 0;
        onChanged();
        return this;
      }

      private int maximumQuantity_ ;
      /**
       * <code>required int32 maximum_quantity = 3;</code>
       * @return Whether the maximumQuantity field is set.
       */
      public boolean hasMaximumQuantity() {
        return ((bitField0_ & 0x00000004) != 0);
      }
      /**
       * <code>required int32 maximum_quantity = 3;</code>
       * @return The maximumQuantity.
       */
      public int getMaximumQuantity() {
        return maximumQuantity_;
      }
      /**
       * <code>required int32 maximum_quantity = 3;</code>
       * @param value The maximumQuantity to set.
       * @return This builder for chaining.
       */
      public Builder setMaximumQuantity(int value) {
        bitField0_ |= 0x00000004;
        maximumQuantity_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 maximum_quantity = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearMaximumQuantity() {
        bitField0_ = (bitField0_ & ~0x00000004);
        maximumQuantity_ = 0;
        onChanged();
        return this;
      }

      private int unitaryPrice_ ;
      /**
       * <code>required int32 unitary_price = 4;</code>
       * @return Whether the unitaryPrice field is set.
       */
      public boolean hasUnitaryPrice() {
        return ((bitField0_ & 0x00000008) != 0);
      }
      /**
       * <code>required int32 unitary_price = 4;</code>
       * @return The unitaryPrice.
       */
      public int getUnitaryPrice() {
        return unitaryPrice_;
      }
      /**
       * <code>required int32 unitary_price = 4;</code>
       * @param value The unitaryPrice to set.
       * @return This builder for chaining.
       */
      public Builder setUnitaryPrice(int value) {
        bitField0_ |= 0x00000008;
        unitaryPrice_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 unitary_price = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearUnitaryPrice() {
        bitField0_ = (bitField0_ & ~0x00000008);
        unitaryPrice_ = 0;
        onChanged();
        return this;
      }

      private int negotiationTime_ ;
      /**
       * <code>required int32 negotiation_time = 5;</code>
       * @return Whether the negotiationTime field is set.
       */
      public boolean hasNegotiationTime() {
        return ((bitField0_ & 0x00000010) != 0);
      }
      /**
       * <code>required int32 negotiation_time = 5;</code>
       * @return The negotiationTime.
       */
      public int getNegotiationTime() {
        return negotiationTime_;
      }
      /**
       * <code>required int32 negotiation_time = 5;</code>
       * @param value The negotiationTime to set.
       * @return This builder for chaining.
       */
      public Builder setNegotiationTime(int value) {
        bitField0_ |= 0x00000010;
        negotiationTime_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>required int32 negotiation_time = 5;</code>
       * @return This builder for chaining.
       */
      public Builder clearNegotiationTime() {
        bitField0_ = (bitField0_ & ~0x00000010);
        negotiationTime_ = 0;
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


      // @@protoc_insertion_point(builder_scope:product.ProductMessage)
    }

    // @@protoc_insertion_point(class_scope:product.ProductMessage)
    private static final protos.product.Product.ProductMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new protos.product.Product.ProductMessage();
    }

    public static protos.product.Product.ProductMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<ProductMessage>
        PARSER = new com.google.protobuf.AbstractParser<ProductMessage>() {
      @java.lang.Override
      public ProductMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new ProductMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<ProductMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<ProductMessage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public protos.product.Product.ProductMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_product_ProductMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_product_ProductMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\rproduct.proto\022\007product\"\203\001\n\016ProductMess" +
      "age\022\014\n\004name\030\001 \002(\t\022\030\n\020minimum_quantity\030\002 " +
      "\002(\005\022\030\n\020maximum_quantity\030\003 \002(\005\022\025\n\runitary" +
      "_price\030\004 \002(\005\022\030\n\020negotiation_time\030\005 \002(\005B\020" +
      "\n\016protos.product"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_product_ProductMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_product_ProductMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_product_ProductMessage_descriptor,
        new java.lang.String[] { "Name", "MinimumQuantity", "MaximumQuantity", "UnitaryPrice", "NegotiationTime", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
