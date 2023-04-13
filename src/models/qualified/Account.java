package models.qualified;

import models.basic.Painting;

public class Account {
    private long accountNumber;
    private double Balance;

    private Bank bank;

    public Account(long accountNumber, double balance) {
        this.accountNumber = accountNumber;
        Balance = balance;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        Bank tempBank = this.bank;

        if(this.bank == null && bank != null) {
            // creating new association
            this.bank = bank;
            if(!bank.getAccounts().containsValue(this)) {
                bank.addAccount(this);
            }
        } else if(this.bank != null && bank == null) {
            // removing all associations
            if(tempBank.getAccounts().containsValue(this)) {
                tempBank.removeAccount(this);
            }
            this.bank = null;
        } else if(this.bank != null && this.bank != bank) {
            // replacing the association
            if(tempBank.getAccounts().containsValue(this)) {
                tempBank.removeAccount(this);
            }
            if(!bank.getAccounts().containsValue(this)) {
                bank.addAccount(this);
            }
            this.bank = bank;
        }
    }

    public double getBalance() {
        return Balance;
    }

    public void setBalance(double balance) {
        if(balance < 0) throw new IllegalArgumentException("Account's balance cannot be negative");
        Balance = balance;
    }

    public long getAccountNumber() {
        return accountNumber;
    }
}
