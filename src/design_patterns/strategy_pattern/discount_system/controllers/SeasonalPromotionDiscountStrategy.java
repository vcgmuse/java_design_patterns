package design_patterns.strategy_pattern.discount_system.controllers;

import java.math.BigDecimal;

import design_patterns.strategy_pattern.discount_system.DiscountStrategy;
import design_patterns.strategy_pattern.discount_system.models.Customer;
import design_patterns.strategy_pattern.discount_system.models.ShoppingCart;

public class SeasonalPromotionDiscountStrategy implements DiscountStrategy{
	
	private boolean hasPromoCode;
	
	public SeasonalPromotionDiscountStrategy(boolean hasPromoCode) {
		this.hasPromoCode = hasPromoCode;
	}
	
	@Override
	public BigDecimal calculateDiscount(Customer customer, ShoppingCart cart) {			
		if (hasPromoCode) {			
			BigDecimal fixedDiscountAmount = new BigDecimal("15.00");
			return fixedDiscountAmount;
		}			
		return BigDecimal.ZERO;
	}

}