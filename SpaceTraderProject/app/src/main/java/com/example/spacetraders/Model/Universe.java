package com.example.spacetraders.Model;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class Universe {

    private String planet;
    private String resource;
    private String techLevel;
    private int xCoordinate;
    private int yCoordinate;

    public Universe() {

    }
    public Universe(String planet, String resource, String techLevel, int xCoordinate, int yCoordinate) {
        this.planet = planet;
        this.resource = resource;
        this.techLevel = techLevel;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("planet", planet);
        result.put("resource", resource);
        result.put("techLevel", techLevel);
        result.put("xCoordinate", xCoordinate);
        result.put("yCoordinate", yCoordinate);

        return result;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

    public String getTechLevel() {
        return techLevel;
    }

    public void setTechLevel(String techLevel) {
        this.techLevel = techLevel;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    @Override
    public String toString() {
        return "Universe{" +
                "planet='" + planet + '\'' +
                ", resource='" + resource + '\'' +
                ", techLevel='" + techLevel + '\'' +
                ", xCoordinate=" + xCoordinate +
                ", yCoordinate=" + yCoordinate +
                '}';
    }

    public String getLocationString() {
        return "<" + xCoordinate + ", " + yCoordinate + ">";
    }
}
