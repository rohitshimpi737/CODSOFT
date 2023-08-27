public class Transaction {
    private String transactionId;
    private String description;
    private String type;
    private double amount;

    public Transaction(String transactionId, String description, String type, double amount) {
        this.transactionId = transactionId;
        this.description = description;
        this.type = type;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}
