package design_patterns.strategy_pattern.discount_system;

import java.math.BigDecimal;
import java.math.RoundingMode;

import design_patterns.strategy_pattern.discount_system.models.Customer;
import design_patterns.strategy_pattern.discount_system.models.ShoppingCart;



public interface DiscountStrategy {
	public BigDecimal calculateDiscount(Customer customer, ShoppingCart cart);
	
	// Moved print statements out of strategies for better separation of concerns.
	// The `printDiscount` method here was problematic as it was too specific ("Loyalty Discount").
	
	public static BigDecimal scaleDiscount(BigDecimal current) {
		// Ensuring consistent scaling for all discount calculations
		return current.setScale(2, RoundingMode.HALF_UP);
	}
}
