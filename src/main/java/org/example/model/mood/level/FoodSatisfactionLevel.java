package org.example.model.mood.level;

public enum FoodSatisfactionLevel {
    EXCELLENT("😋 Excellent - You are satisfied with your food"),
    GOOD("😊 Good - You enjoyed your meal"),
    AVERAGE("😐 Average - It was okay"),
    POOR("😞 Poor - You didn't like it much"),
    TERRIBLE("🤢 Terrible - You couldn't eat it");

    private final String description;

    FoodSatisfactionLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}