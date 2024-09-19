package org.mmurphy.sdvcalculator.daoTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mmurphy.sdvcalculator.dao.JdbcSeedDao;
import org.mmurphy.sdvcalculator.model.Seed;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JdbcSeedDaoTests {

    private JdbcSeedDao jdbcSeedDao;
    private JdbcTemplate mockJdbcTemplate;

    @BeforeEach
    public void setUp() {
        // Arrange
        mockJdbcTemplate = mock(JdbcTemplate.class);
        jdbcSeedDao = new JdbcSeedDao(mockJdbcTemplate);
    }

    // Happy Path: Successful retrieval of all seeds
    @Test
    public void testGetAllSeeds() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true).thenReturn(false);
        when(mockRowSet.getInt("seed_id")).thenReturn(1);
        when(mockRowSet.getString("seed_name")).thenReturn("Parsnip Seeds");

        // Act
        List<Seed> seeds = jdbcSeedDao.getAllSeeds();

        // Assert
        assertEquals(1, seeds.size());
        assertEquals("Parsnip Seeds", seeds.get(0).getSeedName());
    }

    // Unhappy Path: No seeds found
    @Test
    public void testGetAllSeeds_NoSeedsFound() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(false);  // No data found

        // Act
        List<Seed> seeds = jdbcSeedDao.getAllSeeds();

        // Assert
        assertTrue(seeds.isEmpty());
    }

    // Happy Path: Successful retrieval of seeds by season
    @Test
    public void testGetAllSeedsBySeason() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true).thenReturn(false);
        when(mockRowSet.getInt("seed_id")).thenReturn(2);
        when(mockRowSet.getString("seed_name")).thenReturn("Blueberry Seeds");

        // Act
        List<Seed> seeds = jdbcSeedDao.getAllSeedsBySeason("Summer");

        // Assert
        assertEquals(1, seeds.size());
        assertEquals("Blueberry Seeds", seeds.get(0).getSeedName());
    }

    // Unhappy Path: No seeds found for the season
    @Test
    public void testGetAllSeedsBySeason_NoSeedsFound() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(false);  // No data found

        // Act
        List<Seed> seeds = jdbcSeedDao.getAllSeedsBySeason("Winter");

        // Assert
        assertTrue(seeds.isEmpty());
    }

    // Happy Path: Successful retrieval of seed by name
    @Test
    public void testGetSeedByName() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true);
        when(mockRowSet.getInt("seed_id")).thenReturn(3);
        when(mockRowSet.getString("seed_name")).thenReturn("Cauliflower Seeds");

        // Act
        Seed seed = jdbcSeedDao.getSeedByName("Cauliflower Seeds");

        // Assert
        assertNotNull(seed);
        assertEquals("Cauliflower Seeds", seed.getSeedName());
    }

    // Unhappy Path: Seed not found by name
    @Test
    public void testGetSeedByName_SeedNotFound() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(false);  // No data found

        // Act
        Seed seed = jdbcSeedDao.getSeedByName("NonExistentSeed");

        // Assert
        assertNull(seed);
    }

    // Happy Path: Successful retrieval of seed by location
    @Test
    public void testGetSeedByLocation() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true);
        when(mockRowSet.getInt("seed_id")).thenReturn(4);
        when(mockRowSet.getString("seed_name")).thenReturn("Pumpkin Seeds");

        // Act
        Seed seed = jdbcSeedDao.getSeedByLocation("JojaMart");

        // Assert
        assertNotNull(seed);
        assertEquals("Pumpkin Seeds", seed.getSeedName());
    }

    // Unhappy Path: Seed not found by location
    @Test
    public void testGetSeedByLocation_SeedNotFound() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(false);  // No data found

        // Act
        Seed seed = jdbcSeedDao.getSeedByLocation("NonExistentLocation");

        // Assert
        assertNull(seed);
    }

    // Happy Path: Successful retrieval of seed by crop and location
    @Test
    public void testGetSeedByCropAndLocation() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString(), anyBoolean(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true);
        when(mockRowSet.getInt("seed_id")).thenReturn(5);
        when(mockRowSet.getString("seed_name")).thenReturn("Wheat Seeds");

        // Act
        Seed seed = jdbcSeedDao.getSeedByCropAndLocation("Wheat", "Pierre's General Store", false);

        // Assert
        assertNotNull(seed);
        assertEquals("Wheat Seeds", seed.getSeedName());
    }

    // Unhappy Path: Seed not found by crop and location
    @Test
    public void testGetSeedByCropAndLocation_SeedNotFound() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString(), anyBoolean(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(false);  // No data found

        // Act
        Seed seed = jdbcSeedDao.getSeedByCropAndLocation("NonExistentCrop", "NonExistentLocation", false);

        // Assert
        assertNull(seed);
    }

    // Happy Path: Successful retrieval of seed by crop ID
    @Test
    public void testGetSeedByCropId() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyInt())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true);
        when(mockRowSet.getInt("seed_id")).thenReturn(6);
        when(mockRowSet.getString("seed_name")).thenReturn("Radish Seeds");

        // Act
        Seed seed = jdbcSeedDao.getSeedByCropId(6);

        // Assert
        assertNotNull(seed);
        assertEquals("Radish Seeds", seed.getSeedName());
    }

    // Unhappy Path: Seed not found by crop ID
    @Test
    public void testGetSeedByCropId_SeedNotFound() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyInt())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(false);  // No data found

        // Act
        Seed seed = jdbcSeedDao.getSeedByCropId(999);  // Invalid crop ID

        // Assert
        assertNull(seed);
    }
}
