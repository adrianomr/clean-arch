package org.example.grpc;

import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class GreetingServiceImpl extends GreetingServiceGrpc.GreetingServiceImplBase {

    @Override
    public void greet(GreetingRequest request, StreamObserver<GreetingResponse> responseObserver) {

        String reply = String.format("Hello %s, am fine thanks for asking! :)", request.getName());

        GreetingResponse response = GreetingResponse.newBuilder()
                .setReply(reply)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
