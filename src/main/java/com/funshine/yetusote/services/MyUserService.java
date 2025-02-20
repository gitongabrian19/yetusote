package com.funshine.yetusote.services;

import com.funshine.yetusote.entity.MyUser;

import java.util.List;

public interface MyUserService {
    public MyUser createUser(MyUser user);
    public List<MyUser> getUsers();
    public MyUser getUserById(String userId);
    public MyUser updateUser(MyUser user);
    public boolean deleteUser(String userId);
}
