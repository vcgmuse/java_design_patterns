package design_patterns.observer_pattern.stock_monitor.events;

// A simple immutable data class to encapsulate stock price updates.
public class StockUpdateEvent {
    private final String stockSymbol;
    private final double newPrice;

    public StockUpdateEvent(String stockSymbol, double newPrice) {
        this.stockSymbol = stockSymbol;
        this.newPrice = newPrice;
    }

    public String getStockSymbol() {
        return stockSymbol;
    }

    public double getNewPrice() {
        return newPrice;
    }

    @Override
    public String toString() {
        return "StockUpdateEvent [Symbol=" + stockSymbol + ", Price=" + newPrice + "]";
    }
}