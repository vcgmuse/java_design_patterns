package design_patterns.strategy_pattern.payment_system.models;

public class PaymentResult {
    private boolean success;
    private String transactionId; // Simplified, could be UUID
    private String message;

    public PaymentResult(boolean success, String transactionId, String message) {
        this.success = success;
        this.transactionId = transactionId;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Payment Result [Success: " + success + ", Transaction ID: " + transactionId + ", Message: " + message + "]";
    }
}
