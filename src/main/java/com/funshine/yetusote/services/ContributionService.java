package com.funshine.yetusote.services;

import com.funshine.yetusote.entity.Contribution;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;
import java.util.Optional;

public interface ContributionService {
    Contribution save(Contribution contribution);

    List<Contribution> findAll();

    Optional<Contribution> findById(String id);

    Contribution updateContribution(String id, Contribution contribution);

    void deleteById(String id);
}
