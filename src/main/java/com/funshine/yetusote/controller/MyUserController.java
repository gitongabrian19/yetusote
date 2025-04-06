package com.funshine.yetusote.controller;

import com.funshine.yetusote.models.Member;
import com.funshine.yetusote.requests.LoginRequest;
import com.funshine.yetusote.models.MyUser;
import com.funshine.yetusote.response.AuthResponse;
import com.funshine.yetusote.services.MemberService;
import com.funshine.yetusote.services.MyUserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/user")
@Tag(name = "MyUser", description = "MyUser API's")
public class MyUserController {
    @Autowired
    private MyUserService myUserService;
    @Autowired
    private MemberService memberService;

    //?login
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> authenticateUser(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = new AuthResponse();

        try {
            Optional<MyUser> user = myUserService.authenticateUser(loginRequest);
            if (user.isPresent()) {
                authResponse.setId(user.get().getUserId());
                authResponse.setFirstName(user.get().getFirstname());
                authResponse.setLastName(user.get().getLastname());
                authResponse.setEmail(user.get().getEmail());
                authResponse.setPhone(user.get().getPhone().toString());
                authResponse.setRole(user.get().getRole());
            } else {
                Optional<Member> member = memberService.authenticateUser(loginRequest);
                if (member.isPresent()) {
                    authResponse.setId(member.get().getMemberId());
                    authResponse.setFirstName(member.get().getFirstName());
                    authResponse.setLastName(member.get().getLastName());
                    authResponse.setEmail(member.get().getEmail());
                    authResponse.setPhone(member.get().getPhone());
                    authResponse.setRole(member.get().getRole());
                    authResponse.setMembershipType(member.get().getMembershipType());
                    authResponse.setTotalShares(member.get().getTotalShares());
                    authResponse.setOutstandingLoan(member.get().getOutstandingLoan());
                    authResponse.setPenalties(member.get().getPenalties());
                } else {
                    return ResponseEntity.status(404).body(null);
                }
            }
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }

        return ResponseEntity.ok(authResponse);
    }

    //?create user
    @PostMapping
    private ResponseEntity<MyUser> createUser(@RequestBody MyUser user) {
        return ResponseEntity.ok(myUserService.createUser(user));
    }

    //?get users
    @GetMapping
    private ResponseEntity<List<MyUser>> getUsers() {
        return ResponseEntity.ok(myUserService.getUsers());
    }

    //? get user by id
    @GetMapping("/id/{userId}")
    private ResponseEntity<MyUser> getUserById(@PathVariable String userId) {
        return ResponseEntity.ok(myUserService.getUserById(userId));
    }

    //?update user
    @PutMapping("/{userId}")
    private ResponseEntity<MyUser> updateUser(@PathVariable String userId, @RequestBody MyUser myUser) {
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
        return ResponseEntity.ok(myUserService.updateUser(updatedUser));
    }

    //?delete user
    @DeleteMapping("/{userId}")
    private ResponseEntity<String> deleteUser(@PathVariable String userId) {

        if (myUserService.getUserById(userId) != null) {
            if (myUserService.deleteUser(userId)) {
                return ResponseEntity.ok("User deleted successfully");
            } else {
                return ResponseEntity.ok("Error deleting user");
            }
        } else {
            return ResponseEntity.ok("User not found");
        }
    }
}
