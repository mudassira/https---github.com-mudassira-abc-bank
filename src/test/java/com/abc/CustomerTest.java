package com.abc;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {

    @Test //Test customer statement generation
    public void testApp(){

        
		try {
			Account checkingAccount = new CheckingAccount("Henry checking");
		
	        Account savingsAccount = new SavingsAccount("Henry savings");
	
	        Customer henry = new Customer("Henry").openAccount(checkingAccount).openAccount(savingsAccount);
	
	        checkingAccount.deposit(100.0);
	        savingsAccount.deposit(4000.0);
	        savingsAccount.withdraw(200.0);
	
	        assertEquals("Statement for Henry\n" +
	                "\n" +
	                "Checking Account\n" +
	                "  deposit $100.00\n" +
	                "Total $100.00\n" +
	                "\n" +
	                "Savings Account\n" +
	                "  deposit $4,000.00\n" +
	                "  withdrawal $200.00\n" +
	                "Total $3,800.00\n" +
	                "\n" +
	                "Total In All Accounts $3,900.00", henry.getStatement());
		
	        henry.transferAmount(500, "Henry savings", "Henry checking");
	        
	        assertEquals("Statement for Henry\n" +
	                "\n" +
	                "Checking Account\n" +
	                "  deposit $100.00\n" +
	                "  deposit $500.00\n" +
	                "Total $600.00\n" +
	                "\n" +
	                "Savings Account\n" +
	                "  deposit $4,000.00\n" +
	                "  withdrawal $200.00\n" +
	                "  withdrawal $500.00\n" +
	                "Total $3,300.00\n" +
	                "\n" +
	                "Total In All Accounts $3,900.00", henry.getStatement());
		
		} catch (AccountException e) {
			e.printStackTrace();
		}
    }

    @Test
    public void testOneAccount(){
        Customer oscar;
		try {
			oscar = new Customer("Oscar").openAccount(new SavingsAccount("Oscar savings"));
			assertEquals(1, oscar.getNumberOfAccounts());
		} catch (AccountException e) {
			e.printStackTrace();
		}
        
    }

    @Test
    public void testTwoAccount(){
        Customer oscar;
		try {
			oscar = new Customer("Oscar")
			        .openAccount(new SavingsAccount("Oscar savings 1"));
			oscar.openAccount(new CheckingAccount("Oscar checking 1"));
	        assertEquals(2, oscar.getNumberOfAccounts());
		} catch (AccountException e) {
			e.printStackTrace();
		}
        
    }

    @Test
    public void testThreeAcounts() {
        Customer oscar;
		try {
			oscar = new Customer("Oscar")
			        .openAccount(new SavingsAccount("Oscar savings 1"));
			oscar.openAccount(new CheckingAccount("Oscar checking 1"));
	        oscar.openAccount(new CheckingAccount("Oscar checking 2"));
	        assertEquals(3, oscar.getNumberOfAccounts());
		} catch (AccountException e) {
			e.printStackTrace();
		}
        
    }
}
