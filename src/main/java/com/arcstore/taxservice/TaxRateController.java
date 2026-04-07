package com.arcstore.taxservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tax-rate")
public class TaxRateController {

    @Autowired
    private TaxRateService taxRateService;

    @GetMapping
    public TaxRateResponse getTaxRate(@RequestParam String taxCode, @RequestParam String state) {
        return taxRateService.getTaxRate(taxCode, state);
    }
}
