package org.mmurphy.sdvcalculator.model;

import java.util.Objects;

public class Crop {
    private int cropId;
    private String cropName;
    private String cropSeason;
    private String cropQuality;
    private int price;

    public Crop(int cropId, String cropName, String cropSeason, String cropQuality,
                int price) {
        this.cropId = cropId;
        this.cropName = cropName;
        this.cropSeason = cropSeason;
        this.cropQuality = cropQuality;
        this.price = price;
    }

    public Crop (){

    }

    public int getCropId() {

        return cropId;
    }

    public void setCropId(int cropId) {

        this.cropId = cropId;
    }

    public String getCropName() {

        return cropName;
    }

    public void setCropName(String cropName) {

        this.cropName = cropName;
    }

    public String getCropSeason() {
        return cropSeason;
    }

    public void setCropSeason(String cropSeason) {
        this.cropSeason = cropSeason;
    }

    public String getCropQuality() {

        return cropQuality;
    }

    public void setCropQuality(String cropQuality) {

        this.cropQuality = cropQuality;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Crop crop = (Crop) o;
        return cropId == crop.cropId && price == crop.price && Objects.equals(cropName, crop.cropName) && Objects.equals(cropSeason, crop.cropSeason) && Objects.equals(cropQuality, crop.cropQuality);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cropId, cropName, cropSeason, cropQuality, price);
    }

    @Override
    public String toString() {
        return "Crop{" +
                "cropId=" + cropId +
                ", cropName='" + cropName + '\'' +
                ", cropSeason='" + cropSeason + '\'' +
                ", cropQuality='" + cropQuality + '\'' +
                ", price=" + price +
                '}';
    }
}
