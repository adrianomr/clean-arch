package org.example.usecase;

import lombok.AllArgsConstructor;
import org.example.domain.entity.User;
import org.example.exception.UserAlreadyExistsException;
import org.example.port.IdGenerator;
import org.example.port.PasswordEncoder;
import org.example.port.UserRepository;
import org.example.validator.UserValidator;

@AllArgsConstructor
public final class CreateUser {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final IdGenerator idGenerator;

    public User create(final User user) {
        UserValidator.validateCreateUser(user);
        if (repository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserAlreadyExistsException(user.getEmail());
        }
        var userToSave = User.builder()
                .id(idGenerator.generate())
                .email(user.getEmail())
                .password(passwordEncoder.encode(user.getEmail() + user.getPassword()))
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .build();
        return repository.create(userToSave);
    }
}