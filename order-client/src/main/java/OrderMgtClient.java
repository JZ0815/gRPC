import com.google.protobuf.StringValue;
import ecommerce.OrderManagementGrpc;
import ecommerce.Ordermanagement;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class OrderMgtClient {

    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress(
                "localhost", 50053).usePlaintext().build();
        OrderManagementGrpc.OrderManagementBlockingStub stub = OrderManagementGrpc.newBlockingStub(channel);
        OrderManagementGrpc.OrderManagementStub asyncStub = OrderManagementGrpc.newStub(channel);

        Ordermanagement.Order order = Ordermanagement.Order
                .newBuilder()
                .setId("101")
                .addItems("iPhone XS").addItems("Mac Book Pro")
                .setDestination("San Jose, CA")
                .setPrice(2300)
                .build();

        // Add Order
        StringValue result = stub.addOrder(order);

        System.out.println("AddOrder Response -> : " + result.getValue());

        // Get Order
        StringValue id = StringValue.newBuilder().setValue("101").build();
        Ordermanagement.Order orderResponse = stub.getOrder(id);
        System.out.println("GetOrder Response -> : " + orderResponse.toString());


        // Search Orders
        StringValue searchStr = StringValue.newBuilder().setValue("Google").build();
        Iterator<Ordermanagement.Order> matchingOrdersItr;
        matchingOrdersItr = stub.searchOrders(searchStr);
        while (matchingOrdersItr.hasNext()) {
            Ordermanagement.Order matchingOrder = matchingOrdersItr.next();
            System.out.println("Search Order Response -> Matching Order - " + matchingOrder.getId());
            System.out.println(" Order : " + order.getId() + "\n "
                    + matchingOrder.toString());
        }


        // Update Orders
        invokeOrderUpdate(asyncStub);

        // Process Order
        invokeOrderProcess(asyncStub);



    }


    private static void invokeOrderUpdate(OrderManagementGrpc.OrderManagementStub asyncStub) {

        Ordermanagement.Order updOrder1 = Ordermanagement.Order.newBuilder()
                .setId("102")
                .addItems("Google Pixel 3A").addItems("Google Pixel Book")
                .setDestination("Mountain View, CA")
                .setPrice(1100)
                .build();
        Ordermanagement.Order updOrder2 = Ordermanagement.Order.newBuilder()
                .setId("103")
                .addItems("Apple Watch S4").addItems("Mac Book Pro").addItems("iPad Pro")
                .setDestination("San Jose, CA")
                .setPrice(2800)
                .build();
        Ordermanagement.Order updOrder3 = Ordermanagement.Order.newBuilder()
                .setId("104")
                .addItems("Google Home Mini").addItems("Google Nest Hub").addItems("iPad Mini")
                .setDestination("Mountain View, CA")
                .setPrice(2200)
                .build();

        final CountDownLatch finishLatch = new CountDownLatch(1);

        StreamObserver<StringValue> updateOrderResponseObserver = new StreamObserver<StringValue>() {
            @Override
            public void onNext(StringValue value) {
                System.out.println("Update Orders Res : " + value.getValue());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("Update orders response  completed!");
                finishLatch.countDown();
            }
        };

        StreamObserver<Ordermanagement.Order> updateOrderRequestObserver = asyncStub.updateOrders(updateOrderResponseObserver);
        updateOrderRequestObserver.onNext(updOrder1);
        updateOrderRequestObserver.onNext(updOrder2);
        updateOrderRequestObserver.onNext(updOrder3);
        updateOrderRequestObserver.onNext(updOrder3);


        if (finishLatch.getCount() == 0) {
            System.out.println("RPC completed or errored before we finished sending.");
            return;
        }
        updateOrderRequestObserver.onCompleted();

        // Receiving happens asynchronously

        try {
            if (!finishLatch.await(10, TimeUnit.SECONDS)) {
                System.out.println("FAILED : Process orders cannot finish within 10 seconds");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private static void invokeOrderProcess(OrderManagementGrpc.OrderManagementStub asyncStub) {

        final CountDownLatch finishLatch = new CountDownLatch(1);


        StreamObserver<Ordermanagement.CombinedShipment> orderProcessResponseObserver = new StreamObserver<Ordermanagement.CombinedShipment>() {
            @Override
            public void onNext(Ordermanagement.CombinedShipment value) {
                System.out.println("Combined Shipment : " + value.getId() + " : " + value.getOrdersListList());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("Order Processing completed!");
                finishLatch.countDown();
            }
        };

        StreamObserver<StringValue> orderProcessRequestObserver =  asyncStub.processOrders(orderProcessResponseObserver);

        orderProcessRequestObserver.onNext(StringValue.newBuilder().setValue("102").build());
        orderProcessRequestObserver.onNext(StringValue.newBuilder().setValue("103").build());
        orderProcessRequestObserver.onNext(StringValue.newBuilder().setValue("104").build());
        orderProcessRequestObserver.onNext(StringValue.newBuilder().setValue("101").build());

        if (finishLatch.getCount() == 0) {
            System.out.println("RPC completed or errored before we finished sending.");
            return;
        }
        orderProcessRequestObserver.onCompleted();


        try {
            if (!finishLatch.await(120, TimeUnit.SECONDS)) {
                System.out.println("FAILED : Process orders cannot finish within 60 seconds");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}

