package com.example.spacetraders.Entity;

public class Marketplace {
    private TradeGood[] itemsForSale = {
        ("Water",30,4,3,0),
        ("Furs",250,10,10,0),
        ("Food",100,5,5,1),
        ("Ore",350,10,20,2),
        ("Games",250,5,-10,3),
        ("Firearms",1250,100,-75,3),
        ("Medicine",650,10,-20,4),
        ("Machines",900,5,-30,4),
        ("Narcotics",3500,150,-125,5),
        ("Robots",5000,100,-150,6)
    };

    //Constructor checks the current planet's tech lvl and removes items from
    // the list which would be impossible to buy on the the planet. Also calculates
    //final price for each item
    public Marketplace() {
        for (int i = 0; i < itemsForSale.length; i++) {
            if (Planet.getTechLevel() < itemsForSale[i].getMTLP()) {
                itemsForSale[i] = null;
            }
        }
        for(TradeGood item : itemsForSale) {
            item.calculateFinalPrice();
        }

    }

    public String toString() {
        String s = "";
        for (TradeGood item : itemsForSale) {
            s+=item.getName() + " " + item.getFinalPrice() "\n";
        }
        return s;
    }

}
