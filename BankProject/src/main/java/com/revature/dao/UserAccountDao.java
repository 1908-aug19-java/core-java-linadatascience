package com.revature.dao;

import java.util.List;

import com.revature.model.UserAccount;

public interface UserAccountDao {

	 //user account creation
	 public int registeration(String user_name, String email);
//	 public boolean logIn(String user_name);
//	 public boolean logOut();
//	 public boolean addAccount(String user_name, BankAccount account);
	 
	 public int updateUserAccount(String username, String firstname, String lastname, String password);
//	 public int deleteUserAccount(String user_name);
	 
	 public List<UserAccount> getUserAccounts();
	 public UserAccount getUserAccountByUserName(String username);
	 public UserAccount getUserAccountByUserId(int userId);
}
