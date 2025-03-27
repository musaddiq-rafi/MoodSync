package org.example;

public enum ProductivityLevel {
    EXTREMELY_PRODUCTIVE("🚀 Extremely Productive"),
    PRODUCTIVE("💪 Productive"),
    NEUTRAL("😐 Neutral"),
    UNPRODUCTIVE("😓 Unproductive"),
    EXTREMELY_UNPRODUCTIVE("😴 Extremely Unproductive");

    private final String description;

    ProductivityLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}