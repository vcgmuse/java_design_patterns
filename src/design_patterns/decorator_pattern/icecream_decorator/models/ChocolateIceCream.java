package design_patterns.decorator_pattern.icecream_decorator.models;

import design_patterns.decorator_pattern.icecream_decorator.interfaces.IceCream;
// Updated import based on suggestion 1
import design_patterns.decorator_pattern.icecream_decorator.abstracts.IceCreamDecorator; 

public class ChocolateIceCream extends IceCreamDecorator {
    private String chocolateDescription = " with Chocolate";
    private static final double CHOCOLATE_COST = 1.0; // <--- ENCAPSULATED COST

    public ChocolateIceCream(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public double cost() {
        return CHOCOLATE_COST + super.cost(); // Use constant
    }

    @Override
    public String getDescription() { // <--- NEW IMPLEMENTATION
        return super.getDescription() + chocolateDescription; // Appends its own description
    }
}