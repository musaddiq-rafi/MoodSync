package org.example;

public enum WeatherLevel {
    SUNNY("☀️ Sunny"),
    CLOUDY("☁️ Cloudy"),
    RAINY("🌧️ Rainy"),
    STORMY("⛈️ Stormy"),
    SNOWY("❄️Cold");

    private final String description;

    WeatherLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}