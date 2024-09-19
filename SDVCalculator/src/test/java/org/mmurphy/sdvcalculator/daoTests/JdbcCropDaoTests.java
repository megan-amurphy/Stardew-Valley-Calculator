package org.mmurphy.sdvcalculator.daoTests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mmurphy.sdvcalculator.dao.JdbcCropDao;
import org.mmurphy.sdvcalculator.model.Crop;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JdbcCropDaoTests {

    private JdbcCropDao jdbcCropDao;
    private JdbcTemplate mockJdbcTemplate;

    @BeforeEach
    public void setUp() {
        // Arrange
        mockJdbcTemplate = mock(JdbcTemplate.class);
        jdbcCropDao = new JdbcCropDao(mockJdbcTemplate);
    }

    @Test
    public void testGetAllCrops() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true).thenReturn(false);
        when(mockRowSet.getInt("crop_id")).thenReturn(1);
        when(mockRowSet.getString("crop_name")).thenReturn("Parsnip");
        when(mockRowSet.getString("crop_season")).thenReturn("Spring");
        when(mockRowSet.getInt("normal_price")).thenReturn(100);

        // Act
        List<Crop> crops = jdbcCropDao.getAllCrops();

        // Assert
        assertEquals(1, crops.size());
        assertEquals("Parsnip", crops.get(0).getCropName());
        verify(mockJdbcTemplate, times(1)).queryForRowSet(anyString());
    }

    @Test
    public void testGetAllCropsBySeason() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true).thenReturn(false);
        when(mockRowSet.getInt("crop_id")).thenReturn(2);
        when(mockRowSet.getString("crop_name")).thenReturn("Blueberry");
        when(mockRowSet.getString("crop_season")).thenReturn("Summer");
        when(mockRowSet.getInt("normal_price")).thenReturn(150);

        // Act
        List<Crop> crops = jdbcCropDao.getAllCropsBySeason("Summer");

        // Assert
        assertEquals(1, crops.size());
        assertEquals("Blueberry", crops.get(0).getCropName());
        assertEquals("Summer", crops.get(0).getCropSeason());
        verify(mockJdbcTemplate, times(1)).queryForRowSet(anyString(), eq("Summer"));
    }

    @Test
    public void testGetCropByName() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true);
        when(mockRowSet.getInt("crop_id")).thenReturn(1);
        when(mockRowSet.getString("crop_name")).thenReturn("Parsnip");
        when(mockRowSet.getString("crop_season")).thenReturn("Spring");
        when(mockRowSet.getInt("normal_price")).thenReturn(100);

        // Act
        Crop crop = jdbcCropDao.getCropByName("Parsnip");

        // Assert
        assertNotNull(crop);
        assertEquals("Parsnip", crop.getCropName());
        verify(mockJdbcTemplate, times(1)).queryForRowSet(anyString(), eq("Parsnip"));
    }

    @Test
    public void testGetCropByNameAndQuality() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(true);
        when(mockRowSet.getInt("crop_id")).thenReturn(1);
        when(mockRowSet.getString("crop_name")).thenReturn("Parsnip");
        when(mockRowSet.getString("crop_season")).thenReturn("Spring");
        when(mockRowSet.getInt("gold_price")).thenReturn(150);

        // Act
        Crop crop = jdbcCropDao.getCropByNameAndQuality("Parsnip", "gold");

        // Assert
        assertNotNull(crop);
        assertEquals(150, crop.getPrice());
        verify(mockJdbcTemplate, times(1)).queryForRowSet(anyString(), eq("Parsnip"));
    }

    @Test
    public void testGetCropByName_CropNotFound() {
        // Arrange
        SqlRowSet mockRowSet = mock(SqlRowSet.class);
        when(mockJdbcTemplate.queryForRowSet(anyString(), anyString())).thenReturn(mockRowSet);
        when(mockRowSet.next()).thenReturn(false);

        // Act
        Crop crop = jdbcCropDao.getCropByName("NonExistentCrop");

        // Assert
        assertNull(crop);
        verify(mockJdbcTemplate, times(1)).queryForRowSet(anyString(), eq("NonExistentCrop"));
    }
}