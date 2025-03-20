package com.funshine.yetusote.controller;

import com.funshine.yetusote.entity.Loan;
import com.funshine.yetusote.services.LoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/loans")
@Tag(name ="Loan", description = "Loan API's")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping
    public Loan createLoan(@RequestBody Loan loan) {
        System.out.println("LoanController.createLoan");
        return loanService.createLoan(loan);
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/id/{id}")
    public List<Optional<Loan>> getLoanById(@PathVariable("id") String id) {
        return loanService.getLoanById(id);
    }


    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable("id") String id, @RequestBody Loan loan) {
        return loanService.updateLoan(id, loan);

    }

    @DeleteMapping("/{id}")
    public String deleteLoan(@PathVariable String id) {
        loanService.deleteLoan(id);
        return "Loan deleted!";
    }
}

