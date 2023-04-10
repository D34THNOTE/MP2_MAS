package models.qualified;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Bank {
    private String bankName;

    private Map<Long, Account> accounts = new HashMap<>();

    public Bank(String bankName) {
        this.bankName = bankName;
    }

    public void addAccount(Account account) {
        if(account == null) throw new IllegalArgumentException("Account to add hasn't been selected");
        if(accounts.containsKey(account.getAccountNumber())) throw new IllegalArgumentException("Account with this number already exists in this Bank");

        accounts.put(account.getAccountNumber(), account);
        account.setBank(this);
    }

    public void removeAccount(Account account) {
        if(account == null) throw new IllegalArgumentException("Account to remove hasn't been selected");
        if(!accounts.containsKey(account.getAccountNumber())) throw new IllegalArgumentException("Chosen account doesn't exist in the Bank");

        accounts.remove(account.getAccountNumber(), account);
        account.setBank(null);
    }

    public Map<Long, Account> getAccounts() {
        return Collections.unmodifiableMap(accounts);
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        if(bankName == null || bankName.isBlank()) throw new IllegalArgumentException("Bank's name is required");
        this.bankName = bankName;
    }
}
