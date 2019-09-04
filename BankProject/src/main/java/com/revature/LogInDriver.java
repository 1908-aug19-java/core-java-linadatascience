package com.revature;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.dao.impl.BankAccountDaoImpl;
import com.revature.dao.impl.UserAccountDaoImpl;
import com.revature.model.BankAccount;
import com.revature.model.UserAccount;
import com.revature.util.ConnectionUtil;

public class LogInDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserAccount user = null;
		BankAccount acct = null;
		UserAccountDaoImpl uadi = new UserAccountDaoImpl();
		BankAccountDaoImpl badi = new BankAccountDaoImpl();
		
		try{
			Connection c = ConnectionUtil.getHardCodeConnection();
			String driver = c.getMetaData().getDriverName();
			System.out.println(driver);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Log in:");
		System.out.println("Please enter the user name");
		String username = scanner.next();
		
		user = uadi.getUserAccountByUserName(username);
		
		while(true) {
			System.out.println("please enter the password:");
			String password = scanner.next();
			if(!password.equals(user.getPassword())) {
				System.out.println("the password is wrong, please enter password again.");
			}
			else break;
		}
		
		System.out.println("Welcom " + user.getFirst_name() +" " + user.getLast_name() +"!");
		System.out.println(user.toString());
		
		acct = badi.getAccountByUsername(username);
		
		System.out.println(acct.toString());
		
        scanner.close();
	}

}
