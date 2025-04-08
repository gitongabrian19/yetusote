package com.funshine.yetusote.services;


import com.funshine.yetusote.enums.MembershipType;
import com.funshine.yetusote.enums.Status;
import com.funshine.yetusote.models.Loan;
import com.funshine.yetusote.repositories.LoanRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
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

    public Optional<Loan> getLoanById(String id) {
        return loanRepository.findById(id);

    }

    public Loan findByIdNumber(String id) {
        return loanRepository.findByIdNumber(id);
    }

    public boolean approve(Boolean approve, Optional<Loan> loan) {
        if (loan.isPresent()) {
            Loan existingLoan = loan.get();
            if (approve) {
                existingLoan.setStatus(Status.APPROVED);
                if (loan.get().getTotalAmount() > 100) {
                    loanRepository.save(existingLoan);
                } else {
                    deleteLoan(existingLoan.getLoanId());
                }

            } else {
                if (loan.get().getTotalAmount() > 10) {
                    deleteLoan(existingLoan.getLoanId());
                } else {
                    existingLoan.setStatus(Status.NOT_PAID);
                    loanRepository.save(existingLoan);
                }
            }
            return true;
        }
        return false;
    }

    public Loan createLoan(Loan loan) {

        LocalDate currentDate = LocalDate.now(); // Get the current date
        LocalDate repaymentDate = currentDate.plusMonths((long) loan.getRepaymentPeriod()); // Add repayment period in months

        // Convert to Date if needed (for backward compatibility)
        Date repaymentDateAsDate = java.sql.Date.valueOf(repaymentDate);

        Loan loanToBeCreated = Loan.builder()
                .idNumber(loan.getIdNumber())
                .principalAmount(loan.getPrincipalAmount())
                .interestRate(loan.getInterestRate())
                .totalAmount(loan.getTotalAmount())
                .repaymentPeriod(loan.getRepaymentPeriod())
                .monthlyInstallments(loan.getMonthlyInstallments())
                .repaymentDate(repaymentDateAsDate)
                .dateIssued(new Date())
                .build();

        return loanRepository.save(loan);
    }

    public Loan updateLoan(String id, Loan loan) {
        //check if the loan exists
        Loan loanExist = loanRepository.findByIdNumber(id);
        if (loanExist == null) {
            throw new RuntimeException("Loan not found");
        }
        loanExist.setIdNumber(loan.getIdNumber());
        loanExist.setMembershipType(loan.getMembershipType());
        loanExist.setPrincipalAmount(loan.getPrincipalAmount());
        loanExist.setInterestRate(loan.getInterestRate());
        loanExist.setTotalAmount(loan.getTotalAmount());
        loanExist.setRepaymentPeriod(loan.getRepaymentPeriod());
        loanExist.setMonthlyInstallments(loan.getMonthlyInstallments());
        loanExist.setStatus(loan.getStatus());
        return loanRepository.save(loanExist);
    }

    public Loan payLoan(Loan newUpdated, Loan loanExist) {

        loanExist.setTotalAmount(newUpdated.getTotalAmount());
        if (newUpdated.getTotalAmount() <= 1) {
            loanExist.setStatus(Status.PENDING);
        } else {
            loanExist.setStatus(Status.NOT_PAID);
        }
        return loanRepository.save(loanExist);
    }

    ;

    public void deleteLoan(String id) {
        loanRepository.deleteById(id);
    }
}

