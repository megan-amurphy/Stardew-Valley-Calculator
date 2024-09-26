/*
package org.mmurphy.sdvcalculator.controllerTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mmurphy.sdvcalculator.controller.CalculatorController;
import org.mmurphy.sdvcalculator.model.Crop;
import org.mmurphy.sdvcalculator.model.Seed;
import org.mmurphy.sdvcalculator.service.CalculatorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CalculatorControllerTests {

    private CalculatorController calculatorController;
    private CalculatorService mockCalculatorService;

    @BeforeEach
    public void setUp() {
        // Arrange
        mockCalculatorService = mock(CalculatorService.class);
        calculatorController = new CalculatorController(mockCalculatorService);
    }

    // Happy Path: Valid input and successful calculation
    @Test
    public void testCalculateMultipleCrops_Success() {
        // Arrange
        List<Crop> crops = Arrays.asList(new Crop(1, "Parsnip", "Spring", "Normal", 100));
        List<Seed> seeds = Arrays.asList(new Seed(1, "Parsnip Seeds", "Pierre's General Store", 20, 30, "Parsnip", false));
        List<Integer> cropQuantities = Arrays.asList(10);
        List<Integer> seedQuantities = Arrays.asList(5);

        List<String> mockResults = Arrays.asList("Revenue: $1000, Net Profit: $800");
        when(mockCalculatorService.calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities)).thenReturn(mockResults);

        // Act
        ResponseEntity<?> response = calculatorController.calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResults, response.getBody());
        verify(mockCalculatorService, times(1)).calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);
    }

    // Unhappy Path: Mismatched list sizes (crops, seeds, or quantities)
    @Test
    public void testCalculateMultipleCrops_ListSizeMismatch() {
        // Arrange
        List<Crop> crops = Arrays.asList(new Crop(1, "Parsnip", "Spring", "Normal", 100));
        List<Seed> seeds = Arrays.asList(new Seed(1, "Parsnip Seeds", "Pierre's General Store", 20, 30, "Parsnip", false));
        List<Integer> cropQuantities = Arrays.asList(10);
        List<Integer> seedQuantities = Arrays.asList(5, 2);  // Mismatch in size

        // Act
        ResponseEntity<?> response = calculatorController.calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The number of crops, seeds, and quantities must match.", response.getBody());
        verify(mockCalculatorService, times(0)).calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);
    }

    // Unhappy Path: Calculator service throws IllegalArgumentException
    @Test
    public void testCalculateMultipleCrops_ServiceThrowsException() {
        // Arrange
        List<Crop> crops = Arrays.asList(new Crop(1, "Parsnip", "Spring", "Normal", 100));
        List<Seed> seeds = Arrays.asList(new Seed(1, "Parsnip Seeds", "Pierre's General Store", 20, 30, "Parsnip", false));
        List<Integer> cropQuantities = Arrays.asList(10);
        List<Integer> seedQuantities = Arrays.asList(5);

        when(mockCalculatorService.calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities))
                .thenThrow(new IllegalArgumentException("Invalid input for calculation"));

        // Act
        ResponseEntity<?> response = calculatorController.calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid input for calculation", response.getBody());
        verify(mockCalculatorService, times(1)).calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);
    }

    // Unhappy Path: Empty crop list
    @Test
    public void testCalculateMultipleCrops_EmptyCropList() {
        // Arrange
        List<Crop> crops = Arrays.asList();  // Empty crop list
        List<Seed> seeds = Arrays.asList(new Seed(1, "Parsnip Seeds", "Pierre's General Store", 20, 30, "Parsnip", false));
        List<Integer> cropQuantities = Arrays.asList(10);
        List<Integer> seedQuantities = Arrays.asList(5);

        // Act
        ResponseEntity<?> response = calculatorController.calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("The number of crops, seeds, and quantities must match.", response.getBody());
        verify(mockCalculatorService, times(0)).calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);
    }

    // Happy Path: Empty result from service (handled as a valid response)
    @Test
    public void testCalculateMultipleCrops_EmptyResult() {
        // Arrange
        List<Crop> crops = Arrays.asList(new Crop(1, "Parsnip", "Spring", "Normal", 100));
        List<Seed> seeds = Arrays.asList(new Seed(1, "Parsnip Seeds", "Pierre's General Store", 20, 30, "Parsnip", false));
        List<Integer> cropQuantities = Arrays.asList(10);
        List<Integer> seedQuantities = Arrays.asList(5);

        List<String> mockResults = Arrays.asList();  // Empty result
        when(mockCalculatorService.calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities)).thenReturn(mockResults);

        // Act
        ResponseEntity<?> response = calculatorController.calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockResults, response.getBody());
        verify(mockCalculatorService, times(1)).calculateMultipleCrops(crops, seeds, cropQuantities, seedQuantities);
    }
}*/
