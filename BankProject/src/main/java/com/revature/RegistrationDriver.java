package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.impl.UserAccountDaoImpl;
import com.revature.model.UserAccount;
import com.revature.util.ConnectionUtil;

public class RegistrationDriver {

	public static void main(String[] args) {
		try{
			Connection c = ConnectionUtil.getHardCodeConnection();
			String driver = c.getMetaData().getDriverName();
			System.out.println(driver);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		UserAccountDaoImpl uadi = new UserAccountDaoImpl();
		List<UserAccount> userAccounts = new ArrayList<>();
		String username = null;
		String email = null;
		
		
		// registration a user account with user name and user email
		Scanner scanner = new Scanner(System.in);
		userAccounts = uadi.getUserAccounts();
	
		
		System.out.println("Registration:");
		 //check the uniqueness of user name and email
		while(true) {
			System.out.println("Please enter the user name:");
	        username = scanner.next();
	        boolean duplicateUserName = false;
	        for(UserAccount user: userAccounts) {
	        	if(user.getUser_name().equals(username)) {
	        		duplicateUserName = true;
	        		System.out.println("user name has been used, please enter another user name!");
	        		break;
	        	}
	        }
	        if(!duplicateUserName) {
	        	break;
	        }
		}
		
        while(true) {
        	System.out.println("Please enter the user email:");
            email = scanner.next();
            boolean duplicateEmail = false;
            for(UserAccount user: userAccounts) {
            	if(user.getEmail().equals(email)) {
            		duplicateEmail = true;
            		System.out.println("this email has been registered by other user , please enter another email!");
            		break;
            	}
            }
            if(!duplicateEmail) {
            	break;
            }
        }
         
        int UserAccountCreated = uadi.registeration(username, email);
        if(UserAccountCreated==1) {
        	System.out.println("one user account is successfuly registered!");
        }
        
        scanner.close();
	}

}
