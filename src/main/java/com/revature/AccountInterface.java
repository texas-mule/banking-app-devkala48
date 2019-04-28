package com.revature;

public interface AccountInterface {
	void deposit(double n);
	void withdraw(double n);
	void transfer(Account a, double amount);
	public double getBalance();
	public String getAccountId();
}
