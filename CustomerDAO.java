package org.bank.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.bank.connection.JDBCConnection;
import org.bank.dto.CustomerDetails;


public class CustomerDAO {

	private  static final String insert_customer_details="insert into bank_customer_details(Customer_Name, Customer_Mobile_number, Customer_Aadhar_Number, Customer_Pan_Number, Customer_Email_Id, Customer_Address, Customer_Designation, Customer_Gender, IFSC_Code, Branch, Type_Of_Account, Amount,Status) values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String select_all="select * from bank_customer_details";
	private static final String customer_Login="select * from bank_customer_details where (Customer_Email_Id=? or Customer_Mobile_number=?) and Customer_Pin=?";
	private static final String get_Details_By_Using_Account_Number="select * from bank_customer_details where Customer_Account_Number=?";
	private static final String update_Amount="update bank_customer_details set Amount=? where Customer_Account_Number=?";
	private static final String delete_Account="delete from bank_customer_details where Customer_Account_Number=?";
	private static final String get_customer="SELECT * FROM bank_customer_details WHERE Customer_Account_Number IS NULL AND Customer_Pin IS NULL";
	private static final String set_Account_Number="update bank_customer_details set Customer_Account_Number=? ,Customer_Pin=? where Customer_Aadhar_Number=?";
	private static final String delete_Customer_Details="delete from bank_customer_details where Customer_Aadhar_Number=?";
	private static final String update_pin_Number="update bank_customer_details set Customer_Pin=? where Customer_Account_Number=?";
	private static final String set_Status="update bank_customer_details set Status=? where Customer_Account_Number=?";
	private static final String get_Details_By_Using_Status="select * from bank_customer_details where Status='Closing Approval'";
	public boolean insertCustomerDetails(CustomerDetails customerDetails) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();	
			PreparedStatement preparedStatement=connection.prepareStatement(insert_customer_details);
			preparedStatement.setString(1,customerDetails.getCustomername());
			preparedStatement.setLong(2, customerDetails.getMobilenumber());
			preparedStatement.setLong(3, customerDetails.getAadharnumber());
			preparedStatement.setString(4, customerDetails.getPannumber());
			preparedStatement.setString(5, customerDetails.getEmailid());
			preparedStatement.setString(6, customerDetails.getAddress());
			preparedStatement.setString(7, customerDetails.getDesignation());
			preparedStatement.setString(8, customerDetails.getGender());
			preparedStatement.setString(9, customerDetails.getIfsccode());
			preparedStatement.setString(10, customerDetails.getBranch());
			preparedStatement.setString(11, customerDetails.getTypeofaccount());
			preparedStatement.setDouble(12, customerDetails.getAmount());
			preparedStatement.setString(13, customerDetails.getStatus());
			int result=preparedStatement.executeUpdate();
			if(result!=0)
				return true;
			else
				return false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public List<CustomerDetails>  getAllCustomerDetails() {
		/*selection*/
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(select_all);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<CustomerDetails> listOfCustomerDetails=new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					CustomerDetails customerDetails=new CustomerDetails();
					customerDetails.setAadharnumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setMobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					customerDetails.setAccountnumber(resultSet.getLong("Customer_Account_Number"));
					customerDetails.setPinnumber(resultSet.getInt("Customer_Pin"));
					customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
					listOfCustomerDetails.add(customerDetails);
				}
				return listOfCustomerDetails;
			}else
				return null;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public CustomerDetails getCustomerDetailosByUsingEmailIdOrMobileNumberAndPinNumber(String emailIdOrmobileNumber,int pin) {
		
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(customer_Login);
			preparedStatement.setString(1, emailIdOrmobileNumber);
			preparedStatement.setString(2, emailIdOrmobileNumber);
			preparedStatement.setInt(3, pin);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				CustomerDetails customerDetails=new CustomerDetails();
				customerDetails.setCustomerid(resultSet.getInt("Customer_Id"));
				customerDetails.setCustomername(resultSet.getString("Customer_Name"));
				customerDetails.setAadharnumber(resultSet.getLong("Customer_Aadhar_Number"));
				customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
				customerDetails.setMobilenumber(resultSet.getLong("Customer_Mobile_Number"));
				customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
				customerDetails.setAccountnumber(resultSet.getLong("Customer_Account_Number"));
				customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
				customerDetails.setPinnumber(resultSet.getInt("Customer_Pin"));
				customerDetails.setDesignation(resultSet.getString("Customer_Designation"));
				customerDetails.setGender(resultSet.getString("Customer_Gender"));
				customerDetails.setIfsccode(resultSet.getString("IFSC_Code"));
				customerDetails.setBranch(resultSet.getString("Branch"));
				customerDetails.setTypeofaccount(resultSet.getString("Type_Of_Account"));
				customerDetails.setAmount(resultSet.getDouble("Amount"));
				return customerDetails;
			}
			else {
				return null;
			}
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	public CustomerDetails getCustomerDetailsByUsingAccountNumber(long accountNumber) {
		
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(get_Details_By_Using_Account_Number);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			if(resultSet.next()) {
				CustomerDetails customerDetails=new CustomerDetails();
				customerDetails.setCustomerid(resultSet.getInt("Customer_Id"));
				customerDetails.setCustomername(resultSet.getString("Customer_Name"));
				customerDetails.setAadharnumber(resultSet.getLong("Customer_Aadhar_Number"));
				customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
				customerDetails.setMobilenumber(resultSet.getLong("Customer_Mobile_Number"));
				customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
				customerDetails.setAccountnumber(resultSet.getLong("Customer_Account_Number"));
				customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
				customerDetails.setPinnumber(resultSet.getInt("Customer_Pin"));
				customerDetails.setDesignation(resultSet.getString("Customer_Designation"));
				customerDetails.setGender(resultSet.getString("Customer_Gender"));
				customerDetails.setIfsccode(resultSet.getString("IFSC_Code"));
				customerDetails.setBranch(resultSet.getString("Branch"));
				customerDetails.setTypeofaccount(resultSet.getString("Type_Of_Account"));
				customerDetails.setAmount(resultSet.getDouble("Amount"));
				return customerDetails;
			}
			else
				return null;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
	public boolean updateCustomerMoney(double money,long accountNumber) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(update_Amount);
			preparedStatement.setDouble(1, money);
			preparedStatement.setLong(2,accountNumber );
			int result=preparedStatement.executeUpdate();
			if(result!=0) {
				return true;
			}else
				return false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	public boolean closeAccount(long accountNumber) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement  preparedStatement=connection.prepareStatement(delete_Account);
			preparedStatement.setLong(1, accountNumber);
			int result=preparedStatement.executeUpdate();
			if(result!=0)
				return true;
			else
				return false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
	} 
	public List<CustomerDetails> getCustomerDetailsAccountNull() {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(get_customer);
			List<CustomerDetails> allCustomer=new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					CustomerDetails customerDetails=new CustomerDetails();
					customerDetails.setCustomerid(resultSet.getInt("Customer_Id"));
					customerDetails.setCustomername(resultSet.getString("Customer_Name"));
					customerDetails.setAadharnumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setMobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
					customerDetails.setAccountnumber(resultSet.getLong("Customer_Account_Number"));
					customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
					customerDetails.setPinnumber(resultSet.getInt("Customer_Pin"));
					customerDetails.setDesignation(resultSet.getString("Customer_Designation"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setIfsccode(resultSet.getString("IFSC_Code"));
					customerDetails.setBranch(resultSet.getString("Branch"));
					customerDetails.setTypeofaccount(resultSet.getString("Type_Of_Account"));
					customerDetails.setAmount(resultSet.getDouble("Amount"));
					allCustomer.add(customerDetails);
				}
				return allCustomer;
			}else
				return null;
			
			
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		

	}
	public boolean setAccountNumberAndPinNumber(long accountNumber,int pinNumber,long aadharNumber) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(set_Account_Number);
			preparedStatement.setLong(1, accountNumber);
			preparedStatement.setInt(2, pinNumber);
			preparedStatement.setLong(3, aadharNumber);
			int result=preparedStatement.executeUpdate();
			if(result!=0) {
				return true;
			}else
				return false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	public boolean deleteCustomerDetails(long aadharNumber) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(delete_Customer_Details);
			preparedStatement.setLong(1, aadharNumber);
			int result=preparedStatement.executeUpdate();
			if(result!=0)
				return true;
			else
				return false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	public boolean updatePinNumber(int pinNumber,long accountNumber) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(update_pin_Number);
			preparedStatement.setInt(1, pinNumber);
			preparedStatement.setLong(2, accountNumber);
			int result=preparedStatement.executeUpdate();
			if(result!=0)
				return true;
			else
				return false;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public boolean updateStatus(String status,long accountNumber) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(set_Status);
			preparedStatement.setString(1, status);
			preparedStatement.setLong(2, accountNumber);
			int result=preparedStatement.executeUpdate();
			if(result!=0)
				return true;
			else
				return false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	public List<CustomerDetails> getCustomerDEtailsByUsingStatus() {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(get_Details_By_Using_Status);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<CustomerDetails> allCustomer=new ArrayList<CustomerDetails>();
			if(resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					CustomerDetails customerDetails=new CustomerDetails();
					customerDetails.setCustomerid(resultSet.getInt("Customer_Id"));
					customerDetails.setCustomername(resultSet.getString("Customer_Name"));
					customerDetails.setAadharnumber(resultSet.getLong("Customer_Aadhar_Number"));
					customerDetails.setPannumber(resultSet.getString("Customer_Pan_Number"));
					customerDetails.setMobilenumber(resultSet.getLong("Customer_Mobile_Number"));
					customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
					customerDetails.setAccountnumber(resultSet.getLong("Customer_Account_Number"));
					customerDetails.setEmailid(resultSet.getString("Customer_Email_Id"));
					customerDetails.setPinnumber(resultSet.getInt("Customer_Pin"));
					customerDetails.setDesignation(resultSet.getString("Customer_Designation"));
					customerDetails.setGender(resultSet.getString("Customer_Gender"));
					customerDetails.setIfsccode(resultSet.getString("IFSC_Code"));
					customerDetails.setBranch(resultSet.getString("Branch"));
					customerDetails.setTypeofaccount(resultSet.getString("Type_Of_Account"));
					customerDetails.setAmount(resultSet.getDouble("Amount"));
					allCustomer.add(customerDetails);
				}
				return allCustomer;
			}else
				return null;
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
}
