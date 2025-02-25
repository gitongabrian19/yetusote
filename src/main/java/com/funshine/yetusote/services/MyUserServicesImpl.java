package com.funshine.yetusote.services;

import com.funshine.yetusote.LoginRequest;
import com.funshine.yetusote.entity.MyUser;
import com.funshine.yetusote.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MyUserServicesImpl implements MyUserService {

    @Autowired
    private MyUserRepository myUserRepository;


    @Override
    public Optional<MyUser> authenticateUser(LoginRequest loginRequest) {
        Optional<MyUser> user = myUserRepository.findByEmail(loginRequest.getEmail());
        if (user.isPresent() && user.get().getPassword().equals(loginRequest.getPassword())) {
            return user;
        }
        return Optional.empty();
    }

    @Override
    public MyUser createUser(MyUser user) {
        return myUserRepository.save(user);
    }

    @Override
    public List<MyUser> getUsers() {
        return myUserRepository.findAll();
    }

    @Override
    public MyUser getUserById(String userId) {
        return myUserRepository.findById(userId).orElse(null);
    }


    @Override
    public MyUser updateUser(MyUser user) {
        return myUserRepository.save(user);
    }

    @Override
    public boolean deleteUser(String userId) {
        return myUserRepository.existsById(userId);
    }
}
