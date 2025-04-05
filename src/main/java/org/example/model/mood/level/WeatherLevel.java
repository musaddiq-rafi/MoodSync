package org.example.model.mood.level;

public enum WeatherLevel {
    SUNNY("☀️ Sunny - A bright and clear day"),
    CLOUDY("☁️ Cloudy - Overcast skies"),
    RAINY("🌧️ Rainy - Wet and gloomy"),
    STORMY("⛈️ Stormy - Thunder and lightning"),
    SNOWY("❄️Cold - Feeling frozen");

    private final String description;

    WeatherLevel(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}