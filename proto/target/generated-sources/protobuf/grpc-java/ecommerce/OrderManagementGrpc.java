package ecommerce;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.49.0)",
    comments = "Source: ordermanagement.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class OrderManagementGrpc {

  private OrderManagementGrpc() {}

  public static final String SERVICE_NAME = "ecommerce.OrderManagement";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<ecommerce.Ordermanagement.Order,
      com.google.protobuf.StringValue> getAddOrderMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "addOrder",
      requestType = ecommerce.Ordermanagement.Order.class,
      responseType = com.google.protobuf.StringValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<ecommerce.Ordermanagement.Order,
      com.google.protobuf.StringValue> getAddOrderMethod() {
    io.grpc.MethodDescriptor<ecommerce.Ordermanagement.Order, com.google.protobuf.StringValue> getAddOrderMethod;
    if ((getAddOrderMethod = OrderManagementGrpc.getAddOrderMethod) == null) {
      synchronized (OrderManagementGrpc.class) {
        if ((getAddOrderMethod = OrderManagementGrpc.getAddOrderMethod) == null) {
          OrderManagementGrpc.getAddOrderMethod = getAddOrderMethod =
              io.grpc.MethodDescriptor.<ecommerce.Ordermanagement.Order, com.google.protobuf.StringValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "addOrder"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ecommerce.Ordermanagement.Order.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.StringValue.getDefaultInstance()))
              .setSchemaDescriptor(new OrderManagementMethodDescriptorSupplier("addOrder"))
              .build();
        }
      }
    }
    return getAddOrderMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
      ecommerce.Ordermanagement.Order> getSearchOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "searchOrders",
      requestType = com.google.protobuf.StringValue.class,
      responseType = ecommerce.Ordermanagement.Order.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.google.protobuf.StringValue,
      ecommerce.Ordermanagement.Order> getSearchOrdersMethod() {
    io.grpc.MethodDescriptor<com.google.protobuf.StringValue, ecommerce.Ordermanagement.Order> getSearchOrdersMethod;
    if ((getSearchOrdersMethod = OrderManagementGrpc.getSearchOrdersMethod) == null) {
      synchronized (OrderManagementGrpc.class) {
        if ((getSearchOrdersMethod = OrderManagementGrpc.getSearchOrdersMethod) == null) {
          OrderManagementGrpc.getSearchOrdersMethod = getSearchOrdersMethod =
              io.grpc.MethodDescriptor.<com.google.protobuf.StringValue, ecommerce.Ordermanagement.Order>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "searchOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.StringValue.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ecommerce.Ordermanagement.Order.getDefaultInstance()))
              .setSchemaDescriptor(new OrderManagementMethodDescriptorSupplier("searchOrders"))
              .build();
        }
      }
    }
    return getSearchOrdersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<ecommerce.Ordermanagement.Order,
      com.google.protobuf.StringValue> getUpdateOrdersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "updateOrders",
      requestType = ecommerce.Ordermanagement.Order.class,
      responseType = com.google.protobuf.StringValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<ecommerce.Ordermanagement.Order,
      com.google.protobuf.StringValue> getUpdateOrdersMethod() {
    io.grpc.MethodDescriptor<ecommerce.Ordermanagement.Order, com.google.protobuf.StringValue> getUpdateOrdersMethod;
    if ((getUpdateOrdersMethod = OrderManagementGrpc.getUpdateOrdersMethod) == null) {
      synchronized (OrderManagementGrpc.class) {
        if ((getUpdateOrdersMethod = OrderManagementGrpc.getUpdateOrdersMethod) == null) {
          OrderManagementGrpc.getUpdateOrdersMethod = getUpdateOrdersMethod =
              io.grpc.MethodDescriptor.<ecommerce.Ordermanagement.Order, com.google.protobuf.StringValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "updateOrders"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  ecommerce.Ordermanagement.Order.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.StringValue.getDefaultInstance()))
              .setSchemaDescriptor(new OrderManagementMethodDescriptorSupplier("updateOrders"))
              .build();
        }
      }
    }
    return getUpdateOrdersMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static OrderManagementStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderManagementStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderManagementStub>() {
        @java.lang.Override
        public OrderManagementStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderManagementStub(channel, callOptions);
        }
      };
    return OrderManagementStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static OrderManagementBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderManagementBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderManagementBlockingStub>() {
        @java.lang.Override
        public OrderManagementBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderManagementBlockingStub(channel, callOptions);
        }
      };
    return OrderManagementBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static OrderManagementFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<OrderManagementFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<OrderManagementFutureStub>() {
        @java.lang.Override
        public OrderManagementFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new OrderManagementFutureStub(channel, callOptions);
        }
      };
    return OrderManagementFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class OrderManagementImplBase implements io.grpc.BindableService {

    /**
     */
    public void addOrder(ecommerce.Ordermanagement.Order request,
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getAddOrderMethod(), responseObserver);
    }

    /**
     * <pre>
     *rpc getOrder(google.protobuf.StringValue) returns (Order);
     * </pre>
     */
    public void searchOrders(com.google.protobuf.StringValue request,
        io.grpc.stub.StreamObserver<ecommerce.Ordermanagement.Order> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchOrdersMethod(), responseObserver);
    }

    /**
     * <pre>
     *rpc processOrders(stream google.protobuf.StringValue) returns (stream CombinedShipment);
     * </pre>
     */
    public io.grpc.stub.StreamObserver<ecommerce.Ordermanagement.Order> updateOrders(
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getUpdateOrdersMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAddOrderMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                ecommerce.Ordermanagement.Order,
                com.google.protobuf.StringValue>(
                  this, METHODID_ADD_ORDER)))
          .addMethod(
            getSearchOrdersMethod(),
            io.grpc.stub.ServerCalls.asyncServerStreamingCall(
              new MethodHandlers<
                com.google.protobuf.StringValue,
                ecommerce.Ordermanagement.Order>(
                  this, METHODID_SEARCH_ORDERS)))
          .addMethod(
            getUpdateOrdersMethod(),
            io.grpc.stub.ServerCalls.asyncClientStreamingCall(
              new MethodHandlers<
                ecommerce.Ordermanagement.Order,
                com.google.protobuf.StringValue>(
                  this, METHODID_UPDATE_ORDERS)))
          .build();
    }
  }

  /**
   */
  public static final class OrderManagementStub extends io.grpc.stub.AbstractAsyncStub<OrderManagementStub> {
    private OrderManagementStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderManagementStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderManagementStub(channel, callOptions);
    }

    /**
     */
    public void addOrder(ecommerce.Ordermanagement.Order request,
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getAddOrderMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *rpc getOrder(google.protobuf.StringValue) returns (Order);
     * </pre>
     */
    public void searchOrders(com.google.protobuf.StringValue request,
        io.grpc.stub.StreamObserver<ecommerce.Ordermanagement.Order> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getSearchOrdersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *rpc processOrders(stream google.protobuf.StringValue) returns (stream CombinedShipment);
     * </pre>
     */
    public io.grpc.stub.StreamObserver<ecommerce.Ordermanagement.Order> updateOrders(
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getUpdateOrdersMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   */
  public static final class OrderManagementBlockingStub extends io.grpc.stub.AbstractBlockingStub<OrderManagementBlockingStub> {
    private OrderManagementBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderManagementBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderManagementBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.google.protobuf.StringValue addOrder(ecommerce.Ordermanagement.Order request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getAddOrderMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *rpc getOrder(google.protobuf.StringValue) returns (Order);
     * </pre>
     */
    public java.util.Iterator<ecommerce.Ordermanagement.Order> searchOrders(
        com.google.protobuf.StringValue request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getSearchOrdersMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class OrderManagementFutureStub extends io.grpc.stub.AbstractFutureStub<OrderManagementFutureStub> {
    private OrderManagementFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected OrderManagementFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new OrderManagementFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.google.protobuf.StringValue> addOrder(
        ecommerce.Ordermanagement.Order request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getAddOrderMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ADD_ORDER = 0;
  private static final int METHODID_SEARCH_ORDERS = 1;
  private static final int METHODID_UPDATE_ORDERS = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final OrderManagementImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(OrderManagementImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ADD_ORDER:
          serviceImpl.addOrder((ecommerce.Ordermanagement.Order) request,
              (io.grpc.stub.StreamObserver<com.google.protobuf.StringValue>) responseObserver);
          break;
        case METHODID_SEARCH_ORDERS:
          serviceImpl.searchOrders((com.google.protobuf.StringValue) request,
              (io.grpc.stub.StreamObserver<ecommerce.Ordermanagement.Order>) responseObserver);
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
        case METHODID_UPDATE_ORDERS:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.updateOrders(
              (io.grpc.stub.StreamObserver<com.google.protobuf.StringValue>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class OrderManagementBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    OrderManagementBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return ecommerce.Ordermanagement.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("OrderManagement");
    }
  }

  private static final class OrderManagementFileDescriptorSupplier
      extends OrderManagementBaseDescriptorSupplier {
    OrderManagementFileDescriptorSupplier() {}
  }

  private static final class OrderManagementMethodDescriptorSupplier
      extends OrderManagementBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    OrderManagementMethodDescriptorSupplier(String methodName) {
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
      synchronized (OrderManagementGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new OrderManagementFileDescriptorSupplier())
              .addMethod(getAddOrderMethod())
              .addMethod(getSearchOrdersMethod())
              .addMethod(getUpdateOrdersMethod())
              .build();
        }
      }
    }
    return result;
  }
}
