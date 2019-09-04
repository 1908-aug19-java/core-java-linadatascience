package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.impl.TransactionDaoImpl;
import com.revature.model.Transaction;
import com.revature.util.ConnectionUtil;

public class ViewTransactionsDriver {

	public static void main(String[] args) {
		List<Transaction> transactions = new ArrayList<>();
		TransactionDaoImpl tdi = new TransactionDaoImpl();
		
		try{
			Connection c = ConnectionUtil.getHardCodeConnection();
			String driver = c.getMetaData().getDriverName();
			System.out.println(driver);
		}catch(SQLException e) {
			e.printStackTrace();
		}
			
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("please enter the account No:");
        int acctNo = scanner.nextInt();
        
        transactions = tdi.getTransactionsByAccountNo(acctNo);
        
        for(Transaction transaction: transactions) {
        	System.out.println(transaction.toString());
        }
        
        scanner.close();
	}

}
