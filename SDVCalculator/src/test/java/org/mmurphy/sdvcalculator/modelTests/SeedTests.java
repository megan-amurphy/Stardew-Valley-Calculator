package org.mmurphy.sdvcalculator.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mmurphy.sdvcalculator.model.Seed;

import static org.junit.jupiter.api.Assertions.*;

public class SeedTests {

    private Seed seed;

    @BeforeEach
    public void setUp() {
        // Arrange
        seed = new Seed(1, "Parsnip Seeds", "Pierre's General Store", 20, 30, "Parsnip", false);
    }

    // Happy Path: Successfully retrieve the seed ID
    @Test
    public void testGetSeedId() {
        // Act
        int seedId = seed.getSeedId();

        // Assert
        assertEquals(1, seedId);
    }

    // Unhappy Path: Test invalid seed ID
    @Test
    public void testGetSeedId_Invalid() {
        // Act & Assert
        assertNotEquals(999, seed.getSeedId());
    }

    // Happy Path: Successfully set the seed ID
    @Test
    public void testSetSeedId() {
        // Act
        seed.setSeedId(2);

        // Assert
        assertEquals(2, seed.getSeedId());
    }

    // Unhappy Path: Set invalid seed ID
    @Test
    public void testSetSeedId_Invalid() {
        // Act
        seed.setSeedId(-1);  // Invalid seed ID

        // Assert
        assertNotEquals(1, seed.getSeedId());  // Seed ID should be -1 now
    }

    // Happy Path: Successfully retrieve the seed name
    @Test
    public void testGetSeedName() {
        // Act
        String seedName = seed.getSeedName();

        // Assert
        assertEquals("Parsnip Seeds", seedName);
    }

    // Unhappy Path: Test invalid seed name
    @Test
    public void testGetSeedName_Invalid() {
        // Act & Assert
        assertNotEquals("Invalid Seed", seed.getSeedName());
    }

    // Happy Path: Successfully set the seed name
    @Test
    public void testSetSeedName() {
        // Act
        seed.setSeedName("Carrot Seeds");

        // Assert
        assertEquals("Carrot Seeds", seed.getSeedName());
    }

    // Unhappy Path: Set seed name to null or invalid value
    @Test
    public void testSetSeedName_Null() {
        // Act
        seed.setSeedName(null);

        // Assert
        assertNull(seed.getSeedName());
    }

    // Happy Path: Successfully retrieve the purchase location name
    @Test
    public void testGetPurchaseLocationName() {
        // Act
        String purchaseLocationName = seed.getPurchaseLocationName();

        // Assert
        assertEquals("Pierre's General Store", purchaseLocationName);
    }

    // Unhappy Path: Invalid purchase location name
    @Test
    public void testGetPurchaseLocationName_Invalid() {
        // Act & Assert
        assertNotEquals("Invalid Location", seed.getPurchaseLocationName());
    }

    // Happy Path: Successfully set the purchase location name
    @Test
    public void testSetPurchaseLocationName() {
        // Act
        seed.setPurchaseLocationName("JojaMart");

        // Assert
        assertEquals("JojaMart", seed.getPurchaseLocationName());
    }

    // Unhappy Path: Set invalid purchase location name
    @Test
    public void testSetPurchaseLocationName_Null() {
        // Act
        seed.setPurchaseLocationName(null);

        // Assert
        assertNull(seed.getPurchaseLocationName());
    }

    // Happy Path: Successfully retrieve seed price lower
    @Test
    public void testGetSeedPriceLower() {
        // Act
        int seedPriceLower = seed.getSeedPriceLower();

        // Assert
        assertEquals(20, seedPriceLower);
    }

    // Unhappy Path: Invalid seed price lower
    @Test
    public void testGetSeedPriceLower_Invalid() {
        // Act & Assert
        assertNotEquals(0, seed.getSeedPriceLower());
    }

    // Happy Path: Successfully set seed price lower
    @Test
    public void testSetSeedPriceLower() {
        // Act
        seed.setSeedPriceLower(25);

        // Assert
        assertEquals(25, seed.getSeedPriceLower());
    }

    // Unhappy Path: Set invalid seed price lower
    @Test
    public void testSetSeedPriceLower_Invalid() {
        // Act
        seed.setSeedPriceLower(-10);  // Invalid price

        // Assert
        assertNotEquals(20, seed.getSeedPriceLower());
    }

