package com.s8.binance.security.service;

import java.util.Optional;

import javax.transaction.Transactional;
import com.s8.binance.service.impl.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.s8.binance.security.entity.User;
import com.s8.binance.security.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private EmailService emailService;

	public Optional<User> getByUser(String username) {
		return userRepository.findByUsername(username);
	}

	public Boolean existsByUser(String username) {
		return userRepository.existsByUsername(username);
	}

	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	/*public String emailVerification(String email) {
		emailService.sendMail(email,
				"BINANCE - Confirme your Registration",
				"Your Crypto Journey Starts Now\n" +
						"Welcome to Binance. Confirm your registration by using the activation code below. \n" +
						"\n" +
						"Account activation code: \n"
						);
		return "mail enviado";
	}*/
}