package org.bank.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bank.connection.JDBCConnection;
import org.bank.dto.AdminDetails;

public class AdminDAO {

	private static final String admin_login="select * from admin_details where Admin_EmailId=? and Admin_Password=?";
	private static final String  insert_Admin_Details="insert into admin_details(Admin_EmailId,Admin_Password) values (?,?)";
	private static final String select_all_Admin="select * from admin_details";
	public boolean insertAdminDetails(AdminDetails adminDetails) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(insert_Admin_Details);
			preparedStatement.setString(1, adminDetails.getAdminemailid());
			preparedStatement.setInt(2, adminDetails.getAdminpassword());
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
	public List<AdminDetails> selectAllAdminDetails() {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(select_all_Admin);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<AdminDetails> listofadmin=new ArrayList<AdminDetails>();
			while(resultSet.next()) {
				AdminDetails adminDetails=new AdminDetails();
				adminDetails.setAdminId(resultSet.getInt("Admin_Id"));
				adminDetails.setAdminemailid(resultSet.getString("Admin_EmailId"));
				adminDetails.setAdminpassword(resultSet.getInt("Admin_Password"));
				listofadmin.add(adminDetails);
			}
			return listofadmin;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public boolean selectAdminDetailsByUsingEmailIDAndPassword(String emailId,int password) {
		 try {
			 Connection connection=JDBCConnection.forMySQLConnection();
			 PreparedStatement preparedStatement=connection.prepareStatement(admin_login);
			 preparedStatement.setString(1, emailId);
			 preparedStatement.setInt(2, password);
			 ResultSet resultSet=preparedStatement.executeQuery();
			 if(resultSet.next()) {
				    AdminDetails adminDetails=new AdminDetails();
					adminDetails.setAdminId(resultSet.getInt("Admin_Id"));
					adminDetails.setAdminemailid(resultSet.getString("Admin_EmailId"));
					adminDetails.setAdminpassword(resultSet.getInt("Admin_Password"));
				 return true;
			 }else {
				 return false;
			 }
		 }
		 catch (ClassNotFoundException | SQLException e) {
			 e.printStackTrace();
			// TODO: handle exception
			 return false;
		}
	 }
}
