package com.funshine.yetusote.repositories;

import com.funshine.yetusote.models.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
    Optional<Member> findByIdNumber(String idNumber);

    Optional<Member> findByEmail(String email);
}
