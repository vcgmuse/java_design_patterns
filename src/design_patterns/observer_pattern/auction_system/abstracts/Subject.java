package design_patterns.observer_pattern.auction_system.abstracts;
import design_patterns.observer_pattern.auction_system.interfaces.Observer;
import design_patterns.observer_pattern.auction_system.models.Bidder;
import design_patterns.observer_pattern.auction_system.models.AuctionItem; // Assuming AuctionItem is in 'models' too
import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
	protected List<Observer> observers = new ArrayList<>();
    
	public void attach(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Attached " + observer.getClass().getSimpleName() + 
                                (observer instanceof Bidder ? " " + ((Bidder)observer).getName() : "") + 
                                " to " + this.getClass().getSimpleName() + 
                                (this instanceof AuctionItem ? " " + ((AuctionItem)this).getName() : "") + ".");
        }
	}
	
    public void detach(Observer observer) {
        if (observers.remove(observer)) {
            System.out.println("Detached " + observer.getClass().getSimpleName() + 
                                (observer instanceof Bidder ? " " + ((Bidder)observer).getName() : "") + 
                                " from " + this.getClass().getSimpleName() + 
                                (this instanceof AuctionItem ? " " + ((AuctionItem)this).getName() : "") + ".");
        } else {
            System.out.println(observer.getClass().getSimpleName() + 
                                (observer instanceof Bidder ? " " + ((Bidder)observer).getName() : "") + 
                                " is not an observer of " + this.getClass().getSimpleName() + 
                                (this instanceof AuctionItem ? " " + ((AuctionItem)this).getName() : "") + ".");
        }
    }
    
    public abstract void notifyObservers();
	
}
