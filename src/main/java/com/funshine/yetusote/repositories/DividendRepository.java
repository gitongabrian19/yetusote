package com.funshine.yetusote.repositories;


import com.funshine.yetusote.models.Dividend;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DividendRepository extends MongoRepository<Dividend, String> {
    List<Dividend> findByMemberId(String memberId);
}

