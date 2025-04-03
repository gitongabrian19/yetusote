package com.funshine.yetusote.services;

import com.funshine.yetusote.entity.Member;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    Member createMember(Member member);

    List<Member> findAll();

    Optional<Member> findById(String id);

    Member updateMember(String id, Member member);

    void deleteMember(String id);
}
