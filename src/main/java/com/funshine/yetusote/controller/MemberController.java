package com.funshine.yetusote.controller;

import com.funshine.yetusote.models.Member;
import com.funshine.yetusote.services.MemberService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/members")
@Tag(name = "Members", description = "Members API's")
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @PostMapping
    public Member createMember(@RequestBody Member member) {
        return memberService.createMember(member);
    }

    @GetMapping
    public List<Member> findAll() {
        return memberService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Member> findById(@PathVariable("id") String id) {
        return memberService.findById(id);
    }

    @PutMapping("/{id}")
    public Member updateMember(@PathVariable("id") String id, @RequestBody Member member) {
        return memberService.updateMember(id, member);
    }

    @DeleteMapping("/{id}")
    public String deleteMember(@PathVariable String id) {
        memberService.deleteMember(id);
        return "Member deleted!";
    }
}
