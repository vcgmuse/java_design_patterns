package design_patterns.strategy_pattern.discount_system;

import java.math.BigDecimal;
import java.nio.file.attribute.AclFileAttributeView;

import design_patterns.strategy_pattern.discount_system.controllers.LoyaltyDiscountStrategy;
import design_patterns.strategy_pattern.discount_system.controllers.NewCustomerDiscountStrategy;
import design_patterns.strategy_pattern.discount_system.controllers.NoDiscountStrategy;
import design_patterns.strategy_pattern.discount_system.controllers.SeasonalPromotionDiscountStrategy;
import design_patterns.strategy_pattern.discount_system.models.Customer;
import design_patterns.strategy_pattern.discount_system.models.ShoppingCart;

public class Main {
	public static void main(String[] args) {
		CheckoutProcessor checkoutProcessor = new CheckoutProcessor();
		
		// Define Customers
		Customer johnCustomer = new Customer("John (New, 22 orders)", true, 22); // New, but high order count
		Customer rickCustomer = new Customer("Rick (Existing, 6 orders)", false, 6); // Existing, mid-range loyalty
		Customer paulCustomer = new Customer("Paul (Existing, 13 orders)", false, 13); // Existing, high loyalty
		Customer janeCustomer = new Customer("Jane (New, 0 orders)", true, 0); // Truly new customer
		Customer markCustomer = new Customer("Mark (Existing, 2 orders)", false, 2); // Existing, low loyalty
		
		// Define Shopping Cart (using String constructor for BigDecimal precision)
		ShoppingCart cart1 = new ShoppingCart(new BigDecimal("100.00")); // Simple cart total
		ShoppingCart cart2 = new ShoppingCart(new BigDecimal("22.50"));  // Your original cart total
		ShoppingCart cart3 = new ShoppingCart(new BigDecimal("14.00"));  // A small cart to test fixed discount limit

		System.out.println("--- Demonstrating New Customer Discount Strategy ---");
		checkoutProcessor.setDiscountStrategy(new NewCustomerDiscountStrategy());
		checkoutProcessor.processCheckout(janeCustomer, cart1); // New customer
		checkoutProcessor.processCheckout(johnCustomer, cart1); // New but high orders (still gets new customer discount if strategy applies)
		checkoutProcessor.processCheckout(rickCustomer, cart1); // Existing customer
		
		System.out.println("--- Demonstrating Loyalty Discount Strategy ---");
		checkoutProcessor.setDiscountStrategy(new LoyaltyDiscountStrategy());
		checkoutProcessor.processCheckout(markCustomer, cart1); // Low loyalty
		checkoutProcessor.processCheckout(rickCustomer, cart1); // Mid loyalty (6 orders)
		checkoutProcessor.processCheckout(paulCustomer, cart1); // High loyalty (13 orders)
		
		System.out.println("--- Demonstrating Seasonal Promotion Discount Strategy (Active) ---");
		checkoutProcessor.setDiscountStrategy(new SeasonalPromotionDiscountStrategy(true)); // Promo active
		checkoutProcessor.processCheckout(johnCustomer, cart1); // Regular cart
		checkoutProcessor.processCheckout(johnCustomer, cart3); // Small cart to see discount not exceeding total
		
		System.out.println("--- Demonstrating Seasonal Promotion Discount Strategy (Inactive) ---");
		checkoutProcessor.setDiscountStrategy(new SeasonalPromotionDiscountStrategy(false)); // Promo inactive
		checkoutProcessor.processCheckout(johnCustomer, cart1); // Should get no discount
		
		System.out.println("--- Demonstrating No Discount Strategy ---");
		checkoutProcessor.setDiscountStrategy(new NoDiscountStrategy());
		checkoutProcessor.processCheckout(johnCustomer, cart1); // Should get no discount
		checkoutProcessor.processCheckout(paulCustomer, cart2); // Should get no discount
	}
}