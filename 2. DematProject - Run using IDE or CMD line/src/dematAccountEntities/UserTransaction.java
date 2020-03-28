package dematAccountEntities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;


public class UserTransaction {

	private int accountNumber;
	private int transactionID;
	private LocalDate transactionDate;
	private LocalTime transactionTime;
	private String shareName;
	private String transactionType;
	private double sharePrice;
	private int shareQuantity;
	private double transactionChargeRate;
	private double securitiesTransferTaxRate;
	
//Constructor with no parameters
	public UserTransaction() {
		
	}
//Constructor with eight parameters
//Used while creating a new transaction object	
	public UserTransaction(int accountNumber, String shareName, String transactionType, double sharePrice, int shareQuantity, double transactionChargeRate, double securitiesTransferTaxRate) {
		this.accountNumber = accountNumber;
		this.transactionID = getUniqueNumber();
		this.transactionDate = LocalDate.now();
		this.transactionTime = LocalTime.now();
		this.shareName = shareName;
		this.transactionType = transactionType;
		this.sharePrice = sharePrice;
		this.shareQuantity = shareQuantity;
		this.transactionChargeRate = transactionChargeRate;
		this.securitiesTransferTaxRate = securitiesTransferTaxRate;
	}

//Constructor with nine parameters
//Used while fetching transaction details from the usertransaction text file		
	public UserTransaction(int accountNumber, int transactionID, String transactionDate, String transactionTime, String shareName, String transactionType, Double sharePrice, int shareQuantity, double transactionChargeRate, double securitiesTransferTaxRate) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		this.accountNumber = accountNumber;
		this.transactionID = transactionID;
		this.transactionDate = LocalDate.parse(transactionDate, dateFormatter);
		this.transactionTime = LocalTime.parse(transactionTime, timeFormatter);
		this.shareName = shareName;
		this.transactionType = transactionType;
		this.sharePrice = sharePrice;
		this.shareQuantity = shareQuantity;
		this.transactionChargeRate = transactionChargeRate;
		this.securitiesTransferTaxRate = securitiesTransferTaxRate;
	}
	
//Getter method for account number
	public int getAccountNumber() {
		return this.accountNumber;
	}

//Getter method for transactionID	
	public int getTransactionID() {
		return this.transactionID;
	}

//Getter method for transaction date	
	public String getTransactionDate() {
		return this.transactionDate.toString();
	}

//Getter method for transaction time
	public String getTransactionTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
		return formatter.format(this.transactionTime);
	}

//Getter method for share name	
	public String getshareName() {
		return this.shareName;
	}

//Getter method for transaction type		
	public String getTransactionType() {
		return this.transactionType;
	}

//Getter method for share price
	public double getSharePrice() {
		return this.sharePrice;
	}

//Getter method for share quantity
	public int getShareQuantity() {
		return this.shareQuantity;
	}

//Getter method for transaction charge rate
	public double getTransactionChargeRate() {
		return this.transactionChargeRate;
	}

//Getter method for securities transfer rate	
	public double securitiesTransferTaxRate() {
		return this.securitiesTransferTaxRate;
	}

//Setter method for securities transfer rate	
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

//Setter method for transactionID	
	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

//Setter method for transaction date	
	public void setTransactionDate(String transactionDate) {
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");		
		this.transactionDate = LocalDate.parse(transactionDate, dateFormatter);
	}

//Setter method for transaction time
	public void setTransactionTime(String transactionTime) {
		DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");		
		this.transactionTime = LocalTime.parse(transactionTime, timeFormatter);
	}

//Setter method for transaction type
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

//Setter method for share price
	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}

//Setter method for share quantity
	public void setShareQuantity(int shareQuantity) {
		this.shareQuantity = shareQuantity;
	}

//Setter method for transaction charge rate	
	public void setTransactionChargeRate(double transactionChargeRate) {
		this.transactionChargeRate = transactionChargeRate;
	}

//Setter method for securites transfer rate	
	public void setSecuritiesTransferTaxRate (double securitiesTransferTaxRate) {
		this.securitiesTransferTaxRate = securitiesTransferTaxRate;
	}

//Method to return transaction object details
	public String toString() {
		return this.accountNumber+","+this.transactionID+","+this.getTransactionDate()+","+this.getTransactionTime()+","+this.shareName+","+this.transactionType+","+this.sharePrice+","+this.shareQuantity+","+this.transactionChargeRate+","+this.securitiesTransferTaxRate;
	}

//Method to create unique number for transaction ID	
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