package com.funshine.yetusote.services;


import com.funshine.yetusote.entity.Dividend;
import com.funshine.yetusote.entity.Member;
import com.funshine.yetusote.repositories.DividendRepository;
import com.funshine.yetusote.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DividendService {
    private final DividendRepository dividendRepository;
    private final MemberRepository memberRepository;

    public DividendService(DividendRepository dividendRepository, MemberRepository memberRepository) {
        this.dividendRepository = dividendRepository;
        this.memberRepository = memberRepository;
    }

    // Distribute Dividends
    public void distributeDividends(double totalFunds) {
        double dividendPool = totalFunds * 0.60; // 60% distributed
        List<Member> members = memberRepository.findAll();
        double totalShares = members.stream().mapToDouble(Member::getTotalShares).sum();

        for (Member member : members) {
            double memberDividend = (member.getTotalShares() / totalShares) * dividendPool;

            Dividend dividend = new Dividend();
            dividend.setMemberId(member.getMemberId());
            dividend.setDividendAmount(memberDividend);
            dividend.setDateIssued(new Date());

            dividendRepository.save(dividend);
        }
    }

    // Get Dividends by Member ID
    public List<Dividend> getDividendsByMember(String memberId) {
        return dividendRepository.findByMemberId(memberId);
    }
}

