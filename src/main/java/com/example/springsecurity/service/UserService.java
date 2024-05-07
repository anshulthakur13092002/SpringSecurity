package com.example.springsecurity.service;

import com.example.springsecurity.dto.UserDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List < UserDto >getAllUsers();
    UserDto getUserById(Long id);
    ResponseEntity <Void> deleteUser(Long id);
    ResponseEntity<UserDto> updateUser(UserDto userDto, Long id);
    ResponseEntity <UserDto> addUser(UserDto userDto);
}
