package com.revature;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Account implements AccountInterface{
	private String accountNo;
	private String accountHolder;
	private Timestamp openDate;
	private double balance = 0;
	Scanner input = new Scanner(System.in);
	Timestamp timestamp = new Timestamp(System.currentTimeMillis());
	String message;
//	String log4jConfigFile = System.getProperty("user.dir")+ File.separator + "log4j.properties";
//    PropertyConfigurator.configure(log4jConfigFile);
//	final Logger logger = Logger.getLogger(Account.class);
	ArrayList<Account>account = new ArrayList<>();
	
	public Account() {
		super();
	}
	public Account(ArrayList<Account> account) {
		this.accountNo = account.get(0).toString();
		this.accountHolder = account.get(1).toString();
		this.openDate = account.get(2).timestamp;
		this.balance = account.get(3).balance;
	}

	public Account(String accountNo2, String string, Timestamp openDate2, double balance2) {
		this.accountNo = accountNo2;
		this.accountHolder = string;
		this.openDate = openDate2;
		this.balance = balance2;
	}
	public String getAccountNo() {
		return this.accountNo;
	}
	public String getAccountHolder() {
		return this.accountHolder;
	}
	public Timestamp getOpenDate() {
		return this.openDate;
	}
	public double getBalance() {
		return this.balance;
	}
	public void setBalance(double balance) {
		  this.balance = balance;
	}
	public String createAccount(long userId, String accType, ArrayList<String>jcustomerDetails) {
		String accId = null;
		accountNo = getSaltStringAccNo();
		try(Connection conn = ConnectionConfiguration.getConnection()) {
			
			PreparedStatement prestat = conn.prepareStatement("insert into accounts (account_no, account_type, balance, open_date) values(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			prestat.setString(1, accountNo);
			prestat.setString(2, accType);
			prestat.setLong(3, 0);
			prestat.setTimestamp(4, timestamp);
			ResultSet rs = prestat.executeQuery();
			try (ResultSet key = prestat.getGeneratedKeys()) {
				if (key.next()) {
					accId = key.getString(1);
					PreparedStatement accHolder1 = conn.prepareStatement("insert into account_holders (user_id, account_id) values(?,?)", Statement.RETURN_GENERATED_KEYS);
					accHolder1.setLong(1, userId);
					accHolder1.setString(2, key.getString(1));
					ResultSet rsh = accHolder1.executeQuery();
					if(jcustomerDetails!=null) {
						UsersAuth reg = new UsersAuth();
						long jHloderId = reg.register(jcustomerDetails.get(0), jcustomerDetails.get(1), jcustomerDetails.get(2), jcustomerDetails.get(3), jcustomerDetails.get(4));
						PreparedStatement accHolder2 = conn.prepareStatement("insert into account_holders (user_id, account_id) values(?,?)", Statement.RETURN_GENERATED_KEYS);
						accHolder2.setLong(1, jHloderId);
						accHolder2.setString(2, key.getString(1));
						ResultSet rsh2 = accHolder2.executeQuery();
						rsh2.close();
					}
					rsh.close();
				}else {
                    System.out.println("Couldn't get Account ID.");
                }
                key.close();
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return accId;
	}
	//deposit implementation
	@Override
	public void deposit(double amount) {
		boolean okay;
		
		do {
			if(amount>0) {
				okay = true;
				balance = balance+amount;
				
				message = "$"+amount+" has deposited at "+timestamp+"\nNew Total balance :"+ balance;
				System.out.println("Total Balanced :" + balance);
			}else {
				okay = false;
				message = "$"+amount+" Invalid Input.";
				System.out.println("Please enter valid number.");
				amount = input.nextLong();
			}
		}while(!okay);
//		logger.info(message);
	}
	//withdraw implementation
	@Override
	public void withdraw(double amount) {
		boolean okay;
		 do {
			 if(amount>0) {
				 okay = true;
				 if(this.balance>=amount) {
					 balance = balance-amount;
					 message = "$"+amount+" has withdrawn at "+timestamp+"\nNew Total balance :"+ balance;
					 System.out.println("Total Balancew :" + balance);
				 }else {
					 okay = false;
					 message = "$"+amount+" Trying to withdraw more than available balance.";
					 System.out.println("Sorry! Low Balance" + "\nTotal Balance :" + balance);
					 System.out.println("Please enter below total balance.");
					 amount = input.nextLong();
				 } 
			 }else {
				 okay = false;
				 message = "$"+amount+" Invalid Input.";
				 System.out.println("Please enter valid number.");
				 amount = input.nextLong();
			 }
		 }while(!okay);
//		 logger.info(message);
	}
	//transfer implementation
	@Override
	public void transfer(Account a, double amount) {
		this.withdraw(amount);
		a.deposit(amount);
		message = "Transfer Success";
//		logger.info(message);
	}	

	@Override
	public String getAccountId() {
		return null;
	}
	 public void getAccountDetails(long userId){
		 String sql = null;
		 String details = null;
		 try(Connection conn = ConnectionConfiguration.getConnection()) {
			sql = "select * from account_holders where user_id=?";
			PreparedStatement prestat = conn.prepareStatement(sql);
			prestat.setLong(1, userId);
			ResultSet resultSet = prestat.executeQuery();
			
			if(resultSet.next() != false) {
					PreparedStatement pst = conn.prepareStatement("select accounts.* ,fName, lName from users inner join account_holders as ah on ah.user_id = users.id inner join accounts on accounts.id = ah.account_id where users.id=?"); 
					pst.setLong(1, userId);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						accountNo = rs.getString("account_no");
						balance = rs.getDouble("balance");
						openDate = rs.getTimestamp("open_date");
						
						account.add(new Account(accountNo, "username", openDate, balance));
						
//						details = accountNo+"username"+openDate+balance;
					}
				
			}else {
				System.out.println("Select Account type you want to open!\n"+"1.Single Account Holder\n"+"2.Joint Account.");
				int accType = input.nextInt();
//				Customer cust = new Customer();
//				account = cust.requestOpenAcc(userId);
				Staff staff = new Staff();
				account = staff.getResponse(userId, "Single", null);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 Transaction trans = new Transaction();
		 trans.options(account);
     }
	 protected String getSaltStringAccNo() {
	        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
	        StringBuilder salt = new StringBuilder();
	        Random rnd = new Random();
	        while (salt.length() < 18) {
	            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
	            salt.append(SALTCHARS.charAt(index));
	        }
	        String saltStr = salt.toString();
	        return saltStr;
	 }
	 public  void viewAllaccounts() {
			try(Connection con = ConnectionConfiguration.getConnection()) {
				Statement stat = con.createStatement();
				ResultSet rs = stat.executeQuery("select accounts.* ,fName, lName from users inner join account_holders as ah on ah.user_id = users.id inner join accounts on accounts.id = ah.account_id");
				while(rs.next()) {
					int accId = rs.getInt("id");
					String account_No = rs.getString("account_no");
					String account_type = rs.getString("account_type");
					double balance = rs.getDouble("balance");
		            Timestamp date = rs.getTimestamp("open_date");
		            String holderFname = rs.getString("fName");
		            String holderLname = rs.getString("lName");
		            System.out.println(account_No + ", " + holderFname + ", " + holderLname + ", " + account_type + ", "+balance+", "+date);
//		            account.add(new Account(account_No, holderFname, date, balance));
				}
				rs.close();
				stat.close();
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	 }
}
