
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Account {

    private final String accountNumber;
    private final String customerName;
    private double balance;
    private String pin;

    private final List<String> transactionLog = new ArrayList<>();

    protected Account(
            String accountNumber,
            String customerName,
            double openingBalance,
            String pin) {

        if (accountNumber == null || accountNumber.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Account number cannot be empty.");
        }

        if (customerName == null || customerName.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty.");
        }

        if (openingBalance < 0 || !Double.isFinite(openingBalance)) {
            throw new IllegalArgumentException(
                    "Opening balance must be non-negative and finite.");
        }

        validatePIN(pin);

        this.accountNumber = accountNumber.trim();
        this.customerName = customerName.trim();
        this.balance = openingBalance;
        this.pin = pin;

        logTransaction("ACCOUNT_OPENED", openingBalance);
    }

    private static void validatePIN(String pin) {
        if (pin == null || !pin.matches("\\d{4}")) {
            throw new IllegalArgumentException(
                    "PIN must contain exactly four digits.");
        }
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCustomerName() {
        return customerName;
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionLog() {
        return Collections.unmodifiableList(transactionLog);
    }

    public void verifyPIN(String enteredPIN)
            throws InvalidPINException {

        if (enteredPIN == null || !pin.equals(enteredPIN)) {
            throw new InvalidPINException("Incorrect PIN.");
        }
    }

    public void deposit(double amount) {
        validateAmount(amount);

        double updatedBalance = balance + amount;

        if (!Double.isFinite(updatedBalance)) {
            throw new IllegalArgumentException(
                    "Amount exceeds the supported balance limit.");
        }

        balance = updatedBalance;
        logTransaction("DEPOSIT", amount);
    }

    public abstract void withdraw(double amount)
            throws InsufficientFundsException;

    public abstract void applyInterest();

    protected void validateAmount(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            throw new IllegalArgumentException(
                    "Amount must be positive and finite.");
        }
    }

    protected void setBalance(double newBalance) {
        if (!Double.isFinite(newBalance)) {
            throw new IllegalArgumentException(
                    "Account balance must be finite.");
        }

        // Each account type checks its own balance limits.
        balance = newBalance;
}


    protected void logTransaction(String type, double amount) {
        String timestamp = LocalDateTime.now().format(
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        transactionLog.add(String.format(
                "%s | %-15s | Amount: %.2f | Balance: %.2f",
                timestamp, type, amount, balance));
    }

    protected void logWithdrawal(double amount) {
        logTransaction("WITHDRAWAL", amount);
    }
}
