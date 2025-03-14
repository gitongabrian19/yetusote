package com.funshine.yetusote.controller;

import com.funshine.yetusote.requests.LoginRequest;
import com.funshine.yetusote.entity.MyUser;
import com.funshine.yetusote.services.MyUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/user")
@Tag(name ="MyUser", description = "MyUser API's")
public class MyUserController {
    @Autowired
    private MyUserService myUserService;

    //?login
    @PostMapping("/login")
    public ResponseEntity<MyUser> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Optional<MyUser> user = myUserService.authenticateUser(loginRequest);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    //?create user
    @PostMapping
    private MyUser createUser(@RequestBody MyUser user) {
        return myUserService.createUser(user);
    }

    //?get users
    @GetMapping
    private List<MyUser> getUsers() {
        return myUserService.getUsers();
    }

    //? get user by id
    @GetMapping("/id/{userId}")
    private MyUser getUserById(@PathVariable String userId) {
        return myUserService.getUserById(userId);
    }

    //?update user
    @PutMapping("/{userId}")
    private MyUser updateUser(@PathVariable String userId, @RequestBody MyUser myUser) {
        MyUser existingUser = myUserService.getUserById(userId);
        if (existingUser == null) return null;

        MyUser updatedUser = new MyUser();
        updatedUser.setUserId(existingUser.getUserId());
        updatedUser.setFirstname(myUser.getFirstname());
        updatedUser.setLastname(myUser.getLastname());
        updatedUser.setEmail(myUser.getEmail());
        updatedUser.setPhone(myUser.getPhone());
        updatedUser.setRole(myUser.getRole());
        updatedUser.setIdNumber(myUser.getIdNumber());
        updatedUser.setPassword(myUser.getPassword());
        return myUserService.updateUser(updatedUser);
    }

    //?delete user
    @DeleteMapping("/{userId}")
    private String deleteUser(@PathVariable String userId) {

        if (myUserService.getUserById(userId) != null) {
            if (myUserService.deleteUser(userId)) {
                return "User deleted successfully";
            } else {
                return "Error deleting user";
            }
        } else {
            return "User not found";
        }
    }
}
