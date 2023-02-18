package com.proto.productinfo;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.52.1)",
    comments = "Source: productinfo.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ProductInfoGrpc {

  private ProductInfoGrpc() {}

  public static final String SERVICE_NAME = "productinfo.ProductInfo";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.proto.productinfo.Product,
      com.proto.productinfo.ProductID> getAddProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addProduct",
      requestType = com.proto.productinfo.Product.class,
      responseType = com.proto.productinfo.ProductID.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.proto.productinfo.Product,
      com.proto.productinfo.ProductID> getAddProductMethod() {
    io.grpc.MethodDescriptor<com.proto.productinfo.Product, com.proto.productinfo.ProductID> getAddProductMethod;
    if ((getAddProductMethod = ProductInfoGrpc.getAddProductMethod) == null) {
      synchronized (ProductInfoGrpc.class) {
        if ((getAddProductMethod = ProductInfoGrpc.getAddProductMethod) == null) {
          ProductInfoGrpc.getAddProductMethod = getAddProductMethod =
              io.grpc.MethodDescriptor.<com.proto.productinfo.Product, com.proto.productinfo.ProductID>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.productinfo.Product.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.productinfo.ProductID.getDefaultInstance()))
              .setSchemaDescriptor(new ProductInfoMethodDescriptorSupplier("addProduct"))
              .build();
        }
      }
    }
    return getAddProductMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.proto.productinfo.ProductID,
      com.proto.productinfo.Product> getGetProductMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getProduct",
      requestType = com.proto.productinfo.ProductID.class,
      responseType = com.proto.productinfo.Product.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.proto.productinfo.ProductID,
      com.proto.productinfo.Product> getGetProductMethod() {
    io.grpc.MethodDescriptor<com.proto.productinfo.ProductID, com.proto.productinfo.Product> getGetProductMethod;
    if ((getGetProductMethod = ProductInfoGrpc.getGetProductMethod) == null) {
      synchronized (ProductInfoGrpc.class) {
        if ((getGetProductMethod = ProductInfoGrpc.getGetProductMethod) == null) {
          ProductInfoGrpc.getGetProductMethod = getGetProductMethod =
              io.grpc.MethodDescriptor.<com.proto.productinfo.ProductID, com.proto.productinfo.Product>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "getProduct"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.productinfo.ProductID.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.proto.productinfo.Product.getDefaultInstance()))
              .setSchemaDescriptor(new ProductInfoMethodDescriptorSupplier("getProduct"))
              .build();
        }
      }
    }
    return getGetProductMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ProductInfoStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductInfoStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductInfoStub>() {
        @java.lang.Override
        public ProductInfoStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductInfoStub(channel, callOptions);
        }
      };
    return ProductInfoStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ProductInfoBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductInfoBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductInfoBlockingStub>() {
        @java.lang.Override
        public ProductInfoBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductInfoBlockingStub(channel, callOptions);
        }
      };
    return ProductInfoBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ProductInfoFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ProductInfoFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ProductInfoFutureStub>() {
        @java.lang.Override
        public ProductInfoFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ProductInfoFutureStub(channel, callOptions);
        }
      };
    return ProductInfoFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class ProductInfoImplBase implements io.grpc.BindableService {

    /**
     */
    public void addProduct(com.proto.productinfo.Product request,
        io.grpc.stub.StreamObserver<com.proto.productinfo.ProductID> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddProductMethod(), responseObserver);
    }

    /**
     */
    public void getProduct(com.proto.productinfo.ProductID request,
        io.grpc.stub.StreamObserver<com.proto.productinfo.Product> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetProductMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddProductMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.proto.productinfo.Product,
                com.proto.productinfo.ProductID>(
                  this, METHODID_ADD_PRODUCT)))
          .addMethod(
            getGetProductMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                com.proto.productinfo.ProductID,
                com.proto.productinfo.Product>(
                  this, METHODID_GET_PRODUCT)))
          .build();
    }
  }

  /**
   */
  public static final class ProductInfoStub extends io.grpc.stub.AbstractAsyncStub<ProductInfoStub> {
    private ProductInfoStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductInfoStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductInfoStub(channel, callOptions);
    }

    /**
     */
    public void addProduct(com.proto.productinfo.Product request,
        io.grpc.stub.StreamObserver<com.proto.productinfo.ProductID> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddProductMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getProduct(com.proto.productinfo.ProductID request,
        io.grpc.stub.StreamObserver<com.proto.productinfo.Product> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetProductMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ProductInfoBlockingStub extends io.grpc.stub.AbstractBlockingStub<ProductInfoBlockingStub> {
    private ProductInfoBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductInfoBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductInfoBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.proto.productinfo.ProductID addProduct(com.proto.productinfo.Product request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddProductMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.proto.productinfo.Product getProduct(com.proto.productinfo.ProductID request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetProductMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ProductInfoFutureStub extends io.grpc.stub.AbstractFutureStub<ProductInfoFutureStub> {
    private ProductInfoFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ProductInfoFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ProductInfoFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.proto.productinfo.ProductID> addProduct(
        com.proto.productinfo.Product request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddProductMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.proto.productinfo.Product> getProduct(
        com.proto.productinfo.ProductID request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetProductMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_PRODUCT = 0;
  private static final int METHODID_GET_PRODUCT = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ProductInfoImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ProductInfoImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_PRODUCT:
          serviceImpl.addProduct((com.proto.productinfo.Product) request,
              (io.grpc.stub.StreamObserver<com.proto.productinfo.ProductID>) responseObserver);
          break;
        case METHODID_GET_PRODUCT:
          serviceImpl.getProduct((com.proto.productinfo.ProductID) request,
              (io.grpc.stub.StreamObserver<com.proto.productinfo.Product>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class ProductInfoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ProductInfoBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.proto.productinfo.Productinfo.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ProductInfo");
    }
  }

  private static final class ProductInfoFileDescriptorSupplier
      extends ProductInfoBaseDescriptorSupplier {
    ProductInfoFileDescriptorSupplier() {}
  }

  private static final class ProductInfoMethodDescriptorSupplier
      extends ProductInfoBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ProductInfoMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ProductInfoGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ProductInfoFileDescriptorSupplier())
              .addMethod(getAddProductMethod())
              .addMethod(getGetProductMethod())
              .build();
        }
      }
    }
    return result;
  }
}
