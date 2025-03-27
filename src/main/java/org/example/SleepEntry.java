package org.example;

public class SleepEntry extends LogEntry {
    private int hours;
    private SleepQualities sleepQuality;
    private String sleepMessage;

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
        String[] parts = csv.split(";");
        SleepEntry entry = new SleepEntry();
        entry.setHours(Integer.parseInt(parts[0]));
        entry.setSleepQuality(SleepQualities.valueOf(parts[1]));
        entry.setSleepMessage(parts[2]);
        return entry;
    }
}