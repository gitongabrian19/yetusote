package com.funshine.yetusote.services;

import com.funshine.yetusote.repositories.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FinancialReportService {

    @Autowired
    private MyUserRepository myUserRepository;

    public byte[] generateFinancialReport() {

        return new byte[0];
    }
}
