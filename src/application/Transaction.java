package application;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Transaction extends User {

	private int id;
	private int initiatorId;
	private String type;
	private BigDecimal amount;
	private int destinationId;
	private String date = new SimpleDateFormat("MMMMM dd, yyyy").format(new Date());
	private String receiversName;
	private float balance;
	private String newDate;

	public Transaction() {
	}

	public Transaction(int id, int initiatorId, String type, BigDecimal amount, int destinationId, String date) {
		this.id = id;
		this.initiatorId = initiatorId;
		this.type = type;
		this.amount = amount;
		this.destinationId = destinationId;
		this.date = date;
	}

	public Transaction(int initiatorId, String type, BigDecimal amount) {
		this.initiatorId = initiatorId;
		this.type = type;
		this.amount = amount;
	}

	public Transaction(int initiatorId, String type, BigDecimal amount, int destinationId, String date) {
		this.initiatorId = initiatorId;
		this.type = type;
		this.amount = amount;
		this.destinationId = destinationId;
		this.date = date;
	}

	public Transaction(int initiatorId, String type, BigDecimal amount, int destinationId) {
		this.initiatorId = initiatorId;
		this.type = type;
		this.amount = amount;
		this.destinationId = destinationId;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the initiatorId
	 */
	public int getInitiatorId() {
		return initiatorId;
	}

	/**
	 * @param initiatorId the initiatorId to set
	 */
	public void setInitiatorId(int initiatorId) {
		this.initiatorId = initiatorId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the amount
	 */
	public BigDecimal getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * @return the destinationId
	 */
	public int getDestinationId() {
		return destinationId;
	}

	/**
	 * @param destinationId the destinationId to set
	 */
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return this.date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Set the current table row serial number
	 * 
	 * @param sn The serial number to set
	 */
	/*
	 * public void setSn(int sn) { this.sn = sn; }
	 * 
	 *//**
		 *
		 * 
		 * @return The current row number
		 *//*
			 * public int getSn() { return this.sn; }
			 */

	////////////////////////////////////////////////////////////////////////////////
	// FOR TABLE VIEW
	////////////////////////////////////////////////////////////////////////////////

	public String getReceiversName() {
		this.receiversName = new UsersDAO().getUser(this.destinationId).getName();
		return this.receiversName;
	}

	/**
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}

	/**
	 * @return the newDate
	 */
	public String getNewDate() {
		return newDate;
	}

	/**
	 * @param newDate the newDate to set
	 */
	public void setNewDate(String newDate) {
		this.newDate = newDate;
	}
}
