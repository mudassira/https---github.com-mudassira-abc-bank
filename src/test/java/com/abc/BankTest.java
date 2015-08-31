package com.abc;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BankTest {
    private static final double DOUBLE_DELTA = 1e-15;

    @Test
    public void customerSummary() {
        Bank bank = new Bank();
        Customer john = new Customer("John");
        try {
			john.openAccount(new CheckingAccount("John account 1"));
		} catch (AccountException e) {
			e.printStackTrace();
		}
        bank.addCustomer(john);

        assertEquals("Customer Summary\n - John (1 account)", bank.customerSummary());
    }

    @Test
    public void checkingAccount() {
        Bank bank = new Bank();
        Account checkingAccount;
		try {
			checkingAccount = new CheckingAccount("Bill account 1");
			Customer bill = new Customer("Bill").openAccount(checkingAccount);
			bank.addCustomer(bill);
			checkingAccount.deposit(100.0);
	        assertEquals(0.1, bank.totalInterestPaid(), DOUBLE_DELTA);
		} catch (AccountException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void savings_account() {
        Bank bank = new Bank();
        Account checkingAccount;
		try {
			checkingAccount = new SavingsAccount("Bill Account 1");
			bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));
	        checkingAccount.deposit(1500.0);
	        assertEquals(2.0, bank.totalInterestPaid(), DOUBLE_DELTA);
		} catch (AccountException e) {
			e.printStackTrace();
		}
        
    }

    @Test
    public void maxi_savings_account() {
        Bank bank = new Bank();
        Account checkingAccount;
		try {
			checkingAccount = new MaxiSavingsAccount("Bill acc 1");
		
        bank.addCustomer(new Customer("Bill").openAccount(checkingAccount));

        checkingAccount.deposit(3000.0);

        assertEquals(170.0, bank.totalInterestPaid(), DOUBLE_DELTA);
		} catch (AccountException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
