package org.bank.dto;

public class CustomerDetails {

// Customer_Account_Number, Customer_Pin, Customer_Gender, IFSC_Code, Branch, Type_Of_Account, Amount
	private int customerid;
	private String customername;
	private long mobilenumber;
	private long aadharnumber;
	private String pannumber;
	private String emailid;
	private String address;
	private String designation;
	private long accountnumber;
	private int pin;
	private String gender;
	private String ifsccode;
	private String branch;
	private String typeofaccount;
	private double amount;
	private String status;
	public CustomerDetails() {
	}
	public CustomerDetails(int customerid, String customername, long mobilenumber, long aadharnumber, String pannumber,
			String emailid, String address, String designation, long accountnumber, int pin, String gender,
			String ifsccode, String branch, String typeofaccount, double amount,String status) {
		this.customerid = customerid;
		this.customername = customername;
		this.mobilenumber = mobilenumber;
		this.aadharnumber = aadharnumber;
		this.pannumber = pannumber;
		this.emailid = emailid;
		this.address = address;
		this.designation = designation;
		this.accountnumber = accountnumber;
		this.pin = pin;
		this.gender = gender;
		this.ifsccode = ifsccode;
		this.branch = branch;
		this.typeofaccount = typeofaccount;
		this.amount = amount;
		this.status=status;
	}
	public int getCustomerid() {
		return customerid;
	}
	public void setCustomerid(int customerid) {
		this.customerid = customerid;
	}
	public String getCustomername() {
		return customername;
	}
	public void setCustomername(String customername) {
		this.customername = customername;
	}
	public long getMobilenumber() {
		return mobilenumber;
	}
	public void setMobilenumber(long mobilenumber) {
		this.mobilenumber = mobilenumber;
	}
	public long getAadharnumber() {
		return aadharnumber;
	}
	public void setAadharnumber(long aadharnumber) {
		this.aadharnumber = aadharnumber;
	}
	public String getPannumber() {
		return pannumber;
	}
	public void setPannumber(String pannumber) {
		this.pannumber = pannumber;
	}
	public String getEmailid() {
		return emailid;
	}
	public void setEmailid(String emailid) {
		this.emailid = emailid;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public long getAccountnumber() {
		return accountnumber;
	}
	public void setAccountnumber(long accountnumber) {
		this.accountnumber = accountnumber;
	}
	public int getPinnumber() {
		return pin;
	}
	public void setPinnumber(int pin) {
		this.pin = pin;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getIfsccode() {
		return ifsccode;
	}
	public void setIfsccode(String ifsccode) {
		this.ifsccode = ifsccode;
	}
	public String getBranch() {
		return branch;
	}
	public void setBranch(String branch) {
		this.branch = branch;
	}
	public String getTypeofaccount() {
		return typeofaccount;
	}
	public void setTypeofaccount(String typeofaccount) {
		this.typeofaccount = typeofaccount;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CustomerDetails [customerid=" + customerid + ", customername=" + customername + ", mobilenumber="
				+ mobilenumber + ", aadharnumber=" + aadharnumber + ", pannumber=" + pannumber + ", emailid=" + emailid
				+ ", address=" + address + ", designation=" + designation + ", accountnumber=" + accountnumber
				+ ", pin=" + pin + ", gender=" + gender + ", ifsccode=" + ifsccode + ", branch=" + branch
				+ ", typeofaccount=" + typeofaccount + ", amount=" + amount + ", status=" + status + "]";
	}
	
	
}
