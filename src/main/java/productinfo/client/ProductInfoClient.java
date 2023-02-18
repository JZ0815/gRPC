package productinfo.client;

import com.proto.productinfo.ProductInfoGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductInfoClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ProductInfoGrpc.ProductInfoBlockingStub stub =
                ProductInfoGrpc.newBlockingStub(channel);

        com.proto.productinfo.ProductID productID = stub.addProduct(
                com.proto.productinfo.Product.newBuilder()
                        .setName("Samsung S10")
                        .setDescription("Samsung Galaxy S10 is the latest smart phone, " +
                                "launched in February 2019")
                        .setPrice(700.0f)
                        .build());
        System.out.println("Product ID: " + productID.getValue() + " added successfully.");

        com.proto.productinfo.Product product = stub.getProduct(productID);
        System.out.println("Product: " + product.toString());



        System.out.println("Shutting down");
        channel.shutdown();
    }
}
