
public class CheckingAccount extends Account {

    private final double overdraftLimit;

    public CheckingAccount(
            String accountNumber,
            String customerName,
            double openingBalance,
            String pin,
            double overdraftLimit) {

        super(accountNumber, customerName, openingBalance, pin);

        if (!Double.isFinite(overdraftLimit) || overdraftLimit < 0) {
            throw new IllegalArgumentException(
                    "Overdraft limit must be non-negative and finite.");
        }

        this.overdraftLimit = overdraftLimit;
    }

    public double getOverdraftLimit() {
        return overdraftLimit;
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientFundsException {

        validateAmount(amount);

        if (amount > getBalance() + overdraftLimit) {
            throw new InsufficientFundsException(
                    "Withdrawal exceeds the available balance and overdraft limit.");
        }

        setBalance(getBalance() - amount);
        logWithdrawal(amount);
    }

    @Override
    public void applyInterest() {
        // Checking accounts do not earn interest in this simulation.
        logTransaction("INTEREST_NOT_APPLICABLE", 0.0);
    }
}
