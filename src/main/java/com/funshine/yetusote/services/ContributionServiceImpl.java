package com.funshine.yetusote.services;

import com.funshine.yetusote.models.Contribution;
import com.funshine.yetusote.repositories.ContributionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContributionServiceImpl implements ContributionService {
    private final ContributionRepository contributionRepository;

    ContributionServiceImpl(ContributionRepository contributionRepository) {
        this.contributionRepository = contributionRepository;
    }

    @Override
    public Contribution save(Contribution contribution) {
        return contributionRepository.save(contribution);
    }

    @Override
    public List<Contribution> findAll() {
        return contributionRepository.findAll();
    }

    @Override
    public Optional<Contribution> findById(String id) {
        return contributionRepository.findById(id);
    }

    @Override
    public Optional<Contribution> findByMembersId(String id) {
        return contributionRepository.findByMembersId(id);
    }

    @Override
    public Contribution updateContribution(String id, Contribution contribution) {
        Contribution existingContribution = contributionRepository.findById(id).orElseThrow(() -> new RuntimeException("Contribution not found"));
        existingContribution.setAmount(contribution.getAmount());
        existingContribution.setMembersId(contribution.getMembersId());
        existingContribution.setMembershipType(contribution.getMembershipType());
        return existingContribution;
    }

    @Override
    public void deleteById(String id) {
        contributionRepository.deleteById(id);
    }
}
