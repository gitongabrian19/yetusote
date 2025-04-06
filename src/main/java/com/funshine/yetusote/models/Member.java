package com.funshine.yetusote.models;

import com.funshine.yetusote.enums.MemberStatus;
import com.funshine.yetusote.enums.MembershipType;
import com.funshine.yetusote.enums.Role;
import com.funshine.yetusote.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "members")
public class Member {
    @Id
    private String memberId;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String email;
    private String phone;
    private MemberStatus status = MemberStatus.ACTIVE;
    private Role role = Role.USER;
    private MembershipType membershipType = MembershipType.INDIVIDUAL;
    private double totalShares;
    private double outstandingLoan;
    private double penalties;
    private String password;
}
