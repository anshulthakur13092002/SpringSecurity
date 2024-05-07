package com.example.springsecurity.service.impl;

import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.model.Role;
import com.example.springsecurity.model.User;
import com.example.springsecurity.repository.RoleRepository;
import com.example.springsecurity.repository.UserRepository;
import com.example.springsecurity.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private  final ModelMapper modelMapper;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public  List < UserDto >  getAllUsers() {
        return this.userRepository.findAll().stream().map(this::convertEntityTODto).collect(Collectors.toList());
    }

    @Override
    public  UserDto getUserById(Long id) {
        User user1 =this.userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User with ID " + id + " not found"));
        return convertEntityTODto(user1);
    }
   @Override
    public ResponseEntity<Void> deleteUser(Long id) {
        return this.userRepository.findById(id)
                .map(user -> {
                    this.userRepository.deleteById(id);
                    return ResponseEntity.ok().<Void>build();
                })
                .orElseGet(() -> ResponseEntity.notFound().<Void>build());
    }

    @Override
    public ResponseEntity<UserDto> updateUser(UserDto userDto, Long id) {
        User user = userRepository.findById(id).orElseThrow(()->new RuntimeException("this Id is not in the database "));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setMobile(userDto.getMobile());
        userRepository.save(user);
        UserDto updatedUserDto = convertEntityTODto(user);
        return ResponseEntity.ok(updatedUserDto);
    }

    @Override
    public ResponseEntity<UserDto> addUser(UserDto userDto) {
        User user = userRepository.save(convertDtoTOEntity(userDto));
        UserDto savedUserDto = convertEntityTODto(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUserDto);
    }

    private UserDto convertEntityTODto(User user) {
        return this.modelMapper.map(user, UserDto.class);
    }

    private User convertDtoTOEntity(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setMobile(userDto.getMobile());
        user.setRole(userDto.getRole().stream().map(roleDto -> {
            Role role1=new Role();
            role1.setRole(roleDto.getRole());
            roleRepository.save(role1);
            return role1;
        }).collect(Collectors.toList()));

        userRepository.save(user);
        return user;
    }
}
