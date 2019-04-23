package com.example.spacetraders;

import com.example.spacetraders.Entity.TradeGood;
import com.example.spacetraders.Model.MarketPlace;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

/**
This JUnit tests to make sure the marketplaces are constructed correctly
*/

public class marketplaceJUnits {

    private MarketPlace market;
    private MarketPlace market2;

    @Before
    public void setUp() {
        market = new MarketPlace(7);
        market2 = new MarketPlace();
    }

    @Test
    public void testMarketplaceConstruction() {
        TradeGood[] expected = {
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

        assertArrayEquals(expected, market.getItemsForSale());

        TradeGood[] expected2 = {
            new TradeGood("Water", 30, 4, 3, 0),
            new TradeGood("Furs", 250, 10, 10, 0),
            null,
            null,
            null,
            null,
            null,
            null,
            null,
            null
        };

        assertArrayEquals(expected2, market2.getItemsForSale());
    }
}
