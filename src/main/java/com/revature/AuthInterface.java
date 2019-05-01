package com.revature;

public interface AuthInterface {
	long login(String username, String password);
	void logout();
	long register(String fName, String lName, String email, String username, String password);
}
