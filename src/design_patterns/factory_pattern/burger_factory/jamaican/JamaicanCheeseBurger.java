package design_patterns.factory_pattern.burger_factory.jamaican;

import design_patterns.factory_pattern.burger_factory.common.Hamburger;

public class JamaicanCheeseBurger extends Hamburger {

    public JamaicanCheeseBurger() {
        name = "Jamaican Style Cheese Burger";
        sauce = "Spicy jamaican sauce";
        buns = "Cookie dough buns!";
    }

    @Override
    public void cook() {
        System.out.println("Cooking Jamaican style....");
    }
}
