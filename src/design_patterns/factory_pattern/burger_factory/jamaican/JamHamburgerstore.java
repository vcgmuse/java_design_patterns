package design_patterns.factory_pattern.burger_factory.jamaican;

import design_patterns.factory_pattern.burger_factory.common.Hamburger;
import design_patterns.factory_pattern.burger_factory.common.HamburgerStore;

public class JamHamburgerstore extends HamburgerStore {
    @Override
    public Hamburger createHamburger(String type) {

        if (type.equals("cheese")) {
            return new JamaicanCheeseBurger();
        }else if (type.equals("veggie")) {
            return new JamaicanVeggieBurger();

        }else  return null;
    }
}
