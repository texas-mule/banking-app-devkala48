package com.revature;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class App{
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		UsersAuth users = new UsersAuth();
		long returnId_Value = users.option();
		if(returnId_Value != 0) {
			int userRole = 0;
			Connection con = ConnectionConfiguration.getConnection();
			try{
				PreparedStatement preStat = con.prepareStatement("Select role_id from users where id =?");
				preStat.setLong(1, returnId_Value);
				ResultSet rs = preStat.executeQuery();
				while(rs.next()) {
					userRole = rs.getInt("role_id");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if(userRole == 1 || userRole == 2) {
				Account acc = new Account();
				acc.viewAllaccounts();	
			}else if(userRole == 3) {
				Account acc = new Account();
				acc.getAccountDetails(returnId_Value);	
			}
		}
		//log4j configuring
		String log4jConfigFile = System.getProperty("user.dir")
                + File.separator + "log4j.properties";
        PropertyConfigurator.configure(log4jConfigFile);
		final Logger logger = Logger.getLogger(App.class);
		logger.info("Application Executed Successfully.");
	}
}
