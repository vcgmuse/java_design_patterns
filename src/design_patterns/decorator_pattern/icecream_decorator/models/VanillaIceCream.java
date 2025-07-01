package design_patterns.decorator_pattern.icecream_decorator.models;

import design_patterns.decorator_pattern.icecream_decorator.abstracts.IceCreamDecorator;
import design_patterns.decorator_pattern.icecream_decorator.interfaces.IceCream;

public class VanillaIceCream extends IceCreamDecorator {
    private static final double VANILLA_COST = 1.0; // Encapsulate the cost as a constant
    private String vanillaDescription = " with Vanilla"; // Add a description for vanilla

    public VanillaIceCream(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public double cost() {
        return VANILLA_COST + super.cost(); // Use the constant for clarity
    }

    @Override
    public String getDescription() {
        // Append this decorator's description to the base description
        return super.getDescription() + vanillaDescription;
    }
}