package models.qualified;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QualifiedAssociationTest {

    Bank bank1, bank2;
    Account account1, account2, account3, account4;

    @Before
    public void setup() {
        bank1 = new Bank("PKO");
        bank2 = new Bank("mBank");

        account1 = new Account(1, 65.00);
        account2 = new Account(2, 100.00);
        account3 = new Account(3, 24.00);
        account4 = new Account(4, 1670.00);
    }

    @Test
    public void addAccount() {
        assertThrows(IllegalArgumentException.class, () -> bank1.addAccount(null));

        bank1.addAccount(account1);
        assertTrue(bank1.getAccounts().containsValue(account1));
        assertThrows(IllegalArgumentException.class, () -> bank1.addAccount(account1));
        assertEquals(account1, bank1.getAccounts().get(account1.getAccountNumber()));
        assertEquals(bank1, account1.getBank());

        bank1.addAccount(account2);
        assertTrue(bank1.getAccounts().containsValue(account2));
        assertEquals(2, bank1.getAccounts().size());

        assertThrows(IllegalArgumentException.class, () -> bank1.addAccount(new Account(2, 124.00)));

        Account tempAccount = account2;
        account2 = new Account(5, 1634.00);
        bank1.removeAccount(tempAccount);
        bank1.addAccount(account2);
        assertTrue(bank1.getAccounts().containsValue(account2));
        assertFalse(bank1.getAccounts().containsValue(tempAccount));
        assertEquals(2, bank1.getAccounts().size());
    }

    @Test
    public void removeAccount() {
        assertThrows(IllegalArgumentException.class, () -> bank1.removeAccount(null));
        assertThrows(IllegalArgumentException.class, () -> bank1.removeAccount(account1));

        bank1.addAccount(account1);
        assertTrue(bank1.getAccounts().containsValue(account1));

        bank1.removeAccount(account1);
        assertFalse(bank1.getAccounts().containsValue(account1));
        assertNull(account1.getBank());

        bank1.addAccount(account1);
        bank1.addAccount(account2);
        assertEquals(2, bank1.getAccounts().size());
        bank1.removeAccount(account1);
        assertEquals(1, bank1.getAccounts().size());
    }

    @Test
    public void setBankTest() {
        assertNull(account1.getBank());

        account1.setBank(bank1);
        assertEquals(bank1, account1.getBank());
        assertTrue(bank1.getAccounts().containsValue(account1));

        account1.setBank(null);
        assertNull(account1.getBank());
        assertFalse(bank1.getAccounts().containsValue(account1));

        account1.setBank(bank1);
        account1.setBank(bank2);
        assertEquals(bank2, account1.getBank());
        assertFalse(bank1.getAccounts().containsValue(account1));
        assertTrue(bank2.getAccounts().containsValue(account1));

        account2.setBank(bank2);
        assertTrue(bank2.getAccounts().containsValue(account2));
        assertTrue(bank2.getAccounts().containsValue(account1));
    }
}
