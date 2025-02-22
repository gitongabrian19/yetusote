package com.funshine.yetusote.controller;//package com.funshine.yetusote.controller;

import com.funshine.yetusote.entity.Penalty;
import com.funshine.yetusote.services.PenaltyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/penalties")
public class PenaltyController {
    private final PenaltyService penaltyService;

    public PenaltyController(PenaltyService penaltyService) {
        this.penaltyService = penaltyService;
    }

    // Manually Trigger Penalty Calculation
    @PostMapping("/apply")
    public String applyPenalties() {
        penaltyService.applyPenalties();
        return "Penalties applied!";
    }

    // Get Penalties by Member ID
    @GetMapping("/{memberId}")
    public List<Penalty> getPenalties(@PathVariable List<String> memberId) {
        return penaltyService.getPenaltiesByMember(memberId);
    }
}

