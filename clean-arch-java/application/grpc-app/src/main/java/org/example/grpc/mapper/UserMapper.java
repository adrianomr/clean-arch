package org.example.grpc.mapper;

import org.example.controller.model.UserWeb;
import org.example.grpc.user.User;
import org.example.grpc.user.UserRequest;
import org.example.grpc.user.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    public UserWeb map(UserRequest user){
        return UserWeb
                .builder()
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .build();
    }

    public UserResponse map(UserWeb user){
        return UserResponse
                .newBuilder()
                .setId(user.getId())
                .setEmail(user.getEmail())
                .setFirstName(user.getFirstName())
                .setLastName(user.getLastName())
                .build();
    }
}
