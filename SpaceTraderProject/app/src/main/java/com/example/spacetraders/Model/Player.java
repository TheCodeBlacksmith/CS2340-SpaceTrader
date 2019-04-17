package com.example.spacetraders.Model;

import com.example.spacetraders.Model.DifficultyLevel;

import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("ALL")
public class Player {

    private String email;
    private String userName;
    private DifficultyLevel difficultyLevel;
    //private int[] skillPointsDistrib = new int[] {4, 4, 4, 4};
    private Map<String, Integer> skillPointsMap;
    private int skillPoints = 16;

    public Player() {

    }

    public Player(String email, String userName, DifficultyLevel difficultyLevel) {
        this.email = email;
        this.userName = userName;
        this.difficultyLevel = difficultyLevel;
        this.skillPointsMap = convertDefaultArraytoMap();
    }

    public Player(String email, String userName) {
        this(email, userName, DifficultyLevel.BEGINNER);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void changePoints(int pos, int type) {
        // pos: 0 = pilot, 1 = fighter, 2 = trader, 3 = engineer
        // type: 1 = +1, -1 = -1
        String playerType;
        if (pos == 0) {
            playerType = "Pilot Points";
        } else if (pos == 1) {
            playerType = "Fighter Points";
        } else if (pos == 2) {
            playerType = "Trader Points";
        } else {
            playerType = "Engineer Points";
        }
        if ((type == 1) && (skillPoints < 16) && (skillPointsMap.get(playerType) <= 15)) {
            skillPointsMap.put(playerType, skillPointsMap.get(playerType) + 1);
            skillPoints++;
        } else if ((type == -1) && (skillPoints > 0) && (skillPointsMap.get(playerType) >= 1)) {
            skillPointsMap.put(playerType, skillPointsMap.get(playerType) - 1);
            skillPoints--;
        }
    }

    public int getPilotPoints() {
        return skillPointsMap.get("Pilot Points");
    }
    public int getFighterPoints() {
        return skillPointsMap.get("Fighter Points");
    }
    public int getTraderPoints() {
        return skillPointsMap.get("Trader Points");
    }
    public int getEngineerPoints() {
        return skillPointsMap.get("Engineer Points");
    }


    public int getSkillPoints() {
        return skillPoints;
    }

    private HashMap<String, Integer> convertDefaultArraytoMap() {
        HashMap<String, Integer> map = new HashMap<>();
        String[] playerTypes = new String[] {"Pilot Points", "Fighter Points", "Trader Points",
                "Engineer Points"};
        for (String playerType : playerTypes) {
            map.put(playerType, 4);
        }
        return map;
    }

}
