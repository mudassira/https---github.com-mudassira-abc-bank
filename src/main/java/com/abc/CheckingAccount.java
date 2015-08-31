package com.abc;

public class CheckingAccount extends Account {

	public CheckingAccount(String accID) throws AccountException {
		super(accID);
	}

	@Override
	public double interestEarned() {
		return balance * 0.001;
	}

	
}
