package com.funshine.yetusote.controller;

import com.funshine.yetusote.models.Contribution;
import com.funshine.yetusote.services.ContributionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/contribution")
@Tag(name = "Contribution", description = "Contributions API's")
public class ContributionController {
    private final ContributionService contributionService;

    public ContributionController(ContributionService contributionService) {
        this.contributionService = contributionService;
    }

    @PostMapping
    public Contribution save(@RequestBody Contribution contribution) {
        return contributionService.save(contribution);
    }

    @GetMapping
    public List<Contribution> findAll() {
        return contributionService.findAll();
    }

    @GetMapping("/id/{id}")
    public Optional<Contribution> findById(@PathVariable("id") String id) {
        return contributionService.findById(id);
    }

    @GetMapping("/member/{id}")
    public ResponseEntity<?> findByMembersId(@PathVariable("id") String id) {
        Optional<Contribution> contribution = contributionService.findByMembersId(id);
        if (contribution.isPresent()) {
            return ResponseEntity.ok(contribution);
        } else {
            return ResponseEntity.status(404).body("Contribution with member id " + id + " not found");
        }
    }

    @PutMapping("/{id}")
    public Contribution update(@PathVariable("id") String id, @RequestBody Contribution contribution) {
        return contributionService.updateContribution(id, contribution);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        contributionService.deleteById(id);
        return "Contribution Deleted!";
    }
}
