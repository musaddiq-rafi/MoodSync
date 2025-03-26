package org.example;

public class FoodEntry {
    private String healthyFoodName;
    private String otherFoodName;
    private int foodHabitRating;

    public String getHealthyFoodName(){
        return healthyFoodName;
    }

    public void setHealthyFoodName(String healthyFoodName) {
        this.healthyFoodName = healthyFoodName;
    }

    public String getOtherFoodName() {
        return otherFoodName;
    }

    public void setOtherFoodName(String otherFoodName) {
        this.otherFoodName = otherFoodName;
    }

    public int getFoodHabitRating() {
        return foodHabitRating;
    }

    public void setFoodHabitRating(int foodHabitRating) {
        this.foodHabitRating = foodHabitRating;
    }
}
