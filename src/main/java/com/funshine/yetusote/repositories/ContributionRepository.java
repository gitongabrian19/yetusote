package com.funshine.yetusote.repositories;

import com.funshine.yetusote.entity.Contribution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContributionRepository extends MongoRepository<Contribution, String> {
    List<Contribution> findByMembersId(List<String> memberId);
}

