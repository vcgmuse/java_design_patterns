package design_patterns.decorator_pattern.icecream_decorator.models;

import design_patterns.decorator_pattern.icecream_decorator.interfaces.IceCream;

public class BasicIceCream implements IceCream {
    private String description = "Simple Ice Cream"; // Default description

    public BasicIceCream() {
        System.out.println("Creating basic IceCream: ");
    }

    @Override
    public double cost() {
        return 0.50;
    }

    @Override
    public String getDescription() { // <--- NEW IMPLEMENTATION
        return description;
    }
}