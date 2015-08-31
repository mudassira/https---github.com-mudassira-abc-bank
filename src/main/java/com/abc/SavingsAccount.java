package com.abc;

public class SavingsAccount extends Account {

	public SavingsAccount(String accID) throws AccountException {
		super(accID);
	}

	@Override
	public double interestEarned() {
		if (balance <= 1000)
            return balance * 0.001;
        else
            return 1 + (balance-1000) * 0.002;
		
	}

}
