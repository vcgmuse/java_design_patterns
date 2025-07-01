package design_patterns.strategy_pattern.discount_system;

import java.math.BigDecimal;

import design_patterns.strategy_pattern.discount_system.models.Customer;
import design_patterns.strategy_pattern.discount_system.models.ShoppingCart;


public class CheckoutProcessor {
	
	DiscountStrategy discountStrategy;

	public void setDiscountStrategy(DiscountStrategy discountStrategy) {
		this.discountStrategy = discountStrategy;
	}	
	
	public void processCheckout(Customer customer, ShoppingCart cart) {
		BigDecimal originalTotal = cart.calculateTotal();
		BigDecimal discountAmount = discountStrategy.calculateDiscount(customer, cart);
		
		// Ensure discount doesn't make the total negative
		if (discountAmount.compareTo(originalTotal) > 0) {
		    discountAmount = originalTotal; // Discount cannot exceed original total
		}
		
		BigDecimal finalCost = originalTotal.subtract(discountAmount);
		
		// Centralized and clear printing for user experience
		System.out.println("--- Processing Checkout for " + customer.getName() + " ---");
		System.out.println("  Original Cart Total: $" + DiscountStrategy.scaleDiscount(originalTotal));
		System.out.println("  Applied Discount:    $" + DiscountStrategy.scaleDiscount(discountAmount));
		System.out.println("  Final Cost:          $" + DiscountStrategy.scaleDiscount(finalCost));
		System.out.println("-------------------------------------\n");
	}
}
