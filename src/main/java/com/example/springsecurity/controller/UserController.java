package com.example.springsecurity.controller;

import com.example.springsecurity.dto.UserDto;
import com.example.springsecurity.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    //ADMIN
    @GetMapping("/admin/all-users")
    public List<UserDto>getAllUsers(){
        return this.userService.getAllUsers();
    }

    @DeleteMapping("/admin/delete-user/{id}")
    public ResponseEntity<Void>deleteUser(@PathVariable Long id){
        return  this.userService.deleteUser(id);
    }

    @PostMapping("/admin/save-user")
    public ResponseEntity<UserDto>addUser(@RequestBody UserDto userDto){
        return this.userService.addUser(userDto);
    }
    //NORMAL USER
    @PutMapping("/user/update-user/{id}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,@PathVariable Long id){
        return this.userService.updateUser(userDto,id);
    }
    @GetMapping("/user/user-by-id/{id}")
    public  UserDto getUserById(@PathVariable("id") Long id){
        return  this.userService.getUserById(id);
    }


}
