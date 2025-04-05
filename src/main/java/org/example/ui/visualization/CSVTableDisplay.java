package org.example.ui.visualization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVTableDisplay {
    // ANSI escape codes for colors
    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String GREEN = "\u001B[32m";
    private static final String PURPLE = "\u001B[35m";
    private static final String RED = "\u001B[31m";

    // Inner class to hold data for each day
    private static class DailyMoodData {
        String timestamp;
        String date;
        String mood;
        List<String[]> entries = new ArrayList<>();
    }

    public void displayTable(String filename) {
        List<DailyMoodData> dailyMoodDataList = new ArrayList<>();
        DailyMoodData currentMood = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = reader.readLine()) != null) {
                String[] columns = line.split(";");

                // Check if this is a header line (timestamp;date;mood)
                if (columns.length == 3 && !line.startsWith("SleepEntry") &&
                        !line.startsWith("ProductivityEntry") && !line.startsWith("WeatherEntry") &&
                        !line.startsWith("ExerciseEntry") && !line.startsWith("ScreenTimeEntry") &&
                        !line.startsWith("FoodEntry")) {

                    // Create a new daily mood entry
                    currentMood = new DailyMoodData();
                    currentMood.timestamp = columns[0].trim();
                    currentMood.date = columns[1].trim();
                    currentMood.mood = columns[2].trim();
                    dailyMoodDataList.add(currentMood);
                }
                // If we have a current mood and this is an entry line
                else if (currentMood != null) {
                    currentMood.entries.add(columns);
                }
            }
        } catch (IOException e) {
            System.out.println(RED + "Error reading CSV file: " + e.getMessage() + RESET);
            return;
        }

        if (dailyMoodDataList.isEmpty()) {
            System.out.println(RED + "No data found in CSV file." + RESET);
            return;
        }

        // Display each day's data
        for (DailyMoodData dailyMood : dailyMoodDataList) {
            displayDailyMoodData(dailyMood);
            System.out.println(); // Add spacing between days
        }
    }

    private void displayDailyMoodData(DailyMoodData dailyMood) {

        int[] columnWidths = {20, 20, 20};
        for (String[] entry : dailyMood.entries) {
            for (int i = 0; i < Math.min(entry.length, 3); i++) {
                columnWidths[i] = Math.max(columnWidths[i], entry[i].length());
            }
        }


        System.out.println(GREEN + "=".repeat(60) + RESET);
        System.out.printf(GREEN + " Mood Log - Date: %s | Mood: %s%n" + RESET, dailyMood.date, dailyMood.mood);
        System.out.println(GREEN + "=".repeat(60) + RESET);

        // Print column headers with color
        String[] headers = {"Type", "Feel", "Note"};
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = Math.max(columnWidths[i], headers[i].length());
        }

        printSeparator(columnWidths);
        for (int i = 0; i < headers.length; i++) {
            System.out.print("| " + CYAN + padRight(headers[i], columnWidths[i]) + RESET + " ");
        }
        System.out.println("|");
        printSeparator(columnWidths);


        for (String[] row : dailyMood.entries) {
            for (int i = 0; i < Math.min(row.length, 3); i++) {
                System.out.print("| " + YELLOW + padRight(row[i], columnWidths[i]) + RESET + " ");
            }
            // Fill missing columns with empty space
            for (int i = row.length; i < 3; i++) {
                System.out.print("| " + YELLOW + padRight("", columnWidths[i]) + RESET + " ");
            }
            System.out.println("|");
        }
        printSeparator(columnWidths);
    }

    private void printSeparator(int[] columnWidths) {
        for (int width : columnWidths) {
            System.out.print("+");
            System.out.print("-".repeat(width + 2));
        }
        System.out.println("+");
    }

    private String padRight(String text, int length) {
        return String.format("%-" + length + "s", text);
    }
}
