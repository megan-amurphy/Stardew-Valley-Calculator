package org.mmurphy.sdvcalculator.controller;

import org.mmurphy.sdvcalculator.dao.CropDao;
import org.mmurphy.sdvcalculator.model.Crop;
import org.mmurphy.sdvcalculator.model.Seed;
import org.mmurphy.sdvcalculator.service.CropSeedService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/crops")
public class CropController {

    private final CropSeedService cropSeedService;
    private final CropDao cropDao;  // Inject CropDao here

    public CropController(CropSeedService cropSeedService, CropDao cropDao) {
        this.cropSeedService = cropSeedService;
        this.cropDao = cropDao;
    }

    // Get all crops from the CropDao
    @GetMapping
    public ResponseEntity<List<Crop>> getAllCrops() {
        List<Crop> crops = cropDao.getAllCrops();
        if (crops != null && !crops.isEmpty()) {
            return ResponseEntity.ok(crops);
        } else {
            return ResponseEntity.noContent().build();  // Return 204 No Content if the list is empty
        }
    }

    // Get seed by crop name and quality
    @GetMapping("/seed")
    public ResponseEntity<Seed> getSeedByCropAndLocation(
            @RequestParam @NotEmpty String cropName,
            @RequestParam @NotEmpty String purchaseLocation,
            @RequestParam @NotNull Boolean isOutOfSeason) {
        try {
            Seed seed = cropSeedService.getSeedPriceByCropAndQuality(cropName, purchaseLocation, isOutOfSeason);
            if (seed != null) {
                return ResponseEntity.ok(seed);
            } else {
                return ResponseEntity.notFound().build();  // Return 404 Not Found if no seed is found
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);  // Return 400 Bad Request if invalid input
        }
    }
}
