package com.s8.binance.security.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.s8.binance.model.mapper.UserMapper;
import com.s8.binance.model.request.UserRequestDto;
import com.s8.binance.model.response.UserDetailsResponseDto;
import com.s8.binance.model.response.UserListResponseDto;
import com.s8.binance.security.entity.User;
import com.s8.binance.security.repository.IUserRepository;
import com.s8.binance.security.service.IUserService;
import com.s8.binance.service.ITransactionService;
import com.s8.binance.service.impl.EmailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

	private final IUserRepository userRepository;

	private final ITransactionService transactionService;

	private final UserMapper userMapper;

	private final EmailService emailService;

	@Override
    public List<UserListResponseDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserListResponseDto> userResponseDtoList = new ArrayList<>();
        users.forEach(user -> {
            UserListResponseDto response = userMapper.fromEntityToDtoList(user);
            userResponseDtoList.add(response);
        });
        return userResponseDtoList;
    }

	@Override
    public UserDetailsResponseDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow();
		HashMap<String, BigDecimal> balance = transactionService.getWalletBalance(user.getId());
        UserDetailsResponseDto response = userMapper.fromEntityToDto(user, balance);
        return response;
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
    @Transactional
    public UserDetailsResponseDto updateUser(Long id, UserRequestDto UserRequestDto) {
        User user = userRepository.findById(id).orElseThrow();
        User updatedUser = userMapper.updateUser(user, UserRequestDto);
        userRepository.save(updatedUser);
        UserDetailsResponseDto response = userMapper.fromEntityToDto(user, new HashMap<>());
        return response;
    }

	@Override
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
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