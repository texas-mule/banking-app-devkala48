package com.revature;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Scanner;

public class Transaction {
	public void options(ArrayList<Account> account) {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		Scanner input = new Scanner(System.in);
		boolean okay;
		int choice = 0;
			do	
			{ 
				Account acct = new Account("12345", "customer", timestamp, 12333);
				System.out.println("Main Menu\n 1.Withdraw\n 2.Deposite\n 3.Transfer\n 4.Exit");
				System.out.println("Select Options :");
				choice = input.nextInt();
				switch(choice)
				{ 
					case 1:
						System.out.println("Enter amount to withdraw");
						acct.withdraw(input.nextLong());
						break;
	
					case 2:
						System.out.println("Enter amount to Deposit");
						acct.deposit(input.nextLong());
						break;
	
					case 3:
						System.out.println("Enter amount to transfer");
						acct.transfer(acct, input.nextLong());
						break;
					case 4:
						System.out.println("Thank you!");
						break;
					default :
						System.out.println("Please choose valid Options.");
						break;
				}
		}while(choice!=4);
		}
}
