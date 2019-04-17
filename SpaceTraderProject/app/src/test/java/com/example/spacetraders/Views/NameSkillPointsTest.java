package com.example.spacetraders.Views;

import android.view.View;


import org.junit.Before;
import org.junit.Test;

public class NameSkillPointsTest {

    private NameSkillPoints nameSkillPoints;
    private View view;

    @Before
    public void setUp() {
        nameSkillPoints = new NameSkillPoints();
    }

    @Test
    public void changeSP() {
        nameSkillPoints.changeSP(view);
    }
}