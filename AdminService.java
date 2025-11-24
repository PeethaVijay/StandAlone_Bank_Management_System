package org.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import org.bank.dao.AdminDAO;
import org.bank.dao.CustomerDAO;
import org.bank.dto.AdminDetails;
import org.bank.dto.CustomerDetails;
import org.bank.exception.CustomerInvalidDataException;

public class AdminService {

	Scanner sc=new Scanner(System.in);
	
	AdminDAO adminDAO=new AdminDAO();
	AdminDetails adminDetails=new AdminDetails();
	CustomerService customerService=new CustomerService();
	public void admin() {
		System.out.println("Enter \n 1.Admin Registration \n 2.Admin Login");
		switch (sc.nextInt()) {
		case 1:
			adminRegistration();
			break;
		case 2:
			adminLogin();
			break;	
		default:System.out.println("Inavild Request");
			break;
		}
	}
	public void adminRegistration() {
		List<AdminDetails> listodadmin=adminDAO.selectAllAdminDetails();
		System.out.println("Enter the Email Id");
		while(true) {
			String adminemail=sc.next();
			long emailcount=listodadmin.stream().filter((admin)->admin.getAdminemailid().equals(adminemail)).count();
			try {
				if(adminemail.contains("@gmail.com")) {
					if(emailcount==0) {
						adminDetails.setAdminemailid(adminemail);
						break;
					}
					else {
						throw new CustomerInvalidDataException("EmailId Already Existed");
					}
				}else {
					throw new CustomerInvalidDataException("Invalid Email Id...");
				}
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionmsg());
				System.out.println("Please RE_Enter the Email Id");
			}
		}
		System.out.println("Enter the Password you want");
		while(true) {
			int adminpassword=sc.nextInt();
			long passwordcount=listodadmin.stream().filter((admin)->admin.getAdminpassword()==adminpassword).count();
			try {
				if(adminpassword>=1000 && adminpassword<=9999) {
					if(passwordcount==0) {
						adminDetails.setAdminpassword(adminpassword);
						break;
					}
					else {
						throw new CustomerInvalidDataException("Password Already Existed");
					}
				}else {
					throw new CustomerInvalidDataException("Invalid Password...");
				}
			}catch(CustomerInvalidDataException e) {
				System.out.println(e.getExceptionmsg());
				System.out.println("Please RE_Enter the Password");
			}
		}
		if(adminDAO.insertAdminDetails(adminDetails)) {
			System.out.println("Admin Registration is Successfull..");
		}else
			System.out.println("Server 500 Error");
		
	}
	public void adminLogin() {
		System.out.println("Enter the Customer EmailId");
		String email=sc.next();
		System.out.println("Enter the customer Password");
		int password=sc.nextInt();
		if(adminDAO.selectAdminDetailsByUsingEmailIDAndPassword(email, password)) {
			getOperation();
		}
		else
			System.out.println("Sorry Login Address Not Found");
		  
	}
	public void getOperation() {
		System.out.println("Enter \n 1.For Accept Or Reject Account Request \n 2.For Accept Or Reject Account Closing Request \n 3.Exit");
		
		switch(sc.nextInt()) {
		case 1:
			List<CustomerDetails> list=customerService.getAllCustomerDetailsByAccountISNUll();
				int count=1;
				for(CustomerDetails customerDetails:list) {
					System.out.println("S.No :-"+count++);
					System.out.println("Customer Id :-"+customerDetails.getCustomerid());
					System.out.println("Customer Name :-"+customerDetails.getCustomername());
					System.out.println("Customer Mobile Number :-"+customerDetails.getMobilenumber());
					System.out.println("Customer Aadhar Number :-"+customerDetails.getAadharnumber());
					System.out.println("Customer Gender :-"+customerDetails.getGender());
					System.out.println("*************************************************************");
				}
				System.out.println("Enter the S.No For the Customer Details");
				CustomerDetails details=list.get(sc.nextInt()-1);
				System.out.println("Customer Id :-"+details.getCustomerid());
				System.out.println("Customer Name :-"+details.getCustomername());
				System.out.println("Customer Mobile Number :-"+details.getMobilenumber());
				System.out.println("Customer Aadhar Number :-"+details.getAadharnumber());
				System.out.println("Customer Gender :-"+details.getGender());
				System.out.println("Enter \n 1.For Accept \n 2.For Reject \n 3.Exit");
				switch (sc.nextInt()) {
				case 1:
					acceptAccount(details);
					break;
				case 2:
					customerService.deleteAccountDetails(details.getAadharnumber());
					break;
				case 3:
					System.out.println("Ok Thank You");
					break;

				default:
					System.out.println("Invalid Request...");
					break;
				}
			
			break;
		case 2:
			System.out.println("For Accept Or Reject Account Closing Request");
			List<CustomerDetails> list1=customerService.getAllCustomerByUsingStatus();
			int count1=1;
			for(CustomerDetails customerDetails:list1) {
				System.out.println("S.No :-"+count1++);
				System.out.println("Customer Id :-"+customerDetails.getCustomerid());
				System.out.println("Customer Name :-"+customerDetails.getCustomername());
				System.out.println("Customer Account Number:-"+customerDetails.getAccountnumber());
				System.out.println("Customer Mobile Number :-"+customerDetails.getMobilenumber());
				System.out.println("Customer Aadhar Number :-"+customerDetails.getAadharnumber());
				System.out.println("Customer Gender :-"+customerDetails.getGender());
				System.out.println("*************************************************************");
			}
			System.out.println("Enter the S.No For the Customer Details");
			CustomerDetails customerDetails=list1.get(sc.nextInt()-1);
			System.out.println("Customer Id :-"+customerDetails.getCustomerid());
			System.out.println("Customer Name :-"+customerDetails.getCustomername());
			System.out.println("Customer Account Number:-"+customerDetails.getAccountnumber());
			System.out.println("Customer Mobile Number :-"+customerDetails.getMobilenumber());
			System.out.println("Customer Aadhar Number :-"+customerDetails.getAadharnumber());
			System.out.println("Customer Gender :-"+customerDetails.getGender());
			System.out.println("Enter \n 1.For Accept \n 3.Exit");
			switch (sc.nextInt()) {
			case 1:
				customerService.deleteAccount(customerDetails.getAccountnumber());
				break;
			case 2:
				System.out.println("Ok Thank You");
				break;

			default:
				System.out.println("Invalid Request...");
				break;
			}
			
			break;
		case 3:
			System.out.println("Ok Thank You....");
			break;
		default :System.out.println("Invalid Request");
		         break;
		}
	}
