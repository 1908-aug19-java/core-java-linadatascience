package com.revature.model;

import java.io.Serializable;

public class BankAccount implements Serializable{
       private static final long serialVersionUID = 1L;
       
       private int acct_No;
       private String acct_type;
       private double acct_balance;
       private UserAccount user;
       
       public BankAccount() {
    	   super();
       }

	public BankAccount(int acct_No, String acct_type, double acct_balance, UserAccount user) {
		super();
		this.acct_No = acct_No;
		this.acct_type = acct_type;
		this.acct_balance = acct_balance;
		this.user = user;
	}

	public int getAcct_No() {
		return acct_No;
	}

	public void setAcct_No(int acct_No) {
		this.acct_No = acct_No;
	}

	public String getAcct_type() {
		return acct_type;
	}

	public void setAcct_type(String acct_type) {
		this.acct_type = acct_type;
	}

	public double getAcct_balance() {
		return acct_balance;
	}

	public void setAcct_balance(double acct_balance) {
		this.acct_balance = acct_balance;
	}

	public UserAccount getUser() {
		return user;
	}

	public void setUser(UserAccount user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + acct_No;
		long temp;
		temp = Double.doubleToLongBits(acct_balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((acct_type == null) ? 0 : acct_type.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BankAccount other = (BankAccount) obj;
		if (acct_No != other.acct_No)
			return false;
		if (Double.doubleToLongBits(acct_balance) != Double.doubleToLongBits(other.acct_balance))
			return false;
		if (acct_type == null) {
			if (other.acct_type != null)
				return false;
		} else if (!acct_type.equals(other.acct_type))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "BankAccount [acct_No=" + acct_No + ", acct_type=" + acct_type + ", acct_balance=" + acct_balance
				+ ", user=" + user.getUser_name() + "]";
	}
       
       
       
       
}
