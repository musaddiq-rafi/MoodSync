package org.example;

import java.util.ArrayList;
import java.util.List;

public class DailyMood {
    private String date;
    private MoodLevel mood;
    private List<LogEntry> entries;

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

    public void addEntry(LogEntry entry) {
        entries.add(entry);
    }

    public List<LogEntry> getEntries() {
        return entries;
    }
}