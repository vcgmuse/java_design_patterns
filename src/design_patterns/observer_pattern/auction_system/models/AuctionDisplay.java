package design_patterns.observer_pattern.auction_system.models;

import design_patterns.observer_pattern.auction_system.interfaces.Observer;

public class AuctionDisplay implements Observer {
    private String displayName;

    public AuctionDisplay(String displayName) {
        this.displayName = displayName;
        System.out.println("Auction Display '" + displayName + "' created.");
    }

    // Implementation of the update method from the Observer interface
    @Override
    public void update(String itemName, double currentBid, String bidderName) {
        System.out.printf("Display '%s': New bid for '%s': $%.2f%s\n", 
                          this.displayName, itemName, currentBid,
                          (bidderName != null ? " by " + bidderName : ""));
        System.out.println("----------------------------------------"); // Separator for display updates
    }
}