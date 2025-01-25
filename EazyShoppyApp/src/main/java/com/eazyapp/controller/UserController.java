package com.eazyapp.controller;

import com.eazyapp.dto.UserDTO;
import com.eazyapp.exception.EazyShoppyException;
import com.eazyapp.requestwrapper.UserRequestWrapper;
import com.eazyapp.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequestWrapper userRequestWrapper) {
        try {
            userService.createUser(userRequestWrapper);
            return new ResponseEntity<>("User created successfully", HttpStatus.CREATED);
        } catch (EazyShoppyException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable long id) {
        try {
            UserDTO user = userService.getUserById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (EazyShoppyException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        try {
            List<UserDTO> users = userService.getAllUser();
            return new ResponseEntity<>(users, HttpStatus.OK);
        } catch (EazyShoppyException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

