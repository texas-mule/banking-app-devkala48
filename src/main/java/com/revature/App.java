package com.revature;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class App{

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);

		UsersAuth users = new UsersAuth();
		boolean status = users.option();
		if(status == true) {
			
			ArrayList<Account>accounts = TextInputAccounts.readFile("accountDetails.txt");
				System.out.println(accounts);
			Account acct = new Account("1101", "TestCustomer", "04/26/019", 100011);
			int choice = 0;
			do
			{ 
				System.out.println("Main Menu\n 1.Withdraw\n 2.Deposite\n 3.Transfer\n 4.Exit");
				System.out.println("Select Option :");
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
		//log4j configuring
		String log4jConfigFile = System.getProperty("user.dir")
                + File.separator + "log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
		final Logger logger = Logger.getLogger(App.class);
		logger.info("This is info message");
	}
}
