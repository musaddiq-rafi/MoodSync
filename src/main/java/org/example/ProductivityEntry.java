package org.example;

public class ProductivityEntry extends LogEntry {
    private ProductivityLevel productivityLevel;
    private String productivityDescription;

    public ProductivityLevel getProductivityLevel() {
        return productivityLevel;
    }

    public void setProductivityLevel(ProductivityLevel productivityLevel) {
        this.productivityLevel = productivityLevel;
    }

    public String getProductivityDescription() {
        return productivityDescription;
    }

    public void setProductivityDescription(String productivityDescription) {
        this.productivityDescription = productivityDescription;
    }

    public String toCSV() {
        return productivityLevel + ";" + productivityDescription;
    }

    public static ProductivityEntry fromCSV(String csv) {
        String[] parts = csv.split(";");
        ProductivityEntry entry = new ProductivityEntry();
        entry.setProductivityLevel(ProductivityLevel.valueOf(parts[0]));
        entry.setProductivityDescription(parts[1]);
        return entry;
    }
}