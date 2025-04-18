package com.funshine.yetusote.models;

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
@Document(collection = "penalties")
public class Penalty {
    @Id
    private String penaltyId;
    private String membersId;
    private double penaltyAmount;
    @CreatedDate
    private Date dateIssued;
}
