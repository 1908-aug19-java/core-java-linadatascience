package com.revature.model;

import java.io.Serializable;

import java.sql.Timestamp;


public class Transaction implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private int trans_id;
	private Timestamp trans_time;
	private double trans_amount;
	private BankAccount account;
	
	
	public Transaction() {
		super();
	}


	public Transaction(Timestamp trans_time, double trans_amount, BankAccount account) {
		super();
		this.trans_time = trans_time;
		this.trans_amount = trans_amount;
		this.account = account;
	}
    

	public Transaction(int trans_id, Timestamp trans_time, double trans_amount,
			BankAccount account) {
		super();
		this.trans_id = trans_id;
		this.trans_time = trans_time;
		this.trans_amount = trans_amount;
		this.account = account;
	}


	public int getTrans_id() {
		return trans_id;
	}


	public void setTrans_id(int trans_id) {
		this.trans_id = trans_id;
	}


	public Timestamp getTrans_time() {
		return trans_time;
	}


	public void setTrans_time(Timestamp trans_time) {
		this.trans_time = trans_time;
	}


	public double getTrans_amount() {
		return trans_amount;
	}


	public void setTrans_amount(double trans_amount) {
		this.trans_amount = trans_amount;
	}


	public BankAccount getAcct() {
		return this.account;
	}


	public void setAccount(BankAccount account) {
		this.account = account;
	}


	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		long temp;
		temp = Double.doubleToLongBits(trans_amount);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + trans_id;
		result = prime * result + ((trans_time == null) ? 0 : trans_time.hashCode());
//		result = prime * result + ((trans_type == null) ? 0 : trans_type.hashCode());
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
		Transaction other = (Transaction) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (Double.doubleToLongBits(trans_amount) != Double.doubleToLongBits(other.trans_amount))
			return false;
		if (trans_id != other.trans_id)
			return false;
		if (trans_time == null) {
			if (other.trans_time != null)
				return false;
		} else if (!trans_time.equals(other.trans_time))
			return false;
		return true;
	}


	@Override
	public String toString() {
		
		return "Transaction [trans_id=" + Integer.toString(trans_id) + ", trans_time=" + trans_time.toString()
				+ ", trans_amount=" + Double.toString(trans_amount) + ", acct_No=" + account.getAcct_No() +", Balance="+ Double.toString(account.getAcct_balance()) + "]";
	}
	
	

}
