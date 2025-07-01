package design_patterns.decorator_pattern.icecream_decorator;

import design_patterns.decorator_pattern.icecream_decorator.interfaces.IceCream;
import design_patterns.decorator_pattern.icecream_decorator.models.BasicIceCream;
import design_patterns.decorator_pattern.icecream_decorator.models.VanillaIceCream;
import design_patterns.decorator_pattern.icecream_decorator.models.MintIceCream;
public class Main {
    public static void main(String[] args) {
        IceCream basicIceCream = new BasicIceCream();
        System.out.println(basicIceCream.getDescription() + " cost : $" + basicIceCream.cost());

        IceCream vanilla = new VanillaIceCream(basicIceCream);
        System.out.println(vanilla.getDescription() + " cost: $" + vanilla.cost());

        IceCream mintIceCream = new MintIceCream(vanilla);
        System.out.println(mintIceCream.getDescription() + " cost: $" + mintIceCream.cost());
    }
}