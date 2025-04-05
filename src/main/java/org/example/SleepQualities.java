package org.example;

public enum SleepQualities {
    EXCELLENT("😴 Excellent - About 8 hours"),
    GOOD("😊 Good - About 7 hours"),
    AVERAGE("😐 Average - About 6 hours"),
    POOR("😟 Poor - About 5 hours"),
    TERRIBLE("😫 Terrible - Less than 5 hours");

    private final String description;

    SleepQualities(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}