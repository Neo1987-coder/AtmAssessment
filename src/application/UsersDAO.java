package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsersDAO {
	
	Connection con;
	
	// Constructor which initialises our database connection
	UsersDAO() {
		this.con = DB.getConnection();
		//System.out.print(con);
	}

	
	
	public User getUser(String accountNumber, int pin) {
		// Select query.... 
		try {
			PreparedStatement ps = this.con.prepareStatement(
					"select * from users WHERE ACCOUNT_NUMBER = '"+accountNumber+"' AND PIN = '"+pin+"' ", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();
			
	        if (rs.first() == false) {
	        	
	        	// Close the database connection and return
	        	con.close();
	        	return null;
	        }
	        else {
	        	// If query result is not empty
	        	
	        	// Get the user's information
	        	int id = rs.getInt("id");
	        	String name = rs.getString("NAME");
	        	String _accountNumber = rs.getString("ACCOUNT_NUMBER");
	        	int _pin = rs.getInt("PIN");
	        	float balance = rs.getFloat("BALANCE");
	        	String beneficiaries = rs.getString("BENEFICIARIES");
	        	
	        	con.close();
	        	return new User(id, name, _accountNumber, _pin, balance, beneficiaries);
	        }
	    }
		catch (Exception e) {
			e.printStackTrace();
	    	return null;
		}
	}
	
	
	
	public User getUser(String accountNumber) {
		// Select query.... [please, you have to read more on SQL, it'll help you alot]
		try {
			PreparedStatement ps = this.con.prepareStatement(
					"select * from users WHERE ACCOUNT_NUMBER = '"+accountNumber+"' ", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();
			
	        if (rs.first() == false) {
	        	// If query result is empty
	        	// Close the database connection and return
	        	con.close();
	        	return null;
	        }
	        else {
	        	// If query result is not empty
	        	
	        	// Get the user's information
	        	int id = rs.getInt("id");
	        	String name = rs.getString("NAME");
	        	String _accountNumber = rs.getString("ACCOUNT_NUMBER");
	        	int _pin = rs.getInt("PIN");
	        	float balance = rs.getFloat("BALANCE");
	        	String beneficiaries = rs.getString("BENEFICIARIES");
	        	
	        	con.close();
	        	return new User(id, name, _accountNumber, _pin, balance, beneficiaries);
	        }
	    }
		catch (Exception e) {
			e.printStackTrace();
	    	return null;
		}
	}
	
	
	
	public User getUser(int _id) {
		// Select query.... 
		try {
			PreparedStatement ps = this.con.prepareStatement(
					"select * from users WHERE ID = '"+_id+"' ", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();
			
	        if (rs.first() == false) {
	        	// If query result is empty
	        	// Close the database connection and return
	        	con.close();
	        	return null;
	        }
	        else {
	        	// If query result is not empty
	        	
	        	// Get the user's information
	        	int id = rs.getInt("id");
	        	String name = rs.getString("NAME");
	        	String _accountNumber = rs.getString("ACCOUNT_NUMBER");
	        	int _pin = rs.getInt("PIN");
	        	float balance = rs.getFloat("BALANCE");
	        	String beneficiaries = rs.getString("BENEFICIARIES");
	        	
	        	con.close();
	        	return new User(id, name, _accountNumber, _pin, balance, beneficiaries);
	        }
	    }
		catch (Exception e) {
			e.printStackTrace();
	    	return null;
		}
	}
	
	
	
	
	public int update(User user) {
		
		// Get the user details to update
		int id = user.getId();
		String name = user.getName();
		String accountNumber = user.getAccountNumber();
		int pin = user.getPin();
		float balance = user.getBalance();
		String beneficiaries = user.getBeneficiaries();
		
		
		// Error if values in instance are empty
		if(id==0 || name.isBlank() || accountNumber.isBlank()  || pin==0) return 0;	
		
		// Update query.... 
		try {
			PreparedStatement ps = this.con.prepareStatement(
					"UPDATE users SET NAME = '" + name + "', ACCOUNT_NUMBER ='" + accountNumber +"', PIN = '" + pin +"', BALANCE = '"+ balance +"', BENEFICIARIES = '"+ beneficiaries +"' WHERE id ='" + id + "'");
			int x =  ps.executeUpdate();
			con.close();
			return x;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}	
		
	}
}
