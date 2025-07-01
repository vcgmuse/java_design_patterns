package design_patterns.observer_pattern.stock_monitor;

import design_patterns.observer_pattern.stock_monitor.models.StockExchange;
import design_patterns.observer_pattern.stock_monitor.models.StockWatcher;

public class Main {
    public static void main(String[] args) {
        // 1. Create the Subject (Stock Exchange)
        StockExchange stockExchange = new StockExchange();

        // 2. Create various Observers (Stock Watchers)
        StockWatcher allStockMonitor = new StockWatcher("All-Stock Monitor");
        StockWatcher appleWatcher = new StockWatcher("AAPL Trader", "AAPL");
        StockWatcher googleWatcher = new StockWatcher("GOOGL Analyst", "GOOGL");
        StockWatcher specificStockWatcher = new StockWatcher("Tech Investor", "AAPL", "MSFT");


        // 3. Register Observers with the Subject
        stockExchange.register(allStockMonitor);
        stockExchange.register(appleWatcher);
        stockExchange.register(googleWatcher);
        stockExchange.register(specificStockWatcher);


        // 4. Simulate stock price changes
        stockExchange.setStockPrice("AAPL", 175.50);
        stockExchange.setStockPrice("GOOGL", 165.20);
        stockExchange.setStockPrice("MSFT", 420.00); // Only 'All-Stock Monitor' and 'Tech Investor' will react

        // Simulate another change for an existing stock
        stockExchange.setStockPrice("AAPL", 182.00); // AAPL Trader and All-Stock Monitor will react, triggering alert

        // Simulate a stock that didn't change
        stockExchange.setStockPrice("GOOGL", 165.20); // No notification for GOOGL (price didn't change)

        // Simulate a price drop for GOOGL
        stockExchange.setStockPrice("GOOGL", 158.00); // GOOGL Analyst will react, triggering action

        // Simulate a new stock
        stockExchange.setStockPrice("AMZN", 185.00); // Only 'All-Stock Monitor' will react

        // 5. Unregister an observer
        stockExchange.unregister(appleWatcher);

        // 6. Simulate more changes after unregistering
        stockExchange.setStockPrice("AAPL", 183.00); // AAPL Trader won't react now
        stockExchange.setStockPrice("MSFT", 425.00);
    }
}