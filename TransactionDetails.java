package org.bank.dto;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionDetails {

//Transaction_Id, Transaction_Type, Transaction_Date, Transaction_Time, Transaction_Amount, 
	//Balance, Customer_Account_Number
	private int transactionid;
	private String transactiontype;
	private LocalDate transactiondate;
	private LocalTime transactiontime;
	private double transactionamount;
	private double balance;
	private long customeraccountnumber;
	public TransactionDetails() {
	}
	public TransactionDetails(int transactionid, String transactiontype, LocalDate transactiondate, LocalTime transactiontime,
			double transactionamount, double balance, long customeraccountnumber) {
		this.transactionid = transactionid;
		this.transactiontype = transactiontype;
		this.transactiondate = transactiondate;
		this.transactiontime = transactiontime;
		this.transactionamount = transactionamount;
		this.balance = balance;
		this.customeraccountnumber = customeraccountnumber;
	}
	public int getTransactionid() {
		return transactionid;
	}
	public void setTransactionid(int transactionid) {
		this.transactionid = transactionid;
	}
	public String getTransactiontype() {
		return transactiontype;
	}
	public void setTransactiontype(String transactiontype) {
		this.transactiontype = transactiontype;
	}
	public LocalDate getTransactiondate() {
		return transactiondate;
	}
	public void setTransactiondate(LocalDate transactiondate) {
		this.transactiondate = transactiondate;
	}
	public LocalTime getTransactiontime() {
		return transactiontime;
	}
	public void setTransactiontime(LocalTime transactiontime) {
		this.transactiontime = transactiontime;
	}
	public double getTransactionamount() {
		return transactionamount;
	}
	public void setTransactionamount(double transactionamount) {
		this.transactionamount = transactionamount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public long getCustomeraccountnumber() {
		return customeraccountnumber;
	}
	public void setCustomeraccountnumber(long customeraccountnumber) {
		this.customeraccountnumber = customeraccountnumber;
	}
	@Override
	public String toString() {
		return "TransactionDetails [transactionid=" + transactionid + ", transactiontype=" + transactiontype
				+ ", transactiondate=" + transactiondate + ", transactiontime=" + transactiontime
				+ ", transactioamount=" + transactionamount + ", balance=" + balance + ", customeraccountnumber="
				+ customeraccountnumber + "]";
	}
	
	
	
}
