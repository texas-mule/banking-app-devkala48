package com.revature;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer {
	
	private Account account;
	
	Scanner customerObj = new Scanner(System.in);
	ArrayList<String>jcustomerDetails = new ArrayList<>();

    public Account getAccount() {
    	return account;
    }
    
    public void setAccount(Account acct) {
        this.account = acct;
    }
    
	public ArrayList<Account> requestOpenAcc(long userId) {
		ArrayList<Account>details = new ArrayList<Account>();
		System.out.println("Select Account type you want to open!\n"+"1.Single Account Holder\n"+"2.Joint Account.");
		int accType = customerObj.nextInt();
		String type;
		if(accType == 1) { type = "Single";}else {type = "Joint";
		System.out.println("Enter First name.");
		jcustomerDetails.add(customerObj.next());
		
		System.out.println("Enter Last name.");
		jcustomerDetails.add(customerObj.next());
		
		System.out.println("Enter Email.");
		jcustomerDetails.add(customerObj.next());
		
		System.out.println("Enter Username.");
		jcustomerDetails.add(customerObj.next());
		
		System.out.println("Enter Password.");
		jcustomerDetails.add(customerObj.next());
		}
		Staff staff = new Staff();
		staff.getResponse(userId, type, jcustomerDetails);
		return details;
	}
	
}
