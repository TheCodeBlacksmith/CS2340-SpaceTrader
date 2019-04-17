package com.example.spacetraders.Model;

import com.example.spacetraders.Entity.TradeGood;

@SuppressWarnings("ALL")
public class MarketPlace {

    private final TradeGood[] itemsForSale = {
            new TradeGood("Water", 30, 4, 3, 0),
            new TradeGood("Furs", 250, 10, 10, 0),
            new TradeGood("Food", 100, 5, 5, 1),
            new TradeGood("Ore", 350, 10, 20, 2),
            new TradeGood("Games", 250, 5, -10, 3),
            new TradeGood("Firearms", 1250, 100, -75, 3),
            new TradeGood("Medicine", 650, 10, -20, 4),
            new TradeGood("Machines", 900, 5, -30, 4),
            new TradeGood("Narcotics", 3500, 150, -125, 5),
            new TradeGood("Robots", 5000, 100, -150, 6)
    };

    //Constructor checks the current planet's tech lvl and removes items from
    // the list which would be impossible to buy on the the planet. Also calculates
    //final price for each item
    public MarketPlace(int techLVL) {
        for (TradeGood item : itemsForSale) {
            item.calculateFinalPrice(techLVL);
        }
        for (int i = 0; i < itemsForSale.length; i++) {
            if(techLVL < itemsForSale[i].getMTLP()) {
                itemsForSale[i] = null;
            }
        }
    }

    public MarketPlace() {
        this(0);
    }


    public TradeGood[] getItemsForSale() {
        return itemsForSale;
    }

    public int getTotalQuantity() {
        int sum = 0;
        for (TradeGood item : itemsForSale) {
            if (item != null) {
                sum += item.getQuantity();
            }
        }
        return sum;
    }

    public String toString() {
        String s = "";
        for (TradeGood item : itemsForSale) {
            if (item != null) {
                s += item.getName() + " " + item.getFinalPrice() + " " + item.getQuantity() + "\n";
            }
        }
        return s;
    }
}
