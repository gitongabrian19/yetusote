package com.funshine.yetusote.services;

import com.funshine.yetusote.entity.Loan;
import com.funshine.yetusote.entity.Penalty;
import com.funshine.yetusote.repositories.LoanRepository;
import com.funshine.yetusote.repositories.PenaltyRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PenaltyService {
    private final LoanRepository loanRepository;
    private final PenaltyRepository penaltyRepository;

    public PenaltyService(LoanRepository loanRepository, PenaltyRepository penaltyRepository) {
        this.loanRepository = loanRepository;
        this.penaltyRepository = penaltyRepository;
    }

    // Check and Apply Penalties
    public void applyPenalties() {
        List<Loan> overdueLoans = loanRepository.findAll(); // Get all loans

        for (Loan loan : overdueLoans) {
            // If repayment date has passed
            if (loan.getRepaymentDate().before(new Date())) {
                double penaltyAmount = loan.getMonthlyInstallments() * 0.10; // 10% penalty

                // Create Penalty Entry
                Penalty penalty = new Penalty();
                penalty.setMembersId(loan.getMembersId());
                penalty.setPenaltyAmount(penaltyAmount);
                penalty.setDateIssued(new Date());

                penaltyRepository.save(penalty);
            }
        }
    }

    // Get Penalties for a Specific Member
    public List<Penalty> getPenaltiesByMember(List<String> membersId) {
        return penaltyRepository.findByMembersId(membersId);
    }
}
