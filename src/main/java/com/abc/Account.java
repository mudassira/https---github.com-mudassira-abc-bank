package com.abc;

import java.util.ArrayList;
import java.util.List;

public abstract class Account {

    public List<Transaction> transactions;
    protected double balance;
    protected String accountID;
    
    public Account(String accID) throws AccountException {
        if(accID.compareTo("")==0)
        	throw new AccountException("Invalid Account ID.");
    	
    	transactions = new ArrayList<Transaction>();
        balance=0;
        accountID = accID;
    }
    
    public String getAccountId()
    {
    	return accountID;
    }
    
    public double getBalance()
    {
    	return balance;
    }
    
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be greater than zero");
        } else {
            balance+=amount;
        	transactions.add(new Transaction(amount));
        }
    }

public boolean withdraw(double amount) {
    if (amount <= 0) {
        throw new IllegalArgumentException("amount must be greater than zero");
    } 
    else if (amount>balance)
    {
    	return false;
    }
    else {
        balance-=amount;
    	transactions.add(new Transaction(-amount));
    }
    return true;
}

    public abstract double interestEarned(); /*{
        double amount = sumTransactions();
        switch(accountType){
            case SAVINGS:
                if (amount <= 1000)
                    return amount * 0.001;
                else
                    return 1 + (amount-1000) * 0.002;
//            case SUPER_SAVINGS:
//                if (amount <= 4000)
//                    return 20;
            case MAXI_SAVINGS:
                if (amount <= 1000)
                    return amount * 0.02;
                if (amount <= 2000)
                    return 20 + (amount-1000) * 0.05;
                return 70 + (amount-2000) * 0.1;
            default:
                return amount * 0.001;
        }
    }*/

    public double sumTransactions() {
    	double amount = 0.0;
        for (Transaction t: transactions)
            amount += t.amount;
        return amount;
    }

    private boolean checkIfTransactionsExist() {
        return transactions.size()!=0;
    }
    
    public void accrueDailyInterest()
    {
    	balance += interestEarned()/365;
    }

    
}
