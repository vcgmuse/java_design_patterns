package design_patterns.observer_pattern.stock_monitor.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import design_patterns.observer_pattern.stock_monitor.events.StockUpdateEvent;
import design_patterns.observer_pattern.stock_monitor.interfaces.Observer;
import design_patterns.observer_pattern.stock_monitor.interfaces.Subject;

public class StockExchange implements Subject {

    private List<Observer> observers;
    private Map<String, Double> stockPrices; // Stores current prices for various stocks

    public StockExchange() {
        this.observers = new ArrayList<>();
        this.stockPrices = new HashMap<>();
    }

    @Override
    public void register(Observer observer) {
        if (observer == null) {
            throw new NullPointerException("Cannot register a null observer.");
        }
        if (!observers.contains(observer)) {
            observers.add(observer);
            System.out.println("Observer registered: " + observer.getClass().getSimpleName());
        }
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer unregistered: " + observer.getClass().getSimpleName());
    }

    @Override
    public void notifyObservers(StockUpdateEvent event) {
        System.out.println("--- Notifying Observers about: " + event.getStockSymbol() + " new price " + event.getNewPrice() + " ---");
        for (Observer observer : observers) {
            observer.update(event); // Push the event object directly
        }
    }

    // Method to simulate a stock price change
    public void setStockPrice(String symbol, double newPrice) {
        System.out.println("\nStockExchange: Setting price for " + symbol + " to " + newPrice);
        
        // Update the internal state
        double oldPrice = stockPrices.getOrDefault(symbol, 0.0);
        stockPrices.put(symbol, newPrice);

        // Only notify if the price has actually changed (or if it's a new stock)
        if (newPrice != oldPrice) {
            // Create the event object
            StockUpdateEvent event = new StockUpdateEvent(symbol, newPrice);
            // Notify all registered observers
            notifyObservers(event);
        } else {
             System.out.println("StockExchange: Price for " + symbol + " did not change. No notification sent.");
        }
    }

    // Optional: Get current price (observers can't directly pull without knowing the symbol)
    public double getPrice(String symbol) {
        return stockPrices.getOrDefault(symbol, 0.0);
    }
}