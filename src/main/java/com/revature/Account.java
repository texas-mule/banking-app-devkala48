package com.revature;

import java.util.Random;
import java.util.Scanner;

public class Account implements AccountInterface{
	Random randomGenerator = new Random();
	String accountNo = randomGenerator.toString();
	private String accountHolder;
	private String openDate;
	private double balance = 0;
	Scanner input = new Scanner(System.in);
	
	public Account() {
		super();
	}
	public Account(String accNo, String accHd, String date, double accBl) {
		this.accountNo = accNo;
		this.accountHolder = accHd;
		this.openDate = date;
		this.balance = accBl;
	}

	public String getAccountNo() {
		return this.accountNo;
	}
	public String getAccountHolder() {
		return this.accountHolder;
	}
	public String getOpenDate() {
		return this.openDate;
	}
	public double getBalance() {
		return this.balance;
	}
	public void setBalance(double balance) {
		  this.balance = balance;
	}
	//deposit implementation
	@Override
	public void deposit(double amount) {
		boolean okay;
		do {
			if(amount>0) {
				okay = true;
				balance = balance+amount;
				System.out.println("Total Balance :" + balance);
			}else {
				okay = false;
				System.out.println("Please enter valid number.");
				amount = input.nextLong();
			}
		}while(!okay);
	}
	//withdraw implementation
	@Override
	public void withdraw(double amount) {
		boolean okay;
		 do {
			 if(amount>0) {
				 okay = true;
				 if(this.balance>=amount) {
					 balance = balance-amount;
					 System.out.println("Total Balance :" + balance);
				 }else {
					 okay = false;
					 System.out.println("Sorry! Low Balance" + "\nTotal Balance :" + balance);
					 System.out.println("Please enter below total balance.");
					 amount = input.nextLong();
				 } 
			 }else {
				 okay = false;
				 System.out.println("Please enter valid number.");
				 amount = input.nextLong();
			 }
		 }while(!okay);
	}
	//transfer implementation
	@Override
	public void transfer(Account a, double amount) {
		this.withdraw(amount);
		a.deposit(amount);
	}	

	@Override
	public String getAccountId() {
		return null;
	}
	 public String getAccountInfo(){
         return "Account number: " + accountNo + "\nCustomer Name: " + accountHolder + "\nBalance:" + balance +"\n";
     }
}
