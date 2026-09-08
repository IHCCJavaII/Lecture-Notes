package org.example;

public class InsufficientFundsException extends Exception {
    public InsufficientFundsException(double amount, double balance) {
        super("Cannot withdraw $" + amount + " — balance is only $" + balance);
    }
}
