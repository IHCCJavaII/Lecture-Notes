package org.example;

public class BankAccount {

    private String owner;
    private double balance;

    public BankAccount(String owner, double initialBalance) throws InvalidAmountException, Exception {
        setOwner(owner);
        deposit(initialBalance);
    }

    public void setOwner(String owner) throws Exception {
        if (owner == null || owner.isBlank()) {
            // Generic exception
            throw new Exception("Owner name cannot be null or blank.");
        }
        this.owner = owner;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    public void withdraw(double amount) throws InvalidAmountException, InsufficientFundsException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero. Got: " + amount);
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount, balance);
        }
        balance -= amount;
    }

    @Override
    public String toString() {
        return "BankAccount{owner='" + owner + "', balance=$" + balance + "}";
    }
}
