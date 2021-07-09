package application;

/**
 * Our user class to hold user objects as seen in our database logic
 * It will contain getters and setters in collaboration with our Users Database Access Object(DAO)
 * @author dev.charles15531@gmail.com
 *
 */
public class User {

	private int id;
	private String name;
	private String accountNumber;
	private int pin;
	private float balance;
	private String beneficiaries;
	
	// Empty constructor
	public User() {
		
	}
	
	// Constructor with arguments. To be called by our users DAO
	public User(int id, String name, String accountNumber, int pin, float balance, String beneficiaries) {
		this.id = id;
		this.name = name;
		this.accountNumber = accountNumber;
		this.pin = pin;
		this.balance = balance;
		this.beneficiaries = beneficiaries;
	}


	/**
	 * This method gets the user's unique  id
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	
	
	/**
	 * This method returns the user's full name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	
	/**
	 * This method sets the user's name
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	/**
	 * This method returns the user's account number
	 * @return the accountNumber
	 */
	public String getAccountNumber() {
		return accountNumber;
	}
	
	
	/**
	 * This method sets the user's account number
	 * @param accountNumber the accountNumber to set
	 */
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	/**
	 * This method gets the user's pin
	 * @return the pin
	 */
	public int getPin() {
		return pin;
	}
	
	
	/**
	 * This method sets the user's pin 
	 * @param pin the pin to set
	 */
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	
	/**
	 * This method returns the user's account balance
	 * @return the balance
	 */
	public float getBalance() {
		return balance;
	}
	
	
	/**
	 * This method sets the users account balance
	 * @param balance the balance to set
	 */
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	
	/**
	 * This method get the user's beneficiaries
	 * @return the beneficiaries
	 */
	public String getBeneficiaries() {
		return beneficiaries;
	}
	
	
	/**
	 * This method sets the user's beneficiaries
	 * @param beneficiaries the beneficiaries to set
	 */
	public void setBeneficiaries(String beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	
}
