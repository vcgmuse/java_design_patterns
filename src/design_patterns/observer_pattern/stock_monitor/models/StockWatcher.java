package design_patterns.observer_pattern.stock_monitor.models;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import design_patterns.observer_pattern.stock_monitor.events.StockUpdateEvent;
import design_patterns.observer_pattern.stock_monitor.interfaces.Observer;

public class StockWatcher implements Observer {

    private String name;
    private Set<String> interestedSymbols; // Optional: Only watch specific symbols

    // Constructor for a general watcher (watches all stocks)
    public StockWatcher(String name) {
        this.name = name;
        this.interestedSymbols = null; // Indicates interest in all symbols
    }

    // Constructor for a specific watcher (watches only specified symbols)
    public StockWatcher(String name, String... symbols) {
        this.name = name;
        this.interestedSymbols = new HashSet<>(Arrays.asList(symbols));
    }

    @Override
    public void update(StockUpdateEvent event) {
        // Check if this watcher is interested in this specific stock symbol
        if (interestedSymbols == null || interestedSymbols.contains(event.getStockSymbol())) {
            System.out.println(name + ": Received update for " + event.getStockSymbol() + ". New Price: " + event.getNewPrice());
            // --- Simulate some action based on the update ---
            if (event.getStockSymbol().equals("AAPL") && event.getNewPrice() > 180.0) {
                System.out.println("  " + name + ": ALERT! AAPL price is high: " + event.getNewPrice());
            } else if (event.getStockSymbol().equals("GOOGL") && event.getNewPrice() < 160.0) {
                System.out.println("  " + name + ": Action: Consider buying GOOGL! " + event.getNewPrice());
            }
        }
    }

    @Override
    public String toString() {
        return name;
    }
}