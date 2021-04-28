package org.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GrpcServerApplicationTest {
    static ManagedChannel channel;

    @BeforeAll
    static void beforeAll() {
        channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext()
                .build();
    }

    @AfterAll
    static void afterAll() {
        channel.shutdown();
    }

    @Test
    void main() {


        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);

        GreetingResponse helloResponse = stub.greet(GreetingRequest.newBuilder()
                .setName("Test")
                .build());
        System.out.println(helloResponse);
        assertEquals("Hello Test, am fine thanks for asking! :)", helloResponse.getReply());
    }
}