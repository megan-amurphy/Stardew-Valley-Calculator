package org.mmurphy.sdvcalculator.dao;

import org.mmurphy.sdvcalculator.model.Crop;
import java.util.List;

public interface CropDao {
    List<Crop> getAllCrops();
    List<Crop> getAllCropsBySeason(String season);
    Crop getCropByName(String name);
    Crop getCropByNameAndQuality(String cropName, String quality);

}