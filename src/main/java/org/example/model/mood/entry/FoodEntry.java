package org.example.model.mood.entry;

import org.example.model.mood.level.FoodSatisfactionLevel;
import org.example.model.mood.LogEntry;

public class FoodEntry extends LogEntry {
    private FoodSatisfactionLevel foodSatisfactionLevel;
    private String foodDescription;

    public FoodSatisfactionLevel getFoodSatisfactionLevel() {
        return foodSatisfactionLevel;
    }

    public void setFoodSatisfactionLevel(FoodSatisfactionLevel foodSatisfactionLevel) {
        this.foodSatisfactionLevel = foodSatisfactionLevel;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public String toCSV() {
        return foodSatisfactionLevel + ";" + foodDescription;
    }

    public static FoodEntry fromCSV(String csv) {
        String[] parts = csv.split(";", 2); // <-- split only into 2 parts
        FoodEntry entry = new FoodEntry();
        entry.setFoodSatisfactionLevel(FoodSatisfactionLevel.valueOf(parts[0]));
        entry.setFoodDescription(parts[1]);
        return entry;
    }

}