package com.revature;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UsersAuth implements AuthInterface{
	String fName;
	String lName;
	int userId = 0;
	String email;
	String userRole;
	Scanner userObj = new Scanner(System.in);
	@Override
	public void register() {
		System.out.println("Enter your First name.");
		fName = userObj.next();
		System.out.println("Enter your Last name.");
		lName = userObj.next();
		System.out.println("Enter your Email.");
		email = userObj.next();
		System.out.println("Please! Select User Role.");
		userRole = userObj.next().toUpperCase();
		if(userRole.equals("ADMIN")) {
		 userRole = "Admin";
		}else if (userRole.contentEquals("EMPLOYEE")) {
			userRole = "Employee";
		}else {
			userRole = "Customer";
		}
	}
	@Override
	public boolean login(String username, String password, String userRole) {
		boolean authentication = false;
		try(BufferedReader br = new BufferedReader(new FileReader("user.txt"))) {
		    String line = br.readLine();
		    String txtUser = line.split(" ")[0].toLowerCase();
		    String txtPass =line.split(" ")[1].toLowerCase();
		    if (username.equals(txtUser) && password.equals(txtPass)) {
		    	authentication = true;
		        System.out.print(userRole +" logged In.");   
		    } else {
		    	authentication = false;
		        System.out.print("Doesn't match the user or password."); 
		    }
		    return authentication;
		} catch (FileNotFoundException e) { 
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return authentication;    
	}
	@Override
	public void logout() {
		
	}
	public boolean option() {
		boolean userStatus = false;
		boolean okay;
		do{
			System.out.println("Please!\n"+"Press 1 to Login.\n"+"Press 2 to Register.");
			int ch = userObj.nextInt();
			if(ch==1) {
				
				System.out.println("Enter Username.");
				String username = userObj.next();
						
				System.out.println("Enter Password.");
				String password = userObj.next();
					
				System.out.println("User Role.");
				String userrole = userObj.next();
				userStatus = login(username, password, userrole);
				okay=true;
			}else if(ch==2) {
					register();
					System.out.println("Name : " +fName + " " + lName + "\nRole :" + userRole);
					okay = true;
			}else {
				okay = false;
			}
		}while(!okay);
		return userStatus;		
	}
}
