package com.funshine.yetusote.config;

import com.funshine.yetusote.controller.ContributionController;
import com.funshine.yetusote.controller.DividendController;
import com.funshine.yetusote.controller.LoanController;
import com.funshine.yetusote.controller.MemberController;
import com.funshine.yetusote.enums.MembershipType;
import com.funshine.yetusote.enums.Status;
import com.funshine.yetusote.models.Contribution;
import com.funshine.yetusote.models.Dividend;
import com.funshine.yetusote.models.Loan;
import com.funshine.yetusote.models.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Slf4j
@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final MemberController memberController;
    private final LoanController loanController;
    private final ContributionController contributionController;
    private final DividendController dividendController;
    private List<Member> savedMembers = new ArrayList<>();

    @Autowired
    public DatabaseInitializer(
            MemberController memberController,
            LoanController loanController,
            ContributionController contributionController,
            DividendController dividendController) {
        this.memberController = memberController;
        this.loanController = loanController;
        this.contributionController = contributionController;
        this.dividendController = dividendController;
    }

    @Override
    public void run(String... args) {
        log.info("Initializing database with sample data...");

        // Create members first
        createMembers();

        // Create loans, contributions, and dividends
        createLoans();
        createContributions();
        createDividends();

        log.info("Database initialization completed successfully!");
    }

    private void createMembers() {
        for (int i = 1; i <= 10; i++) {
            Member member = new Member();
            member.setFirstName("User" + i);
            member.setLastName("Test" + i);
            member.setIdNumber("ID" + (100000 + i));
            member.setEmail("user" + i + "@example.com");
            member.setPhone("0712345" + String.format("%03d", i));
            member.setPassword("password" + i);
            member.setMembershipType(MembershipType.INDIVIDUAL);
            member.setTotalShares(1000.0 * i);
            member.setOutstandingLoan(0.0);
            member.setPenalties(0.0);

            Member savedMember = memberController.createMember(member);
            savedMembers.add(savedMember);
            log.info("Created member: {} {}", savedMember.getFirstName(), savedMember.getLastName());
        }
    }

    private void createLoans() {
        for (int i = 0; i < 10; i++) {
            Member member = savedMembers.get(i % savedMembers.size());

            Loan loan = new Loan();
            loan.setIdNumber(member.getIdNumber());
            loan.setMembershipType(member.getMembershipType());
            loan.setPrincipalAmount(10000.0 + (i * 5000.0));
            loan.setInterestRate(10.0 + (i % 5));

            double totalAmount = loan.getPrincipalAmount() * (1 + (loan.getInterestRate() / 100));
            loan.setTotalAmount(totalAmount);

            int repaymentPeriod = 6 + (i % 7);
            loan.setRepaymentPeriod(repaymentPeriod);
            loan.setMonthlyInstallments(totalAmount / repaymentPeriod);

            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, repaymentPeriod);
            loan.setRepaymentDate(calendar.getTime());
            loan.setStatus(i % 2 == 0 ? Status.PAID : Status.NOT_PAID);

            loanController.createLoan(loan);

            // Update member's outstanding loan
            member.setOutstandingLoan(member.getOutstandingLoan() + totalAmount);
            memberController.updateMember(member.getMemberId(), member);
        }
    }

    private void createContributions() {
        for (int i = 0; i < 10; i++) {
            Member member = savedMembers.get(i % savedMembers.size());

            Contribution contribution = new Contribution();
            contribution.setMembersId(member.getMemberId());
            contribution.setMembershipType(member.getMembershipType());
            contribution.setAmount(2000.0 + (i * 1000.0));
            contribution.setDateAdded(new Date());

            contributionController.save(contribution);
        }
    }

    private void createDividends() {
        for (int i = 0; i < 10; i++) {
            Member member = savedMembers.get(i % savedMembers.size());

            Dividend dividend = new Dividend();
            dividend.setMemberId(member.getMemberId());
            dividend.setDividendAmount(5000.0 + (i * 500.0));
            dividend.setDateIssued(new Date());

            // Since DividendController doesn't have a direct method to save a dividend,
            // we'll use the distribute method which will create dividends in bulk
            if (i == 0) {
                dividendController.distributeDividends(50000.0);
            }
        }
    }
}