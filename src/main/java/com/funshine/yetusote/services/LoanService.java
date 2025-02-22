package com.funshine.yetusote.services;


import com.funshine.yetusote.entity.Loan;
import com.funshine.yetusote.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class LoanService {
    private final LoanRepository loanRepository;

    public LoanService(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    public List<Optional<Loan>> getLoanById(String id) {
        return Collections.singletonList(loanRepository.findById(id));

    }

    public Loan createLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan updateLoan(String id, Loan loan) {
        //chcek if the loan exists
        Loan loanExist = loanRepository.findById(id).orElseThrow(() -> new RuntimeException("Loan not found"));
        loanExist.setMembersId(loan.getMembersId());
        loanExist.setMembershipType(loan.getMembershipType());
        loanExist.setPrincipalAmount(loan.getPrincipalAmount());
        loanExist.setInterestRate(loan.getInterestRate());
        loanExist.setTotalAmount(loan.getTotalAmount());
        loanExist.setRepaymentPeriod(loan.getRepaymentPeriod());
        loanExist.setMonthlyInstallments(loan.getMonthlyInstallments());
        loanExist.setRepaymentDate(loan.getRepaymentDate());
        loanExist.setStatus(loan.getStatus());
        return loanRepository.save(loanExist);
    }

    public void deleteLoan(String id) {
        loanRepository.deleteById(id);
    }
}

