package org.bank.service;

import java.util.List;

import org.bank.dao.TransactionDAO;
import org.bank.dto.TransactionDetails;

public class TransactionService {

	TransactionDAO transactionDAO=new TransactionDAO();
	TransactionDetails transactionDetails=new TransactionDetails();
	public void insertTransationDetails(TransactionDetails transactionDetails ) {
		
		
		if(!transactionDAO.insertTransactionDetails(transactionDetails)) {
			System.out.println("Server Error 500");
		}
		

	}
	public List<TransactionDetails> getTransactionDetails(long accountNumber) {
		return transactionDAO.getAllTransactionDetails(accountNumber);
	}
	
}
