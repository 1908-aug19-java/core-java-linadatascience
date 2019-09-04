package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.dao.impl.BankAccountDaoImpl;
import com.revature.util.ConnectionUtil;

public class ViewBalanceDriver {

	public static void main(String[] args) {
		
	   int acctNo = 0;
//	   double balance = 0;
	   BankAccountDaoImpl badi = new BankAccountDaoImpl();
		
       try {
    	   Connection c = ConnectionUtil.getHardCodeConnection();
    	   String driver = c.getMetaData().getDriverName();
    	   System.out.println(driver);
       }catch(SQLException e) {
    	   e.printStackTrace();
       }
       
       Scanner scanner = new Scanner(System.in);
       
       System.out.println("Please enter the account No:");
       acctNo = scanner.nextInt();
       
       badi.viewBalance(acctNo);
       scanner.close();
	}

}
