package design_patterns.strategy_pattern.payment_system.controllers;

import java.util.UUID;

import design_patterns.strategy_pattern.payment_system.models.PaymentResult;

public class PaypalAlgorithm implements Payment {
    private String email;
    private String password; // In a real app, never store raw passwords!

    public PaypalAlgorithm(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public PaymentResult pay(int amount) {
        System.out.println("Processing PayPal payment for $" + amount + "...");
        // Simulate payment gateway interaction: 95% chance of success
        boolean success = Math.random() > 0.05;
        String transactionId = success ? "PP_" + UUID.randomUUID().toString().substring(0, 8) : "N/A";
        String message = success ? "Payment successful via PayPal." : "PayPal payment failed. Authentication error.";

        System.out.println(message);
        return new PaymentResult(success, transactionId, message);
    }
}
