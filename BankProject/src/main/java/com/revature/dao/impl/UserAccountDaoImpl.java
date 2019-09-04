package com.revature.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.UserAccountDao;
import com.revature.model.BankAccount;
import com.revature.model.Transaction;
import com.revature.model.UserAccount;
import com.revature.util.ConnectionUtil;

public class UserAccountDaoImpl implements UserAccountDao{
	
	public List<UserAccount> getUserAccounts(){
		List<UserAccount> useraccounts = new ArrayList<UserAccount>();
		String sql = "select * from useraccount";
		
		try (Connection c = ConnectionUtil.getHardCodeConnection();
				Statement s = c.createStatement(); 
				ResultSet rs = s.executeQuery(sql);){
			
			while (rs.next()) {
				String username = rs.getString("user_name");
				String password = rs.getString("user_password");
				String firstname = rs.getString("first_name");
				String lastname = rs.getString("last_name");
				String email = rs.getString("user_email");
				UserAccount uc = new UserAccount(username, password, firstname, lastname, email);
				useraccounts.add(uc);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return useraccounts;
	}


	@Override
	public int registeration(String user_name, String email) {
		
		int userAccountCreated = 0;		
		String sql = "insert into useraccount(user_name, user_email) values (?,?)";
				
		try (Connection c = ConnectionUtil.getHardCodeConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			
			ps.setString(1, user_name);
			ps.setString(2, email);
			userAccountCreated = ps.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return userAccountCreated;
	}



	@Override
	public UserAccount getUserAccountByUserName(String username) {
		
		UserAccount user = null;
		String sql = "select * from useraccount where user_name = ?";
		
		try(Connection c = ConnectionUtil.getHardCodeConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
			
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				
				String userName = rs.getString("user_name");
				String userPassword = rs.getString("user_password");
				String firstName = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String email = rs.getString("user_email");
				user = new UserAccount(userName, userPassword, firstName, lastName, email);
				
			}
//			System.out.println("this is getUserAccountByName: "+user.toString());
			rs.close();
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		
		return user;
	}


	@Override
	public UserAccount getUserAccountByUserId(int userId) {
		UserAccount user = null;
		String sql = "select * from useraccount where user_id = ?";
		
		try(Connection c =  ConnectionUtil.getHardCodeConnection();
			PreparedStatement ps = c.prepareStatement(sql);){
			
			     ps.setInt(1, userId);
			     ResultSet rs = ps.executeQuery();
			
			     while(rs.next()) {
				
				        String userName = rs.getString("user_name");
				        String userPassword = rs.getString("user_password");
				        String firstName = rs.getString("first_name");
				        String lastName = rs.getString("last_name");
				        String email = rs.getString("user_email");
				        user = new UserAccount(userName, userPassword, firstName, lastName, email);
			     }
			     
			     rs.close();
			}catch (SQLException e) {
				e.printStackTrace();
			}
			     
		return user;
	}


	@Override
	public int updateUserAccount(String username, String firstname, String lastname, String password) {

		int numOfUserAccountUpdated = 0;
	    String sql = "update useraccount "
	    		+ "set first_name = ?, last_name = ?, user_password =? "
	    		+ "where user_name = ?";
	    
	    try(Connection c = ConnectionUtil.getHardCodeConnection();
				PreparedStatement ps = c.prepareStatement(sql);){
				
				   ps.setString(1, firstname);
				   ps.setString(2,  lastname);
				   ps.setString(3,  password);
				   ps.setString(4,  username);
				   
				   numOfUserAccountUpdated = ps.executeUpdate();
				   
			} catch (SQLException e) {
				   e.printStackTrace();
			}
		
		return numOfUserAccountUpdated;
	}

/*	public boolean logIn(String user_name) {
		// TODO Auto-generated method stub
		
		return false;
	} */

/*	public boolean logOut() {
		// TODO Auto-generated method stub
		return false;
	}*/

/*	public boolean addAccount(String user_name, BankAccount account) {
		// TODO Auto-generated method stub
		return false;
	}*/

	/*public int updateUserAccount(UserAccount user_account) {
		// TODO Auto-generated method stub
		return 0;
	}*/

	/*public int deleteUserAccount(String user_name) {
		// TODO Auto-generated method stub
		return 0;
	}*/	

}