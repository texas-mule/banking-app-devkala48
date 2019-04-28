package com.revature;

import java.util.Scanner;

public class BankAccount {
	private double balance = 100;
	static Scanner bank_obj = new Scanner(System.in);
	
	//deposit method
	void deposit() {
		System.out.println("Enter amount to deposite");
		boolean okay;
		do {
			double amount = bank_obj.nextLong();
			if(amount>0) {
				okay = true;
				balance = balance+amount;
				System.out.println("Total Balance :" + balance);
			}else {
				okay = false;
				System.out.println("Please enter valid number.");
			}
		}while(!okay);
	}
	//withdraw method
	void withdraw() {
	 System.out.println("Enter amount to withdraw");
	 boolean okay;
	 do {
		 double amount = bank_obj.nextLong();
		 if(amount>0) {
			 okay = true;
			 if(balance>=amount) {
				 balance = balance-amount;
				 System.out.println("Total Balance :" + balance);
			 }else {
				 okay = false;
				 System.out.println("Sorry! Low Balance" + "\nTotal Balance :" + balance);
			 } 
		 }else {
			 okay = false;
			 System.out.println("Please enter valid number.");	 
		 }
	 }while(!okay);
	 
	}
	//transfer method
	void transfer() {
	
	}

	public static void main(String[] args) {
		BankAccount acct = new BankAccount();
		
		int ch;
		do
		{
			System.out.println("Main Menu\n 1.Withdraw\n 2.Deposite\n 3.Transfer\n 4.Exit");
			System.out.println("Select Option :");
			ch = bank_obj.nextInt();
			switch(ch)
			{ 
				case 1:
					acct.withdraw();
					break;

				case 2:
					acct.deposit();
					break;

				case 3:
					acct.transfer();
					break;
				case 4:
					System.out.println("Thank you!");
					break;
				default :
					System.out.println("Please choose valid Options.");
					break;
			}
		}
		while(ch!=4);
	}

}
