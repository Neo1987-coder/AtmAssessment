package application;


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


	
	public int getId() {
		return id;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public String getAccountNumber() {
		return accountNumber;
	}
	
	
	
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	
	
	public int getPin() {
		return pin;
	}
	
	
	
	public void setPin(int pin) {
		this.pin = pin;
	}
	
	
	
	public float getBalance() {
		return balance;
	}
	
	
	
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	
	
	public String getBeneficiaries() {
		return beneficiaries;
	}
	
	
	
	public void setBeneficiaries(String beneficiaries) {
		this.beneficiaries = beneficiaries;
	}
	
}
