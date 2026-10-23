
import java.util.List;
import java.util.Scanner;

public class ATM {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("================================");
        System.out.println("     WELCOME TO JAVA ATM");
        System.out.println("================================");

        Account account = createAccount();

        Customer customer = new Customer(
                "C101",
                account.getCustomerName(),
                account
        );

        System.out.println("\nAccount created successfully!");
        System.out.println("Account Number: " + account.getAccountNumber());
        System.out.printf("Opening Balance: ₹%.2f%n", account.getBalance());

        boolean running = true;

        while (running) {
            displayMenu();

            int choice = readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        if (authenticate(account)) {
                            System.out.printf(
                                    "Available Balance: ₹%.2f%n",
                                    account.getBalance()
                            );
                        }
                        break;

                    case 2:
                        if (authenticate(account)) {
                            double amount = readAmount("Enter deposit amount: ₹");
                            account.deposit(amount);
                            System.out.println("Deposit successful!");
                            System.out.printf(
                                    "Updated Balance: ₹%.2f%n",
                                    account.getBalance()
                            );
                        }
                        break;

                    case 3:
                        if (authenticate(account)) {
                            double amount = readAmount("Enter withdrawal amount: ₹");
                            account.withdraw(amount);
                            System.out.println("Withdrawal successful!");
                            System.out.printf(
                                    "Updated Balance: ₹%.2f%n",
                                    account.getBalance()
                            );
                        }
                        break;

                    case 4:
                        if (authenticate(account)) {
                            account.applyInterest();
                            System.out.println(
                                    "Interest operation completed."
                            );
                            System.out.printf(
                                    "Current Balance: ₹%.2f%n",
                                    account.getBalance()
                            );
                        }
                        break;

                    case 5:
                        if (authenticate(account)) {
                            displayTransactionHistory(account);
                        }
                        break;

                    case 6:
                        if (authenticate(account)) {
                            customer.displayCustomerDetails();
                        }
                        break;

                    case 7:
                        running = false;
                        System.out.println(
                                "Thank you for using Java ATM. Goodbye!"
                        );
                        break;

                    default:
                        System.out.println(
                                "Invalid choice. Please select 1 to 7."
                        );
                }

            } catch (InvalidPINException |
                     InsufficientFundsException |
                     IllegalArgumentException e) {

                System.out.println("Operation failed: " + e.getMessage());

            } catch (Exception e) {
                System.out.println(
                        "Unexpected error: " + e.getMessage()
                );
            }
        }

        scanner.close();
    }

    private static Account createAccount() {

        System.out.println("\nChoose Account Type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");

        int type;

        do {
            type = readInt("Enter account type: ");

            if (type != 1 && type != 2) {
                System.out.println("Please enter 1 or 2.");
            }
        } while (type != 1 && type != 2);

        String accountNumber = readText("Enter account number: ");
        String customerName = readText("Enter customer name: ");
        double openingBalance = readAmount(
                "Enter opening balance: ₹"
        );
        String pin = readText("Set a four-digit PIN: ");

        if (type == 1) {
            double interestRate = readAmount(
                    "Enter annual interest rate (%): "
            );

            return new SavingsAccount(
                    accountNumber,
                    customerName,
                    openingBalance,
                    pin,
                    interestRate
            );

        } else {
            double overdraftLimit = readAmount(
                    "Enter overdraft limit: ₹"
            );

            return new CheckingAccount(
                    accountNumber,
                    customerName,
                    openingBalance,
                    pin,
                    overdraftLimit
            );
        }
    }

    private static void displayMenu() {
        System.out.println("\n========== ATM MENU ==========");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Apply Interest");
        System.out.println("5. View Transaction History");
        System.out.println("6. Customer Details");
        System.out.println("7. Exit");
        System.out.println("==============================");
    }

    private static boolean authenticate(Account account)
            throws InvalidPINException {

        String enteredPIN = readText("Enter your four-digit PIN: ");
        account.verifyPIN(enteredPIN);

        System.out.println("PIN verified successfully.");
        return true;
    }

    private static void displayTransactionHistory(Account account) {

        List<String> transactions = account.getTransactionLog();

        System.out.println("\n====== TRANSACTION HISTORY ======");

        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }

        for (String transaction : transactions) {
            System.out.println(transaction);
        }
    }

    private static String readText(String message) {
        System.out.print(message);
        return scanner.nextLine().trim();
    }

    private static int readInt(String message) {

        while (true) {
            try {
                System.out.print(message);
                return Integer.parseInt(scanner.nextLine().trim());

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private static double readAmount(String message) {

        while (true) {
            try {
                System.out.print(message);
                double amount = Double.parseDouble(
                        scanner.nextLine().trim()
                );

                if (!Double.isFinite(amount) || amount < 0) {
                    System.out.println(
                            "Enter a finite, non-negative number."
                    );
                    continue;
                }

                return amount;

            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}
