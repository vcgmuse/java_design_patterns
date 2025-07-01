package design_patterns.observer_pattern.auction_system.models;

import design_patterns.observer_pattern.auction_system.interfaces.Observer;

public class Bidder implements Observer {
    private String name;

    public Bidder(String name) {
        this.name = name;
        System.out.println("Bidder '" + name + "' created.");
    }

    public String getName() {
        return name;
    }

    // Implementation of the update method from the Observer interface
    @Override
    public void update(String itemName, double currentBid, String bidderName) {
        System.out.printf("Bidder '%s': %s - Current bid is $%.2f%s\n",
                          this.name, itemName, currentBid, 
                          (bidderName != null ? " (by " + bidderName + ")" : ""));
        // In a real system, a bidder might decide to place a new bid here
    }
}