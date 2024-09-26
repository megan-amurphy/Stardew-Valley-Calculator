package org.mmurphy.sdvcalculator.controller;

import org.mmurphy.sdvcalculator.dto.CalculateRequest;
import org.mmurphy.sdvcalculator.dto.CalculationResult;
import org.mmurphy.sdvcalculator.model.Crop;
import org.mmurphy.sdvcalculator.model.Seed;
import org.mmurphy.sdvcalculator.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/calculate")
public class CalculatorController {

    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @PostMapping
    public ResponseEntity<List<CalculationResult>> calculateMultipleCrops(
            @RequestBody @Valid CalculateRequest request) {

        try {
            if (request.getCrops().size() != request.getSeeds().size() ||
                    request.getCrops().size() != request.getCropQuantities().size() ||
                    request.getCrops().size() != request.getSeedQuantities().size()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(null);
            }

            // Calculate revenue and net profit for multiple crops
            List<CalculationResult> results = calculatorService.calculateMultipleCrops(
                    request.getCrops(),
                    request.getSeeds(),
                    request.getCropQuantities(),
                    request.getSeedQuantities()
            );

            return ResponseEntity.ok(results);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
