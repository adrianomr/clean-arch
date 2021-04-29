package org.example.controller.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.domain.entity.User;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserWeb {
    private String id;
    private String email;
    private String password;
    private String lastName;
    private String firstName;

    public User toUser() {
        return User.builder()
                .email(email)
                .password(password)
                .lastName(lastName)
                .firstName(firstName)
                .build();
    }

    public static UserWeb toUserWeb(final User user) {
        var userWeb = new UserWeb();
        userWeb.setId(user.getId());
        userWeb.setEmail(user.getEmail());
        // do not map password
        userWeb.setLastName(user.getLastName());
        userWeb.setFirstName(user.getFirstName());
        return userWeb;
    }

}