//	public void acceptAccountNumber() {
//		Random r=new Random();
//		long accountNumber=r.nextInt(10000000);
//		if(accountNumber<10000000)
//			accountNumber+=10000000;
//		
//		int pin=r.nextInt(10000);
//		if(pin<1000)
//			pin+=1000;
//	}
	public void acceptAccount(CustomerDetails details) {
		List<CustomerDetails> listofCustomers=customerService.getAllCustomerDetails();
		while(true) {
			long number = (long)(Math.random() * 9000000000L) + 1000000000L;
			int pinnumber=(int)(Math.random() * 9000) + 1000;
			long accountnumberCount=listofCustomers.stream().filter((customer-> customer.getAccountnumber()==number)).count();
			long pincount=listofCustomers.stream().filter((pin-> pin.getPinnumber()==pinnumber)).count();
				if(accountnumberCount==0 && pincount==0) {
					if(customerService.getAccountNumber(number, pinnumber, details.getAadharnumber())) {
						String status="Active";
						customerService.updateStatus(status, number);
						System.out.println(" Customer Name:-"+details.getCustomername()+" \n Account No:-"+number+"\n Pin Number :-"+pinnumber+" now your Account Is Conformation  Successfully");
						break;
					}else
						System.out.println("Server 500 Error");
				}
		}
	}
	
}
