package com.revature.dao.impl;

import java.sql.CallableStatement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import com.revature.dao.BankAccountDao;
import com.revature.model.BankAccount;
import com.revature.model.Transaction;
import com.revature.model.UserAccount;
import com.revature.util.ConnectionUtil;

public class BankAccountDaoImpl implements BankAccountDao{

	@Override
	public int withDraw(int account_No, double amount) {
		
		return 0;
	}

	@Override
	public boolean deposit(int account_No, double amount) {
		TransactionDaoImpl tdi = new TransactionDaoImpl();
		String sql = "{call deposit(?,?)}";
		Transaction transaction = null;
		BankAccount account = null;
		boolean makeDeposit = false;
		int numOfTransactionAdded = 0;
		
		
		try(Connection c =ConnectionUtil.getHardCodeConnection();
			CallableStatement cs = c.prepareCall(sql)){
			
		    cs.setInt(1, account_No);
		    cs.setDouble(2,amount);
		    
		    makeDeposit = cs.execute();
		    
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		//add a deposit recored into transaction table
		String transType = "Deposit";
		Timestamp transTime = new Timestamp(System.currentTimeMillis());
		double transAmount = amount;
	//	int acctNo = account_No;
		account = getAccountByAcctNo(account_No);
		transaction = new Transaction(transTime, transAmount, account);
		numOfTransactionAdded = tdi.addTransaction(transaction);
		if(makeDeposit && (numOfTransactionAdded > 0)){
			  return true;
		}
		else return false;
	}
    
	@Override
	public BankAccount getAccountByUsername(String username) {
		// TODO Auto-generated method stub
		BankAccount account = null;
		UserAccount user = null;
		UserAccountDaoImpl uadi = new UserAccountDaoImpl();
		
		user = uadi.getUserAccountByUserName(username);
		String sql = "select ba.acct_no, ba.acct_type, ba.acct_balance"
				+ " from bankaccount ba "
				+ "join useraccount ua "
				+ "on ba.user_id = ua.user_id "
				+ "where ua.user_name = ?";
		
		
		try(Connection c = ConnectionUtil.getHardCodeConnection();
			PreparedStatement ps = c.prepareStatement(sql);){
			
			ps.setString(1,username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				int acctNo = rs.getInt("acct_no");
				String acctType = rs.getString("acct_type");
				double balance = rs.getDouble("acct_balance");
				account = new BankAccount(acctNo, acctType, balance, user);

			}
			
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return account;
	}

	@Override
	public void viewBalance(int account_no) {
		String sql = "select acct_balance from bankaccount where acct_no = ?";
		double balance = 0;
		try(Connection c = ConnectionUtil.getHardCodeConnection();
			PreparedStatement ps = c.prepareStatement(sql)){
			
			    ps.setInt(1, account_no);
			    ResultSet rs = ps.executeQuery();
			    
			    while(rs.next()) {
			    	balance = rs.getDouble("acct_balance");
			    }
		}
		// TODO Auto-generated method stub
             catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(Double.toString(balance));
		
	}

	@Override
	public BankAccount getAccountByAcctNo(int acctNo) {
		BankAccount account = null;
		String sql = "select * from bankaccount where acct_no = ?";
		UserAccountDaoImpl uadi = new UserAccountDaoImpl();
		
		try(Connection c = ConnectionUtil.getHardCodeConnection();
			PreparedStatement ps = c.prepareStatement(sql)){
			
			     ps.setInt(1, acctNo);
			     
			     ResultSet rs = ps.executeQuery();
			     
			     while(rs.next()) {
			    	 int acctno = rs.getInt("acct_no");
			    	 String acctType = rs.getString("acct_type");
			    	 double balance = rs.getDouble("acct_balance");
			    	 int userId = rs.getInt("user_id");
			    	 UserAccount user = uadi.getUserAccountByUserId(userId);
			    	 account = new BankAccount(acctno, acctType, balance, user);
			     }
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return account;
	}

	@Override
	public boolean withdraw(int accountNo, double amount) {
		TransactionDaoImpl tdi = new TransactionDaoImpl();
		String sql = "update bankaccount "
				+ "set acct_balance = acct_balance - ? "
				+ "where acct_no = ?";
		
		Transaction transaction = null;
		BankAccount account = null;
		int numOfTransactionAdded = 0;
		
		
		try(Connection c = ConnectionUtil.getHardCodeConnection();
			PreparedStatement ps = c.prepareStatement(sql)){
			
		    ps.setDouble(1, amount);
		    ps.setInt(2, accountNo);
		    
		    ps.execute();
		    
		} catch (SQLException e) {
	
			e.printStackTrace();
		}
		//add a withdraw recored into transaction table
		Timestamp transTime = new Timestamp(System.currentTimeMillis());
		double transAmount = -amount;
	//	int acctNo = account_No;
		account = getAccountByAcctNo(accountNo);
		transaction = new Transaction(transTime, transAmount, account);
		numOfTransactionAdded = tdi.addTransaction(transaction);
		System.out.println(Integer.toString(numOfTransactionAdded)+ " number of transaction is added");
		if(numOfTransactionAdded > 0){
			  return true;
		}
		else return false;
	}
	

}
