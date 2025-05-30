package design_patterns.strategy_pattern.payment_system.controllers;

import java.util.UUID;

import design_patterns.strategy_pattern.payment_system.models.PaymentResult;

public class CreditCardAlgorithm implements Payment {
    private String name;
    private String cardNumber; // In a real app, never store raw card numbers!

    public CreditCardAlgorithm(String name, String cardNumber) {
        this.name = name;
        this.cardNumber = cardNumber;
    }

    @Override
    public PaymentResult pay(int amount) {
        System.out.println("Processing credit card payment for $" + amount + "...");
        // Simulate payment gateway interaction: 90% chance of success
        boolean success = Math.random() > 0.1;
        String transactionId = success ? "CC_" + UUID.randomUUID().toString().substring(0, 8) : "N/A";
        String message = success ? "Payment successful via Credit Card." : "Credit Card payment failed. Invalid details or insufficient funds.";

        System.out.println(message);
        return new PaymentResult(success, transactionId, message);
    }
}
