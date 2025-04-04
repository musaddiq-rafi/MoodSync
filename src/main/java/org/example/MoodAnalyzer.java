package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.HashMap;

public class MoodAnalyzer {

    public static void analyzeMood(DailyMood currentMood, DataManager dataManager) {
        String date = currentMood.getDate();
        analyzeSpecificDate(currentMood, date, dataManager);
    }

    public static void analyzeSpecificDate(DailyMood mood, String date, DataManager dataManager) {
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
            LocalDate parsedDate = LocalDate.parse(date, formatter);
            String dayName = parsedDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.getDefault());

            System.out.println("\n╔══════════════════════════════════════════╗");
            System.out.println("║     MOOD ANALYSIS: " + date + " (" + dayName + ")     ║");
            System.out.println("╚══════════════════════════════════════════╝");

            // Current mood status
            System.out.println("\n📊 MOOD STATUS: " + mood.getMood().getDescription());

            // Analyze all entry types
            analyzeEntries(mood);

            // Find correlations with historical data
            findCorrelations(mood, dataManager);

            // Provide personalized recommendations
            provideRecommendations(mood);

        } catch (DateTimeParseException e) {
            System.out.println("Error: Invalid date format. Please use the format YYYY-MM-DD.");
        } catch (Exception e) {
            System.out.println("Error analyzing mood for date: " + date);
            e.printStackTrace();
        }
    }


    private static void analyzeEntries(DailyMood mood) {
        System.out.println("\n📝 DAILY ENTRIES ANALYSIS:");

        // Sleep analysis
        if (mood.isHasSleepEntry()) {
            SleepEntry entry = findEntry(mood, SleepEntry.class);
            assert entry != null;
            System.out.println("🛌 Sleep: " + entry.getSleepQuality().getDescription());
            System.out.println("   Hours: " + entry.getHours());

            if (entry.getHours() < 7) {
                System.out.println("   ⚠️ Sleep deficit may be affecting your mood");
            } else if (entry.getHours() > 9) {
                System.out.println("   ℹ️ Oversleeping can sometimes affect energy levels");
            }
        } else {
            System.out.println("🛌 Sleep: No data");
        }

        // Exercise analysis
        if (mood.isHasExerciseEntry()) {
            ExerciseEntry entry = findEntry(mood, ExerciseEntry.class);
            assert entry != null;
            System.out.println("🏃 Exercise: " + entry.getExerciseLevel().getDescription());

            if (mood.getMood().ordinal() < 2 && entry.getExerciseLevel() == ExerciseLevel.NONE) {
                System.out.println("   💡 Exercise can help improve negative moods");
            }
        } else {
            System.out.println("🏃 Exercise: No data");
        }

        // Food analysis
        if (mood.isHasFoodEntry()) {
            FoodEntry entry = findEntry(mood, FoodEntry.class);
            assert entry != null;
            System.out.println("🍽️ Food: " + entry.getFoodSatisfactionLevel());
            System.out.println("   Description: " + entry.getFoodDescription());
        } else {
            System.out.println("🍽️ Food: No data");
        }

        // Weather analysis
        if (mood.isHasWeatherEntry()) {
            WeatherEntry entry = findEntry(mood, WeatherEntry.class);
            assert entry != null;
            System.out.println("☁️ Weather: " + entry.getWeatherLevel().getDescription());
        } else {
            System.out.println("☁️ Weather: No data");
        }

        // Productivity analysis
        if (mood.isHasProductivityEntry()) {
            ProductivityEntry entry = findEntry(mood, ProductivityEntry.class);
            assert entry != null;
            System.out.println("📈 Productivity: " + entry.getProductivityLevel().getDescription());
        } else {
            System.out.println("📈 Productivity: No data");
        }

        // Screen time analysis
        if (mood.isHasScreenTimeEntry()) {
            ScreenTimeEntry entry = findEntry(mood, ScreenTimeEntry.class);
            System.out.println("📱 Screen Time: " + entry.getScreenTimeLevel().getDescription());
        } else {
            System.out.println("📱 Screen Time: No data");
        }
    }

    private static void findCorrelations(DailyMood currentMood, DataManager dataManager) {
        System.out.println("\n🔍 PERSONAL INSIGHTS:");

        // Load historical data
        List<DailyMood> allMoods = dataManager.loadAllDailyMoods();

        // This would be expanded with actual correlation logic using historical data
        // For now, providing some placeholder insights

        if (currentMood.isHasExerciseEntry() && currentMood.isHasSleepEntry()) {
            ExerciseEntry exerciseEntry = findEntry(currentMood, ExerciseEntry.class);
            SleepEntry sleepEntry = findEntry(currentMood, SleepEntry.class);

            assert exerciseEntry != null;
            if (exerciseEntry.getExerciseLevel().ordinal() >= ExerciseLevel.MODERATE.ordinal()) {
                assert sleepEntry != null;
                if (sleepEntry.getSleepQuality().ordinal() >= 2) {
                    System.out.println("• Exercise and good sleep appear to positively affect your mood");
                }
            }
        }

        System.out.println("• For more detailed correlations, continue tracking your moods daily");
    }

    private static void provideRecommendations(DailyMood mood) {
        System.out.println("\n💡 RECOMMENDATIONS:");

        MoodLevel currentMood = mood.getMood();
        int moodOrdinal = currentMood.ordinal();

        if (moodOrdinal <= 1) { // TERRIBLE or BAD
            System.out.println("• Consider talking to someone about how you're feeling");
            System.out.println("• Try a short walk outside to clear your mind");
            System.out.println("• Practice mindfulness or deep breathing for 5 minutes");

        } else if (moodOrdinal == 2) { // NEUTRAL
            System.out.println("• Try adding a new activity to your routine today");
            System.out.println("• Connect with a friend or family member");
            System.out.println("• Consider limiting screen time before bed");

        } else { // GOOD or EXCELLENT
            System.out.println("• Keep up the good habits that are working for you!");
            System.out.println("• Share your positive energy with others");
            System.out.println("• Journal about what went well today to remember later");
        }
    }

    @SuppressWarnings("unchecked")
    private static <T extends LogEntry> T findEntry(DailyMood mood, Class<T> type) {
        for (LogEntry entry : mood.getEntries()) {
            if (type.isInstance(entry)) {
                return (T) entry;
            }
        }
        return null;
    }
}