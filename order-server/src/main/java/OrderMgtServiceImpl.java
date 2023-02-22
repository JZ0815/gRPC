import com.google.protobuf.StringValue;
import ecommerce.Order;
import ecommerce.OrderManagementGrpc;
import io.grpc.stub.StreamObserver;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderMgtServiceImpl extends OrderManagementGrpc.OrderManagementImplBase {
    private Order ord1 = Order.newBuilder()
            .setId("102")
            .addItems("Google Pixel 3A").addItems("Mac Book Pro")
            .setDestination("Mountain View, CA")
            .setPrice(1800)
            .build();
    private Order ord2 = Order.newBuilder()
            .setId("103")
            .addItems("Apple Watch S4")
            .setDestination("San Jose, CA")
            .setPrice(400)
            .build();
    private Order ord3 = Order.newBuilder()
            .setId("104")
            .addItems("Google Home Mini").addItems("Google Nest Hub")
            .setDestination("Mountain View, CA")
            .setPrice(400)
            .build();
    private Order ord4 = Order.newBuilder()
            .setId("105")
            .addItems("Amazon Echo")
            .setDestination("San Jose, CA")
            .setPrice(30)
            .build();
    private Order ord5 = Order.newBuilder()
            .setId("106")
            .addItems("Amazon Echo").addItems("Apple iPhone XS")
            .setDestination("Mountain View, CA")
            .setPrice(300)
            .build();
    private Map<String, Order> orderMap = Stream.of(
                    new AbstractMap.SimpleEntry<>(ord1.getId(), ord1),
                    new AbstractMap.SimpleEntry<>(ord2.getId(), ord2),
                    new AbstractMap.SimpleEntry<>(ord3.getId(), ord3),
                    new AbstractMap.SimpleEntry<>(ord4.getId(), ord4),
                    new AbstractMap.SimpleEntry<>(ord5.getId(), ord5))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    // Unary
    @Override
    public void addOrder(Order request, StreamObserver<StringValue> responseObserver) {
        System.out.println("Order Added - ID: " + request.getId() + ", Destination : " + request.getDestination());
        orderMap.put(request.getId(), request);
        StringValue id = StringValue.newBuilder().setValue("100500").build();
        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }

    // Unary
    @Override
    public void getOrder(StringValue request, StreamObserver<Order> responseObserver) {
        Order order = orderMap.get(request.getValue());
        if (order != null) {
            System.out.printf("Order Retrieved : ID - %s", order.getId());
            responseObserver.onNext(order);
            responseObserver.onCompleted();
        } else  {
            System.out.println("Order : " + request.getValue() + " - Not found.");
            responseObserver.onCompleted();
        }
        // ToDo  Handle errors
        // responseObserver.onError();
    }

    @Override
    public void searchOrders(StringValue request, StreamObserver<Order> responseObserver) {

        for (Map.Entry<String, Order> orderEntry : orderMap.entrySet()) {
            Order order = orderEntry.getValue();
            int itemsCount = order.getItemsCount();
            for (int index = 0; index < itemsCount; index++) {
                String item = order.getItems(index);
                if (item.contains(request.getValue())) {
                    System.out.println("Item found " + item);
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException ex) {

                    }



                    responseObserver.onNext(order);
                    break;
                }
            }
        }
        responseObserver.onCompleted();
    }

}
