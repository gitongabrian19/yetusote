package com.funshine.yetusote.models;

import org.springframework.data.annotation.Id;

import java.util.List;

public class Utils {
    @Id
    private String utilsId;
    private List<String> blackListIds;
    private double individualInterest;
    private double groupInterestRate;
}
