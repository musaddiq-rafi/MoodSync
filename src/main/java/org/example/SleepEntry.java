package org.example;

public class SleepEntry extends LogEntry {
    private int hours;
    private SleepQualities sleepQuality;

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public SleepQualities getSleepQuality() {
        return sleepQuality;
    }

    public void setSleepQuality(SleepQualities sleepQuality) {
        this.sleepQuality = sleepQuality;
    }
}