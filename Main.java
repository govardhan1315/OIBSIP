import java.util.Scanner;

// Class representing a transaction
class Transaction {
    private String type;
    private double amount;

    // Constructor to initialize transaction details
    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    // Custom string representation of a transaction
    @Override
    public String toString() {
        return "Type: " + type + ", Amount: $" + amount;
    }
}

// Class representing a user
class User_1 {
    private String userID;
    private String userPIN;
    private Transaction[] transactionHistory;
    private int transactionCount;
    private double balance;

    // Constructor to initialize user details
    public User_1(String userID, String userPIN) {
        this.userID = userID;
        this.userPIN = userPIN;
        this.transactionHistory = new Transaction[100]; // Cap at 100 transactions
        this.transactionCount = 0;
        this.balance = 0; // Start with zero balance
    }

    // Getters for user ID and PIN
    public String getUserID() {
        return userID;
    }

    public String getUserPIN() {
        return userPIN;
    }

    // Getters for transaction history and balance
    public Transaction[] getTransactionHistory() {
        return transactionHistory;
    }

    public double getBalance() {
        return balance;
    }

    // Method to add a transaction to the history
    public void addTransaction(Transaction transaction) {
        if (transactionCount < transactionHistory.length) {
            transactionHistory[transactionCount] = transaction;
            transactionCount++;
        } else {
            System.out.println("Transaction history full. Cannot add more transactions.");
        }
    }

    // Method to update the user's balance
    public void updateBalance(double amount) {
        balance += amount;
    }
}

// Interface defining ATM operations
interface ATMOperations {
    void showTransactionHistory(User_1 user);
    void withdraw(User_1 user, double amount);
    void deposit(User_1 user, double amount);
    void transfer(User_1 user, String recipientID, double amount);
}

// Class implementing the ATM operations
class ATMImplementation implements ATMOperations {
    @Override
    public void showTransactionHistory(User_1 user) {
        System.out.println("Transaction History for User: " + user.getUserID());
        Transaction[] transactions = user.getTransactionHistory();
        for (Transaction transaction : transactions) {
            if (transaction != null) {
                System.out.println(transaction);
            }
        }
        System.out.println("Current Balance: $" + user.getBalance());
    }

    @Override
    public void withdraw(User_1 user, double amount) {
        if (amount > user.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        user.addTransaction(new Transaction("Withdraw", amount));
        user.updateBalance(-amount);
        System.out.println("$" + amount + " withdrawn successfully.");
        System.out.println("Current Balance: $" + user.getBalance());
    }

    @Override
    public void deposit(User_1 user, double amount) {
        user.addTransaction(new Transaction("Deposit", amount));
        user.updateBalance(amount);
        System.out.println("$" + amount + " deposited successfully.");
        System.out.println("Current Balance: $" + user.getBalance());
    }

    @Override
    public void transfer(User_1 user, String recipientID, double amount) {
        if (amount > user.getBalance()) {
            System.out.println("Insufficient funds.");
            return;
        }
        user.addTransaction(new Transaction("Transfer to " + recipientID, amount));
        user.updateBalance(-amount);
        System.out.println("$" + amount + " transferred successfully to " + recipientID + ".");
        System.out.println("Current Balance: $" + user.getBalance());
    }
}

// Main class to run the ATM program
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATMImplementation atm = new ATMImplementation();
        User_1 user = new User_1("123456", "7890");
        boolean continueATM = true;
        System.out.println("Welcome to the ATM!");

        while (continueATM) {
            System.out.println("\nPlease choose an option:");
            System.out.println("1. Show Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    atm.showTransactionHistory(user);
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    atm.withdraw(user, withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    atm.deposit(user, depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient ID: ");
                    String recipientID = scanner.next();
                    System.out.print("Enter transfer amount: ");
                    double transferAmount = scanner.nextDouble();
                    atm.transfer(user, recipientID, transferAmount);
                    break;
                case 5:
                    continueATM = false;
                    System.out.println("Thank you for using the ATM. Have a great day!");
                    break;
                default:
                    System.out.println("Invalid option. Please choose a valid option.");
            }
        }
    }
}
