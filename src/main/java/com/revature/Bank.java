package com.revature;

public class Bank {
	private Account[] accounts;
	private int numOfAccounts;
	
	private Customer[] customers = new Customer[100];
	 
	public Customer[] getCustomer() {
		return customers;
	}
	public Bank() {
	   accounts = new Account[100];
	   numOfAccounts = 0;
	}
	// Creates a new bank account and returns the account number of this new account. It also adds this account into the account list
	// of the Bank calling object.
	public String openNewAccount(String accountNo, String acccountHolder, String date, double balance) {

	    Account b = new Account(accountNo, acccountHolder, date, balance);
	    accounts[numOfAccounts] = b;
	    numOfAccounts++;
	    return b.getAccountNo();
	}
	// Prints the account number, the customer name and the balance of the bank account whose
	// account number is given. If the account is not available at the bank, it should print a message.
	public void accountInfo(String accountNum) {
	    for (int i =0; i<numOfAccounts; i++) {
	                if (accountNum == accounts[i].getAccountNo()  ) {
	                    System.out.println(accounts[i].getAccountInfo());
	                    return;
	                }
	            }
	    System.out.println("Account number not found.");
	}
}
