package org.example;

public enum SleepQualities {
    EXCELLENT("😴 Excellent"),
    GOOD("😊 Good"),
    AVERAGE("😐 Average"),
    POOR("😟 Poor"),
    TERRIBLE("😫 Terrible");

    private final String description;

    SleepQualities(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}