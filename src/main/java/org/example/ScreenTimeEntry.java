package org.example;

public class ScreenTimeEntry extends LogEntry{
    private int hours;
    private String screenTimeDescription;


    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public String getScreenTimeDescription() {
        return screenTimeDescription;
    }

    public void setScreenTimeDescription(String screenTimeDescription) {
        this.screenTimeDescription = screenTimeDescription;
    }
}
