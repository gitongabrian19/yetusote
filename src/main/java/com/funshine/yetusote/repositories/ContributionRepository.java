package com.funshine.yetusote.repositories;

import com.funshine.yetusote.models.Contribution;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContributionRepository extends MongoRepository<Contribution, String> {
    Optional<Contribution> findByMembersId(String id);
}

