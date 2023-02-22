import protobuf.com.jie.api.Product;
import protobuf.com.jie.api.ProductID;
import protobuf.com.jie.api.ProductInfoGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductInfoClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50052)
                .usePlaintext()
                .build();

        ProductInfoGrpc.ProductInfoBlockingStub stub =
                ProductInfoGrpc.newBlockingStub(channel);

        ProductID productID = stub.addProduct(
                         Product.newBuilder()
                        .setName("Samsung S10")
                        .setDescription("Samsung Galaxy S10 is the latest smart phone, " +
                                "launched in February 2019")
                        .setPrice(700.0f)
                        .build());
        System.out.println("Product ID: " + productID.getValue() + " added successfully.");

        Product product = stub.getProduct(productID);
        System.out.println("Product: " + product.toString());



        System.out.println("Shutting down");
        channel.shutdown();
    }
}
