import io.grpc.Metadata;

public class ServerAuth {
    public static final Metadata.Key<String> TOKEN = Metadata.Key.of("client-token",
            Metadata.ASCII_STRING_MARSHALLER);

}
