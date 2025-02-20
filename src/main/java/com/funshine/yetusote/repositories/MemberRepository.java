package com.funshine.yetusote.repositories;

import com.funshine.yetusote.entity.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByNationalId(String nationalId);
}
