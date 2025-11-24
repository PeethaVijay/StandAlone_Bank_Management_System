package org.bank.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;

import org.bank.dao.CustomerDAO;
import org.bank.dao.TransactionDAO;
import org.bank.dto.CustomerDetails;
import org.bank.dto.TransactionDetails;
import org.bank.exception.CustomerInvalidDataException;

public class CustomerService {

	CustomerDAO customerDAO=new CustomerDAO();
	CustomerDetails customerDetails=new CustomerDetails();
	TransactionService transactionService=new TransactionService();
	
	TransactionDAO transactionDAO=new TransactionDAO();
	Scanner sc=new Scanner(System.in);
	public void customerRegistration() {
		List<CustomerDetails> allCustomerDetails=customerDAO.getAllCustomerDetails();
		System.out.println("Enter Customer Name");
		String name=sc.next();
		for(int i=0;i<=name.length()-1;i++) {
			if((name.charAt(i)>='A' &&name.charAt(i)<='Z') || (name.charAt(i)>='a' && name.charAt(i)<='z')) {
				customerDetails.setCustomername(name);
			}
			else {
				throw new CustomerInvalidDataException("Invalid name");
			}
		}
		System.out.println("Enter Customer Mobile Number");
		while(true) {
			long mobileNumber=sc.nextLong();
			long mobileNumberCount=allCustomerDetails.stream().filter((customer->customer.getMobilenumber()==mobileNumber)).count();
			try {
				if(mobileNumber>=6000000000l && mobileNumber<=9999999999l) {
					if(mobileNumberCount==0) {
					    customerDetails.setMobilenumber(mobileNumber);
					    break;
					}
					else
						throw new CustomerInvalidDataException("Mobile Number Already Existed......");
				}
				else {
					throw new CustomerInvalidDataException("Inavlid Mobile Number");
				}
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionmsg());
     			System.out.println("Please Re_enter the EmailId");

			}
		}
		System.out.println("Enter Customer Email Id");
		while(true) {
			String emailId=sc.next();	
			try {
				if(emailId.contains("@gmail.com")) {
					long emailIdCount=allCustomerDetails.stream().filter((customer-> customer.getEmailid().equals(emailId))).count();
					if(emailIdCount==0) {
					   customerDetails.setEmailid(emailId);
					   break;
					}
					else {
						throw new CustomerInvalidDataException("❌ EmailId Already Existed....");
					}
				}
				else {
					throw new CustomerInvalidDataException("Invalid email id");

					
				}
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionmsg());
     			System.out.println("Please Re_enter the EmailId");
			}
		}
		System.out.println("Enter Customer Aadhar Number");
		while(true) {
			long aadhar=sc.nextLong();
			long aadharCount=allCustomerDetails.stream().filter((customer->customer.getAadharnumber()==aadhar)).count();
			try {
				if(aadhar>=100000000000l && aadhar<=999999999999l) {
					if(aadharCount==0) {
					    customerDetails.setAadharnumber(aadhar);
					    break;
					}
					else
						throw new CustomerInvalidDataException("❌ Aadhar Number is Already Existed");
				}
				else {
					throw new CustomerInvalidDataException("Invalid Aadhar Number");
				}
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionmsg());
     			System.out.println("Please Re_enter the EmailId");
			}
		}
		System.out.println("Enter Customer Pan Number");
		while(true) {
			String panNumber=sc.next();
			long panCount=allCustomerDetails.stream().filter((customer->customer.getPannumber().equals(panNumber))).count();
			try {
				if(panNumber.matches("[A-Z]{5}\\d{4}[A-Z]")) {
					if(panCount==0) {
					    customerDetails.setPannumber(panNumber);
					    break;
					}
					else
						throw new CustomerInvalidDataException("Pan Number is Already Existed");
				}
				else
					throw new CustomerInvalidDataException("Invalid Pan Number");
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionmsg());
     			System.out.println("Please Re_enter the EmailId");
			}
			
		}
		System.out.println("Enter Customer Address");
		String address=sc.next();
		customerDetails.setAddress(address);
		System.out.println("Enter Customer Designation");
		String designation=sc.next();
		customerDetails.setDesignation(designation);
		System.out.println("Enter Customer Gender");
		String gender=sc.next();
		if(gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female")|| gender.equalsIgnoreCase("others")) {
			customerDetails.setGender(gender);
		}
		else {
			throw new CustomerInvalidDataException("Inavlid Gender Details");
		}
		System.out.println("Enter Type Of Account \n 1.Saving Account \n 2.Current Account");
		String typeOFAccount="";
		switch (sc.nextInt()) {
		case 1:
			typeOFAccount="Saving Account";
			customerDetails.setTypeofaccount(typeOFAccount);
			break;
		case 2:
			typeOFAccount="Current Account";
			customerDetails.setTypeofaccount(typeOFAccount);
			break;	
		default:
			System.out.println("Invalid Selection");
			break;
		}
		System.out.println("Enter the  amount");
		double amount=sc.nextDouble();
		if(amount>=500) {
			customerDetails.setAmount(amount);
		}
		else {
			throw new CustomerInvalidDataException("Please enter the amount minimum 500/-");
		}
		customerDetails.setBranch("JNTU");
		customerDetails.setIfsccode("SBIN0009876");
		customerDetails.setStatus("Approval Pending");
		if(customerDAO.insertCustomerDetails(customerDetails)) {
		     System.out.println(customerDetails.getCustomername()+"  Your Registration Successfully......");
		}else
			System.out.println("Server 500 error");
	}
	
	public void customerLogin() {
		
		System.out.println("Enter the Email Id Or Mobile Number");
		String emailidOrMobileNuber=sc.next();
		System.out.println("Enter the Pin Number");
		int pin=sc.nextInt();
		customerDetails=customerDAO.getCustomerDetailosByUsingEmailIdOrMobileNumberAndPinNumber(emailidOrMobileNuber, pin);
		if(customerDetails!=null) {
			if(customerDetails.getGender().equalsIgnoreCase("male")) {
				System.out.println("Hello Mr: "+customerDetails.getCustomername()+" Your Login Successfull");
				customerOperation(customerDetails.getAccountnumber());
			}else if(customerDetails.getGender().equalsIgnoreCase("female")) {
				System.out.println("Hello Miss: "+customerDetails.getCustomername()+" Your Login Successfull");
				customerOperation(customerDetails.getAccountnumber());
			}else {
				System.out.println("Hello "+customerDetails.getCustomername()+" your login is Successfull");
				customerOperation(customerDetails.getAccountnumber());
			}
		}
		else
			System.out.println("Invalid Details.....");

	}
	public void customerOperation(long accountNumber ) {
		
		System.out.println("Enter \n 1.For Credit \n 2.For Debit \n 3.For Check Balance \n 4.For Statement \n 5.For Close Account \n 6.For Change PIN \n 7.Exit");
		
		switch (sc.nextInt()) {
		case 1:
			System.out.println("Credit");
			credit(accountNumber);
			break;
		case 2:
			System.out.println("Debit");
			debit(accountNumber);
			break;
		case 3:
			System.out.println("Check Balance");
			checkBalance(accountNumber);
			break;
		case 4:
			System.out.println("Statement");
			getStatement(accountNumber);
			break;
		case 5:
			System.out.println("Close Account");
			closeAccount(accountNumber);
			break;
		case 6:
			System.out.println("Change PIN");
			changePinNumber(accountNumber);
			break;
		case 7:
			System.out.println("Ok Thank You...");
			break;
		default:
			System.out.println("Invalid REQUEST...");
			break;
		}

	}
	public void credit(long dataBaseAccountNumber) {
		System.out.println("Enter the Account Number");
		long userAccountNumber=sc.nextLong();
		CustomerDetails customerDetails=customerDAO.getCustomerDetailsByUsingAccountNumber(userAccountNumber);
		try {
			if(customerDetails!=null && dataBaseAccountNumber==userAccountNumber ) {
				System.out.println("Enter the Amount to be CREDITED..");
				double amount=sc.nextDouble();
				if(amount>0) {
					double money=customerDetails.getAmount()+amount;
					if(customerDAO.updateCustomerMoney(money,dataBaseAccountNumber)) {
						TransactionDetails transactionDetails=new TransactionDetails();
						transactionDetails.setTransactiontype("CREDIT");
						transactionDetails.setTransactiondate(LocalDate.now());
						transactionDetails.setTransactiontime(LocalTime.now());
						transactionDetails.setTransactionamount(amount);
						transactionDetails.setBalance(money);
						transactionDetails.setCustomeraccountnumber(userAccountNumber);
						transactionService.insertTransationDetails(transactionDetails);
						System.out.println(customerDetails.getCustomername()+" Your "+amount +" Rupees is Deposited Successfully..");
					}
					else
						throw new CustomerInvalidDataException("Server 500 error");
				}else
					throw new CustomerInvalidDataException("Insuccient Fund....");
			
	        }
	        else {
		        throw new CustomerInvalidDataException("Your Account Number Is MisMatched....");
	        }
		}catch (CustomerInvalidDataException e) {
			System.out.println(e.getExceptionmsg());
		}

	}
	public void debit(long dataBaseAcccountNumber) {
		
		System.out.println("Enter the Account Number");
		long useraccountNumber=sc.nextLong();
		CustomerDetails customer=customerDAO.getCustomerDetailsByUsingAccountNumber(useraccountNumber);
		try {
			if(customer!=null && dataBaseAcccountNumber==useraccountNumber ) {
				System.out.println("Enter the Amount to be DEBITED..");
				double amount=sc.nextDouble();
				if(customer.getAmount()>=amount) {
					double money=customer.getAmount()-amount;
					if(customerDAO.updateCustomerMoney(money,dataBaseAcccountNumber)) {
						TransactionDetails transactionDetails=new TransactionDetails();
						transactionDetails.setTransactiontype("DEBITED");
						transactionDetails.setTransactiondate(LocalDate.now());
						transactionDetails.setTransactiontime(LocalTime.now());
						transactionDetails.setTransactionamount(amount);
						transactionDetails.setBalance(money);
						transactionDetails.setCustomeraccountnumber(useraccountNumber);
						transactionService.insertTransationDetails(transactionDetails);
						System.out.println(customer.getCustomername()+" Your "+ amount  +" Rupees is Debited Successfully..");
					}
					else
						throw new CustomerInvalidDataException("Server 500 error");
				}else
					throw new CustomerInvalidDataException("Insuccient Fund....");
			
	        }
	        else {
		        throw new CustomerInvalidDataException("Your Account Number Is MisMatched....");
	        }
		}catch (CustomerInvalidDataException e) {
			System.out.println(e.getExceptionmsg());
		}
	}
	public void checkBalance(long dataBaseAccountNumber) {
		System.out.println("Enter the Account Number");
		long userAccountNumber=sc.nextLong();
		CustomerDetails customerDetails=customerDAO.getCustomerDetailsByUsingAccountNumber(userAccountNumber);
		try {
			if(customerDetails!=null && dataBaseAccountNumber==userAccountNumber ) {
				System.out.println(customerDetails.getCustomername()+" Your Account Balance :-"+customerDetails.getAmount());
	        }
	        else {
		        throw new CustomerInvalidDataException("Your Account Number Is MisMatched....");
	        }
		}catch (CustomerInvalidDataException e) {
			System.out.println(e.getExceptionmsg());
		}

	}
	public void getStatement(long dataBaseAccountNumber) {
		System.out.println("Enter the Account Number");
		long userAccountNumber=sc.nextLong();
		CustomerDetails customerDetails=customerDAO.getCustomerDetailsByUsingAccountNumber(userAccountNumber);
		List<TransactionDetails> allTransaction=transactionService.getTransactionDetails(userAccountNumber);
		int count=1;
		try {
			if(customerDetails!=null && dataBaseAccountNumber==userAccountNumber ) {
				for(TransactionDetails transactionDetails:allTransaction) {
					System.out.println("S.No :-"+count++);
					System.out.println("Transaction Id :-"+transactionDetails.getTransactionid());
					System.out.println("Customer Account Number :-"+transactionDetails.getCustomeraccountnumber());
					System.out.println("Transaction Type :-"+transactionDetails.getTransactiontype());
					System.out.println("Transaction Date :-"+transactionDetails.getTransactiondate());
					System.out.println("Transaction Time :-"+transactionDetails.getTransactiontime());
					System.out.println("Transaction Amount :-"+transactionDetails.getTransactionamount());
					System.out.println("Available Amount :-"+transactionDetails.getBalance());
				}
	        }
	        else {
		        throw new CustomerInvalidDataException("Your Account Number Is MisMatched....");
	        }
		}catch (CustomerInvalidDataException e) {
			System.out.println(e.getExceptionmsg());
		}

	}
	public void closeAccount(long dataBaseAccountNumber) {
		System.out.println("Enter the Account Number");
		long userAccountNumber=sc.nextLong();
		CustomerDetails customerDetails=customerDAO.getCustomerDetailsByUsingAccountNumber(userAccountNumber);
		try {
			if(customerDetails!=null && dataBaseAccountNumber==userAccountNumber ) {
				String statusUpdate="Closing Approval";
				if(customerDAO.updateStatus(statusUpdate, userAccountNumber)) {
					System.out.println("Customer Name:- "+customerDetails.getCustomername()+" \nYour Account Number :-"+customerDetails.getAccountnumber()+"  \n Your account closure request is awaiting approval.");
				}
				else {
					throw new CustomerInvalidDataException("Server 500 error");
				}
	        }
	        else {
		        throw new CustomerInvalidDataException("Your Account Number Is MisMatched....");
	        }
		}catch (CustomerInvalidDataException e) {
			System.out.println(e.getExceptionmsg());
		}

	}
	public boolean getAccountNumber(long accountNumber,int pinNumber,long aadharAccount) {
		List<CustomerDetails> allCustomerDetails=customerDAO.getAllCustomerDetails();
		long accountCount=allCustomerDetails.stream().filter((account)->account.getAccountnumber()==accountNumber).count();
		long pinCount=allCustomerDetails.stream().filter((pin)->pin.getPinnumber()==pinNumber).count();
		if(accountCount==0 && pinCount==0) {
			if(customerDAO.setAccountNumberAndPinNumber(accountNumber, pinNumber, aadharAccount)) {
				return true;
			}
			else
				return false;
		}
		else
			return false;
		
	}
	public void deleteAccountDetails(long aadharNumber) {
		if(customerDAO.deleteCustomerDetails(aadharNumber)) {
			System.out.println("Your Details Details Deleted Successfully..");
		}else
			System.out.println("Sever 500 Error");
	}

	public void changePinNumber(long dataBaseAccountNumber) {
		System.out.println("Enter Your Account Number");
		long accountNumber=sc.nextLong();
		CustomerDetails customerDetails=customerDAO.getCustomerDetailsByUsingAccountNumber(accountNumber);
		try {
			if(customerDetails!=null && dataBaseAccountNumber==accountNumber ) {
				System.out.println("Set Your New Pin Number");
				int pin=sc.nextInt();
				if(customerDAO.updatePinNumber(pin, accountNumber)) {
					System.out.println(customerDetails.getCustomername()+" Your Pin Number Change Successfull...");
				}
				else
					throw new CustomerInvalidDataException("Server 500 Error");
			}
			else
				throw new CustomerInvalidDataException("Account Number Not Found");
			
		}catch(CustomerInvalidDataException e) {
			System.out.println(e.getExceptionmsg());
		}
	}
	public List<CustomerDetails> getAllCustomerDetailsByAccountISNUll(){
		List<CustomerDetails> list=customerDAO.getCustomerDetailsAccountNull();
		try {
			if(!list.isEmpty())
				return list;
			else
				throw new CustomerInvalidDataException("There No pending Customer Registration ");
		}catch(CustomerInvalidDataException e) {
			System.out.println(e.getExceptionmsg());
			return null;
		}
	}
	public List<CustomerDetails> getAllCustomerDetails(){
		return customerDAO.getAllCustomerDetails();
	}
	public void updateStatus(String status,long accountNumber) {
		if(customerDAO.updateStatus(status, accountNumber)) {
			
		}else
			System.out.println("Sever 500 Error");
			
	}
	public List<CustomerDetails> getAllCustomerByUsingStatus(){
		List<CustomerDetails> list=customerDAO.getCustomerDEtailsByUsingStatus();
		try {
			if(!list.isEmpty())
				return list;
			else
				throw new CustomerInvalidDataException("There No Closing Approvals ");
		}catch(CustomerInvalidDataException e) {
			System.out.println(e.getExceptionmsg());
			return null;
		}
	}
	public void deleteAccount(long accountNumber) {
		CustomerDetails customerDetails=customerDAO.getCustomerDetailsByUsingAccountNumber(accountNumber);
		if(customerDAO.closeAccount(accountNumber)) {
			System.out.println("Customer Name :-"+customerDetails.getCustomername()+"\n Customer Account Number :-"+customerDetails.getAccountnumber()+" Account Deleted Successfully");
		}else
			System.out.println("Server 500 Error");
	}
	
}
