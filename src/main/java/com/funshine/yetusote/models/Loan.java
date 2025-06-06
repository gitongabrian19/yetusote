package com.funshine.yetusote.models;

import com.funshine.yetusote.enums.MembershipType;
import com.funshine.yetusote.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "loans")
@Builder
public class Loan {
    @Id
    private String loanId;
    private String idNumber;
    private MembershipType membershipType = MembershipType.INDIVIDUAL;
    private double principalAmount;
    private double interestRate;
    private double totalAmount;
    private double repaymentPeriod;
    private double monthlyInstallments;
    private Date repaymentDate;
    private Status status = Status.PENDING;
    @CreatedDate
    private Date dateIssued;
}
