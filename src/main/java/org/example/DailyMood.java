package org.example;

import java.io.*;
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