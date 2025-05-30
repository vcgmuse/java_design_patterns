package design_patterns.strategy_pattern.payment_system;

import java.util.InputMismatchException;
import java.util.Scanner;

import design_patterns.strategy_pattern.payment_system.controllers.CreditCardAlgorithm;
import design_patterns.strategy_pattern.payment_system.controllers.Payment;
import design_patterns.strategy_pattern.payment_system.controllers.PaypalAlgorithm;
import design_patterns.strategy_pattern.payment_system.controllers.ShoppingCart;
import design_patterns.strategy_pattern.payment_system.models.PaymentResult;
import design_patterns.strategy_pattern.payment_system.models.Product;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingCart cart = new ShoppingCart();

        System.out.println("--- Welcome to Our Online Store! ---");

        // Add some products to the cart initially
        Product pants = new Product("SKU001", "Jeans", 50);
        Product shirt = new Product("SKU002", "T-Shirt", 20);
        Product shoes = new Product("SKU003", "Sneakers", 80);

        cart.addProduct(pants, 1);
        cart.addProduct(shirt, 2);
        cart.addProduct(shoes, 1);
        // cart.removeProduct(shirt); // Example: removing a product
        // cart.addProduct(pants, 1); // Example: adding more of an existing product

        cart.displayCart();

        // --- Payment Process ---
        System.out.println("\n--- Choose Your Payment Method ---");
        System.out.println("1. PayPal");
        System.out.println("2. Credit Card");
        System.out.print("Enter your choice (1 or 2): ");

        int choice = -1;
        try {
            choice = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number (1 or 2).");
            scanner.nextLine(); // Consume the invalid input
            scanner.close();
            return;
        }
        scanner.nextLine(); // Consume the newline left-over after nextInt()

        Payment paymentStrategy = null;

        // Directly instantiate the concrete strategy based on user choice
        if (choice == 1) {
            System.out.print("Enter PayPal email: ");
            String email = scanner.nextLine();
            System.out.print("Enter PayPal password: ");
            String password = scanner.nextLine();
            paymentStrategy = new PaypalAlgorithm(email, password);
        } else if (choice == 2) {
            System.out.print("Enter Cardholder Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Card Number: ");
            String cardNumber = scanner.nextLine();
            paymentStrategy = new CreditCardAlgorithm(name, cardNumber);
        } else {
            System.out.println("Invalid payment method choice. Exiting.");
            scanner.close();
            return;
        }

        // Perform checkout using the selected payment strategy
        PaymentResult result = cart.checkout(paymentStrategy);

        System.out.println("\nFinal Payment Status:");
        System.out.println(result); // Uses PaymentResult's toString()

        if (result.isSuccess()) {
            System.out.println("Thank you for your purchase!");
        } else {
            System.out.println("Payment failed. Please try another method or check your details.");
        }

        scanner.close();
        System.out.println("\n--- Program Ended ---");
    }
}
