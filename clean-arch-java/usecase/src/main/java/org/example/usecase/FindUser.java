package org.example.usecase;

import lombok.AllArgsConstructor;
import org.example.domain.entity.User;
import org.example.port.UserRepository;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public final class FindUser {

	private final UserRepository repository;

	public Optional<User> findById(final String id) {
		return repository.findById(id);
	}

	public List<User> findAllUsers() {
		return repository.findAllUsers();
	}
}