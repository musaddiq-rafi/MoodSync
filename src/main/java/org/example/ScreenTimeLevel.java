package org.example;

public enum ScreenTimeLevel {
    NONE("🚫 No Screen Time"),
    LOW("📱 Low Screen Time"),
    MODERATE("💻 Moderate Screen Time"),
    HIGH("🖥️ High Screen Time"),
    EXCESSIVE("📺 Excessive Screen Time");

    private final String description;

    ScreenTimeLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}