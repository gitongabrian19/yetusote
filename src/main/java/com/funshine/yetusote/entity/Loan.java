package com.funshine.yetusote.entity;

import com.funshine.yetusote.enums.MembershipType;
import com.funshine.yetusote.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "loans")
public class Loan {
    @Id
    private String loanId;
    private List<String> membersId;
    private MembershipType membershipType;
    private double principalAmount;
    private double interestRate;
    private double totalAmount;
    private double repaymentPeriod;
    private double monthlyInstallments;
    private Date repaymentDate;
    private Status status;
    @CreatedDate
    private Date dateIssued;

    /* TODO:- repayLoan/update, createLoan, getLoans, deleteLoans,
    *
    * */
}
