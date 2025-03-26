package org.example;

public class WeatherEntry extends LogEntry {
    private WeatherCondition weatherCondition;

    public WeatherCondition getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(WeatherCondition weatherCondition) {
        this.weatherCondition = weatherCondition;
    }
}