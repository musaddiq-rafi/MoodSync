package org.example;

public class ProductivityEntry {
    private int productivityLevel;
    private String productivityDescription;

    public int getProductivityLevel(){
        return productivityLevel;
    }
    public int setProductivityLevel(int productivityLevel){
        this.productivityLevel = productivityLevel;
        return productivityLevel;
    }
    public String getProductivityDescription(){
        return productivityDescription;
    }
    public String setProductivityDescription(String productivityDescription){
        this.productivityDescription = productivityDescription;
        return productivityDescription;
    }

    public void setTasksCompleted(int tasksCompleted) {

    }
}
