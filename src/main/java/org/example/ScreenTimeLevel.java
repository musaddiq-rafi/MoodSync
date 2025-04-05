package org.example;

public enum ScreenTimeLevel {
    NONE("🚫 No Screen Time"),
    LOW("📱 Low Screen Time - About an hour"),
    MODERATE("💻 Moderate Screen Time - About three hours"),
    HIGH("🖥️ High Screen Time - About five hours"),
    EXCESSIVE("📺 Excessive Screen Time - More than five hours");

    private final String description;

    ScreenTimeLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}