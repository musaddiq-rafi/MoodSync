package org.example.service.mood;

import org.example.service.repository.DataManager;
import org.example.model.mood.DailyMood;
import org.example.model.mood.LogEntry;
import org.example.model.mood.entry.*;
import org.example.model.mood.level.MoodLevel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

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
            System.out.println("║     MOOD ANALYSIS: " + date + " (" + dayName + ")║");
            System.out.println("╚══════════════════════════════════════════╝");

            // Current mood status
            System.out.println("\n📊 MOOD STATUS: " + mood.getMood().getDescription());

            // Analyze all entry types
            analyzeEntries(mood);

            // Find correlations with historical data
         //   findCorrelations(mood, dataManager);

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
            System.out.println(" \uD83D\uDCDD  What you wrote about your sleep that day : " + entry.getSleepMessage());

            switch (entry.getSleepQuality()) {
                case EXCELLENT -> System.out.println("   🌟 Great sleep! Keep your routine consistent.");
                case GOOD -> System.out.println("   😊 Good sleep boosts your mood. Stay consistent.");
                case AVERAGE -> System.out.println("   😐 Consider reducing screen time before bed.");
                case POOR -> System.out.println("   😟 Try relaxing activities before sleep like reading.");
                case TERRIBLE -> System.out.println("   ⚠️ Poor sleep may be affecting your mood. Reflect on your nighttime habits.");
            }
        } else {
            System.out.println("🛌 Sleep: No data");
        }

        // Exercise analysis
        if (mood.isHasExerciseEntry()) {
            ExerciseEntry entry = findEntry(mood, ExerciseEntry.class);
            assert entry != null;
            System.out.println("🏃 Exercise: " + entry.getExerciseLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  What you wrote about your exercise that day : " + entry.getExerciseDescription());

            switch (entry.getExerciseLevel()) {
                case NONE -> System.out.println("   🧘 Consider light stretching or a walk to get started.");
                case LIGHT -> System.out.println("   👍 Light exercise is a good foundation. Keep it up!");
                case MODERATE -> System.out.println("   💪 You're maintaining a healthy level of activity.");
                case INTENSE -> System.out.println("   🔥 Impressive! Don’t forget to rest and hydrate.");
                case EXTREME -> System.out.println("   🚀 Extreme training—make sure you're not overexerting.");
            }
        } else {
            System.out.println("🏃 Exercise: No data");
        }

        // Food analysis
        if (mood.isHasFoodEntry()) {
            FoodEntry entry = findEntry(mood, FoodEntry.class);
            assert entry != null;
            System.out.println("🍽️ Food: " + entry.getFoodSatisfactionLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  What you wrote about your food that day : " + entry.getFoodDescription());

            switch (entry.getFoodSatisfactionLevel()) {
                case EXCELLENT -> System.out.println("   🥗 You're fueling your body well. Keep choosing nourishing meals.");
                case GOOD -> System.out.println("   😊 Good meals contribute to positive energy and mood.");
                case AVERAGE -> System.out.println("   🍱 Consider adding more variety or nutrients.");
                case POOR -> System.out.println("   😞 Unfulfilling meals may be affecting your energy levels.");
                case TERRIBLE -> System.out.println("   🚫 Try to prioritize a balanced meal, even if you're busy.");
            }
        } else {
            System.out.println("🍽️ Food: No data");
        }

        // Weather analysis
        if (mood.isHasWeatherEntry()) {
            SleepEntry.WeatherEntry entry = findEntry(mood, SleepEntry.WeatherEntry.class);
            assert entry != null;
            System.out.println("☁️ Weather: " + entry.getWeatherLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  What you wrote about your weather that day : " + entry.getWeatherDescription());

            switch (entry.getWeatherLevel()) {
                case SUNNY -> System.out.println("   ☀️ Great day for outdoor activities!");
                case CLOUDY -> System.out.println("   🌥️ Cloudy days can feel slow—try brightening your space.");
                case RAINY -> System.out.println("   🌧️ Rainy mood? Maybe enjoy something cozy indoors.");
                case STORMY -> System.out.println("   ⛈️ Stormy weather—stay safe and practice self-care.");
                case SNOWY -> System.out.println("   ❄️ Cold and snowy—warm drinks and comfort go a long way.");
            }
        } else {
            System.out.println("☁️ Weather: No data");
        }

        // Productivity analysis
        if (mood.isHasProductivityEntry()) {
            ProductivityEntry entry = findEntry(mood, ProductivityEntry.class);
            assert entry != null;
            System.out.println("📈 Productivity: " + entry.getProductivityLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  What you wrote about your productivity that day : " + entry.getProductivityDescription());

            switch (entry.getProductivityLevel()) {
                case EXTREMELY_PRODUCTIVE -> System.out.println("   🚀 You crushed it today! Celebrate your wins.");
                case PRODUCTIVE -> System.out.println("   💪 Solid day! Keep building that momentum.");
                case NEUTRAL -> System.out.println("   😐 Maybe a small goal tomorrow can spark motivation.");
                case UNPRODUCTIVE -> System.out.println("   😓 Everyone has off-days. Try a small win tomorrow.");
                case EXTREMELY_UNPRODUCTIVE -> System.out.println("   😴 Reflect on what slowed you down. Rest is productive too.");
            }
        } else {
            System.out.println("📈 Productivity: No data");
        }

        // Screen time analysis
        if (mood.isHasScreenTimeEntry()) {
            ScreenTimeEntry entry = findEntry(mood, ScreenTimeEntry.class);
            assert entry != null;
            System.out.println("📱 Screen Time: " + entry.getScreenTimeLevel().getDescription());
            System.out.println(" \uD83D\uDCDD  What you wrote about your screen time that day : " + entry.getScreenTimeDescription());

            switch (entry.getScreenTimeLevel()) {
                case NONE -> System.out.println("   👏 Great job staying off screens!");
                case LOW -> System.out.println("   👍 Balanced screen use is good for mental clarity.");
                case MODERATE -> System.out.println("   ⚖️ Keep an eye on usage, especially before bed.");
                case HIGH -> System.out.println("   📉 Consider taking screen breaks during the day.");
                case EXCESSIVE -> System.out.println("   ⚠️ High screen time can affect mood and sleep. Try unplugging a bit.");
            }
        } else {
            System.out.println("📱 Screen Time: No data");
        }
    }



    private static void provideRecommendations(DailyMood mood) {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("💡OVERALL MOOD RECOMMENDATIONS:");
        System.out.println("╚══════════════════════════════════════════╝");

        MoodLevel currentMood = mood.getMood();
        System.out.println("\nYour overall mood is: " + currentMood.getDescription());

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