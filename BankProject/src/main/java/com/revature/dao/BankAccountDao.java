package com.revature.dao;

import java.util.List;

import com.revature.model.BankAccount;

public interface BankAccountDao {
      
	public int withDraw(int account_No, double amount);
	public boolean deposit(int account_No, double amount);
	public boolean withdraw(int account_No, double amount);
	
	public BankAccount getAccountByUsername(String username);
	public BankAccount getAccountByAcctNo(int acctNo);
	public void viewBalance(int account_no);
	
}
