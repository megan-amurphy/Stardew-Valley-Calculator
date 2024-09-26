package org.mmurphy.sdvcalculator.dto;
import org.mmurphy.sdvcalculator.model.Crop;
import org.mmurphy.sdvcalculator.model.Seed;

import java.util.List;

public class CalculateRequest {private List<Crop> crops;
    private List<Seed> seeds;
    private List<Integer> cropQuantities;
    private List<Integer> seedQuantities;

    // Getters and Setters
    public List<Crop> getCrops() {
        return crops;
    }

    public void setCrops(List<Crop> crops) {
        this.crops = crops;
    }

    public List<Seed> getSeeds() {
        return seeds;
    }

    public void setSeeds(List<Seed> seeds) {
        this.seeds = seeds;
    }

    public List<Integer> getCropQuantities() {
        return cropQuantities;
    }

    public void setCropQuantities(List<Integer> cropQuantities) {
        this.cropQuantities = cropQuantities;
    }

    public List<Integer> getSeedQuantities() {
        return seedQuantities;
    }

    public void setSeedQuantities(List<Integer> seedQuantities) {
        this.seedQuantities = seedQuantities;
    }
}

