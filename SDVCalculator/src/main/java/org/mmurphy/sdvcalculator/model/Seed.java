package org.mmurphy.sdvcalculator.model;
import java.util.Objects;

public class Seed {
    private int seedId;
    private String seedName;
    private String purchaseLocationName;  // From purchase_location table
    private int seedPriceLower;           // From seed_location table
    private int seedPriceUpper;           // From seed_location table
    private String itemNonGold;           // From seed_location table
    private boolean isOutOfSeason;        // From seed_location table

    // Constructors
    public Seed() {}

    public Seed(int seedId, String seedName, String purchaseLocationName, int seedPriceLower, int seedPriceUpper, String itemNonGold, boolean isOutOfSeason) {
        this.seedId = seedId;
        this.seedName = seedName;
        this.purchaseLocationName = purchaseLocationName;
        this.seedPriceLower = seedPriceLower;
        this.seedPriceUpper = seedPriceUpper;
        this.itemNonGold = itemNonGold;
        this.isOutOfSeason = isOutOfSeason;
    }

    // Getters and Setters
    public int getSeedId() {
        return seedId;
    }

    public void setSeedId(int seedId) {
        this.seedId = seedId;
    }

    public String getSeedName() {
        return seedName;
    }

    public void setSeedName(String seedName) {
        this.seedName = seedName;
    }

    public String getPurchaseLocationName() {
        return purchaseLocationName;
    }

    public void setPurchaseLocationName(String purchaseLocationName) {
        this.purchaseLocationName = purchaseLocationName;
    }

    public int getSeedPriceLower() {
        return seedPriceLower;
    }

    public void setSeedPriceLower(int seedPriceLower) {
        this.seedPriceLower = seedPriceLower;
    }

    public int getSeedPriceUpper() {
        return seedPriceUpper;
    }

    public void setSeedPriceUpper(int seedPriceUpper) {
        this.seedPriceUpper = seedPriceUpper;
    }

    public String getItemNonGold() {
        return itemNonGold;
    }

    public void setItemNonGold(String itemNonGold) {
        this.itemNonGold = itemNonGold;
    }

    public boolean isOutOfSeason() {
        return isOutOfSeason;
    }

    public void setOutOfSeason(boolean outOfSeason) {
        isOutOfSeason = outOfSeason;
    }

    // hashCode, equals, and toString methods for easier debugging and logging
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seed seed = (Seed) o;
        return seedId == seed.seedId &&
                seedPriceLower == seed.seedPriceLower &&
                seedPriceUpper == seed.seedPriceUpper &&
                isOutOfSeason == seed.isOutOfSeason &&
                Objects.equals(seedName, seed.seedName) &&
                Objects.equals(purchaseLocationName, seed.purchaseLocationName) &&
                Objects.equals(itemNonGold, seed.itemNonGold);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seedId, seedName, purchaseLocationName, seedPriceLower, seedPriceUpper, itemNonGold, isOutOfSeason);
    }

    @Override
    public String toString() {
        return "Seed{" +
                "seedId=" + seedId +
                ", seedName='" + seedName + '\'' +
                ", purchaseLocationName='" + purchaseLocationName + '\'' +
                ", seedPriceLower=" + seedPriceLower +
                ", seedPriceUpper=" + seedPriceUpper +
                ", itemNonGold='" + itemNonGold + '\'' +
                ", isOutOfSeason=" + isOutOfSeason +
                '}';
    }

    public void setPrice(int seedPriceUpper) {

    }
}
