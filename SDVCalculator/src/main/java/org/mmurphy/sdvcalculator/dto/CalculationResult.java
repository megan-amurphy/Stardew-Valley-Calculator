package org.mmurphy.sdvcalculator.dto;

public class CalculationResult {   private String cropName;
    private int revenue;
    private String revenueDetails;
    private int cost;
    private String costDetails;
    private int netProfit;
    private String netProfitDetails;

    // Getters and Setters
    public String getCropName() {
        return cropName;
    }

    public void setCropName(String cropName) {
        this.cropName = cropName;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public String getRevenueDetails() {
        return revenueDetails;
    }

    public void setRevenueDetails(String revenueDetails) {
        this.revenueDetails = revenueDetails;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCostDetails() {
        return costDetails;
    }

    public void setCostDetails(String costDetails) {
        this.costDetails = costDetails;
    }

    public int getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(int netProfit) {
        this.netProfit = netProfit;
    }

    public String getNetProfitDetails() {
        return netProfitDetails;
    }

    public void setNetProfitDetails(String netProfitDetails) {
        this.netProfitDetails = netProfitDetails;
    }
}
