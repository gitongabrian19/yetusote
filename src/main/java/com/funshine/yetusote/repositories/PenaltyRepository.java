package com.funshine.yetusote.repositories;

import com.funshine.yetusote.entity.Penalty;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PenaltyRepository extends MongoRepository<Penalty, String> {
    List<Penalty> findByMembersId(List<String> membersId);
}
