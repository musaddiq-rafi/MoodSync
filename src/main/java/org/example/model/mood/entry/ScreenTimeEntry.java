package org.example.model.mood.entry;

import org.example.model.mood.level.ScreenTimeLevel;
import org.example.model.mood.LogEntry;

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
        String[] parts = csv.split(";",2); // <-- split only into 2 parts
        ScreenTimeEntry entry = new ScreenTimeEntry();
        entry.setScreenTimeLevel(ScreenTimeLevel.valueOf(parts[0]));
        entry.setScreenTimeDescription(parts[1]);
        return entry;
    }
}