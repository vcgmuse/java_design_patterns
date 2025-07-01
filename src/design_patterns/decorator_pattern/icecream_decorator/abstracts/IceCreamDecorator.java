// File: design_patterns/decorator_pattern/icecream_decorator/abstracts/IceCreamDecorator.java
package design_patterns.decorator_pattern.icecream_decorator.abstracts;

import design_patterns.decorator_pattern.icecream_decorator.interfaces.IceCream;

public abstract class IceCreamDecorator implements IceCream {
    private IceCream iceCream;

    public IceCreamDecorator(IceCream iceCream) {
        if (iceCream == null) {
            throw new IllegalArgumentException("Cannot decorate a null IceCream object.");
        }
        this.iceCream = iceCream;
    }

    @Override
    public double cost() {
        return this.iceCream.cost(); // Delegates to the wrapped component
    }

    @Override // <--- THIS IS THE MISSING PIECE! IceCreamDecorator must implement this method
    public String getDescription() {
        return this.iceCream.getDescription(); // Delegates to the wrapped component's description
    }
}