// File: design_patterns/decorator_pattern/icecream_decorator/models/MintIceCream.java
package design_patterns.decorator_pattern.icecream_decorator.models;

import design_patterns.decorator_pattern.icecream_decorator.abstracts.IceCreamDecorator;
import design_patterns.decorator_pattern.icecream_decorator.interfaces.IceCream;

public class MintIceCream extends IceCreamDecorator {
    private static final double MINT_COST = 1.50; // Encapsulate the cost
    private String mintDescription = " with Mint"; // Add a description for mint

    public MintIceCream(IceCream iceCream) {
        super(iceCream);
    }

    @Override
    public double cost() {
        return MINT_COST + super.cost(); // Use the constant
    }

    @Override
    public String getDescription() {
        // Append this decorator's description to the base description
        return super.getDescription() + mintDescription;
    }
}