import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class OrderMgtServer {
    private Server server;

    private void start() throws IOException {
        int port = 50055;
        server = ServerBuilder.forPort(port)
                .addService(new OrderMgtServiceImpl())
                .build()
                .start();
        System.out.println("grpc server started, listening on port " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("");
            OrderMgtServer.this.stop();
            System.out.println("server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }
    public static void main(String[] args) throws InterruptedException, IOException {
        final OrderMgtServer server = new OrderMgtServer();
        server.start();
        server.blockUntilShutdown();

    }
}
