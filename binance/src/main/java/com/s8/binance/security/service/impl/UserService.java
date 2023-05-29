package com.s8.binance.security.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.s8.binance.model.request.UserRequestDto;
import com.s8.binance.model.response.UserDetailsResponseDto;
import com.s8.binance.model.response.UserListResponseDto;
import com.s8.binance.security.entity.User;
import com.s8.binance.security.repository.IUserRepository;
import com.s8.binance.security.service.IUserService;
import com.s8.binance.service.impl.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

	private final IUserRepository userRepository;

	private final EmailService emailService;

	@Override
	public List<UserListResponseDto> getAllUsers() {
		List<User> users = userRepository.findAll();
		return null;
	}

	@Override
	public UserDetailsResponseDto getUserById(Long id) {
		User user = userRepository.findById(id).orElseThrow();
		return null;
	}

	public Optional<User> getByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	public Boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}

	public void save(User user) {
		userRepository.save(user);
	}

	@Override
	public UserDetailsResponseDto updateUser(Long id, UserRequestDto user) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'deleteUser'");
	}

	public void emailVerification(String email, Integer num) {
		emailService.sendMail(email,
				"BINANCE - Confirm your Registration",
				"Your Crypto Journey Starts Now\n" +
						"Welcome to Binance. Confirm your registration by using the activation code below.\n" +
						"\n" +
						"Account activation code:\n" +
						num);
	}
}