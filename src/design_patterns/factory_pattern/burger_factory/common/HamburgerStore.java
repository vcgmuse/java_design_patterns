package design_patterns.factory_pattern.burger_factory.common;


public abstract class HamburgerStore {


    public Hamburger orderHamburger(String type){
        Hamburger burger;

        //We now user our factory! Not the if statements.
        burger =  createHamburger(type);  //factory.createHamburger(type);

        burger.prepare();
        burger.cook();
        burger.box();

        return burger;

    }

    abstract public Hamburger createHamburger(String type);
}
