package design_patterns.observer_pattern.stock_monitor.interfaces;

import design_patterns.observer_pattern.stock_monitor.events.StockUpdateEvent;

public interface Subject {
    void register(Observer observer);
    void unregister(Observer observer);
    // This method is called by the Subject to notify all its registered observers
    // It pushes the specific event object containing the details of the change.
    void notifyObservers(StockUpdateEvent event);
}