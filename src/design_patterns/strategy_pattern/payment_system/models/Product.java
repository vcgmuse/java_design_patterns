package design_patterns.strategy_pattern.payment_system.models;

import java.util.Objects;

public class Product {
    private String upcCode;
    private String name; // Added for better display
    private int price;

    public Product(String upcCode, String name, int price) {
        this.upcCode = upcCode;
        this.name = name;
        this.price = price;
    }

    // Getters and Setters
    public String getUpcCode() {
        return upcCode;
    }

    public void setUpcCode(String upcCode) {
        this.upcCode = upcCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    // IMPORTANT: Override equals and hashCode for use as Map key (based on UPC code)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(upcCode, product.upcCode); // Equality based on UPC code
    }

    @Override
    public int hashCode() {
        return Objects.hash(upcCode); // Hash based on UPC code
    }
}
