package org.example;

import java.time.LocalDate;

public class LogEntry {

    private LocalDate timeStamp;

    public LogEntry(){
        this.timeStamp = LocalDate.now();
    }
    public LocalDate getTimeStamp(){
        return timeStamp;
    }
    public void setTimeStamp(LocalDate timeStamp){
        this.timeStamp = timeStamp;
    }
}
