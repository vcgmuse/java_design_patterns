package design_patterns.strategy_pattern.payment_system.controllers;

import design_patterns.strategy_pattern.payment_system.models.PaymentResult;

public interface Payment {
    public PaymentResult pay(int amount); // Modified to return PaymentResult
}
