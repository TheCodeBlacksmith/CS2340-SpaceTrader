package com.example.spacetraders.Entity;

public class TradeGood {
    private String name;
    private double basePrice;
    private int variance;
    private int ipl;  //IPL = price increase per tech level
    private int mtlp; //MTLP = min. tech lvl required to produce this resource
    private double finalPrice;

    public TradeGood(String name, double basePrice, int variance, int ipl, int mtlp) {
        this.name = name;
        this.basePrice = basePrice;
        this.variance = variance;
        this.ipl = ipl;
        this.mtlp = mtlp;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getFinalPrice() {
        return finalPrice;
    }

    public int getVariance() {
        return variance;
    }

    public int getIPL() {
        return ipl;
    }

    public int getMTLP() {
        return mtlp;
    }

    public void setName(String s) {
        name = s;
    }

    public void setBasePrice(double d) {
        basePrice = d;
    }

    public void setVariance(int x) {
        variance = x;
    }

    public void setIPL(int x) {
        ipl = x;
    }

    public void setMTLP(int x) {
        mtlp = x;
    }

    public void calculateFinalPrice() {
        finalPrice = basePrice + 3 * 2 * (ipl * (Planet.getTechLevel - mtlp))
        + (int) (Math.random() * variance);
    }





}
