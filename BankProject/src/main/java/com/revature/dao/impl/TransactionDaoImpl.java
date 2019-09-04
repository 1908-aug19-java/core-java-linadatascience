package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.TransactionDao;
import com.revature.model.BankAccount;
import com.revature.model.Transaction;
import com.revature.util.ConnectionUtil;

public class TransactionDaoImpl implements TransactionDao {
    
	@Override
	public List<Transaction> getTransactionsByAccountNo(int account_no) {
		BankAccountDaoImpl badi = new BankAccountDaoImpl();
		List<Transaction> transactions = new ArrayList<>();
		String sql = "select * from transactions where acct_no = ?";
		
		try(Connection c = ConnectionUtil.getHardCodeConnection();
			PreparedStatement ps = c.prepareStatement(sql);){
			
			   ps.setInt(1,account_no);
			   ResultSet rs = ps.executeQuery();
			   
			   while(rs.next()) {
				   int transid = rs.getInt("trans_id");
				   Timestamp transTime = rs.getTimestamp("trans_time");
				   double transAmount = rs.getDouble("trans_amount");
				   BankAccount account = badi.getAccountByAcctNo(account_no);
				   Transaction transaction = new Transaction(transid, transTime, transAmount, account);
				   transactions.add(transaction);
			   }
			   
			   rs.close();
			   
		} catch (SQLException e) {
			   e.printStackTrace();
		}
		
		return transactions ;
	}

	@Override
	public int addTransaction(Transaction transaction) {
		int numOfTransactionAdded = 0;
		BankAccount account = transaction.getAcct();
		int acctNo = account.getAcct_No();
		Timestamp transTime = transaction.getTrans_time();
	    double transAmount = transaction.getTrans_amount();
	    
		String sql = "insert into transactions (trans_time, trans_amount, acct_no) values (?, ?,?)";
		
		try(Connection c = ConnectionUtil.getHardCodeConnection();
		    PreparedStatement ps = c.prepareStatement(sql)){
			
			    
			    ps.setTimestamp(1, transTime);
			    ps.setDouble(2, transAmount);
			    ps.setInt(3, acctNo);
			    numOfTransactionAdded = ps.executeUpdate();
			    
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(numOfTransactionAdded > 0) {
			System.out.println(Integer.toString(numOfTransactionAdded)+ " transactions is added!");
		}
		return numOfTransactionAdded;
	}
	
	

}
