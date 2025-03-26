package org.example;

public enum MoodLevel {
    EXTREMELY_HAPPY("😁 Extremely Happy"),
    HAPPY("😊 Happy"),
    NEUTRAL("😐 Neutral"),
    SAD("😢 Sad"),
    EXTREMELY_SAD("😭 Extremely Sad");

    private final String description;

    MoodLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}