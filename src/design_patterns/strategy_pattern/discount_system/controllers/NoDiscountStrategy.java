package design_patterns.strategy_pattern.discount_system.controllers;

import java.math.BigDecimal;

import design_patterns.strategy_pattern.discount_system.DiscountStrategy;
import design_patterns.strategy_pattern.discount_system.models.Customer;
import design_patterns.strategy_pattern.discount_system.models.ShoppingCart;

public class NoDiscountStrategy implements DiscountStrategy{

	@Override
	public BigDecimal calculateDiscount(Customer customer, ShoppingCart cart) {
		return BigDecimal.ZERO;
	}

}
