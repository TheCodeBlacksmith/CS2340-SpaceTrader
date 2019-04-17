package com.example.spacetraders.Model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    private Player p1;
    private static final int TIMEOUT = 200;
    private int pos;
    private int type;

    @Before
    public void setup() {
        String testEmail = "test@gmail.com";
        String testPass = "123456";
        p1 = new Player(testEmail, testPass);
        pos = 0;
        type = 1;

    }

    @Test(timeout =  TIMEOUT)
    public void testAddPilotPoints(){
        int previousPilotPoints = p1.getPilotPoints();
        p1.changePoints(pos, type);
        assertTrue("points should be less than previous", previousPilotPoints == p1.getPilotPoints());
        p1.changePoints(0, 0);
        assertEquals(previousPilotPoints, p1.getPilotPoints());
    }
    @Test(timeout =  TIMEOUT)
    public void testRemovePilotPoints(){
        int previousPilotPoints = p1.getPilotPoints();
        assertEquals(p1.getPilotPoints(), 4);
        p1.changePoints(0, 0);
        assertNotEquals(previousPilotPoints, p1.getPilotPoints());
        p1.changePoints(0, 1);
        assertEquals(previousPilotPoints, p1.getPilotPoints());
    }

    @Test(timeout =  TIMEOUT)
    public void testaddFighterPoints(){
        int previousFighterPoints = p1.getFighterPoints();
        p1.changePoints(1, 1);
        assertTrue("points should be less than previous", previousFighterPoints == p1.getFighterPoints());
        p1.changePoints(1, 0);
        assertEquals(previousFighterPoints, p1.getFighterPoints());
    }
    @Test(timeout =  TIMEOUT)
    public void testRemoveFighterPoints(){
        int previousFighterPoints = p1.getFighterPoints();
        assertEquals(p1.getFighterPoints(), 4);
        p1.changePoints(0, 0);
        assertNotEquals(previousFighterPoints, p1.getFighterPoints());
        p1.changePoints(0, 1);
        assertEquals(previousFighterPoints, p1.getFighterPoints());
    }

    @Test(timeout =  TIMEOUT)
    public void testaddTraderPoints(){
        int previousTraderPoints = p1.getTraderPoints();
        p1.changePoints(2, 1);
        assertTrue("points should be less than previous", previousTraderPoints == p1.getTraderPoints());
        p1.changePoints(2, 0);
        assertEquals(previousTraderPoints, p1.getTraderPoints());
    }
    @Test(timeout =  TIMEOUT)
    public void testRemoveTraderPoints(){
        int previousTraderPoints = p1.getTraderPoints();
        assertEquals(p1.getTraderPoints(), 4);
        p1.changePoints(2, 0);
        assertNotEquals(previousTraderPoints, p1.getTraderPoints());
        p1.changePoints(2, 1);
        assertEquals(previousTraderPoints, p1.getTraderPoints());
    }

    @Test(timeout =  TIMEOUT)
    public void testaddEngineerPoints(){
        int previousEngineerPoints = p1.getEngineerPoints();
        p1.changePoints(3, 1);
        assertTrue("points should be less than previous", previousEngineerPoints == p1.getEngineerPoints());
        p1.changePoints(3, 0);
        assertEquals(previousEngineerPoints, p1.getEngineerPoints());
    }
    @Test(timeout =  TIMEOUT)
    public void testRemoveEngineerPoints(){
        int previousEngineerPoints = p1.getEngineerPoints();
        assertEquals(p1.getEngineerPoints(), 4);
        p1.changePoints(3, 0);
        assertNotEquals(previousEngineerPoints, p1.getEngineerPoints());
        p1.changePoints(3, 1);
        assertEquals(previousEngineerPoints, p1.getEngineerPoints());
    }
}