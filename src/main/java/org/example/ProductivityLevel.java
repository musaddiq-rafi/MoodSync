package org.example;

public enum ProductivityLevel {
    EXTREMELY_PRODUCTIVE("🚀 Extremely Productive - You have accomplished a lot!"),
    PRODUCTIVE("💪 Productive - You are getting things done!"),
    NEUTRAL("😐 Neutral - You are just going through the motions."),
    UNPRODUCTIVE("😓 Unproductive - You are struggling to focus."),
    EXTREMELY_UNPRODUCTIVE("😴 Extremely Unproductive - You are not getting anything done.");

    private final String description;

    ProductivityLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}