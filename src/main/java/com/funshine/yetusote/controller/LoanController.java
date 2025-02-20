package com.funshine.yetusote.controller;

import com.funshine.yetusote.entity.Loan;
import com.funshine.yetusote.services.LoanService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/loans")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/{id}")
    public Optional<Loan> getLoanById(@PathVariable String id) {
        return loanService.getLoanById(id);
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        return loanService.createLoan(loan);
    }

    @DeleteMapping("/{id}")
    public String deleteLoan(@PathVariable String id) {
        loanService.deleteLoan(id);
        return "Loan deleted!";
    }
}

