package com.funshine.yetusote.services;

import com.funshine.yetusote.models.Member;
import com.funshine.yetusote.requests.LoginRequest;

import java.util.List;
import java.util.Optional;

public interface MemberService {
    public Optional<Member> authenticateUser(LoginRequest loginRequest);

    Member createMember(Member member);

    List<Member> findAll();

    Optional<Member> findById(String id);

    Member updateMember(String id, Member member);

    void deleteMember(String id);
}
