package org.example;

public enum ExerciseLevel {
    NONE("🚫 No Exercise"),
    LIGHT("🏃 Light Exercise"),
    MODERATE("🏋️ Moderate Exercise"),
    INTENSE("💪 Intense Exercise"),
    EXTREME("🔥 Extreme Exercise");

    private final String description;

    ExerciseLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}