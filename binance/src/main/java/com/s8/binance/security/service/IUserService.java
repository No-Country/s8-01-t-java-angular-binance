package com.s8.binance.security.service;

import java.util.List;
import java.util.Optional;

import com.s8.binance.model.request.UserRequestDto;
import com.s8.binance.model.response.UserDetailsResponseDto;
import com.s8.binance.model.response.UserListResponseDto;
import com.s8.binance.security.entity.User;

public interface IUserService {

    public List<UserListResponseDto> getAllUsers();

    public UserDetailsResponseDto getUserById(Long id);
    
    public Optional<User> getByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);

    public void save(User user);

    public UserDetailsResponseDto updateUser(Long id, UserRequestDto user);

    public void deleteUser(Long id);

    public void emailVerification(String email, Integer num);
}
