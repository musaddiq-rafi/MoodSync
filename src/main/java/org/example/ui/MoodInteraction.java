package org.example.ui;

import org.example.model.mood.DailyMood;
import org.example.model.mood.level.MoodLevel;
import org.example.service.mood.MoodAnalyzer;
import org.example.service.mood.MoodSyncDoctor;
import org.example.service.mood.MoodTracker;
import org.example.service.repository.DataManager;

import java.util.List;
import java.util.Scanner;

public class MoodInteraction {

    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";
    private static final String RED = "\u001B[31m";

    public static void inputDate(Scanner scanner, MoodTracker moodTracker, DataManager dataManager) {
        System.out.print(YELLOW + "\n📅 Enter date (YYYY-MM-DD): " + RESET);
        String date = scanner.nextLine();
        DailyMood dailyMood = new DailyMood(date);

        // Introduction to the app as a mental health companion
        System.out.println(GREEN + "\n🧠 Welcome to MoodSync, your personal mental health companion." + RESET);
        System.out.println(CYAN + "💡 Please be honest and thoughtful while entering your mood and other data. Your mental health journey is important." + RESET);
        System.out.println(PURPLE + "💬 Remember, the more detailed and careful your entries are, the more insights we can provide for you!" + RESET);

        System.out.println("\nHow are you feeling today?");
        for (MoodLevel mood : MoodLevel.values()) {
            System.out.println("  " + (mood.ordinal() + 1) + ". " + mood.getDescription());
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
            System.out.println(CYAN + "\n🧾 DAILY MOOD MENU" + RESET);
            System.out.println("------------------------");
            System.out.println("1️⃣  Add Sleep Entry");
            System.out.println("2️⃣  Add Productivity Entry");
            System.out.println("3️⃣  Add Weather Entry");
            System.out.println("4️⃣  Add Exercise Entry");
            System.out.println("5️⃣  Add Food Entry");
            System.out.println("6️⃣  Add Screen Time Entry");
            System.out.println("7️⃣  Analyze Mood");
            System.out.println("8️⃣  Talk to MoodSync Doctor");
            System.out.println("9️⃣  Show Saved Data");
            System.out.println("🔟  Show Data in Table");
            System.out.println("0️⃣  Exit & Save");

            System.out.print(YELLOW + "\nChoose an option: " + RESET);
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
                case 0 -> {
                    dataManager.saveDailyMoodToFile(dailyMood);
                    System.out.println(GREEN + "✅ Mood entry saved. See you tomorrow! 💫" + RESET);
                    exit = true;
                }
                default -> System.out.println(RED + "❌ Invalid choice. Try again." + RESET);
            }
        }
    }

    private static void showMoodAnalyzerMenu(Scanner scanner, DataManager dataManager) {
        System.out.println(PURPLE + "\n📊 MOOD ANALYZER" + RESET);
        System.out.println("-----------------------");

        List<String> availableDates = dataManager.getAvailableDates();

        if (availableDates.isEmpty()) {
            System.out.println(RED + "⚠️  No mood entries found. Please add entries first." + RESET);
            return;
        }

        System.out.println("📅 Select a date to analyze:");
        for (int i = 0; i < availableDates.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + availableDates.get(i));
        }

        System.out.print(YELLOW + "\nYour choice: " + RESET);
        try {
            int selection = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (selection >= 1 && selection <= availableDates.size()) {
                String selectedDate = availableDates.get(selection - 1);
                DailyMood selectedMood = dataManager.findMoodByDate(selectedDate);

                if (selectedMood != null) {
                    MoodAnalyzer.analyzeMood(selectedMood, dataManager);
                } else {
                    System.out.println(RED + "❌ Error loading mood data for " + selectedDate + RESET);
                }
            } else {
                System.out.println(RED + "❌ Invalid selection. Try again." + RESET);
            }
        } catch (Exception e) {
            System.out.println(RED + "❌ Invalid input. Please enter a number." + RESET);
            scanner.nextLine(); // Clear buffer
        }
    }
}
