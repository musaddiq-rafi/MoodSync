package org.example.ui;

import org.example.model.mood.DailyMood;
import org.example.model.mood.entry.*;
import org.example.model.mood.level.*;

import java.util.Scanner;

public class EntryHandler {

    private static final String RESET = "\u001B[0m";
    private static final String CYAN = "\u001B[36m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String PURPLE = "\u001B[35m";
    private static final String BOLD = "\033[1m";

    private Scanner scanner;

    public EntryHandler(Scanner scanner) {
        this.scanner = scanner;
    }

    private static void printHeader(String sectionTitle) {
        System.out.println(BOLD + PURPLE +
                "\n========================================");
        System.out.println(" MoodSync – Savor the favor of your 'Mental' health ");
        System.out.println(" " + sectionTitle);
        System.out.println("========================================" + RESET);
    }

    public static void addSleepEntry(Scanner scanner, DailyMood dailyMood) {
        printHeader("😴 Sleep Entry – Sleep deeply, feel better!");

        System.out.println(CYAN + "Your sleep directly affects your emotional stability. Be honest! 💤" + RESET);

        scanner.nextLine(); // Clear buffer

        System.out.println("Select sleep quality:");
        System.out.println("1. " + SleepQualities.EXCELLENT.getDescription());
        System.out.println("2. " + SleepQualities.GOOD.getDescription());
        System.out.println("3. " + SleepQualities.AVERAGE.getDescription());
        System.out.println("4. " + SleepQualities.POOR.getDescription());
        System.out.println("5. " + SleepQualities.TERRIBLE.getDescription());

        int qualityChoice = scanner.nextInt();
        scanner.nextLine();

        SleepQualities sleepQuality = switch (qualityChoice) {
            case 1 -> SleepQualities.EXCELLENT;
            case 2 -> SleepQualities.GOOD;
            case 3 -> SleepQualities.AVERAGE;
            case 4 -> SleepQualities.POOR;
            case 5 -> SleepQualities.TERRIBLE;
            default -> SleepQualities.AVERAGE;
        };

        System.out.print(YELLOW + "Enter your thoughts on sleep today: " + RESET);
        String message = scanner.nextLine();

        SleepEntry entry = new SleepEntry();
        entry.setSleepQuality(sleepQuality);
        entry.setSleepMessage(message);

        if (dailyMood.addEntry(entry)) {
            System.out.println(GREEN + "✓ Sleep entry added successfully!" + RESET);
        } else {
            System.out.println("❌ Failed to add sleep entry.");
        }
    }

    public static void addProductivityEntry(Scanner scanner, DailyMood dailyMood) {
        printHeader("💼 Productivity Entry – Your wins matter!");

        System.out.println(CYAN + "Being productive boosts confidence and lifts your mood ☀️" + RESET);

        System.out.println("Select productivity level:");
        System.out.println("1. " + ProductivityLevel.EXTREMELY_PRODUCTIVE.getDescription());
        System.out.println("2. " + ProductivityLevel.PRODUCTIVE.getDescription());
        System.out.println("3. " + ProductivityLevel.NEUTRAL.getDescription());
        System.out.println("4. " + ProductivityLevel.UNPRODUCTIVE.getDescription());
        System.out.println("5. " + ProductivityLevel.EXTREMELY_UNPRODUCTIVE.getDescription());

        int choice = scanner.nextInt();
        scanner.nextLine();

        ProductivityLevel level = switch (choice) {
            case 1 -> ProductivityLevel.EXTREMELY_PRODUCTIVE;
            case 2 -> ProductivityLevel.PRODUCTIVE;
            case 3 -> ProductivityLevel.NEUTRAL;
            case 4 -> ProductivityLevel.UNPRODUCTIVE;
            case 5 -> ProductivityLevel.EXTREMELY_UNPRODUCTIVE;
            default -> ProductivityLevel.NEUTRAL;
        };

        System.out.print(YELLOW + "Describe what you accomplished today: " + RESET);
        String description = scanner.nextLine();

        ProductivityEntry entry = new ProductivityEntry();
        entry.setProductivityLevel(level);
        entry.setProductivityDescription(description);

        dailyMood.addEntry(entry);
        System.out.println(GREEN + "✓ Productivity entry added!" + RESET);
    }

    public static void addWeatherEntry(Scanner scanner, DailyMood dailyMood) {
        printHeader("⛅ Weather Entry – Nature's effect on mood");

        System.out.println(CYAN + "Weather affects your energy and emotions – track it mindfully 🌦️" + RESET);

        System.out.println("Select weather type:");
        System.out.println("1. " + WeatherLevel.SUNNY.getDescription());
        System.out.println("2. " + WeatherLevel.CLOUDY.getDescription());
        System.out.println("3. " + WeatherLevel.RAINY.getDescription());
        System.out.println("4. " + WeatherLevel.STORMY.getDescription());
        System.out.println("5. " + WeatherLevel.SNOWY.getDescription());

        int choice = scanner.nextInt();
        scanner.nextLine();

        WeatherLevel level = switch (choice) {
            case 1 -> WeatherLevel.SUNNY;
            case 2 -> WeatherLevel.CLOUDY;
            case 3 -> WeatherLevel.RAINY;
            case 4 -> WeatherLevel.STORMY;
            case 5 -> WeatherLevel.SNOWY;
            default -> WeatherLevel.SUNNY;
        };

        System.out.print(YELLOW + "Describe today’s weather: " + RESET);
        String description = scanner.nextLine();

        SleepEntry.WeatherEntry entry = new SleepEntry.WeatherEntry();
        entry.setWeatherLevel(level);
        entry.setWeatherDescription(description);

        dailyMood.addEntry(entry);
        System.out.println(GREEN + "✓ Weather entry added!" + RESET);
    }

    public static void addExerciseEntry(Scanner scanner, DailyMood dailyMood) {
        printHeader("🏃 Exercise Entry – Move for a better mood!");

        System.out.println(CYAN + "Movement boosts endorphins. Even a little matters! 💪" + RESET);

        System.out.println("Select exercise level:");
        System.out.println("1. " + ExerciseLevel.NONE.getDescription());
        System.out.println("2. " + ExerciseLevel.LIGHT.getDescription());
        System.out.println("3. " + ExerciseLevel.MODERATE.getDescription());
        System.out.println("4. " + ExerciseLevel.INTENSE.getDescription());
        System.out.println("5. " + ExerciseLevel.EXTREME.getDescription());

        int choice = scanner.nextInt();
        scanner.nextLine();

        ExerciseLevel level = switch (choice) {
            case 1 -> ExerciseLevel.NONE;
            case 2 -> ExerciseLevel.LIGHT;
            case 3 -> ExerciseLevel.MODERATE;
            case 4 -> ExerciseLevel.INTENSE;
            case 5 -> ExerciseLevel.EXTREME;
            default -> ExerciseLevel.NONE;
        };

        System.out.print(YELLOW + "Describe your physical activity today: " + RESET);
        String description = scanner.nextLine();

        ExerciseEntry entry = new ExerciseEntry();
        entry.setExerciseLevel(level);
        entry.setExerciseDescription(description);

        dailyMood.addEntry(entry);
        System.out.println(GREEN + "✓ Exercise entry added!" + RESET);
    }

    public static void addFoodEntry(Scanner scanner, DailyMood dailyMood) {
        printHeader("🍱 Food Entry – Nourish to flourish!");

        System.out.println(CYAN + "What you eat can shift your energy and focus. Be mindful 🍓" + RESET);

        System.out.println("Select food satisfaction level:");
        System.out.println("1. " + FoodSatisfactionLevel.EXCELLENT.getDescription());
        System.out.println("2. " + FoodSatisfactionLevel.GOOD.getDescription());
        System.out.println("3. " + FoodSatisfactionLevel.AVERAGE.getDescription());
        System.out.println("4. " + FoodSatisfactionLevel.POOR.getDescription());
        System.out.println("5. " + FoodSatisfactionLevel.TERRIBLE.getDescription());

        int choice = scanner.nextInt();
        scanner.nextLine();

        FoodSatisfactionLevel level = switch (choice) {
            case 1 -> FoodSatisfactionLevel.EXCELLENT;
            case 2 -> FoodSatisfactionLevel.GOOD;
            case 3 -> FoodSatisfactionLevel.AVERAGE;
            case 4 -> FoodSatisfactionLevel.POOR;
            case 5 -> FoodSatisfactionLevel.TERRIBLE;
            default -> FoodSatisfactionLevel.AVERAGE;
        };

        System.out.print(YELLOW + "Write what you ate and how it felt: " + RESET);
        String description = scanner.nextLine();

        FoodEntry entry = new FoodEntry();
        entry.setFoodSatisfactionLevel(level);
        entry.setFoodDescription(description);

        dailyMood.addEntry(entry);
        System.out.println(GREEN + "✓ Food entry added!" + RESET);
    }

    public static void addScreenTimeEntry(Scanner scanner, DailyMood dailyMood) {
        printHeader("📱 Screen Time Entry – Balance is key");

        System.out.println(CYAN + "Too much screen time can drain you. Track it for better balance 📵" + RESET);

        System.out.println("Select screen time level:");
        System.out.println("1. " + ScreenTimeLevel.NONE.getDescription());
        System.out.println("2. " + ScreenTimeLevel.LOW.getDescription());
        System.out.println("3. " + ScreenTimeLevel.MODERATE.getDescription());
        System.out.println("4. " + ScreenTimeLevel.HIGH.getDescription());
        System.out.println("5. " + ScreenTimeLevel.EXCESSIVE.getDescription());

        int choice = scanner.nextInt();
        scanner.nextLine();

        ScreenTimeLevel level = switch (choice) {
            case 1 -> ScreenTimeLevel.NONE;
            case 2 -> ScreenTimeLevel.LOW;
            case 3 -> ScreenTimeLevel.MODERATE;
            case 4 -> ScreenTimeLevel.HIGH;
            case 5 -> ScreenTimeLevel.EXCESSIVE;
            default -> ScreenTimeLevel.NONE;
        };

        System.out.print(YELLOW + "Describe your screen habits today: " + RESET);
        String description = scanner.nextLine();

        ScreenTimeEntry entry = new ScreenTimeEntry();
        entry.setScreenTimeLevel(level);
        entry.setScreenTimeDescription(description);

        dailyMood.addEntry(entry);
        System.out.println(GREEN + "✓ Screen time entry added!" + RESET);
    }
}
