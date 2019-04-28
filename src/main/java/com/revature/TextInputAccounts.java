package com.revature;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextInputAccounts {
	
	public static ArrayList<Account> readFile(String filename){
		ArrayList<Account>account = new ArrayList<>();
//		try(BufferedReader br = new BufferedReader(new FileReader("accountDetails.txt"))) {
//			String line = br.readLine();
//			while(line != null) {
//				String[] currentAccount = line.split(" ");
//				String accNumber = currentAccount[0];
//				String accHolder = currentAccount[1];
//				String openDate = currentAccount[2];
//				double balance = Double.parseDouble(currentAccount[3]);
//				Account acc = new Account(accNumber, accHolder, openDate, balance);
//				account.add(acc);	
//			}
//			br.close();
//		} catch (FileNotFoundException e) { 
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		return account;
	}
	
	public static void writeFile(ArrayList<Account>accounts, String filename) {
		try(BufferedWriter bw = new BufferedWriter(new FileWriter("accountDetails.txt"))){
			for(Account a : accounts) {
				bw.write(a.toString()+" "+a.getAccountHolder()+" "+a.getOpenDate()+" "+String.valueOf(a.getBalance())+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
