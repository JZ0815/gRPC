package productinfo.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ProductInfoServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 50051;
        Server server = ServerBuilder
                .forPort(port)
                .addService(new ProductInfoImpl())
                .build();

        server.start();
        System.out.println("Server started");
        System.out.println("Listing on port: " + port);
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Recieved shutdown rquest");
            server.shutdown();
            System.out.println("Server stopped");
        }));

        server.awaitTermination();

    }
}
