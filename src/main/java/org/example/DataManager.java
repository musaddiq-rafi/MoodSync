// In `src/main/java/org/example/DataManager.java`
package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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

    public List<DailyMood> loadAllDailyMoods() {
        List<DailyMood> allMoods = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("daily_mood_data.csv"))) {
            String line;
            DailyMood currentMood = null;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    currentMood = new DailyMood(parts[1]);
                    try {
                        currentMood.setMood(MoodLevel.valueOf(parts[2]));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Warning: Invalid mood level '" + parts[2] + "' in data. Skipping entry.");
                        continue;
                    }
                    allMoods.add(currentMood);
                } else if (currentMood != null) {
                    switch (parts[0]) {
                        case "SleepEntry" -> currentMood.addEntry(SleepEntry.fromCSV(parts[1]));
                        case "ProductivityEntry" -> currentMood.addEntry(ProductivityEntry.fromCSV(parts[1]));
                        case "WeatherEntry" -> currentMood.addEntry(WeatherEntry.fromCSV(parts[1]));
                        case "ExerciseEntry" -> currentMood.addEntry(ExerciseEntry.fromCSV(parts[1]));
                        case "ScreenTimeEntry" -> currentMood.addEntry(ScreenTimeEntry.fromCSV(parts[1]));
                        case "FoodEntry" -> currentMood.addEntry(FoodEntry.fromCSV(parts[1]));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading daily moods: " + e.getMessage());
        }
        return allMoods;
    }

    public List<String> getAvailableDates() {
        Set<String> uniqueDates = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("daily_mood_data.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    uniqueDates.add(parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading dates: " + e.getMessage());
        }
        return new ArrayList<>(uniqueDates);
    }

    public DailyMood findMoodByDate(String targetDate) {
        try (BufferedReader reader = new BufferedReader(new FileReader("daily_mood_data.csv"))) {
            String line;
            DailyMood currentMood = null;
            boolean foundDate = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3) {
                    String date = parts[1];
                    if (date.equals(targetDate)) {
                        foundDate = true;
                        currentMood = new DailyMood(date);
                        try {
                            currentMood.setMood(MoodLevel.valueOf(parts[2]));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Warning: Invalid mood level in data. Using NEUTRAL instead.");
                            currentMood.setMood(MoodLevel.NEUTRAL);
                        }
                    } else if (foundDate) {
                        break;
                    }
                } else if (foundDate && currentMood != null && parts.length > 1) {
                    switch (parts[0]) {
                        case "SleepEntry" -> currentMood.addEntry(SleepEntry.fromCSV(parts[1]));
                        case "ProductivityEntry" -> currentMood.addEntry(ProductivityEntry.fromCSV(parts[1]));
                        case "WeatherEntry" -> currentMood.addEntry(WeatherEntry.fromCSV(parts[1]));
                        case "ExerciseEntry" -> currentMood.addEntry(ExerciseEntry.fromCSV(parts[1]));
                        case "ScreenTimeEntry" -> currentMood.addEntry(ScreenTimeEntry.fromCSV(parts[1]));
                        case "FoodEntry" -> currentMood.addEntry(FoodEntry.fromCSV(parts[1]));
                    }
                }
            }
            return currentMood;
        } catch (IOException e) {
            System.out.println("Error finding mood by date: " + e.getMessage());
            return null;
        }
    }
}