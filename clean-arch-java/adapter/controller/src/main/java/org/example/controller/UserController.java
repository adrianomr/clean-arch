package org.example.controller;

import org.example.controller.model.UserWeb;
import org.example.usecase.CreateUser;
import org.example.usecase.FindUser;

import java.util.List;
import java.util.stream.Collectors;

public class UserController {

    private final CreateUser createUser;
    private final FindUser findUser;

    public UserController(final CreateUser createUser, final FindUser findUser) {
        this.createUser = createUser;
        this.findUser = findUser;
    }

    public UserWeb createUser(final UserWeb userWeb) {
        var user = userWeb.toUser();
        return UserWeb.toUserWeb(createUser.create(user));
    }

    public UserWeb getUser(final String userId) {
        return UserWeb.toUserWeb(findUser.findById(userId).orElseThrow(() -> new RuntimeException("user not found")));
    }

    public List<UserWeb> allUsers() {
        return findUser.findAllUsers()
                .stream()
                .map(UserWeb::toUserWeb)
                .collect(Collectors.toList());
    }
}