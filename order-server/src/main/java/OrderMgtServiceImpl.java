import com.google.protobuf.StringValue;
import ecommerce.OrderManagementGrpc;
import ecommerce.Ordermanagement;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderMgtServiceImpl extends OrderManagementGrpc.OrderManagementImplBase {

    private Ordermanagement.Order ord1 = Ordermanagement.Order.newBuilder()
            .setId("102")
            .addItems("Google Pixel 3A").addItems("Mac Book Pro")
            .setDestination("Mountain View, CA")
            .setPrice(1800)
            .build();
    private Ordermanagement.Order ord2 = Ordermanagement.Order.newBuilder()
            .setId("103")
            .addItems("Apple Watch S4")
            .setDestination("San Jose, CA")
            .setPrice(400)
            .build();
    private Ordermanagement.Order ord3 = Ordermanagement.Order.newBuilder()
            .setId("104")
            .addItems("Google Home Mini").addItems("Google Nest Hub")
            .setDestination("Mountain View, CA")
            .setPrice(400)
            .build();
    private Ordermanagement.Order ord4 = Ordermanagement.Order.newBuilder()
            .setId("105")
            .addItems("Amazon Echo")
            .setDestination("San Jose, CA")
            .setPrice(30)
            .build();
    private Ordermanagement.Order ord5 = Ordermanagement.Order.newBuilder()
            .setId("106")
            .addItems("Amazon Echo").addItems("Apple iPhone XS")
            .setDestination("Mountain View, CA")
            .setPrice(300)
            .build();

    private Map<String, Ordermanagement.Order> orderMap = Stream.of(
                    new AbstractMap.SimpleEntry<>(ord1.getId(), ord1),
                    new AbstractMap.SimpleEntry<>(ord2.getId(), ord2),
                    new AbstractMap.SimpleEntry<>(ord3.getId(), ord3),
                    new AbstractMap.SimpleEntry<>(ord4.getId(), ord4),
                    new AbstractMap.SimpleEntry<>(ord5.getId(), ord5))
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    private Map<String, Ordermanagement.CombinedShipment> combinedShipmentMap = new HashMap<>();

    public static final int BATCH_SIZE = 3;


    // Unary
    @Override
    public void addOrder(Ordermanagement.Order request, StreamObserver<StringValue> responseObserver) {
        System.out.println("Order Added - ID: " + request.getId() + ", Destination : " + request.getDestination());
        orderMap.put(request.getId(), request);
        StringValue id = StringValue.newBuilder().setValue("100500").build();
        responseObserver.onNext(id);
        responseObserver.onCompleted();
    }

    // Unary
    @Override
    public void getOrder(StringValue request, StreamObserver<Ordermanagement.Order> responseObserver) {
        Ordermanagement.Order order = orderMap.get(request.getValue());
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

    // Server Streaming
    @Override
    public void searchOrders(StringValue request, StreamObserver<Ordermanagement.Order> responseObserver) {

        for (Map.Entry<String, Ordermanagement.Order> orderEntry : orderMap.entrySet()) {
            Ordermanagement.Order order = orderEntry.getValue();
            int itemsCount = order.getItemsCount();
            for (int index = 0; index < itemsCount; index++) {
                String item = order.getItems(index);
                if (item.contains(request.getValue())) {
                    System.out.println("Item found " + item);
                    responseObserver.onNext(order);
                    break;
                }
            }
        }
        responseObserver.onCompleted();
    }

    // Client Streaming
    @Override
    public StreamObserver<Ordermanagement.Order> updateOrders(StreamObserver<StringValue> responseObserver) {
        return new StreamObserver<Ordermanagement.Order>() {
            StringBuilder updatedOrderStrBuilder = new StringBuilder().append("Updated Order IDs : ");

            @Override
            public void onNext(Ordermanagement.Order value) {
                if (value != null) {
                    orderMap.put(value.getId(), value);
                    updatedOrderStrBuilder.append(value.getId()).append(", ");
                    System.out.println("Order ID : " + value.getId() + " - Updated");
                }
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("Order ID update error " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                System.out.println("Update orders - Completed");
                StringValue updatedOrders = StringValue.newBuilder().setValue(updatedOrderStrBuilder.toString()).build();
                responseObserver.onNext(updatedOrders);
                responseObserver.onCompleted();
            }
        };
    }


    // Bi-di Streaming
    @Override
    public StreamObserver<StringValue> processOrders(StreamObserver<Ordermanagement.CombinedShipment> responseObserver) {

        return new StreamObserver<StringValue>() {
            int batchMarker = 0;
            @Override
            public void onNext(StringValue value) {
                System.out.println("Order Proc : ID - " + value.getValue());
                Ordermanagement.Order currentOrder = orderMap.get(value.getValue());
                if (currentOrder == null) {
                    System.out.println("No order found. ID - " + value.getValue());
                    return;
                }
                // Processing an order and increment batch marker to
                batchMarker++;
                String orderDestination = currentOrder.getDestination();
                Ordermanagement.CombinedShipment existingShipment = combinedShipmentMap.get(orderDestination);

                if (existingShipment != null) {
                    existingShipment = Ordermanagement.CombinedShipment.newBuilder(existingShipment).addOrdersList(currentOrder).build();
                    combinedShipmentMap.put(orderDestination, existingShipment);
                } else {
                    Ordermanagement.CombinedShipment shipment = Ordermanagement.CombinedShipment.newBuilder().build();
                    shipment = shipment.newBuilderForType()
                            .addOrdersList(currentOrder)
                            .setId("CMB-" + new Random().nextInt(1000)+ ":" + currentOrder.getDestination())
                            .setStatus("Processed!")
                            .build();
                    combinedShipmentMap.put(currentOrder.getDestination(), shipment);
                }

                if (batchMarker == BATCH_SIZE) {
                    // Order batch completed. Flush all existing shipments.
                    for (Map.Entry<String, Ordermanagement.CombinedShipment> entry : combinedShipmentMap.entrySet()) {
                        responseObserver.onNext(entry.getValue());
                    }
                    // Reset batch marker
                    batchMarker = 0;
                    combinedShipmentMap.clear();
                }
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                for (Map.Entry<String, Ordermanagement.CombinedShipment> entry : combinedShipmentMap.entrySet()) {
                    responseObserver.onNext(entry.getValue());
                }
                responseObserver.onCompleted();
            }

        };
    }
}

