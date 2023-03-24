package com.example.ubiupp;

public class BuildingData {
    private String buildingName;
    private String buildingId;
    private Integer buildingImage;

    public BuildingData(String buildingName, String buildingId, Integer buildingImage) {
        this.buildingName = buildingName;
        this.buildingId = buildingId;
        this.buildingImage = buildingImage;
    }

    public String getBuildingName() {
        return buildingName;
    }

    public void setBuildingName(String buildingName) {
        this.buildingName = buildingName;
    }

    public String getBuildingId() {
        return buildingId;
    }

    public void setBuildingId(String buildingId) {
        this.buildingId = buildingId;
    }

    public Integer getBuildingImage() {
        return buildingImage;
    }

    public void setBuildingImage(Integer buildingImage) {
        this.buildingImage = buildingImage;
    }
}
