package org.example;

public enum ExerciseLevel {
    NONE("🚫 No Exercise - You haven't exercised today"),
    LIGHT("🏃 Light Exercise - A little bit of movement"),
    MODERATE("🏋️ Moderate Exercise - A good workout"),
    INTENSE("💪 Intense Exercise - Pushing your limits"),
    EXTREME("🔥 Extreme Exercise - Going all out!");

    private final String description;

    ExerciseLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}