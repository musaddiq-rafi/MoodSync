package org.example;

public enum MoodLevel {
    EXTREMELY_HAPPY("😁 Extremely Happy - You are on top of the world!"),
    HAPPY("😊 Happy - Things are going well!"),
    NEUTRAL("😐 Neutral - Just another day."),
    SAD("😢 Sad - Things could be better."),
    EXTREMELY_SAD("😭 Extremely Sad - You are feeling down.");

    private final String description;

    MoodLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}