package org.example.model.mood;

import org.example.model.mood.entry.*;
import org.example.model.mood.level.MoodLevel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class DailyMood {
    private String date;
    private MoodLevel mood;
    private List<LogEntry> entries;

    // Boolean flags for each entry type
    private boolean hasSleepEntry = false;
    private boolean hasProductivityEntry = false;
    private boolean hasWeatherEntry = false;
    private boolean hasExerciseEntry = false;
    private boolean hasFoodEntry = false;

    public boolean isHasSleepEntry() {
        return hasSleepEntry;
    }

    public void setHasSleepEntry(boolean hasSleepEntry) {
        this.hasSleepEntry = hasSleepEntry;
    }

    public boolean isHasProductivityEntry() {
        return hasProductivityEntry;
    }

    public void setHasProductivityEntry(boolean hasProductivityEntry) {
        this.hasProductivityEntry = hasProductivityEntry;
    }

    public boolean isHasWeatherEntry() {
        return hasWeatherEntry;
    }

    public void setHasWeatherEntry(boolean hasWeatherEntry) {
        this.hasWeatherEntry = hasWeatherEntry;
    }

    public boolean isHasExerciseEntry() {
        return hasExerciseEntry;
    }


    public void setHasExerciseEntry(boolean hasExerciseEntry) {
        this.hasExerciseEntry = hasExerciseEntry;
    }

    public boolean isHasFoodEntry() {
        return hasFoodEntry;
    }

    public void setHasFoodEntry(boolean hasFoodEntry) {
        this.hasFoodEntry = hasFoodEntry;
    }

    public boolean isHasScreenTimeEntry() {
        return hasScreenTimeEntry;
    }

    public void setHasScreenTimeEntry(boolean hasScreenTimeEntry) {
        this.hasScreenTimeEntry = hasScreenTimeEntry;
    }

    private boolean hasScreenTimeEntry = false;

    public DailyMood(String date) {
        this.date = date;
        this.entries = new ArrayList<>();
    }

    public String getDate() {
        return date;
    }

    public MoodLevel getMood() {
        return mood;
    }

    public void setMood(MoodLevel mood) {
        this.mood = mood;
    }

    public boolean addEntry(LogEntry entry) {
        if (entry instanceof SleepEntry && !hasSleepEntry) {
            entries.add(entry);
            hasSleepEntry = true;
            setHasSleepEntry(true);
            return true;
        } else if (entry instanceof ProductivityEntry && !hasProductivityEntry) {
            entries.add(entry);
            hasProductivityEntry = true;
            setHasProductivityEntry(true);
            return true;
        } else if (entry instanceof SleepEntry.WeatherEntry && !hasWeatherEntry) {
            entries.add(entry);
            hasWeatherEntry = true;
            setHasWeatherEntry(true);
            return true;
        } else if (entry instanceof ExerciseEntry && !hasExerciseEntry) {
            entries.add(entry);
            hasExerciseEntry = true;
            setHasExerciseEntry(true);
            return true;
        } else if (entry instanceof FoodEntry && !hasFoodEntry) {
            entries.add(entry);
            hasFoodEntry = true;
            setHasFoodEntry(true);
            return true;
        } else if (entry instanceof ScreenTimeEntry && !hasScreenTimeEntry) {
            entries.add(entry);
            hasScreenTimeEntry = true;
            setHasScreenTimeEntry(true);
            return true;
        }
        return false; // Entry type already exists
    }

    public List<LogEntry> getEntries() {
        return entries;
    }

    public void saveToFile(String filename) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(this);
        }
    }

    public static DailyMood loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (DailyMood) ois.readObject();
        }
    }
}