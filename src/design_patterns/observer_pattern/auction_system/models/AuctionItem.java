package design_patterns.observer_pattern.auction_system.models;

import design_patterns.observer_pattern.auction_system.abstracts.Subject;
import design_patterns.observer_pattern.auction_system.interfaces.Observer;

public class AuctionItem extends Subject {
    private String name;
    private double currentBid;
    private String highestBidder;

    public AuctionItem(String name, double startingBid) {
        this.name = name;
        this.currentBid = startingBid;
        this.highestBidder = null;
        System.out.printf("\nAuction Item '%s' created with starting bid: $%.2f\n", name, startingBid);
    }

    public String getName() {
        return name;
    }

    public double getCurrentBid() {
        return currentBid;
    }

    public String getHighestBidder() {
        return highestBidder;
    }

    // Method to place a new bid on the item
    public void placeBid(String bidderName, double newBid) {
        if (newBid > this.currentBid) {
            System.out.printf("\n%s places a bid of $%.2f on '%s'.\n", bidderName, newBid, this.name);
            this.currentBid = newBid;
            this.highestBidder = bidderName;
            // State has changed, so notify all attached observers
            notifyObservers();
        } else {
            System.out.printf("\n%s's bid of $%.2f on '%s' is too low. Current bid: $%.2f.\n", 
                              bidderName, newBid, this.name, this.currentBid);
        }
    }

    // Implementation of the abstract notifyObservers method
    @Override
    public void notifyObservers() {
        System.out.println("--- Notifying observers for '" + this.name + "' ---");
        // Iterate through all attached observers and call their update method
        for (Observer observer : observers) {
            observer.update(this.name, this.currentBid, this.highestBidder);
        }
        System.out.println("----------------------------------------");
    }
}
