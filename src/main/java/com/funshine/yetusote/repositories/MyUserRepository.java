package com.funshine.yetusote.repositories;

import com.funshine.yetusote.entity.MyUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends MongoRepository<MyUser, String> {
}
