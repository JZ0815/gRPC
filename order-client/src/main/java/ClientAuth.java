import io.grpc.Metadata;

public class ClientAuth {
    private static final Metadata METADATA = new Metadata();
    static {
        METADATA.put(
                Metadata.Key.of("client-token", Metadata.ASCII_STRING_MARSHALLER),
                "this is an invalid jwt token"
                //"this is a valid jwt token"
        );
    }
    public static Metadata getClientToken() {
        return METADATA;
    }
}
