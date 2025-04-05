package org.example.model.mood.entry;

import org.example.model.mood.level.SleepQualities;
import org.example.model.mood.level.WeatherLevel;
import org.example.model.mood.LogEntry;

public class SleepEntry extends LogEntry {
    private int hours;
    private SleepQualities sleepQuality;
    private String sleepMessage;

    public SleepQualities getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(SleepQualities sleepQuality) {
        this.sleepQuality = sleepQuality;
    }

    public String getSleepMessage() {
        return sleepMessage;
    }

    public void setSleepMessage(String sleepMessage) {
        this.sleepMessage = sleepMessage;
    }

    public String toCSV() {
        return sleepQuality + ";" + sleepMessage;
    }

    public static SleepEntry fromCSV(String csv) {
        String[] parts = csv.split(";",2);
        SleepEntry entry = new SleepEntry();
        entry.setSleepQuality(SleepQualities.valueOf(parts[0]));
        entry.setSleepMessage(parts[1]);
        return entry;
    }

    public static class WeatherEntry extends LogEntry {
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
            return weatherLevel + ";" + weatherDescription;
        }

        public static WeatherEntry fromCSV(String csv) {
            String[] parts = csv.split(";",2); // <-- split only into 2 parts
            WeatherEntry entry = new WeatherEntry();
            entry.setWeatherLevel(WeatherLevel.valueOf(parts[0]));
            entry.setWeatherDescription(parts[1]);
            return entry;
        }
    }
}