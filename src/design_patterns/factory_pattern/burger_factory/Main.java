package design_patterns.factory_pattern.burger_factory;

import design_patterns.factory_pattern.burger_factory.generic.CheeseBurger;
import design_patterns.factory_pattern.burger_factory.mozambican.MozHamburgerStore;
import design_patterns.factory_pattern.burger_factory.common.Hamburger;
import design_patterns.factory_pattern.burger_factory.common.HamburgerStore;
import design_patterns.factory_pattern.burger_factory.jamaican.JamHamburgerstore;
public class Main {

    public static void main(String[] args) {

        CheeseBurger cheeseBurger = new CheeseBurger();

        HamburgerStore mozambicanBurgerStore = new MozHamburgerStore();
        HamburgerStore jamaicanBurgerStore = new JamHamburgerstore();

        Hamburger hamburger = mozambicanBurgerStore.orderHamburger("cheese");
        System.out.println("Paulo ordered " + hamburger.getName() + "\n" );


        hamburger = jamaicanBurgerStore.orderHamburger("veggie");
        System.out.println("James Bond ordered: " + hamburger.getName() + "\n");




    }
}
