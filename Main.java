package org.bank.main;

import java.util.Scanner;

import org.bank.service.AdminService;
import org.bank.service.CustomerService;

public class Main {
	
	public static void getTime(String st) {
		for(int i=0;i<=st.length()-1;i++) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.print(st.charAt(i));
		}
		System.out.println();
	}
	  public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		CustomerService customerService=new CustomerService();
		AdminService adminService=new AdminService();
		String st="***********🙏🙏🙏🙏🙏🙏🙏Welcome_To_A15_Bank_Management🙏🙏🙏🙏🙏🙏🙏***********";
		getTime(st);
		boolean start=true;
		do {
			System.out.println("Enter \n 1. Customer Registration \n 2. Customer Login \n 3. Admin Service");
			switch(sc.nextInt()) {
			case 1:  System.out.println("Customer Registration");
			          customerService.customerRegistration();
			           break;
			case 2: System.out.println("Customer Login");
			         customerService.customerLogin();
			          break;
			case 3: System.out.println("Admin Login");
			adminService.admin();
				       break;
		    default: System.out.println("Invaild Request");
		               break;    
			}
			String st2="Do you want to continue @ @ @ \n Enter \n 1.Yes \n 2.No \n";
			getTime(st2);
			int st1=sc.nextInt();
			if(st1==1)
				start=true;
			else
				start=false;
		}while(start);
		String st3="Thank You Visit Again...🙏🙏🙏🙏";
		getTime(st3);
		sc.close();
	}
}
