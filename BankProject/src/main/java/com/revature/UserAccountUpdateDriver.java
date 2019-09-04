package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.dao.impl.UserAccountDaoImpl;
import com.revature.model.UserAccount;
import com.revature.util.ConnectionUtil;

public class UserAccountUpdateDriver {

	public static void main(String[] args) {
		int numOfUserAccountUpdated = 0;
		List<UserAccount> users = new ArrayList<>();
		UserAccountDaoImpl uadi = new UserAccountDaoImpl();
		try {
      	  Connection c = ConnectionUtil.getHardCodeConnection();
      	  String driver = c.getMetaData().getDriverName();
      	  System.out.println(driver);
        }catch(SQLException e) {
      	  e.printStackTrace();
        }
        
		Scanner scanner = new Scanner(System.in);
        
        
        
        //check the validity of username
        users = uadi.getUserAccounts();
        boolean validUsername = false;
        System.out.println("please enter your username");        
        String username = scanner.next();
        while(true) {
        	for(UserAccount user: users) {
            	if(user.getUser_name().equals(username)){
            		validUsername = true;
            		break;
            	}
            }
        	
        	if(!validUsername) {
            	System.out.println("not valid username! please enter a valid usename.");
            }
            else break;
        	
        	System.out.println("please enter your username");        
            username = scanner.next();
            
            
        }
        
        
        System.out.println("please enter your firstname");
        String firstname = scanner.next();
        
        System.out.println("please enter your lastname");
        String lastname = scanner.next();
        
        System.out.println("please enter your password");
        String password = scanner.next();
        
        numOfUserAccountUpdated = uadi.updateUserAccount(username, firstname, lastname, password);
        
        scanner.close();
        if(numOfUserAccountUpdated > 0) {
        	System.out.println("the user account: '" +username +"' has been successfully updated");
        }
        else {
        	System.out.println("something wrong with the updation on the user account: '" +username +"'");
        }
        
       
	}

}
