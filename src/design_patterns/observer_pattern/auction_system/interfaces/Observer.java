package design_patterns.observer_pattern.auction_system.interfaces;

public interface Observer {
	void update(String itemName, double currentBid, String bidderNameString);

}
