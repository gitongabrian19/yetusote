package com.funshine.yetusote.entity;

import com.funshine.yetusote.enums.MembershipType;
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
@Document(collection = "contributions")
public class Contribution {
    @Id
    private String contributionId;
    private List<String> membersId;
    private MembershipType membershipType;
    private double amount;
    @CreatedDate
    private Date dateAdded;
}
