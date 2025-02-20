package com.funshine.yetusote.repositories;

import com.funshine.yetusote.entity.Loan;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends MongoRepository<Loan, String> {
    List<Loan> findByMembersId(List<String> membersId);
}
