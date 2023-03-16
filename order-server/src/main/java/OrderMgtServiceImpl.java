import com.google.protobuf.StringValue;
import com.google.protobuf.StringValueOrBuilder;
import ecommerce.OrderManagementGrpc;
import ecommerce.Ordermanagement;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderMgtServiceImpl extends OrderManagementGrpc.OrderManagementImplBase {

    int port;
    public OrderMgtServiceImpl(int port) {
        this.port = port;
    }

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
        StringValue id = StringValue.newBuilder().setValue("add order by service on port " + port).build();
        responseObserver.onNext(id);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
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

}

