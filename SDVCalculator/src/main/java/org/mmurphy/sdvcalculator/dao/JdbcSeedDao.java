package org.mmurphy.sdvcalculator.dao;

import org.mmurphy.sdvcalculator.model.Seed;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcSeedDao implements SeedDao {

    private final JdbcTemplate jdbcTemplate;

    public JdbcSeedDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Seed> getAllSeeds() {
        String sql = "SELECT s.seed_id, s.seed_name, pl.purchase_location_name, " +
                "sl.seed_price_lower, sl.seed_price_upper, sl.item_non_gold, sl.is_out_of_season " +
                "FROM seeds s " +
                "JOIN seed_location sl ON s.seed_id = sl.seed_id " +
                "JOIN purchase_location pl ON sl.location_id = pl.purchase_location_id";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        List<Seed> seeds = new ArrayList<>();

        while (results.next()) {
            seeds.add(mapRowToSeed(results));
        }
        return seeds;
    }

    @Override
    public List<Seed> getAllSeedsBySeason(String season) {
        String sql = "SELECT s.seed_id, s.seed_name, pl.purchase_location_name, " +
                "sl.seed_price_lower, sl.seed_price_upper, sl.item_non_gold, sl.is_out_of_season " +
                "FROM seeds s " +
                "JOIN seed_location sl ON s.seed_id = sl.seed_id " +
                "JOIN purchase_location pl ON sl.location_id = pl.purchase_location_id " +
                "JOIN crops c ON s.seed_id = c.crop_id " +
                "WHERE c.crop_season ILIKE ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, "%" + season + "%");
        List<Seed> seeds = new ArrayList<>();

        while (results.next()) {
            seeds.add(mapRowToSeed(results));
        }
        return seeds;
    }

    @Override
    public Seed getSeedByName(String name) {
        String sql = "SELECT s.seed_id, s.seed_name, pl.purchase_location_name, " +
                "sl.seed_price_lower, sl.seed_price_upper, sl.item_non_gold, sl.is_out_of_season " +
                "FROM seeds s " +
                "JOIN seed_location sl ON s.seed_id = sl.seed_id " +
                "JOIN purchase_location pl ON sl.location_id = pl.purchase_location_id " +
                "WHERE s.seed_name ILIKE ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, name);
        if (result.next()) {
            return mapRowToSeed(result);
        }
        return null;
    }

    @Override
    public Seed getSeedByLocation(String location) {
        String sql = "SELECT s.seed_id, s.seed_name, pl.purchase_location_name, " +
                "sl.seed_price_lower, sl.seed_price_upper, sl.item_non_gold, sl.is_out_of_season " +
                "FROM seeds s " +
                "JOIN seed_location sl ON s.seed_id = sl.seed_id " +
                "JOIN purchase_location pl ON sl.location_id = pl.purchase_location_id " +
                "WHERE pl.purchase_location_name ILIKE ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, "%" + location + "%");
        if (result.next()) {
            return mapRowToSeed(result);
        }
        return null;
    }

    @Override
    public Seed getSeedByCropAndLocation(String cropName, String location, boolean isOutOfSeason) {
        // SQL query to fetch seed details based on crop name, quality, location, and whether it's out of season
        String sql = "SELECT s.seed_id, s.seed_name, pl.purchase_location_name, " +
                "sl.seed_price_lower, sl.seed_price_upper, sl.item_non_gold, sl.is_out_of_season " +
                "FROM seeds s " +
                "JOIN crop_seed cs ON s.seed_id = cs.seed_id " +
                "JOIN crops c ON cs.crop_id = c.crop_id " +
                "JOIN seed_location sl ON s.seed_id = sl.seed_id " +
                "JOIN purchase_location pl ON sl.location_id = pl.purchase_location_id " +
                "WHERE c.crop_name ILIKE ? AND sl.is_out_of_season = ? AND pl.purchase_location_name ILIKE ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, "%" + cropName + "%", isOutOfSeason, "%" + location + "%");
        if (result.next()) {
            return mapRowToSeed(result);
        }
        return null;
    }

    public Seed getSeedByCropId(int cropId) {
        String sql = "SELECT s.seed_id, s.seed_name, pl.purchase_location_name, " +
                "sl.seed_price_lower, sl.seed_price_upper, sl.item_non_gold, sl.is_out_of_season " +
                "FROM seeds s " +
                "JOIN crop_seed cs ON s.seed_id = cs.seed_id " +
                "JOIN seed_location sl ON s.seed_id = sl.seed_id " +
                "JOIN purchase_location pl ON sl.location_id = pl.purchase_location_id " +
                "WHERE cs.crop_id = ?";

        SqlRowSet result = jdbcTemplate.queryForRowSet(sql, cropId);
        if (result.next()) {
            return mapRowToSeed(result);
        }
        return null;
    }

    private Seed mapRowToSeed(SqlRowSet rowSet) {
        Seed seed = new Seed();
        seed.setSeedId(rowSet.getInt("seed_id"));
        seed.setSeedName(rowSet.getString("seed_name"));
        seed.setPurchaseLocationName(rowSet.getString("purchase_location_name"));
        seed.setSeedPriceLower(rowSet.getInt("seed_price_lower"));
        seed.setSeedPriceUpper(rowSet.getInt("seed_price_upper"));
        seed.setItemNonGold(rowSet.getString("item_non_gold"));
        seed.setOutOfSeason(rowSet.getBoolean("is_out_of_season"));
        return seed;
    }
}