import com.google.protobuf.StringValue;
import com.google.protobuf.StringValueOrBuilder;
import ecommerce.OrderManagementGrpc;
import ecommerce.Ordermanagement;
import io.grpc.Context;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OrderMgtServiceImpl extends OrderManagementGrpc.OrderManagementImplBase {

    private static final Logger logger = Logger.getLogger(OrderMgtServiceImpl.class.getName());


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
        logger.info("Order Added - ID: " + request.getId() + ", Destination : " + request.getDestination());
        orderMap.put(request.getId(), request);
        StringValue id = StringValue.newBuilder().setValue("100500").build();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        responseObserver.onNext(id);
        responseObserver.onCompleted();


    }

    // Server Streaming
    @Override
    public void searchOrders(StringValue request, StreamObserver<Ordermanagement.Order> responseObserver) {

        for (Map.Entry<String, Ordermanagement.Order> orderEntry : orderMap.entrySet()) {
            Ordermanagement.Order order = orderEntry.getValue();
            int itemsCount = order.getItemsCount();
            if (itemsCount == 0) {
                Status status = Status.FAILED_PRECONDITION.withDescription("Didn't found this");
                responseObserver.onError(status.asRuntimeException());
                return;
            }


            for (int index = 0; index < itemsCount * 5; index++) {
                try {
                    Thread.sleep(index * 500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                String item = order.getItems(index % itemsCount);
                if (item.contains(request.getValue())) {
                    logger.info("Item found " + item);

                    if (!Context.current().isCancelled()) {
                        responseObserver.onNext(order);
                    } else {
                        break;
                    }
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
                    logger.info("Order ID : " + value.getId() + " - Updated");
                }
            }

            @Override
            public void onError(Throwable t) {
                logger.info("Order ID update error " + t.getMessage());
            }

            @Override
            public void onCompleted() {
                logger.info("Update orders - Completed");
                StringValue updatedOrders = StringValue.newBuilder().setValue(updatedOrderStrBuilder.toString()).build();
                responseObserver.onNext(updatedOrders);
                responseObserver.onCompleted();
            }
        };
    }

}

