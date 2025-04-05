package org.example.ui.visualization;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class CSVTableDisplay {
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
            System.out.println("Error reading CSV file: " + e.getMessage());
            return;
        }

        if (dailyMoodDataList.isEmpty()) {
            System.out.println("No data found in CSV file.");
            return;
        }

        // Display each day's data
        for (DailyMoodData dailyMood : dailyMoodDataList) {
            displayDailyMoodData(dailyMood);
            System.out.println(); // Add spacing between days
        }
    }

    private void displayDailyMoodData(DailyMoodData dailyMood) {
        if (dailyMood.entries.isEmpty()) {
            System.out.println("No entries for this day.");
            return;
        }

        // Calculate column widths
        int[] columnWidths = {20, 20, 20}; // Type, Feel, Note
        for (String[] entry : dailyMood.entries) {
            for (int i = 0; i < Math.min(entry.length, 3); i++) {
                columnWidths[i] = Math.max(columnWidths[i], entry[i].length());
            }
        }

        // Print mood header
        System.out.println("=".repeat(60));
        System.out.printf(" Mood Log - Date: %s | Mood: %s%n", dailyMood.date, dailyMood.mood);
        System.out.println("=".repeat(60));

        // Print column headers
        String[] headers = {"Type", "Feel", "Note"};
        for (int i = 0; i < headers.length; i++) {
            columnWidths[i] = Math.max(columnWidths[i], headers[i].length());
        }

        printSeparator(columnWidths);
        for (int i = 0; i < headers.length; i++) {
            System.out.print("| " + padRight(headers[i], columnWidths[i]) + " ");
        }
        System.out.println("|");
        printSeparator(columnWidths);

        // Print data rows
        for (String[] row : dailyMood.entries) {
            for (int i = 0; i < Math.min(row.length, 3); i++) {
                System.out.print("| " + padRight(row[i], columnWidths[i]) + " ");
            }
            // Fill missing columns with empty space
            for (int i = row.length; i < 3; i++) {
                System.out.print("| " + padRight("", columnWidths[i]) + " ");
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