package design_patterns.strategy_pattern.discount_system.controllers;

import java.math.BigDecimal;
import java.math.RoundingMode;

import design_patterns.strategy_pattern.discount_system.DiscountStrategy;
import design_patterns.strategy_pattern.discount_system.models.Customer;
import design_patterns.strategy_pattern.discount_system.models.ShoppingCart;

public class LoyaltyDiscountStrategy implements DiscountStrategy{

	@Override
	public BigDecimal calculateDiscount(Customer customer, ShoppingCart cart) {
		BigDecimal discountAmount = BigDecimal.ZERO; // Renamed to avoid confusion with percentage 'discount'

		if (customer.getOrderCount() >= 10) {
			BigDecimal discountPercentage = new BigDecimal("0.10"); // 10%
			discountAmount = DiscountStrategy.scaleDiscount(
							discountPercentage
							.multiply(cart.calculateTotal()));
			
			// Removed printDiscount call
			return discountAmount;
		} 
		
		else if(customer.getOrderCount() >= 5) {
			BigDecimal discountPercentage = new BigDecimal("0.05"); // 5%
			discountAmount = DiscountStrategy.scaleDiscount(
							discountPercentage
							.multiply(cart.calculateTotal()));
			
			// Removed printDiscount call
			return discountAmount;
		}
		
		return BigDecimal.ZERO; // No discount applies
	}
}