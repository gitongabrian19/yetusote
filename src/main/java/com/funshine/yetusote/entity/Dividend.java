package com.funshine.yetusote.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "dividends")
public class Dividend {
    @Id
    private String dividendId;
    private String memberId;
    private double dividendAmount;
    @CreatedDate
    private Date dateIssued;
}
