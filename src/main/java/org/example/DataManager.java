package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataManager {
    public void saveDailyMoodToFile(DailyMood dailyMood) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("daily_mood_data.csv", true))) {
            String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            writer.write(timestamp + ";" + dailyMood.getDate() + ";" + dailyMood.getMood() + "\n");
            for (LogEntry entry : dailyMood.getEntries()) {
                if (entry instanceof SleepEntry) {
                    writer.write("SleepEntry;" + ((SleepEntry) entry).toCSV() + "\n");
                } else if (entry instanceof ProductivityEntry) {
                    writer.write("ProductivityEntry;" + ((ProductivityEntry) entry).toCSV() + "\n");
                } else if (entry instanceof WeatherEntry) {
                    writer.write("WeatherEntry;" + ((WeatherEntry) entry).toCSV() + "\n");
                } else if (entry instanceof ExerciseEntry) {
                    writer.write("ExerciseEntry;" + ((ExerciseEntry) entry).toCSV() + "\n");
                } else if (entry instanceof ScreenTimeEntry) {
                    writer.write("ScreenTimeEntry;" + ((ScreenTimeEntry) entry).toCSV() + "\n");
                } else if (entry instanceof FoodEntry) {
                    writer.write("FoodEntry;" + ((FoodEntry) entry).toCSV() + "\n");
                }
            }
        } catch (IOException e) {
            System.out.println("Error saving daily mood: " + e.getMessage());
        }
    }

    public void showSavedData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("daily_mood_data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading saved data: " + e.getMessage());
        }
    }

    public void displayTable() {
        CSVTableDisplay tableDisplay = new CSVTableDisplay();
        tableDisplay.displayTable("daily_mood_data.csv");
    }
}