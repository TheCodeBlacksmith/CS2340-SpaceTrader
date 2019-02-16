package com.example.spacetraders.Entity;

import com.example.spacetraders.Entity.DifficultyLevel;

public class Player {

    private String userName;
    private String password;
    private DifficultyLevel difficultyLevel;

    public Player(String userName, String password, DifficultyLevel difficultyLevel) {
        this.userName = userName;
        this.password = password;
        this.difficultyLevel = difficultyLevel;
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
}
