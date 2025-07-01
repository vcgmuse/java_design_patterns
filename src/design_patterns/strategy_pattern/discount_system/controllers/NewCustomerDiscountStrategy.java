package design_patterns.strategy_pattern.discount_system.controllers;

import java.math.BigDecimal;

import design_patterns.strategy_pattern.discount_system.DiscountStrategy;
import design_patterns.strategy_pattern.discount_system.models.Customer;
import design_patterns.strategy_pattern.discount_system.models.ShoppingCart;

public class NewCustomerDiscountStrategy implements DiscountStrategy{

	@Override
	public BigDecimal calculateDiscount(Customer customer, ShoppingCart cart) {
		BigDecimal discount;		
		if (customer.isNewCustomer()) {
			BigDecimal discountPercentage = new BigDecimal("0.10"); // 10%
			discount = DiscountStrategy.scaleDiscount(
							discountPercentage
							.multiply(cart.calculateTotal()));
			
			// Removed printDiscount call
			return discount;
		}
		else {
			return BigDecimal.ZERO;			
		}
	}

}
