package org.mmurphy.sdvcalculator.modelTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mmurphy.sdvcalculator.model.Crop;

import static org.junit.jupiter.api.Assertions.*;

public class CropTests {
    private Crop crop;

        @BeforeEach
        public void setUp() {
            // Arrange
            crop = new Crop(1, "Parsnip", "Spring", "Normal", 100);
        }

        @Test
        public void testGetCropId() {
            // Act
            int cropId = crop.getCropId();

            // Assert
            assertEquals(1, cropId);
        }

        @Test
        public void testSetCropId() {
            // Act
            crop.setCropId(2);

            // Assert
            assertEquals(2, crop.getCropId());
        }

        @Test
        public void testGetCropName() {
            // Act
            String cropName = crop.getCropName();

            // Assert
            assertEquals("Parsnip", cropName);
        }

        @Test
        public void testSetCropName() {
            // Act
            crop.setCropName("Carrot");

            // Assert
            assertEquals("Carrot", crop.getCropName());
        }

        @Test
        public void testGetCropSeason() {
            // Act
            String cropSeason = crop.getCropSeason();

            // Assert
            assertEquals("Spring", cropSeason);
        }

        @Test
        public void testSetCropSeason() {
            // Act
            crop.setCropSeason("Summer");

            // Assert
            assertEquals("Summer", crop.getCropSeason());
        }

        @Test
        public void testGetCropQuality() {
            // Act
            String cropQuality = crop.getCropQuality();

            // Assert
            assertEquals("Normal", cropQuality);
        }

        @Test
        public void testSetCropQuality() {
            // Act
            crop.setCropQuality("Gold");

            // Assert
            assertEquals("Gold", crop.getCropQuality());
        }

        @Test
        public void testGetPrice() {
            // Act
            int price = crop.getPrice();

            // Assert
            assertEquals(100, price);
        }

        @Test
        public void testSetPrice() {
            // Act
            crop.setPrice(150);

            // Assert
            assertEquals(150, crop.getPrice());
        }

        @Test
        public void testEquals() {
            // Arrange
            Crop crop2 = new Crop(1, "Parsnip", "Spring", "Normal", 100);

            // Act & Assert
            assertEquals(crop, crop2);
        }

        @Test
        public void testNotEquals() {
            // Arrange
            Crop crop2 = new Crop(2, "Carrot", "Summer", "Gold", 200);

            // Act & Assert
            assertNotEquals(crop, crop2);
        }

        @Test
        public void testHashCode() {
            // Arrange
            Crop crop2 = new Crop(1, "Parsnip", "Spring", "Normal", 100);

            // Act & Assert
            assertEquals(crop.hashCode(), crop2.hashCode());
        }

        @Test
        public void testToString() {
            // Act
            String cropString = crop.toString();

            // Assert
            assertEquals("Crop{cropId=1, cropName='Parsnip', cropSeason='Spring', cropQuality='Normal', price=100}", cropString);
        }
}
