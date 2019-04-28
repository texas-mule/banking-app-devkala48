package com.revature;
import java.util.Scanner;

public class Customer {
	
	private String fName;
	private String lName;
	private Account account;
	
	Scanner customerObj = new Scanner(System.in);

    Customer(String firstName, String lastName, String number) {   
        this.fName = firstName;
        this.lName = lastName;
    }
    
    public Account getAccount() {
    	return account;
    }
    
    public void setAccount(Account acct) {
        this.account = acct;
    }
    
	public void requestOpenAcc() {
		
		System.out.println("Enter your First name.");
		fName = customerObj.nextLine();
		System.out.println("Enter your Last name.");
		lName = customerObj.nextLine();
		Customer.this.applicationResponse(fName, lName);
		
	}
	public void applicationResponse(String customerFN, String customerLN) {
		String response;
		System.out.println("Press 1 to Accept\n" + "or" + "\nPress 2 to Decline.");
		response = this.customerObj.next();
		if(response.equals("1")) {
			Customer.this.openAcc(customerFN, customerLN);
		}else {
			Customer.this.denyAcc();
		}
	}
	public void openAcc(String customerFN, String customerLN) {
		System.out.println("account created");
	}
	public void denyAcc() {
		System.out.println("Application has been denied.");
	}
	public String customerAccount(Customer customer) {
		
		return fName;
		
	}
}
