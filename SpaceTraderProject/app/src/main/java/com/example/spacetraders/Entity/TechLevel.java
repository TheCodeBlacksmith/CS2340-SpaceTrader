package com.example.spacetraders.Entity;

@SuppressWarnings("ALL")
public enum TechLevel {
    PREAGRICULTURE (0),
    AGRICULTURE (1),
    MEDIEVAL (2),
    RENAISSANCE (3),
    EARLYINDUSTRIAL (4),
    INDUSTRIAL (5),
    POSTINDUSTRIAL (6),
    HITECH (7);

    final int code;

    TechLevel(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
