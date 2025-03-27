package org.example;

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
        String[] parts = csv.split(";");
        FoodEntry entry = new FoodEntry();
        entry.setFoodSatisfactionLevel(FoodSatisfactionLevel.valueOf(parts[0]));
        entry.setFoodDescription(parts[1]);
        return entry;
    }
}