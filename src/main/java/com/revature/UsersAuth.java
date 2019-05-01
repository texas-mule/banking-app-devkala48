package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Scanner;

public class UsersAuth implements AuthInterface{
	String fName;
	String lName;
	private long userId = 0;
	String email;
	private String username;
	private String password;
	private int userRole;
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	Scanner userObj = new Scanner(System.in);

	@Override
	public long register(String fName, String lName, String email, String username, String password) {
		String sql;
//		customer role
		userRole = 3;
		PreparedStatement prepStmt = null;
		Connection con = ConnectionConfiguration.getConnection();
        try{
        	con.setAutoCommit(false);
            prepStmt = con.prepareStatement("SELECT  username from logins WHERE username=?");
            prepStmt.setString(1, username);
            ResultSet rs = prepStmt.executeQuery();
            
            if (rs.next() == false) { 
            	sql = "INSERT  into users (fname, lname, email, registration_date, role_id) VALUES (?,?,?,?,?)";
            	try {
	            	prepStmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
	            	prepStmt.setString(1, fName);
	            	prepStmt.setString(2, lName);
	            	prepStmt.setString(3, email);
	            	prepStmt.setTimestamp(4, timestamp);
	            	prepStmt.setInt(5, userRole);  
	            	prepStmt.executeUpdate();
	            	
	            	try (ResultSet key = prepStmt.getGeneratedKeys()) {
	                    if (key.next()) {
	                    	userId = key.getLong(1);
	                    	sql = "INSERT  into logins (user_id, username, hash_password, salt_password) VALUES (?,?,?,?)";
	    	            	
	                    	PreparedStatement logStmt = con.prepareStatement(sql);
	    	            	logStmt.setLong(1, key.getLong(1));
	    	            	logStmt.setString(2, username);
	    	            	logStmt.setString(3, password);
	    	            	logStmt.setString(4, password);  
	    	            	logStmt.executeUpdate();
	    	            	logStmt.close();
	                    }
	                    else {
	                        System.out.println("Couldn't get User ID.");
	                    }
	                    key.close();
	                }catch(SQLException se) {
	                	 se.printStackTrace();
	                }
	            	prepStmt.close();
	            	con.commit();
	            }catch(SQLException se) {
	            	 se.printStackTrace();
	            }
            }else { 
            	System.out.println("Username already exist!");		
            }
            con.rollback();
           }catch (SQLException e) {
	    	  System.out.println(e.getMessage());
           }
		return userId;
	}
	@Override
	public long login(String username, String password) {
		
		String sql = "SELECT username, user_id FROM logins where username=" + "'"+username+"'";
		Connection con = ConnectionConfiguration.getConnection();
		try {
			Statement stm = con.createStatement();
			ResultSet rs = stm.executeQuery(sql);
			while(rs.next()) {
				userId = rs.getInt("user_id");
			}
			rs.close();
			stm.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		return userId;    
	}
	@Override
	public void logout() {
		
	}
	public long option() {
		boolean okay;
		do{
			System.out.println("Please!\n"+"Press 1 to Login.\n"+"Press 2 to Register.");
			int ch = userObj.nextInt();
			if(ch==1) {
				
				System.out.println("Enter Username.");
				String username = userObj.next();
						
				System.out.println("Enter Password.");
				String password = userObj.next();
				userId = login(username, password);
				okay=true;
			}else if(ch==2) {
				System.out.println("Enter First name.");
				fName = userObj.next();
				
				System.out.println("Enter Last name.");
				lName = userObj.next();
				
				System.out.println("Enter Email.");
				email = userObj.next();
				
				System.out.println("Enter Username.");
				username = userObj.next();
				
				System.out.println("Enter Password.");
				password = userObj.next();
				userId = register(fName, lName, email, username, password);
				okay = true;
			}else {
				okay = false;
			}
		}while(!okay);
		return userId;		
	}
}
