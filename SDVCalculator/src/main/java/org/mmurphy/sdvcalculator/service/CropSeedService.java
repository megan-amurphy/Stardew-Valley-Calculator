package org.mmurphy.sdvcalculator.service;

import org.mmurphy.sdvcalculator.dao.CropDao;
import org.mmurphy.sdvcalculator.dao.SeedDao;
import org.mmurphy.sdvcalculator.model.Seed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class CropSeedService {

    private final CropDao cropDao;
    private final SeedDao seedDao;
    private static final Logger logger = LoggerFactory.getLogger(CropSeedService.class);

    public CropSeedService(CropDao cropDao, SeedDao seedDao) {
        this.cropDao = cropDao;
        this.seedDao = seedDao;
    }

    public Seed getSeedPriceByCropAndQuality(String cropName, String purchaseLocation, Boolean isOutOfSeason) {
        // Logic to fetch the seed by crop name, purchase location, and if it was purchased out of season
        Seed seed = seedDao.getSeedByCropAndLocation(cropName, purchaseLocation, isOutOfSeason);

        if (seed != null) {
            // Adjust seed price based on the isOutOfSeason flag
            if (isOutOfSeason) {
                seed.setPrice(seed.getSeedPriceUpper());
            } else {
                seed.setPrice(seed.getSeedPriceLower());
            }
        }

        return seed;
    }
}