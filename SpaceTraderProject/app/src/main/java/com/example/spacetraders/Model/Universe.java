package com.example.spacetraders.Model;

import com.example.spacetraders.Entity.Planet;
import com.example.spacetraders.Entity.Resource;
import com.example.spacetraders.Entity.TechLevel;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Universe {

    public String planet;
    public String resource;
    public String techLevel;
    public int xCoordinate;
    public int yCoordinate;

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
        HashMap<String, Object> result = new HashMap<>();
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

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
}
