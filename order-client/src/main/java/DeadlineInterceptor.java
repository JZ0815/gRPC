import io.grpc.*;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class DeadlineInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        // return channel.newCall(methodDescriptor, callOptions.withDeadline(Deadline.after(2, TimeUnit.SECONDS)));

        // check if deadline is set or not
        Deadline dealline = CallOptions.DEFAULT.getDeadline();
        if (Objects.isNull(dealline)) {
            callOptions = callOptions.withDeadline(Deadline.after(2, TimeUnit.SECONDS));
        }
        return channel.newCall(methodDescriptor, callOptions);
    }
}
