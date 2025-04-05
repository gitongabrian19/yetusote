package com.funshine.yetusote.response;

import com.funshine.yetusote.enums.MembershipType;
import com.funshine.yetusote.enums.Role;
import lombok.Data;

@Data
public class AuthResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String idNumber;
    private String email;
    private String phone;
    private Role role;
    //specific for member
    private MembershipType membershipType;
    private double totalShares;
    private double outstandingLoan;
    private double penalties;
}
