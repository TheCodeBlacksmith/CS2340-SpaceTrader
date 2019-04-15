package com.example.spacetraders;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PlayerTest extends P_T_D{
    @Test
    public void addition_isCorrect() {
        assertEquals(4, playerHealth + 2);
        assertEquals(0, playerHealth - pirateDamage);
        assertEquals(0, playerHealth - pirateDamage);
        assertEquals(2, playerHealthEnding);
        assertEquals(0, pirateHealth - pirateDamage);
        for(int i = 0; i < pirateHealth; i++){
            i++;
        }
        assertEquals(0, playerHealth - pirateDamage);
        assertEquals(2, playerHealthEnding);
        assertEquals(0, pirateDamage - pirateDamage);
        assertEquals(true, pirateDeath);
    }

//    @Test
//    public void addition_isCorrect() {
//        assertEquals(0, playerHealth - pirateDamage);
//    }
//
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(2, playerHealthEnding);
//    }
//
//    @Test
//    public void addition_isCorrect() {
//        assertEquals(0, pirateHealth - pirateDamage);
//    }


}
