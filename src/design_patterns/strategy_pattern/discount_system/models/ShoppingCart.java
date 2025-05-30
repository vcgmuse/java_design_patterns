package design_patterns.strategy_pattern.discount_system.models;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

import design_patterns.strategy_pattern.payment_system.models.Product;

public class ShoppingCart {
	private BigDecimal totalAmount;
	
	public ShoppingCart(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
    
    public BigDecimal calculateTotal() {
        return totalAmount;
    }
	
	

}
