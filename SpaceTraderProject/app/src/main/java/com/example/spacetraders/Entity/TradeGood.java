package com.example.spacetraders.Entity;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class TradeGood {
    private String name;
    private double basePrice;
    private int variance;
    private int ipl;  //IPL = price increase per tech level
    private int mtlp; //MTLP = min. tech lvl required to produce this resource
    private double finalPrice;
    private int quantity;

    public TradeGood(String name, double basePrice, int variance, int ipl, int mtlp) {
        this.name = name;
        this.basePrice = basePrice;
        this.variance = variance;
        this.ipl = ipl;
        this.mtlp = mtlp;
        quantity = (int) (Math.random() * 100) + 100;
        calculateFinalPrice(7);
    }
    public TradeGood() {

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

    public int getQuantity() { return quantity; }

    public void setQuantity(int x) {
        quantity = x;
    }

    public void subtractQuantity(int x) {
        quantity -= x;
    }

    //Can only sell one item at a time to marketplace so quantity is incremented
    public void addQuantity() {
        quantity++;
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

    public Map<String, Object> toMap() {
        Map<String, Object> result = new HashMap<>();
        result.put("name", name);
        result.put("basePrice", basePrice);
        result.put("variance", variance);
        result.put("ipl", ipl);
        result.put("mpl", mtlp);
        result.put("finalPrice", finalPrice);
        result.put("quantity", quantity);

        return result;
    }

    public void calculateFinalPrice(int planetTechLevel) {
        finalPrice = Math.abs(basePrice + (3 * 2 * (ipl * (planetTechLevel - mtlp)))
                + (int) (Math.random() * variance));
    }





}