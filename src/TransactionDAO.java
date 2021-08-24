package application;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class TransactionDAO {
	
	Connection con;
	
	// Constructor which initialises our database connection
	TransactionDAO() {
		this.con = DB.getConnection();
		//System.out.print(con);
	}

	
	
	public ObservableList<Transaction> getTransaction(int _id) {
		ObservableList<Transaction> data = FXCollections.observableArrayList();
		// Select query....
		try {
			PreparedStatement ps = this.con.prepareStatement(
					"select * from TRANSACTIONS WHERE INITIATOR_ID = '"+_id+"' ORDER BY ID DESC", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();
			
	        if (rs.first() == false) {
	        	// If query result is empty
	        	// Close the database connection and return
	        	con.close();
	        	return null;
	        }
	        else {
	        	while(rs.next()) {
	        		// If query result is not empty
		        	
		        	// Get the transaction's information
		        	int id = rs.getInt("ID");
		        	int initiatorId = rs.getInt("INITIATOR_ID");
		        	String type = rs.getString("TYPE");
		        	BigDecimal amount = rs.getBigDecimal("AMOUNT");
		        	int destinationId = rs.getInt("DESTINATION_ID");
		        	String date = rs.getString("DATE");
		        	Transaction tran = new Transaction(id, initiatorId, type, amount, destinationId, date);
		        	tran.setNewDate(date);
		        	float balance = new UsersDAO().getUser(initiatorId).getBalance();
		        	
		        	if(type=="DEPOSIT") {
		        		tran.setBalance(balance + amount.floatValue());
		        	}
		        	else {
		        		tran.setBalance(balance - amount.floatValue());
		        	}
		        	data.add(tran); 
	        	}
	        	con.close();
	        	return data;
	        }
	    }
		catch (Exception e) {
			e.printStackTrace();
	    	return null;
		}
	}
	
	
	
	
	public int getTransactionsCount(int id) {
		try {
			PreparedStatement ps = this.con.prepareStatement(
					"select * from TRANSACTIONS WHERE INITIATOR_ID = '"+id+"' ORDER BY ID DESC", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();
			
	        if (rs.first() == false) {
	        	// If query result is empty
	        	con.close();
	        	return 0;
	        }
	        else {
	        	// If query result is not empty
	        	rs.last();
	        	int count = rs.getRow();
	        	
	        	con.close();
	        	return count;
	        }
	    }
		catch (Exception e) {
	    	return 0;
		}
	}
	
	
	
	
	public int save(Transaction trans) {
		// Variable to hold return value
		int returnVal;
		// Get the transaction's information
    	int initiatorId = trans.getInitiatorId();
    	String type = trans.getType();
    	BigDecimal amount = trans.getAmount();
    	int destinationId = trans.getDestinationId();
    	String date = trans.getDate();
		
		
		// Error if values in instance are empty
		if(initiatorId==0 || type.isBlank() || amount.intValue()==0) return 0;	
		
		try {
			//System.out.println("Start Saving");
			PreparedStatement ps = this.con.prepareStatement(
					"insert into TRANSACTIONS(INITIATOR_ID, TYPE, AMOUNT, DESTINATION_ID, DATE) values(?,?,?,?,?)");
			
			ps.setInt(1, initiatorId);
			ps.setString(2, type);
			ps.setBigDecimal(3, amount);
			ps.setInt(4, destinationId);
			ps.setString(5, date);
				
			returnVal = ps.executeUpdate();
			con.close();
		} 
		catch (SQLException e) {
			e.printStackTrace();
			returnVal = 0;
		}	
		return returnVal;
	}
	
}
