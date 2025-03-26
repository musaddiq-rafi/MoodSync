package org.example;

public class MoodEntry extends LogEntry {
    private MoodLevel moodLevel;

    public MoodLevel getMoodLevel() {
        return moodLevel;
    }

    public void setMoodLevel(MoodLevel moodLevel) {
        this.moodLevel = moodLevel;
    }
}