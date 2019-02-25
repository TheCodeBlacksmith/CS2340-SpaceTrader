package com.example.spacetraders.Entity;

import com.example.spacetraders.Entity.DifficultyLevel;

public class Player {

    private String userName;
    private String password;
    private DifficultyLevel difficultyLevel;
    private int[] skillPointsDistrib;

    private int skillPoints = 0;

    public Player(String userName, String password, DifficultyLevel difficultyLevel) {
        this.userName = userName;
        this.password = password;
        this.difficultyLevel = difficultyLevel;
        this.skillPointsDistrib = new int[4];
    }

    public Player(String userName, String password) {
        this(userName, password, null);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        this.difficultyLevel = difficultyLevel;
    }

    public void changePoints(int pos, int type) {
        // pos: 0 = pilot, 1 = fighter, 2 = trader, 3 = engineer
        // type: 1 = +1, -1 = -1
        if (type == 1 && skillPoints < 16 && skillPointsDistrib[pos] <= 15) {
            skillPointsDistrib[pos]++;
            skillPoints++;
        } else if (type == -1 && skillPoints > 0 && skillPointsDistrib[pos] >= 1) {
            skillPointsDistrib[pos]--;
            skillPoints--;
        }
    }

    public int getPilotPoints() {
        return skillPointsDistrib[0];
    }
    public int getFighterPoints() {
        return skillPointsDistrib[1];
    }
    public int getTraderPoints() {
        return skillPointsDistrib[2];
    }
    public int getEngineerPoints() {
        return skillPointsDistrib[3];
    }

    public int[] getSkillPointsDistrib() {
        return skillPointsDistrib;
    }

    public int getSkillPoints() {
        return skillPoints;
    }
}
