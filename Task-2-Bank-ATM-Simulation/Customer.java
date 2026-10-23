
public class Customer {

    private final String customerId;
    private final String name;
    private final Account account;

    public Customer(String customerId, String name, Account account) {
        if (customerId == null || customerId.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Customer ID cannot be empty.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException(
                    "Customer name cannot be empty.");
        }

        if (account == null) {
            throw new IllegalArgumentException(
                    "Customer account cannot be null.");
        }

        if (!name.trim().equals(account.getCustomerName())) {
            throw new IllegalArgumentException(
                    "Customer name must match the account holder.");
        }

        this.customerId = customerId.trim();
        this.name = name.trim();
        this.account = account;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getName() {
        return name;
    }

    public Account getAccount() {
        return account;
    }

    public void displayCustomerDetails() {
        System.out.println("\n------ CUSTOMER DETAILS ------");
        System.out.println("Customer ID : " + customerId);
        System.out.println("Name        : " + name);
        System.out.println("Account No. : " + account.getAccountNumber());
        System.out.printf("Balance     : ₹%.2f%n", account.getBalance());
    }
}
