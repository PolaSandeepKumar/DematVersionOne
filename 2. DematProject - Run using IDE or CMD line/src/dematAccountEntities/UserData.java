package dematAccountEntities;

import java.util.UUID;

public class UserData{

	private String userName;
	private int accountNumber;
	private double accountBalance;
	private String accountPassword;
	
	//Constructor for UserData with no parameters
	public UserData () {
		
	}
	
	//Constructor for ShareMarket with two parameters
	public UserData (String userName, String accountPassword) {
		this.userName = userName;
		this.accountNumber = getUniqueNumber();
		this.accountBalance = 10000.00;
		this.accountPassword = accountPassword;
	}
	
	//Constructor for ShareMarket with all field parameters. 
	//This is used while fetching the data from UserData text file
	public UserData (String userName, int accountNumber, double accountBalance, String accountPassword) {
		this.userName = userName;
		this.accountNumber = accountNumber;
		this.accountBalance = accountBalance;
		this.accountPassword = accountPassword;
	}

	//Getter method for User Name
	public String getUserName() {
		return this.userName;
	}
	
	//Getter method for Account number
	public int getAccountNumber() {
		return this.accountNumber;
	}
	
	//Getter method for Account balance
	public double getAccountBalance() {

		return this.accountBalance;
	}
	
	////Getter method for password
	public String getAccountPassword() {
		return this.accountPassword;
	}
	
	//Setter method for User Name
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	//Setter method for Account number
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	//Setter method for Account Balance
	public void setAccountBalance(double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	//Setter method for Account password
	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}
	
	//Method to return the User Details
	public String toString() {
		return this.userName+","+this.accountNumber+","+this.accountBalance+","+this.accountPassword;
	}
	
	//Method to generate random account number while creating the account
	public static int getUniqueNumber() {
		UUID uniqueID = UUID.randomUUID();
        String stringID=""+uniqueID;        
        int uniqueInteger = stringID.hashCode();
        stringID = ""+uniqueInteger;
        stringID = stringID.replaceAll("-", "");
        uniqueInteger = Integer.parseInt(stringID);
        return uniqueInteger;
	}	
}