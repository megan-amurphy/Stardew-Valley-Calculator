package org.mmurphy.sdvcalculator.service;

import org.mmurphy.sdvcalculator.dao.CropDao;
import org.mmurphy.sdvcalculator.dao.SeedDao;
import org.mmurphy.sdvcalculator.dto.CalculationResult;
import org.mmurphy.sdvcalculator.model.Crop;
import org.mmurphy.sdvcalculator.model.Seed;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CalculatorService {

    private final CropDao cropDao;
    private final SeedDao seedDao;

    public CalculatorService(CropDao cropDao, SeedDao seedDao) {
        this.cropDao = cropDao;
        this.seedDao = seedDao;
    }

    /**
     * Calculate revenue and net profit for multiple crops, including user-provided quantities of crops and seeds.
     *
     * @param crops List of Crop objects representing the crops.
     * @param seeds List of Seed objects representing the seeds.
     * @param cropQuantities List of integers representing the quantity of crops sold.
     * @param seedQuantities List of integers representing the quantity of seeds purchased.
     * @return A list of calculated results (revenue and net profit) for each crop.
     */
    public List<CalculationResult> calculateMultipleCrops(List<Crop> crops, List<Seed> seeds,
                                                          List<Integer> cropQuantities, List<Integer> seedQuantities) {
        List<CalculationResult> results = new ArrayList<>();

        for (int i = 0; i < crops.size(); i++) {
            Crop crop = crops.get(i);
            Seed seed = seeds.get(i);
            int cropQuantity = cropQuantities.get(i);
            int seedQuantity = seedQuantities.get(i);

            // Calculate revenue based on crop quantity
            int revenue = calculateRevenue(crop.getCropName(), crop.getCropQuality(), cropQuantity);

            // Calculate net profit based on seed purchase
            int netProfit = calculateNetProfit(crop.getCropName(), crop.getCropQuality(),
                    cropQuantity, seedQuantity, seed.getPurchaseLocationName(),
                    seed.isOutOfSeason() ? seed.getSeedPriceUpper() : null);

            // Create a CalculationResult object for this crop
            CalculationResult result = new CalculationResult();
            result.setCropName(crop.getCropName());
            result.setRevenue(revenue);
            result.setRevenueDetails("Revenue = price * quantity");
            result.setCost(seed.getSeedPriceLower() * seedQuantity);
            result.setCostDetails("Cost = seed price * quantity");
            result.setNetProfit(netProfit);
            result.setNetProfitDetails("Net Profit = revenue - total seed costs");

            results.add(result);
        }

        return results;
    }

    public int calculateRevenue(String cropName, String quality, int quantity) {
        Crop crop = cropDao.getCropByNameAndQuality(cropName, quality);
        if (crop != null) {
            return crop.getPrice() * quantity;  // Calculate total revenue = price * quantity
        }
        return 0;
    }

    public int calculateNetProfit(String cropName, String quality, int cropQuantity, int seedQuantity,
                                  String purchaseLocation, Integer userProvidedSeedPrice) {
        // Calculate revenue first
        int revenue = calculateRevenue(cropName, quality, cropQuantity);

        // Retrieve seed information based on the purchase location
        Seed seed = seedDao.getSeedByLocation(purchaseLocation);
        if (seed != null) {
            int seedCost;

            // Special handling for the "Traveling Cart" where the user provides the seed price
            if ("Traveling Cart".equalsIgnoreCase(purchaseLocation)) {
                int seedPriceLower = seed.getSeedPriceLower();
                int seedPriceUpper = seed.getSeedPriceUpper();

                // Ensure the user has provided a price and it's within the valid range
                if (userProvidedSeedPrice == null) {
                    throw new IllegalArgumentException("Please provide a seed price for Traveling Cart.");
                }
                if (userProvidedSeedPrice < seedPriceLower || userProvidedSeedPrice > seedPriceUpper) {
                    throw new IllegalArgumentException("The provided seed price is out of range. It must be between " + seedPriceLower + " and " + seedPriceUpper);
                }

                // Use the user-provided seed price for "Traveling Cart"
                seedCost = userProvidedSeedPrice;
            } else {
                // For all other locations, use the pre-determined price from the database
                seedCost = seed.isOutOfSeason() ? seed.getSeedPriceUpper() : seed.getSeedPriceLower();
            }

            // Calculate the total seed cost and the net profit
            int totalSeedCost = seedCost * seedQuantity;  // Total seed cost based on seeds purchased
            return revenue - totalSeedCost;  // Net profit = revenue - seed costs
        }

        return revenue;  // If no seed info is found, return the revenue as the net profit (assuming no seed cost)
    }
}
