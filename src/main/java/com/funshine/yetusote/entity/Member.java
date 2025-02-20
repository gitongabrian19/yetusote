package com.funshine.yetusote.entity;

import com.funshine.yetusote.enums.MembershipType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collation = "members")
public class Member {
    @Id
    private String memberId;
    private String nationalId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private MembershipType membershipType;
    private double totalShares;
    private double outstandingLoan;
    private double penalties;
    private String password;
}
