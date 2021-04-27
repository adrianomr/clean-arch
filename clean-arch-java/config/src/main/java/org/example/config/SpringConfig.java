package org.example.config;

import org.example.db.InMemoryUserRepository;
import org.example.jug.JugIdGenerator;
import org.example.port.PasswordEncoder;
import org.example.port.UserRepository;
import org.example.usecase.CreateUser;
import org.example.usecase.FindUser;
import org.slalom.example.encoder.Sha256PasswordEncoder;

public class SpringConfig {

	private final UserRepository userRepository = new InMemoryUserRepository();
	private final PasswordEncoder passwordEncoder = new Sha256PasswordEncoder();

	public CreateUser createUser() {
		return new CreateUser(userRepository, passwordEncoder, new JugIdGenerator());
	}

	public FindUser findUser() {
		return new FindUser(userRepository);
	}

}