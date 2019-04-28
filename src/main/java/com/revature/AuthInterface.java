package com.revature;

public interface AuthInterface {
	boolean login(String username, String password, String userRole);
	void logout();
	void register();
}
