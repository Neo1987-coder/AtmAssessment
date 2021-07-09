package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Our users Database Access Object which will enable communication between the user object and database
 * Queries will be written here 
 * @author dev.charles15531@gmail.com
 *
 */
public class UsersDAO {
	
	Connection con;
	
	// Constructor which initialises our database connection
	UsersDAO() {
		this.con = DB.getConnection();
		//System.out.print(con);
	}

	
	/**
	 * This method is called To get a particular user by account number and pin
	 * 
	 * @param accountNumber The user's account number 
	 * @param pin The user's pin
	 * @return The user object if the user exist, or null if the user does not exist
	 */
	public User getUser(String accountNumber, int pin) {
		// Select query.... [please, you have to read more on SQL, it'll help you alot]
		try {
			PreparedStatement ps = this.con.prepareStatement(
					"select * from users WHERE ACCOUNT_NUMBER = '"+accountNumber+"' AND PIN = '"+pin+"' ", ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
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
	
	
	/**
	 * This method is called To get a particular user by account number only
	 * 
	 * @param accountNumber The user's account number 
	 * @param pin The user's pin
	 * @return The user object if the user exist, or null if the user does not exist
	 */
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
	
	
	/**
	 * This method is called To get a particular user by unique id only
	 * 
	 * @param accountNumber The user's account number 
	 * @param pin The user's pin
	 * @return The user object if the user exist, or null if the user does not exist
	 */
	public User getUser(int _id) {
		// Select query.... [please, you have to read more on SQL, it'll help you alot]
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
	
	
	
	/**
	 * This method is for updating user info in database where the user's unique id == user object id
	 * @param user The user object to 
	 * @return integer greater than 0 (>0) on success and an integer less than 0 (<0) on failure
	 */
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
		
		// Update query.... [please, you have to read more on SQL, it'll help you alot]
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
