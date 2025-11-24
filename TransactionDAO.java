package org.bank.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.JDBCType;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.bank.connection.JDBCConnection;
import org.bank.dto.TransactionDetails;

public class TransactionDAO {

	private static final String insert_transaction_details="insert into transaction_details (Transaction_Type, Transaction_Date, Transaction_Time, Transaction_Amount, Balance, Customer_Account_Number) values(?,?,?,?,?,?)";
	private static final String select_Transaction_Details="select * from transaction_details where Customer_Account_Number=?";
	public boolean insertTransactionDetails(TransactionDetails transactionDetails) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(insert_transaction_details);
			preparedStatement.setString(1, transactionDetails.getTransactiontype());
			preparedStatement.setDate(2, Date.valueOf(transactionDetails.getTransactiondate()));
			preparedStatement.setTime(3, Time.valueOf(transactionDetails.getTransactiontime()));
			preparedStatement.setDouble(4, transactionDetails.getTransactionamount());
			preparedStatement.setDouble(5, transactionDetails.getBalance());
			preparedStatement.setLong(6, transactionDetails.getCustomeraccountnumber());
			int result=preparedStatement.executeUpdate();
			if(result!=0) {
				return true;
			}
			else
				return false;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

	}
	public List<TransactionDetails> getAllTransactionDetails(long accountNumber) {
		try {
			Connection connection=JDBCConnection.forMySQLConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(select_Transaction_Details);
			preparedStatement.setLong(1, accountNumber);
			ResultSet resultSet=preparedStatement.executeQuery();
			List<TransactionDetails> alltransaction=new ArrayList<TransactionDetails>();
			if(resultSet.isBeforeFirst()) {
				while(resultSet.next()) {
					TransactionDetails transactionDetails=new TransactionDetails();
					transactionDetails.setTransactionid(resultSet.getInt("Transaction_Id"));
					transactionDetails.setTransactiontype(resultSet.getString("Transaction_Type"));
					transactionDetails.setTransactiondate(resultSet.getDate("Transaction_Date").toLocalDate());
					transactionDetails.setTransactiontime(resultSet.getTime("Transaction_Time").toLocalTime());
					transactionDetails.setTransactionamount(resultSet.getDouble("Transaction_Amount"));
					transactionDetails.setBalance(resultSet.getDouble("Balance"));
					transactionDetails.setCustomeraccountnumber(resultSet.getLong("Customer_Account_Number"));
					alltransaction.add(transactionDetails);
					
				}
				return alltransaction;
			}
			else
				return null;
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}
}
