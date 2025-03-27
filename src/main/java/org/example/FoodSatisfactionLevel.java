package org.example;

public enum FoodSatisfactionLevel {
    EXCELLENT("😋 Excellent"),
    GOOD("😊 Good"),
    AVERAGE("😐 Average"),
    POOR("😞 Poor"),
    TERRIBLE("🤢 Terrible");

    private final String description;

    FoodSatisfactionLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}