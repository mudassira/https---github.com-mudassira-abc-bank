package com.abc;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name) {
        this.name = name;
        this.accounts = new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public Customer openAccount(Account account) throws AccountException {
        for(Account ac: accounts)
        {
        	if(ac.getAccountId().compareTo(account.getAccountId())==0)
        	{
        		throw new AccountException("Account ID already exists for customer.");
        	}
        }
    	
    	accounts.add(account);
        return this;
    }

    public int getNumberOfAccounts() {
        return accounts.size();
    }

    public double totalInterestEarned() {
        double total = 0;
        for (Account a : accounts)
            total += a.interestEarned();
        return total;
    }

    public String getStatement() {
        StringBuilder statement = new StringBuilder();
        statement.append("Statement for ").append(name).append( "\n");
        double total = 0.0;
        for (Account a : accounts) {
            try {
				statement.append( "\n").append(statementForAccount(a)).append("\n");
			} catch (AccountException e) {
				e.printStackTrace();
			}
            total += a.sumTransactions();
        }
        statement.append( "\nTotal In All Accounts ").append(toDollars(total));
        return statement.toString();
    }

    private String statementForAccount(Account a) throws AccountException {
        StringBuilder sb = new StringBuilder();
       //Translate to pretty account type
        if(a instanceof CheckingAccount)
        	sb.append("Checking Account\n");
        else if(a instanceof SavingsAccount)
        	sb.append("Savings Account\n");
        else if(a instanceof MaxiSavingsAccount)
        	sb.append("Maxi Savings Account\n");
        else
        	throw new AccountException("Unknown Account type");
        

        //Now total up all the transactions
        double total = 0.0;
        for (Transaction t : a.transactions) {
            sb.append("  ").append(t.amount < 0 ? "withdrawal" : "deposit").append(" ").append(toDollars(t.amount)).append("\n");
            total += t.amount;
        }
        sb.append( "Total ").append(toDollars(total));
        return sb.toString();
    }

    private String toDollars(double d){
        return String.format("$%,.2f", abs(d));
    }
    
    public boolean transferAmount(double amount, String accountIdFrom, String accountIdTo) throws AccountException
    {
    	if(amount <= 0)
    		return false;
    	
    	if(accountIdFrom.compareTo(accountIdTo)==0)
    		throw new AccountException("Cannot transfer to the same account");
    	
    	Account accountFrom = null;
    	Account accountTo = null;
    	for(Account acc: accounts)
    	{
    		if(acc.getAccountId().compareTo(accountIdFrom)==0)
    			accountFrom=acc;
    		if(acc.getAccountId().compareTo(accountIdTo)==0)
    			accountTo=acc;
    	}
    	
    	if(accountFrom==null)
    		throw new AccountException("Account from not found.");
    	
    	if(accountTo==null)
    		throw new AccountException("Account to not found.");
    	
    	if(accountFrom.balance<amount)
    		return false;
    	
    	accountFrom.withdraw(amount);
    	accountTo.deposit(amount);
    	return true;
    	
    }
}
