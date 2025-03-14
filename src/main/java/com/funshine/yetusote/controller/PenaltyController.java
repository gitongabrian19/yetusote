package com.funshine.yetusote.controller;//package com.funshine.yetusote.controller;

import com.funshine.yetusote.entity.Penalty;
import com.funshine.yetusote.services.PenaltyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/penalties")
@Tag(name ="Penalty", description = "Penalty API's")
public class PenaltyController {
    private final PenaltyService penaltyService;

    public PenaltyController(PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }

    // Manually Trigger Penalty Calculation
    @PostMapping("/apply")
    public List<Penalty> applyPenalties() {
        return penaltyService.applyPenalties();

    }

    // Get Penalties by Member ID
    @GetMapping("/{memberId}")
    public List<Penalty> getPenalties(@PathVariable List<String> memberId) {
        return penaltyService.getPenaltiesByMember(memberId);
    }

    // Get All Penalties
    @PutMapping("/{id}")
    public Penalty updatePenalty(@PathVariable("id") String id, @RequestBody Penalty penalty) {
        return penaltyService.updatePenalty(id, penalty);

    }

    // Delete Penalty
    @DeleteMapping("/{id}")
    public String deletePenalty(@PathVariable String id) {
        penaltyService.deletePenalty(id);
        return "Penalty deleted!";
    }
}

