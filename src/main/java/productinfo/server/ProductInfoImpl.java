package productinfo.server;

import com.proto.productinfo.ProductInfoGrpc;
import io.grpc.Status;
import io.grpc.StatusException;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    private Map productMap = new HashMap<String, com.proto.productinfo.Product>();
    @Override
    public void addProduct(com.proto.productinfo.Product request,
                           io.grpc.stub.StreamObserver<com.proto.productinfo.ProductID> responseObserver) {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString();
        request = request.toBuilder().setId(randomUUIDString).build();
        productMap.put(randomUUIDString, request);
        com.proto.productinfo.ProductID id
                = com.proto.productinfo.ProductID.newBuilder().setValue(randomUUIDString).build();
        responseObserver.onNext(id);
        responseObserver.onCompleted();

    }

    @Override
    public void getProduct(com.proto.productinfo.ProductID request,
                           io.grpc.stub.StreamObserver<com.proto.productinfo.Product> responseObserver) {
        String id = request.getValue();
        if (productMap.containsKey(id)) {
            responseObserver.onNext((com.proto.productinfo.Product) productMap.get(id));
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }
    }

}
