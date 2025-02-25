package com.funshine.yetusote.services;

import com.funshine.yetusote.LoginRequest;
import com.funshine.yetusote.entity.MyUser;

import java.util.List;
import java.util.Optional;

public interface MyUserService {
    public Optional<MyUser> authenticateUser(LoginRequest loginRequest);
    public MyUser createUser(MyUser user);
    public List<MyUser> getUsers();
    public MyUser getUserById(String userId);
    public MyUser updateUser(MyUser user);
    public boolean deleteUser(String userId);
}
