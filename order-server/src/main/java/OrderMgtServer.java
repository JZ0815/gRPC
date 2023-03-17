import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class OrderMgtServer {

    private Server server;

    //Reference : https://blog.csdn.net/gaoliang1719/article/details/106030827?spm=1001.2101.3001.6650.16&utm_medium=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-16-106030827-blog-105814132.pc_relevant_3mothn_strategy_and_data_recovery&depth_1-utm_source=distribute.pc_relevant.none-task-blog-2%7Edefault%7EBlogCommendFromBaidu%7ERate-16-106030827-blog-105814132.pc_relevant_3mothn_strategy_and_data_recovery&utm_relevant_index=17
    private void start() throws IOException {
        /* The port on which the server should run */
        int port = 50053;
        server = ServerBuilder.forPort(port)
                .intercept(new CompressionInterceptor())
                .addService(new OrderMgtServiceImpl())
                .build()
                .start();
        System.out.println("Server started, listening on " + port);

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.out.println("*** shutting down gRPC server since JVM is shutting down");
            OrderMgtServer.this.stop();
            System.out.println("*** server shut down");
        }));
    }

    private void stop() {
        if (server != null) {
            server.shutdown();
        }
    }

    /**
     * Await termination on the main thread since the grpc library uses daemon threads.
     */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        final OrderMgtServer server = new OrderMgtServer();
        server.start();
        server.blockUntilShutdown();
    }


}
