package org.example;

public class App {
    public static void main(String[] args) {

        // --- Happy path ---
        System.out.println("=== Happy Path ===");
        try {
            BankAccount account = new BankAccount("Alice", 500.00);
            System.out.println("Created: " + account);

            account.deposit(200.00);
            System.out.println("After deposit $200: " + account);

            account.withdraw(100.00);
            System.out.println("After withdrawal $100: " + account);
        } catch (InvalidAmountException | InsufficientFundsException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Caught generic exception: " + e.getMessage());
        }

        // --- InvalidAmountException: bad deposit ---
        System.out.println("\n=== InvalidAmountException (negative deposit) ===");
        try {
            BankAccount account = new BankAccount("Bob", 300.00);
            account.deposit(-50.00);
        } catch (InvalidAmountException e) {
            System.out.println("Caught InvalidAmountException: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Caught generic exception: " + e.getMessage());
        }

        // --- InsufficientFundsException: withdraw more than balance ---
        System.out.println("\n=== InsufficientFundsException (overdraft) ===");
        try {
            BankAccount account = new BankAccount("Carol", 100.00);
            account.withdraw(999.00);
        } catch (InvalidAmountException e) {
            System.out.println("Caught InvalidAmountException: " + e.getMessage());
        } catch (InsufficientFundsException e) {
            System.out.println("Caught InsufficientFundsException: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Caught generic exception: " + e.getMessage());
        }

        // --- Exception (generic): blank owner name ---
        System.out.println("\n=== Exception (generic) blank owner name ===");
        try {
            BankAccount account = new BankAccount("", 200.00);
        } catch (InvalidAmountException e) {
            System.out.println("Caught InvalidAmountException: " + e.getMessage());
        }catch (Exception e) {
            System.out.println("Caught generic exception: " + e.getMessage());
        }
    }
}
