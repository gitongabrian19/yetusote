package com.funshine.yetusote.repositories;

import com.funshine.yetusote.models.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.OptionalInt;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {
    Loan findByIdNumber(String idNumber);
}