    // Happy Path: Successfully retrieve seed price upper
    @Test
    public void testGetSeedPriceUpper() {
        // Act
        int seedPriceUpper = seed.getSeedPriceUpper();

        // Assert
        assertEquals(30, seedPriceUpper);
    }

    // Unhappy Path: Invalid seed price upper
    @Test
    public void testGetSeedPriceUpper_Invalid() {
        // Act & Assert
        assertNotEquals(0, seed.getSeedPriceUpper());
    }

    // Happy Path: Successfully set seed price upper
    @Test
    public void testSetSeedPriceUpper() {
        // Act
        seed.setSeedPriceUpper(35);

        // Assert
        assertEquals(35, seed.getSeedPriceUpper());
    }

    // Unhappy Path: Set invalid seed price upper
    @Test
    public void testSetSeedPriceUpper_Invalid() {
        // Act
        seed.setSeedPriceUpper(-5);  // Invalid price

        // Assert
        assertNotEquals(30, seed.getSeedPriceUpper());
    }

    // Happy Path: Successfully retrieve itemNonGold
    @Test
    public void testGetItemNonGold() {
        // Act
        String itemNonGold = seed.getItemNonGold();

        // Assert
        assertEquals("Parsnip", itemNonGold);
    }

    // Unhappy Path: Invalid itemNonGold
    @Test
    public void testGetItemNonGold_Invalid() {
        // Act & Assert
        assertNotEquals("InvalidItem", seed.getItemNonGold());
    }

    // Happy Path: Successfully set itemNonGold
    @Test
    public void testSetItemNonGold() {
        // Act
        seed.setItemNonGold("Carrot");

        // Assert
        assertEquals("Carrot", seed.getItemNonGold());
    }

    // Unhappy Path: Set invalid itemNonGold
    @Test
    public void testSetItemNonGold_Null() {
        // Act
        seed.setItemNonGold(null);

        // Assert
        assertNull(seed.getItemNonGold());
    }

    // Happy Path: Successfully retrieve isOutOfSeason flag
    @Test
    public void testIsOutOfSeason() {
        // Act
        boolean isOutOfSeason = seed.isOutOfSeason();

        // Assert
        assertFalse(isOutOfSeason);
    }

    // Unhappy Path: Invalid isOutOfSeason flag
    @Test
    public void testIsOutOfSeason_Invalid() {
        // Act
        assertNotEquals(true, seed.isOutOfSeason());  // The default is false
    }

    // Happy Path: Successfully set isOutOfSeason flag
    @Test
    public void testSetOutOfSeason() {
        // Act
        seed.setOutOfSeason(true);

        // Assert
        assertTrue(seed.isOutOfSeason());
    }

    // Happy Path: Test equality with another identical seed object
    @Test
    public void testEquals() {
        // Arrange
        Seed seed2 = new Seed(1, "Parsnip Seeds", "Pierre's General Store", 20, 30, "Parsnip", false);

        // Act & Assert
        assertEquals(seed, seed2);
    }

    // Unhappy Path: Test inequality with a different seed object
    @Test
    public void testNotEquals() {
        // Arrange
        Seed seed2 = new Seed(2, "Carrot Seeds", "JojaMart", 25, 35, "Carrot", true);

        // Act & Assert
        assertNotEquals(seed, seed2);
    }

    // Happy Path: Test hashCode equality with another identical seed object
    @Test
    public void testHashCode() {
        // Arrange
        Seed seed2 = new Seed(1, "Parsnip Seeds", "Pierre's General Store", 20, 30, "Parsnip", false);

        // Act & Assert
        assertEquals(seed.hashCode(), seed2.hashCode());
    }

    // Unhappy Path: Test hashCode inequality with a different seed object
    @Test
    public void testHashCode_NotEqual() {
        // Arrange
        Seed seed2 = new Seed(2, "Carrot Seeds", "JojaMart", 25, 35, "Carrot", true);

        // Act & Assert
        assertNotEquals(seed.hashCode(), seed2.hashCode());
    }

    // Happy Path: Test toString method
    @Test
    public void testToString() {
        // Act
        String seedString = seed.toString();

        // Assert
        assertEquals("Seed{seedId=1, seedName='Parsnip Seeds', purchaseLocationName='Pierre's General Store', seedPriceLower=20, seedPriceUpper=30, itemNonGold='Parsnip', isOutOfSeason=false}", seedString);
    }

    // Unhappy Path: Invalid toString output (intentionally altered)
    @Test
    public void testToString_Invalid() {
        // Act
        String seedString = seed.toString();

        // Assert
        assertNotEquals("InvalidString", seedString);
    }
}
