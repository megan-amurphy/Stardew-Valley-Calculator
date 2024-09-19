package org.mmurphy.sdvcalculator.dao;

import org.mmurphy.sdvcalculator.model.Crop;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository  // Ensures Spring manages this class as a bean
public class JdbcCropDao implements CropDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcCropDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Crop> getAllCrops() {
        List<Crop> crops = new ArrayList<>();
        String sql = "SELECT * FROM crops";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            crops.add(mapRowToCrop(results));
        }
        return crops;
    }

    @Override
    public List<Crop> getAllCropsBySeason(String season) {
        List<Crop> crops = new ArrayList<>();
        String sql = "SELECT * FROM crops WHERE crop_season = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, season);
        while (results.next()) {
            crops.add(mapRowToCrop(results));
        }
        return crops;
    }

    @Override
    public Crop getCropByName(String name) {
        String sql = "SELECT * FROM crops WHERE crop_name = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, name);
        if (result.next()) {
            return mapRowToCrop(result);
        }
        return null;
    }

    @Override
    public Crop getCropByNameAndQuality(String cropName, String quality) {
        String sql = "SELECT * FROM crops WHERE crop_name = ?";
        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, cropName);
        if (result.next()) {
            return mapRowToCrop(result, quality);
        }
        return null;
    }

    private Crop mapRowToCrop(SqlRowSet rowSet, String quality) {
        Crop crop = new Crop();
        crop.setCropId(rowSet.getInt("crop_id"));
        crop.setCropName(rowSet.getString("crop_name"));
        crop.setCropSeason(rowSet.getString("crop_season"));

        switch (quality.toLowerCase()) {
            case "silver":
                crop.setPrice(rowSet.getInt("silver_price"));
                break;
            case "gold":
                crop.setPrice(rowSet.getInt("gold_price"));
                break;
            case "iridium":
                crop.setPrice(rowSet.getInt("iridium_price"));
                break;
            default:
                crop.setPrice(rowSet.getInt("normal_price"));
        }
        return crop;
    }

    private Crop mapRowToCrop(SqlRowSet rowSet) {
        Crop crop = new Crop();
        crop.setCropId(rowSet.getInt("crop_id"));
        crop.setCropName(rowSet.getString("crop_name"));
        crop.setCropSeason(rowSet.getString("crop_season"));
        crop.setPrice(rowSet.getInt("normal_price"));  // Default price for mapping without quality
        return crop;
    }
}
