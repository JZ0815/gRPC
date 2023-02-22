import com.google.protobuf.StringValue;
import ecommerce.Order;
import ecommerce.OrderManagementGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import javax.management.StringValueExp;
import java.util.Iterator;

public class OrderMgtClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50055)
                .usePlaintext()
                .build();

        OrderManagementGrpc.OrderManagementBlockingStub stub = OrderManagementGrpc.newBlockingStub(channel);

        Order order = Order.newBuilder().setId("101")
                .addItems("iPhone XS").addItems("Mac Book Pro")
                .setDestination("San Jose, CA")
                .setPrice(2300)
                .build();

        StringValue result = stub.addOrder(order);

        System.out.println("We added an order, response " + result.getValue());

        StringValue id = StringValue.newBuilder().setValue("101").build();
        Order orderResponse = stub.getOrder(id);
        System.out.println("We find the order" + orderResponse.toString());


        // Search Orders
        StringValue searchStr = StringValue.newBuilder().setValue("Google").build();
        Iterator<Order> matchingOrdersItr;
        matchingOrdersItr = stub.searchOrders(searchStr);
        while (matchingOrdersItr.hasNext()) {
            Order matchingOrder = matchingOrdersItr.next();
            System.out.println("Search Order Response -> Matching Order - " + matchingOrder.getId());
            System.out.println(" Order : " + order.getId() + "\n "
                    + matchingOrder.toString());
        }


    }
}
