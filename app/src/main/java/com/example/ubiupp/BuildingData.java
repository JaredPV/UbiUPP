package com.example.ubiupp;

public class BuildingData {
    private String buildingName;
    private String buildingId;
    private String buildingImage;
    private String buildingDescription;
    private String buildingLocation;

    public BuildingData() {

    }

    public BuildingData(String buildingName, String buildingId, String buildingImage, String buildingDescription, String buildingLocation) {
        this.buildingName = buildingName;
        this.buildingId = buildingId;
        this.buildingImage = buildingImage;
        this.buildingDescription = buildingDescription;
        this.buildingLocation = buildingLocation;
    }

    public String getBuildingDescription() {
        return buildingDescription;
    }

    public void setBuildingDescription(String buildingDescription) {
        this.buildingDescription = buildingDescription;
    }

    public String getBuildingLocation() {
        return buildingLocation;
    }

    public void setBuildingLocation(String buildingLocation) {
        this.buildingLocation = buildingLocation;
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

    public String getBuildingImage() {
        return buildingImage;
    }

    public void setBuildingImage(String buildingImage) {
        this.buildingImage = buildingImage;
    }
}
