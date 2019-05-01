package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionConfiguration {
	static Connection connection = null;
	private ConnectionConfiguration() {
		
	}
	public static Connection getConnection() {
		// url = jdbc:subprotocol://url-ip:rdbms-port/database-name
		String url = "jdbc:postgresql://127.0.0.1:8008/banking_app";
		String username = "postgres";
		String password = "password";
		try{
			if(connection == null)
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}
}
