package com.abc;

public class BankManager {

	protected String managerName;
	protected Bank managerBank;
	
	BankManager(String name, Bank bank )
	{
		managerName = name;
		managerBank = bank;
	}
	
	public String getCustomerReport()
	{
		return managerBank.customerSummary();
		
	}
	public String getTotalInterestPaid()
	{
		return "Total Interest Paid: " + managerBank.totalInterestPaid() + "\n";
	}

}
