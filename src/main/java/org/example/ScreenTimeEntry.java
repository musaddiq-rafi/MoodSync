package org.example;

public class ScreenTimeEntry extends LogEntry {
    private ScreenTimeLevel screenTimeLevel;
    private String screenTimeDescription;

    public ScreenTimeLevel getScreenTimeLevel() {
        return screenTimeLevel;
    }

    public void setScreenTimeLevel(ScreenTimeLevel screenTimeLevel) {
        this.screenTimeLevel = screenTimeLevel;
    }

    public String getScreenTimeDescription() {
        return screenTimeDescription;
    }

    public void setScreenTimeDescription(String screenTimeDescription) {
        this.screenTimeDescription = screenTimeDescription;
    }

    public String toCSV() {
        return screenTimeLevel + ";" + screenTimeDescription;
    }

    public static ScreenTimeEntry fromCSV(String csv) {
        String[] parts = csv.split(";");
        ScreenTimeEntry entry = new ScreenTimeEntry();
        entry.setScreenTimeLevel(ScreenTimeLevel.valueOf(parts[0]));
        entry.setScreenTimeDescription(parts[1]);
        return entry;
    }
}