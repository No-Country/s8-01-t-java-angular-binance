package com.s8.binance.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.s8.binance.model.request.UserRequestDto;
import com.s8.binance.model.response.UserDetailsResponseDto;
import com.s8.binance.model.response.UserListResponseDto;
import com.s8.binance.security.service.IUserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/v1/users")
@RequiredArgsConstructor
@Api(tags = "Users", description = "Management of users available in Binance. It allows modifying and deleting users, as well as obtaining detailed information about them.")
public class UserController {

    private final IUserService userService;

    @GetMapping
    @ApiOperation("Get all users.")
    public ResponseEntity<List<UserListResponseDto>> getAllUsers() {
        List<UserListResponseDto> responseEntity = userService.getAllUsers();
        return ResponseEntity.ok().body(responseEntity);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get a user by Id.")
    public ResponseEntity<UserDetailsResponseDto> getUserById(@PathVariable Long id) {
        UserDetailsResponseDto responseEntity = userService.getUserById(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(responseEntity);
    }

    @PutMapping("/update/{id}")
    @ApiOperation("Update an existing user by Id.")
    public ResponseEntity<UserDetailsResponseDto> updateUser(@Valid @PathVariable Long id,
            @RequestBody UserRequestDto UserRequestDto) {
        UserDetailsResponseDto responseEntity = userService.updateUser(id, UserRequestDto);
        return ResponseEntity.ok().body(responseEntity);
    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation("Delete an existing user by Id")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok().body("User successfully deleted");
    }
}