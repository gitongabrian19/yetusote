package com.funshine.yetusote.services;


import com.funshine.yetusote.entity.Member;
import com.funshine.yetusote.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;

    MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    @Override
    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    @Override
    public Optional<Member> findById(String id) {
        return memberRepository.findById(id);
    }

    @Override
    public Member updateMember(String id, Member member) {
        Member memberExist = memberRepository.findById(id).orElseThrow(() -> new RuntimeException("Member not found"));
        memberExist.setEmail(member.getEmail());
        memberExist.setPassword(member.getPassword());
        memberExist.setPhone(member.getPhone());
        memberExist.setMembershipType(member.getMembershipType());
        memberExist.setFirstName(member.getFirstName());
        memberExist.setLastName(member.getLastName());
        memberExist.setNationalId(member.getNationalId());
        memberExist.setOutstandingLoan(member.getOutstandingLoan());
        memberExist.setPenalties(member.getPenalties());
        memberExist.setTotalShares(member.getTotalShares());
        return memberRepository.save(memberExist);
    }

    @Override
    public void deleteMember(String id) {
        memberRepository.deleteById(id);
    }
}
