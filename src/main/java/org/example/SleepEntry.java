package org.example;

public class SleepEntry extends LogEntry{
    private int hours;
    private String sleepDescription;

    public int getHours(){
        return hours;
    }
    public void setHours(int hours){
        this.hours = hours;
    }
    public String getSleepDescription(){
        return sleepDescription;
    }
    public void setSleepDescription(String sleepDescription){
        this.sleepDescription = sleepDescription;
    }
}
