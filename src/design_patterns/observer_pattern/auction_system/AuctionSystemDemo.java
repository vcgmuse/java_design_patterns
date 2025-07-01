package design_patterns.observer_pattern.auction_system;
import design_patterns.observer_pattern.auction_system.models.AuctionDisplay;
import design_patterns.observer_pattern.auction_system.models.AuctionItem;
import design_patterns.observer_pattern.auction_system.models.Bidder;

public class AuctionSystemDemo {
public static void main(String[] args) {
    // Create an auction item (Subject)
    AuctionItem artPiece = new AuctionItem("Abstract Painting", 100.00);

    // Create bidders and a public display (Observers)
    Bidder bidder1 = new Bidder("Alice");
    Bidder bidder2 = new Bidder("Bob");
    Bidder bidder3 = new Bidder("Charlie");
    AuctionDisplay publicDisplay = new AuctionDisplay("Main Gallery Display");

    // Alice and Bob are interested, so they attach to the auction item
    artPiece.attach(bidder1);
    artPiece.attach(bidder2);
    artPiece.attach(publicDisplay);

    // Charlie is not yet interested.

    // --- Auction Progress ---

    // 1. Alice places the first bid
    artPiece.placeBid("Alice", 110.00);

    // 2. Bob places a higher bid
    artPiece.placeBid("Bob", 125.00);

    // 3. Charlie tries to bid low (not effective, no notification to observers)
    artPiece.placeBid("Charlie", 120.00);

    // 4. Alice bids again, but removes herself as an observer first (e.g., she's leaving)
    artPiece.detach(bidder1);
    artPiece.placeBid("Alice", 130.00); // Alice bids, but won't receive notifications anymore

    // 5. Charlie decides to join and places a winning bid
    artPiece.attach(bidder3); // Charlie becomes an observer
    artPiece.placeBid("Charlie", 150.00);

    // 6. Bob tries to bid after auction theoretically ends (no more state changes or notifications)
    System.out.println("\n--- Auction Concluded (Simulated) ---");
    System.out.printf("Final bid for '%s': $%.2f by %s\n", 
                      artPiece.getName(), artPiece.getCurrentBid(), artPiece.getHighestBidder());

    // Detach all observers if the auction officially ends or is no longer relevant
    artPiece.detach(bidder2);
    artPiece.detach(bidder3);
    artPiece.detach(publicDisplay);
}
}
