package design_patterns.observer_pattern.stock_monitor.interfaces;

import design_patterns.observer_pattern.stock_monitor.events.StockUpdateEvent;

public interface Observer {
    // This is the callback method the Subject will invoke to notify observers.
    // It directly receives the event object containing the details of the change.
    void update(StockUpdateEvent event);
}