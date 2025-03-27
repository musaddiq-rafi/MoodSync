package org.example;

public class WeatherEntry extends LogEntry {
    private WeatherLevel weatherLevel;
    private String weatherDescription;

    public WeatherLevel getWeatherLevel() {
        return weatherLevel;
    }

    public void setWeatherLevel(WeatherLevel weatherLevel) {
        this.weatherLevel = weatherLevel;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public String toCSV() {
        return weatherLevel + "," + weatherDescription;
    }

    public static WeatherEntry fromCSV(String csv) {
        String[] parts = csv.split(",");
        WeatherEntry entry = new WeatherEntry();
        entry.setWeatherLevel(WeatherLevel.valueOf(parts[0]));
        entry.setWeatherDescription(parts[1]);
        return entry;
    }
}