import java.math.BigDecimal;

public class BankingDemo {
    public static void main(String[] args) {
        // Create a new banking system
        Banking banking = new Banking();

        // Create some customers
        Customer ulkar = new Customer("Ulkar");
        banking.addCustomer(ulkar);

        Customer rovshan = new Customer("Rovshan");
        banking.addCustomer(rovshan);

        // Create some accounts for Ulkar
        CheckingAccount ulkarChecking = banking.createCheckingAccount(ulkar, new BigDecimal("5000.00"), new BigDecimal("500.00"));
        SavingAccount ulkarSaving = banking.createSavingAccount(ulkar, new BigDecimal("10000.00"), new BigDecimal("0.01"));

        // Create an account for Rovshan
        CheckingAccount rovshanChecking = banking.createCheckingAccount(rovshan, new BigDecimal("2000.00"), new BigDecimal("200.00"));

        // Print Ulkar's total balance
        System.out.println("Ulkar's total balance: " + ulkar.getTotalBalance());

        // Print Rovshan's total balance
        System.out.println("Rovshan's total balance: " + rovshan.getTotalBalance());

        // Deposit $1000 into Ulkar's checking account
        ulkarChecking.deposit(new BigDecimal("1000.00"));

        // Attempt to deposit negative amount (should throw an exception)
        try {
            ulkarChecking.deposit(new BigDecimal("-100.00"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Withdraw $3000 from Ulkar's checking account
        ulkarChecking.withdraw(new BigDecimal("3000.00"));

        // Withdraw $10000 from Ulkar's checking account (should throw an exception)
        try {
            ulkarChecking.withdraw(new BigDecimal("10000.00"));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Withdraw $200 from Rovshan's checking account
        rovshanChecking.withdraw(new BigDecimal("200.00"));

        // Remove Ulkar's checking account
        ulkar.removeAccount(ulkarChecking);

        // Attempt to remove Ulkar's checking account again (should throw an exception)
        try {
            ulkar.removeAccount(ulkarChecking);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Remove Rovshan's checking account
        rovshan.removeAccount(rovshanChecking);

        // Remove Rovshan as a customer
        banking.removeCustomer(rovshan);

        // Attempt to remove Rovshan again (should throw an exception)
        try {
            banking.removeCustomer(rovshan);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        // Attempt to retrieve a non-existent customer (should throw an exception)
        try {
            Customer nonexistentCustomer = banking.getCustomer("unknown_id");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}