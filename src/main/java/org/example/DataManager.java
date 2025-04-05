package org.example;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataManager {
    private static final String DATA_FILE = "daily_mood_data.csv";

    public void saveDailyMoodToFile(DailyMood dailyMood) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(DATA_FILE, true))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
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
        tableDisplay.displayTable(DATA_FILE);
    }

    public List<DailyMood> loadAllDailyMoods() {
        List<DailyMood> allMoods = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            DailyMood currentMood = null;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && !isEntryType(parts[0])) {
                    currentMood = new DailyMood(parts[1]);
                    try {
                        currentMood.setMood(MoodLevel.valueOf(parts[2].trim()));
                    } catch (IllegalArgumentException e) {
                        System.out.println("Warning: Invalid mood level '" + parts[2] + "' in data. Skipping entry.");
                        continue;
                    }
                    allMoods.add(currentMood);
                } else if (currentMood != null && isEntryType(parts[0]) && parts.length >= 2) {
                    String entryType = parts[0];
                    String entryCSV = getEntryCSV(parts);

                    try {
                        switch (entryType) {
                            case "SleepEntry" -> currentMood.addEntry(SleepEntry.fromCSV(entryCSV));
                            case "ProductivityEntry" -> currentMood.addEntry(ProductivityEntry.fromCSV(entryCSV));
                            case "WeatherEntry" -> currentMood.addEntry(WeatherEntry.fromCSV(entryCSV));
                            case "ExerciseEntry" -> currentMood.addEntry(ExerciseEntry.fromCSV(entryCSV));
                            case "ScreenTimeEntry" -> currentMood.addEntry(ScreenTimeEntry.fromCSV(entryCSV));
                            case "FoodEntry" -> currentMood.addEntry(FoodEntry.fromCSV(entryCSV));
                        }
                    } catch (Exception e) {
                        System.out.println("Error parsing entry: " + e.getMessage());
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
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && !isEntryType(parts[0])) {
                    uniqueDates.add(parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading dates: " + e.getMessage());
        }
        return new ArrayList<>(uniqueDates);
    }

    public DailyMood findMoodByDate(String targetDate) {
        try (BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE))) {
            String line;
            DailyMood currentMood = null;
            boolean foundDate = false;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";");
                if (parts.length == 3 && !isEntryType(parts[0])) {
                    String date = parts[1].trim();
                    if (date.equals(targetDate)) {
                        foundDate = true;
                        currentMood = new DailyMood(date);
                        try {
                            currentMood.setMood(MoodLevel.valueOf(parts[2].trim()));
                        } catch (IllegalArgumentException e) {
                            System.out.println("Warning: Invalid mood level in data. Using NEUTRAL instead.");
                            currentMood.setMood(MoodLevel.NEUTRAL);
                        }
                    } else if (foundDate) {
                        break;
                    }
                } else if (foundDate && currentMood != null && isEntryType(parts[0]) && parts.length >= 2) {
                    String entryType = parts[0];
                    String entryCSV = getEntryCSV(parts);

                    try {
                        switch (entryType) {
                            case "SleepEntry" -> currentMood.addEntry(SleepEntry.fromCSV(entryCSV));
                            case "ProductivityEntry" -> currentMood.addEntry(ProductivityEntry.fromCSV(entryCSV));
                            case "WeatherEntry" -> currentMood.addEntry(WeatherEntry.fromCSV(entryCSV));
                            case "ExerciseEntry" -> currentMood.addEntry(ExerciseEntry.fromCSV(entryCSV));
                            case "ScreenTimeEntry" -> currentMood.addEntry(ScreenTimeEntry.fromCSV(entryCSV));
                            case "FoodEntry" -> currentMood.addEntry(FoodEntry.fromCSV(entryCSV));
                        }
                    } catch (Exception e) {
                        System.out.println("Error parsing entry: " + e.getMessage());
                    }
                }
            }
            return currentMood;
        } catch (IOException e) {
            System.out.println("Error finding mood by date: " + e.getMessage());
            return null;
        }
    }

    private boolean isEntryType(String value) {
        return switch (value.trim()) {
            case "SleepEntry", "ProductivityEntry", "WeatherEntry",
                 "ExerciseEntry", "ScreenTimeEntry", "FoodEntry" -> true;
            default -> false;
        };
    }

    private String getEntryCSV(String[] parts) {
        // Joins everything after the first part into a single CSV string
        if (parts.length < 2) return "";
        return String.join(";", Arrays.copyOfRange(parts, 1, parts.length));
    }
}
