package org.example;

import java.util.List;
import java.util.Scanner;

public class MoodInteraction {
    public static void inputDate(Scanner scanner, MoodTracker moodTracker, DataManager dataManager) {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        DailyMood dailyMood = new DailyMood(date);

        System.out.println("How are you feeling today?");
        for (MoodLevel mood : MoodLevel.values()) {
            System.out.println((mood.ordinal() + 1) + ". " + mood.getDescription());
        }

        int moodChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        MoodLevel moodLevel = (moodChoice >= 1 && moodChoice <= MoodLevel.values().length)
                ? MoodLevel.values()[moodChoice - 1]
                : MoodLevel.NEUTRAL;

        dailyMood.setMood(moodLevel);
        displayInnerMenu(scanner, moodTracker, dailyMood, dataManager);
    }

    private static void displayInnerMenu(Scanner scanner, MoodTracker moodTracker, DailyMood dailyMood, DataManager dataManager) {
        boolean exit = false;

        while (!exit) {
            System.out.println("1. Add Sleep Entry");
            System.out.println("2. Add Productivity Entry");
            System.out.println("3. Add Weather Entry");
            System.out.println("4. Add Exercise Entry");
            System.out.println("5. Add Food Entry");
            System.out.println("6. Add Screen Time Entry");
            System.out.println("7. Analyze Mood");
            System.out.println("8. Talk to MoodSync Doctor");
            System.out.println("9. Show Saved Data");
            System.out.println("10. Show Data in Table");
            System.out.println("11. Exit");

            int menuChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (menuChoice) {
                case 1 -> EntryHandler.addSleepEntry(scanner, dailyMood);
                case 2 -> EntryHandler.addProductivityEntry(scanner, dailyMood);
                case 3 -> EntryHandler.addWeatherEntry(scanner, dailyMood);
                case 4 -> EntryHandler.addExerciseEntry(scanner, dailyMood);
                case 5 -> EntryHandler.addFoodEntry(scanner, dailyMood);
                case 6 -> EntryHandler.addScreenTimeEntry(scanner, dailyMood);
                case 7 -> showMoodAnalyzerMenu(scanner, dataManager);
                case 8 -> MoodSyncDoctor.startSession(scanner);
                case 9 -> dataManager.showSavedData();
                case 10 -> dataManager.displayTable();
                case 11 -> {
                    dataManager.saveDailyMoodToFile(dailyMood);
                    exit = true;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private static void showMoodAnalyzerMenu(Scanner scanner, DataManager dataManager) {
        System.out.println("\n📊 MOOD ANALYZER");
        System.out.println("------------------");

        List<String> availableDates = dataManager.getAvailableDates();

        if (availableDates.isEmpty()) {
            System.out.println("No mood entries found. Please add entries first.");
            return;
        }

        System.out.println("Select a date to analyze:");
        for (int i = 0; i < availableDates.size(); i++) {
            System.out.println((i + 1) + ". " + availableDates.get(i));
        }

        try {
            int selection = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (selection >= 1 && selection <= availableDates.size()) {
                String selectedDate = availableDates.get(selection - 1);
                DailyMood selectedMood = dataManager.findMoodByDate(selectedDate);

                if (selectedMood != null) {
                    MoodAnalyzer.analyzeMood(selectedMood, dataManager);
                } else {
                    System.out.println("Error: Failed to load mood data for " + selectedDate);
                }
            } else {
                System.out.println("Invalid selection. Please try again.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a number.");
            scanner.nextLine(); // Clear the scanner buffer
        }
    }
}