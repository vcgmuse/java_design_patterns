package design_patterns.abstract_factory_pattern.gui_toolkit;

// Abstract Factory
interface WidgetFactory {
    Button createButton();
    Checkbox createCheckbox();
}

// Concrete Factories
class WindowsWidgetFactory implements WidgetFactory {
    public Button createButton() { return new WindowsButton(); }
    public Checkbox createCheckbox() { return new WindowsCheckbox(); }
}
class MacWidgetFactory implements WidgetFactory {
    public Button createButton() { return new MacButton(); }
    public Checkbox createCheckbox() { return new MacCheckbox(); }
}

// Abstract Products
interface Button { void paint(); }
interface Checkbox { void paint(); }

// Concrete Products
class WindowsButton implements Button {
    public void paint() { System.out.println("Rendering Windows button"); }
}
class MacButton implements Button {
    public void paint() { System.out.println("Rendering Mac button"); }
}
class WindowsCheckbox implements Checkbox {
    public void paint() { System.out.println("Rendering Windows checkbox"); }
}
class MacCheckbox implements Checkbox {
    public void paint() { System.out.println("Rendering Mac checkbox"); }
}

// Client
public class GuiToolkitExample {
    public static void main(String[] args) {
        // Use case: Switch GUI rendering based on OS
        WidgetFactory factory = getFactory("Windows");
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();
        button.paint();
        checkbox.paint();
    }
    static WidgetFactory getFactory(String os) {
        if (os.equalsIgnoreCase("Windows")) return new WindowsWidgetFactory();
        else return new MacWidgetFactory();
    }
}
// Use case: Easily add new OS support by creating new factories and products. 