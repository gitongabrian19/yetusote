package com.funshine.yetusote.controller;//package com.funshine.yetusote.controller;


import com.funshine.yetusote.entity.Dividend;
import com.funshine.yetusote.services.DividendService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/v1/dividends")
@Tag(name ="Dividend", description = "Dividend API's")
public class DividendController {
    private final DividendService dividendService;

    public DividendController(DividendService dividendService) {
        this.dividendService = dividendService;
    }

    // Manually Trigger Dividend Distribution
    @PostMapping("/distribute")
    public String distributeDividends(@RequestParam double totalFunds) {
        dividendService.distributeDividends(totalFunds);
        return "Dividends distributed!";
    }

    // Get Dividends by Member ID
    @GetMapping("/{memberId}")
    public List<Dividend> getDividends(@PathVariable String memberId) {
        return dividendService.getDividendsByMember(memberId);
    }
}
