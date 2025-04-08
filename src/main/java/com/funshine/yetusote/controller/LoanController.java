package com.funshine.yetusote.controller;

import com.funshine.yetusote.enums.MembershipType;
import com.funshine.yetusote.enums.Status;
import com.funshine.yetusote.models.Loan;
import com.funshine.yetusote.services.LoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

//@CrossOrigin(origins = "http://localhost:5173")
@Slf4j
@RestController
@RequestMapping("/api/v1/loans")
@Tag(name = "Loan", description = "Loan API's")
public class LoanController {
    private final LoanService loanService;

    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @PostMapping("/apply")
    public ResponseEntity<?> applyForLoan(@RequestBody Loan loan) {
        log.info("Applying for loan:: {}", loan.toString());
        Loan createdLoan = loanService.createLoan(loan);
        if (createdLoan != null) {
            return ResponseEntity.ok(createdLoan);
        } else {
            return ResponseEntity.badRequest().body("Error applying for loan");
        }
    }

    @GetMapping
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }

    @GetMapping("/id/{id}")
    public Optional<Loan> getLoanById(@PathVariable("id") String id) {
        return loanService.getLoanById(id);
    }

    @GetMapping("/idNumber/{idNumber}")
    public ResponseEntity<Loan> findByIdNumber(@PathVariable("idNumber") String idNumber) {
        Loan loan = loanService.findByIdNumber(idNumber);
        if (loan != null) {
            return ResponseEntity.ok(loan);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/pay")
    public ResponseEntity<?> payLoan(@RequestBody Loan loan) {
        try {
            log.info("Paying loan:: {}", loan.toString());
            Loan loan1 = loanService.findByIdNumber(loan.getIdNumber());
            if (loan1 != null) {
                Loan savedLoan = loanService.payLoan(loan, loan1);
                return ResponseEntity.ok(savedLoan);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error paying loan:: ", e);
            return ResponseEntity.badRequest().body("Error Paying Loan: " + e.getMessage());
        }
    }

    @PutMapping("/approve/{id}/{decision}")
    public ResponseEntity<?> approve(@PathVariable("id") String id, @PathVariable("decision") String decision) {
        log.info("Approving loan {} with decision {}", id, decision);
        Optional<Loan> loan = loanService.getLoanById(id);
        if (loan.isPresent()) {
            Boolean approved = ("APPROVE".equals(decision));
            boolean isUpdated = loanService.approve(approved, loan);
            if (isUpdated) {
                log.info("Loan {} has been {}", id, approved ? "approved" : "rejected");
                return ResponseEntity.ok("Loan approved successfully");
            } else {
                log.error("Error approving loan {}", id);
                return ResponseEntity.badRequest().body("Error approving loan");
            }
        } else {
            log.error("Loan {} not found", id);
            return ResponseEntity.badRequest().body("Loan not found");
        }
    }

    @PutMapping("/{id}")
    public Loan updateLoan(@PathVariable("id") String id, @RequestBody Loan loan) {
        log.info("Updating loan {}", loan.toString());
        return loanService.updateLoan(id, loan);

    }

    @DeleteMapping("/{id}")
    public String deleteLoan(@PathVariable String id) {
        loanService.deleteLoan(id);
        return "Loan deleted!";
    }
}

