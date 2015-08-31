package com.abc;

import java.util.Date;

public class MaxiSavingsAccount extends Account {

	public MaxiSavingsAccount(String accID) throws AccountException {
		super(accID);
	}

	@Override
	public double interestEarned() {
		if (balance <= 1000)
            return balance * 0.02;
		else if (balance <= 2000)
            return 20 + (balance-1000) * 0.05;
		else 
			return 70 + (balance-2000) * 0.1;
	}
	
	public double updatedInterestEarned() {
		
		boolean transactionInTenDays = false;
		
		Date currentTime = DateProvider.getInstance().now();
		for(Transaction tran: transactions)
		{
			if((currentTime.getTime()-tran.getTransactionTime().getTime())/(1000*60*60*24)>10)
			{
				transactionInTenDays = true;
				break;
			}
		}
		if(transactionInTenDays)
			return balance * 0.001;
		else
			return balance * 0.05;
			
	
	}

}
