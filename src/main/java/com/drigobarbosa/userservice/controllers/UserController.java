package com.drigobarbosa.userservice.controllers;

import com.drigobarbosa.userservice.dtos.UserRecordDto;
import com.drigobarbosa.userservice.models.UserModel;
import com.drigobarbosa.userservice.services.UserService;
import com.fasterxml.jackson.databind.util.BeanUtil;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/users")
    public ResponseEntity<UserModel> saveUser(@RequestBody @Valid UserRecordDto userRecordDto) {
        var userModel = new UserModel(userRecordDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(userModel));
    }

}
