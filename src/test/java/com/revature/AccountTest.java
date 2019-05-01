package com.revature;

import java.sql.Date;
import java.sql.Timestamp;

import org.junit.Before;
import org.junit.Test;

import junit.framework.TestCase;

public class AccountTest extends TestCase{
	Timestamp date= new Timestamp(System.currentTimeMillis());
	double initialBalance1 = 100011;
	double initialBalance2 = 200022;
	Account acc1;
	Account acc2;
	double expectedResult;
	@Before
	public  void setUp(){
		acc1 = new Account("1101", "TestCustomer1", date, initialBalance1);
		acc2 = new Account("1102", "TestCustomer2", date, initialBalance2);
	}
	@Test
	public void testDeposit() {
		acc1.deposit(100);
		expectedResult = 100111;
		assertEquals(expectedResult , acc1.getBalance());
	}
	@Test
	public void testDeposit0() {
		try {
			expectedResult = 100011;
			assertEquals(expectedResult, acc1.getBalance());
			acc1.deposit(0);
			fail("deposit of zero should throw IllegalArgumentException");
		}catch(IllegalArgumentException e) {
			assertEquals(initialBalance1, acc1.getBalance());
		}
	}
	@Test 
	public void testWithdraw() {
		acc1.withdraw(11);
		expectedResult = 100000;
		assertEquals(expectedResult, acc1.getBalance());
	}
	
	//transfer $123 from account1(100011) to account2(200022).
	@Test
	public void testTransfer() {
		double transferAmount = 123;
		acc1.transfer(acc2, transferAmount);
		expectedResult = 200145;
		assertEquals(expectedResult, acc2.getBalance());
		System.out.println("Account2 total balance = "+acc2.getBalance()+"\n Account1 total balance = "+acc1.getBalance());
	}
		
}
