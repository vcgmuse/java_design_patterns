package design_patterns.strategy_pattern.payment_system.controllers;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import design_patterns.strategy_pattern.payment_system.models.PaymentResult;
import design_patterns.strategy_pattern.payment_system.models.Product;

public class ShoppingCart {
    // Using a Map to store products and their quantities
    Map<Product, Integer> items;

    public ShoppingCart() {
        this.items = new LinkedHashMap<>(); // Maintains insertion order for display
    }

    public void addProduct(Product product, int quantity) {
        if (quantity <= 0) {
            System.out.println("Cannot add zero or negative quantity of product.");
            return;
        }
        // If product already in cart, update quantity, otherwise put new product
        items.merge(product, quantity, Integer::sum);
        System.out.println("Added " + quantity + " x " + product.getName() + " ($" + product.getPrice() + " each)");
    }

    public void removeProduct(Product product) {
        if (items.containsKey(product)) {
            items.remove(product);
            System.out.println("Removed product: " + product.getName());
        } else {
            System.out.println("Product not found in cart: " + product.getName());
        }
    }

    public int calculateTotal() {
        int sum = 0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    // Renamed to 'checkout' for clarity; this is where the Strategy is used
    public PaymentResult checkout(Payment paymentStrategy) {
        int amount = calculateTotal();
        if (amount <= 0) {
            System.out.println("\nCart is empty. No payment needed.");
            return new PaymentResult(false, "N/A", "Cart is empty.");
        }
        System.out.println("\n--- Initiating Checkout ---");
        System.out.println("Total amount to pay: $" + amount);
        PaymentResult result = paymentStrategy.pay(amount); // Call the strategy
        System.out.println("--- Checkout Finished ---");
        return result;
    }

    public void displayCart() {
        System.out.println("\n--- Your Shopping Cart ---");
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            System.out.println(qty + " x " + p.getName() + " (UPC: " + p.getUpcCode() + ", $" + p.getPrice() + " each) = $" + (qty * p.getPrice()));
        }
        System.out.println("--------------------------");
        System.out.println("Cart Total: $" + calculateTotal());
        System.out.println("--------------------------");
    }
}