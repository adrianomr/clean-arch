package org.example.grpc.user;

import io.grpc.stub.StreamObserver;
import org.example.controller.UserController;
import org.example.grpc.GreetingRequest;
import org.example.grpc.GreetingResponse;
import org.example.grpc.GreetingServiceGrpc;
import org.example.grpc.mapper.UserMapper;
import org.example.usecase.CreateUser;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@GRpcService
public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    @Autowired
    UserController userController;
    @Autowired
    UserMapper userMapper;

    @Override
    public void findAll(Empty request, StreamObserver<ListUserResponse> responseObserver) {
        List<UserResponse> users = userController
                .allUsers()
                .stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
        ListUserResponse response = ListUserResponse
                .newBuilder()
                .addAllResults(users)
                .build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void findOne(userId request, StreamObserver<UserResponse> responseObserver) {
        UserResponse userResponse = userMapper.map(userController.getUser(request.getId()));
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }

    @Override
    public void save(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        UserResponse userResponse = userMapper.map( userController.createUser(userMapper.map(request)));
        responseObserver.onNext(userResponse);
        responseObserver.onCompleted();
    }

}
