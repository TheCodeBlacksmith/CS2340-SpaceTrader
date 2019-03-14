package com.example.spacetraders.Entity;


import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.example.spacetraders.Model.Player;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Marketplace {


    private TradeGood[] itemsForSale = {
            new TradeGood("Water",30,4,3,0),
            new TradeGood("Furs",250,10,10,0),
            new TradeGood("Food",100,5,5,1),
            new TradeGood("Ore",350,10,20,2),
            new TradeGood("Games",250,5,-10,3),
            new TradeGood("Firearms",1250,100,-75,3),
            new TradeGood("Medicine",650,10,-20,4),
            new TradeGood("Machines",900,5,-30,4),
            new TradeGood("Narcotics",3500,150,-125,5),
            new TradeGood("Robots",5000,100,-150,6)
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
            if (item != null)
                item.calculateFinalPrice();
        }

    }

    public TradeGood[] getItemsForSale() {
        return itemsForSale;
    }

    public String toString() {
            String s = "";
            for (TradeGood item : itemsForSale) {
                if (item != null)
                    s += item.getName() + " " + item.getFinalPrice() + "\n";
            }
            return s;
        }

    }