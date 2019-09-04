package com.revature.dao;

import java.util.List;

import com.revature.model.Transaction;

public interface TransactionDao {
      
	public List<Transaction> getTransactionsByAccountNo(int account_no);
	public int addTransaction(Transaction transaction);
	
}
