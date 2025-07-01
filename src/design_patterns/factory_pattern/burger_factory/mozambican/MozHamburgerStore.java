package design_patterns.factory_pattern.burger_factory.mozambican;

import design_patterns.factory_pattern.burger_factory.common.Hamburger;
import design_patterns.factory_pattern.burger_factory.common.HamburgerStore;
import design_patterns.factory_pattern.burger_factory.mozambican.MozambicanCheeseBurger;
import design_patterns.factory_pattern.burger_factory.mozambican.MozambicanVeggieBurger;

public class MozHamburgerStore extends HamburgerStore {
    @Override
    public Hamburger createHamburger(String type) {

        if (type.equals("cheese")) {
            return new MozambicanCheeseBurger();
        }else if (type.equals("Veggie")) {
            return new MozambicanVeggieBurger();

        }else  return null;

    }
}
