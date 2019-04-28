package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Driver {

	public static void main(String[] args) {
		// url = jdbc:subprotocol://url-ip:rdbms-port/database-name
		String url = "jdbc:postgresql://127.0.0.1:8008/banking_app";
		String username = "postgres";
		String password = "password";
		String sql;
		
		// Create/Open a connection to a postgres instance and take in Scanner input from stdin
		try (Connection connection = DriverManager.getConnection(url, username, password)) {
			Scanner scanner = new Scanner(System.in);
			
			while(true) {
				Statement statement = connection.createStatement();
				System.out.print("sql> ");
				sql = scanner.nextLine();
				if (sql.equalsIgnoreCase("quit"))
					break;
				
				boolean isResultSet = statement.execute(sql);
				
				if (isResultSet) {
					ResultSet resultSet = statement.getResultSet();
					ResultSetMetaData rsmd = resultSet.getMetaData();
					
					// Print rows
					while (resultSet.next()) {
						//Food currentRow = new Food();
						for (int i = 1; i <= rsmd.getColumnCount(); i++) {
							System.out.print(resultSet.getString(i) + "\t");
//							currentRow.id = resultSet.getInt("id");
//							currentRow.name = resultSet.getString(2);
						}
						//System.out.println(currentRow);
						System.out.println();
					}
					resultSet.close();
				} else {
					// Print rows affected
					System.out.println(statement.getUpdateCount() + " rows affected.");
				}
				
				statement.close();
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

	}

}

