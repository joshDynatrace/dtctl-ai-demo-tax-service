package com.arcstore.taxservice;

import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class TaxRateService {

    private static final Map<String, Map<String, Double>> TAX_RATES = Map.of(
        "ELECTRONICS", Map.of(
            "CA", 0.0725,
            "NY", 0.08,
            "TX", 0.0625
        ),
        "AUDIO_EQUIPMENT", Map.of(
            "CA", 0.0725,
            "NY", 0.08,
            "TX", 0.0625
        )
    );

    public TaxRateResponse getTaxRate(String taxCode, String state) {
        if (taxCode == null || taxCode.isEmpty()) {
            return new TaxRateResponse(taxCode, state, null);
        }

        Map<String, Double> stateRates = TAX_RATES.get(taxCode);
        if (stateRates == null) {
            return new TaxRateResponse(taxCode, state, null);
        }

        Double rate = stateRates.getOrDefault(state, 0.07);
        return new TaxRateResponse(taxCode, state, rate);
    }
}
