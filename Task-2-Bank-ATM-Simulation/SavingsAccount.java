
public class SavingsAccount extends Account {

    private final double annualInterestRate;

    public SavingsAccount(
            String accountNumber,
            String customerName,
            double openingBalance,
            String pin,
            double annualInterestRate) {

        super(accountNumber, customerName, openingBalance, pin);

        if (!Double.isFinite(annualInterestRate)
                || annualInterestRate < 0) {
            throw new IllegalArgumentException(
                    "Interest rate must be non-negative and finite.");
        }

        this.annualInterestRate = annualInterestRate;
    }

    public double getAnnualInterestRate() {
        return annualInterestRate;
    }

    @Override
    public void withdraw(double amount)
            throws InsufficientFundsException {

        validateAmount(amount);

        if (amount > getBalance()) {
            throw new InsufficientFundsException(
                    "Insufficient funds in savings account.");
        }

        setBalance(getBalance() - amount);
        logWithdrawal(amount);
    }

    @Override
    public void applyInterest() {
        double interest = getBalance() * annualInterestRate / 100.0;

        if (interest > 0) {
            deposit(interest);
            logTransaction("INTEREST_CREDITED", interest);
        }
    }
}
