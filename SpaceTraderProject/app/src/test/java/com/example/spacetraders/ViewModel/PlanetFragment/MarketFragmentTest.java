package com.example.spacetraders.ViewModel.PlanetFragment;

import com.example.spacetraders.Entity.TradeGood;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MarketFragmentTest {


    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void purhcase_itemNonNull() {
        TradeGood testItem = new TradeGood();
        assertNotNull("A non-null item", testItem);
    }

    @Test
    public void purchase_itemNull() {
        assertNull("A null tradegood", null);
    }

}
