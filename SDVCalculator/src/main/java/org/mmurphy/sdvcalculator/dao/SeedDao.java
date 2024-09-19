package org.mmurphy.sdvcalculator.dao;
import org.mmurphy.sdvcalculator.model.Seed;

import java.util.List;

public interface SeedDao {
    List<Seed> getAllSeeds();
    List<Seed> getAllSeedsBySeason(String season);
    Seed getSeedByName(String name);
    Seed getSeedByLocation(String location);
    Seed getSeedByCropAndLocation(String cropName, String location, boolean isOutOfSeason);
    Seed getSeedByCropId(int cropId);

}

