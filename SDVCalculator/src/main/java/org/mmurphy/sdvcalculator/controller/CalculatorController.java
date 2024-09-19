package org.mmurphy.sdvcalculator.controller;

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

    /**
     * Handles the POST request to calculate revenue and net profit for multiple crops and seeds.
     *
     * @param crops List of Crop objects representing the crops.
     * @param seeds List of Seed objects representing the seeds.
     * @param cropQuantities List of integers representing the quantity of each crop.
     * @param seedQuantities List of integers representing the quantity of seeds purchased for each crop.
     * @return ResponseEntity containing calculated revenue and net profit for each crop.
     */
    @PostMapping
    public ResponseEntity<?> calculateMultipleCrops(
            @RequestBody @Valid List<Crop> crops,
            @RequestBody @Valid List<Seed> seeds,
            @RequestBody List<Integer> cropQuantities,
            @RequestBody List<Integer> seedQuantities) {

        try {
            // Validate that the sizes of the lists match
            if (crops.size() != seeds.size() || crops.size() != cropQuantities.size() || crops.size() != seedQuantities.size()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("The number of crops, seeds, and quantities must match.");
            }

            // Calculate revenue and net profit for multiple crops
            List<String> results = calculatorService.calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);

            return ResponseEntity.ok(results);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
