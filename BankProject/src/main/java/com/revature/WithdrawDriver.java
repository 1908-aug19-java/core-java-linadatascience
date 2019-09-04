package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.dao.impl.BankAccountDaoImpl;
import com.revature.model.BankAccount;
import com.revature.util.ConnectionUtil;

public class WithdrawDriver {

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
         
         //get the account balance before withdraw
         BankAccount account = badi.getAccountByAcctNo(acctNo);
         double balance = account.getAcct_balance();
         
         //get the amount of money to withdraw
        while(true) {
              System.out.println("please enter withdraw amount:");
              amount = scanner.nextDouble();
          
       //check the deposit amount is valid or not
 		       if((balance - amount) <= 0) {
 			        System.out.println("you can not overdraw!");
 		      }	
 		       else break;
 		}
        
        
 	    boolean withdrawDone = badi.withdraw(acctNo, amount);
 	
 		
 		if(withdrawDone) {
 			System.out.println("the withdraw of " + Double.toString(amount) + "is done, thank you!");
 		}
 		else {
 			System.out.println("Sorry, the withdraw is completed unsuccessfully");
 		}
       scanner.close();
	}

}

	


