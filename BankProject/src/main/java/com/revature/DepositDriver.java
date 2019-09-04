package com.revature;

import java.sql.Connection;

import java.sql.SQLException;
import java.util.Scanner;

import com.revature.dao.impl.BankAccountDaoImpl;
import com.revature.util.ConnectionUtil;

public class DepositDriver {

	public static void main(String[] args) {
		  
		  BankAccountDaoImpl badi = new BankAccountDaoImpl();
		
          try {
        	  Connection c = ConnectionUtil.getHardCodeConnection();
        	  String driver = c.getMetaData().getDriverName();
        	  System.out.println(driver);
          }catch(SQLException e) {
        	  e.printStackTrace();
          }
          
          int acctNo = 0;
          double amount = 0;
          Scanner scanner = new Scanner(System.in);
          
          System.out.println("please enter your account number");
          
          acctNo = scanner.nextInt();
          
          //check the acctNo is valid or not
          
          
          //get the amount of money to deposit
         while(true) {
               System.out.println("please enter deposit amount:");
                amount = scanner.nextDouble();
          
        //check the deposit amount is valid or not
  		       if(amount <= 0) {
  			        System.out.println("the deposit amount is invalid!");
  		      }	
  		       else break;
  		}
         
         
  	    boolean depositDone = badi.deposit(acctNo, amount);
  	
  		
  		if(depositDone) {
  			System.out.println("the deposit of " + Double.toString(amount) + "is done, thank you!");
  		}
  		else {
  			System.out.println("Sorry, the deposit is completed unsuccessfully");
  		}
        scanner.close();
	}

}
