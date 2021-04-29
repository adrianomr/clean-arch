package org.example.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.grpc.user.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GrpcServerApplicationTest {
    static ManagedChannel channel;
    static UserServiceGrpc.UserServiceBlockingStub stub;
    long average;

    @BeforeAll
    static void beforeAll() {
        channel = ManagedChannelBuilder.forAddress("localhost", 8081)
                .usePlaintext()
                .build();
        stub = UserServiceGrpc.newBlockingStub(channel);
    }

    @AfterAll
    static void afterAll() {
        channel.shutdown();
    }

    @Test
    void greeting() {

        GreetingServiceGrpc.GreetingServiceBlockingStub stub = GreetingServiceGrpc.newBlockingStub(channel);
        Long startMili = Instant.now().toEpochMilli();
        GreetingResponse helloResponse = stub.greet(GreetingRequest.newBuilder()
                .setName("Test")
                .build());
        Long endMilli = Instant.now().toEpochMilli();
        System.out.println("Request time in mili: " + (endMilli - startMili));
        assertEquals("Hello Test, am fine thanks for asking! :)", helloResponse.getReply());
    }

    @Test
    void createUser() {
        average = 0;
        for (int i = 0; i < 100; i++) {
            UserResponse userResponse = saveUser(i);
            assertEquals("adriano" + i + "@test.com", userResponse.getEmail());
            assertEquals("Adriano", userResponse.getFirstName());
            assertEquals("Rodrigues", userResponse.getLastName());
        }
        average = BigDecimal
                .valueOf(average)
                .divide(BigDecimal.valueOf(99), 0, RoundingMode.CEILING)
                .longValueExact();
        System.out.println("User insertion average: "+average);
    }

    @Test
    void findOneUser() {
        average = 0;
        UserResponse userSaved = saveUser(200);
        UserResponse userFound;
        for (int i = 0; i < 100; i++) {
            Long startMili = Instant.now().toEpochMilli();
            userFound = stub.findOne(userId.newBuilder().setId(userSaved.getId()).build());
            Long endMilli = Instant.now().toEpochMilli();
            Long diff = (endMilli - startMili);
            if(i > 0)
                average += diff;
            System.out.println("Request time in mili: " + diff);;
            assertEquals(userSaved, userFound);
        }
        average = BigDecimal
                .valueOf(average)
                .divide(BigDecimal.valueOf(99), 0, RoundingMode.CEILING)
                .longValueExact();
        System.out.println("Fetch user average: "+average);
    }

    @Test
    void findAllUser() {
        UserResponse userSaved = saveUser(300);
        ListUserResponse listUserResponse = stub.findAll(Empty.newBuilder().build());
        UserResponse userFound = userFound = listUserResponse.getResultsList().get(0);
        assertEquals("Adriano", userFound.getFirstName());
        assertEquals("Rodrigues", userFound.getLastName());
    }

    private UserResponse saveUser(int i) {
        Long startMili = Instant.now().toEpochMilli();
        UserResponse userResponse = stub.save(
                UserRequest
                        .newBuilder()
                        .setEmail("adriano"+i+"@test.com")
                        .setFirstName("Adriano")
                        .setLastName("Rodrigues")
                        .setPassword("123456")
                        .build());
        Long endMilli = Instant.now().toEpochMilli();
        Long diff = (endMilli - startMili);
        if(i > 0)
            average += diff;
        System.out.println("Request time in mili: " + diff);
        return userResponse;
    }
}