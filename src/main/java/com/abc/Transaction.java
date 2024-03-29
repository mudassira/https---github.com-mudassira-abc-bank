package com.abc;

import java.util.Date;

public class Transaction {
    public final double amount;

    private Date transactionTime;

    public Transaction(double amount) {
        this.amount = amount;
        this.transactionTime = DateProvider.getInstance().now();
    }
    
    public Date getTransactionTime()
    {
    	return transactionTime;
    }

}
