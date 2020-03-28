package dematAccountEntities;

public class UserShare {
	
	private int accountNumber;
	private String shareName;
	private double averageSharePrice;
	private int shareQuantity;
	
	//Constructor for ShareMarket with no parameters
	public UserShare(){
		
	}
	
	//Constructor for ShareMarket with three parameters
	public UserShare(int accountNumber, String shareName, double averageSharePrice, int shareQuantity) {
		this.accountNumber = accountNumber;
		this.shareName = shareName;
		this.averageSharePrice = averageSharePrice;
		this.shareQuantity = shareQuantity;
	}

	//Getter method for Account Number
	public int getAccountNumber() {
		return this.accountNumber;
	}
	
	//Getter method for Share Name
	public String getShareName() {
		return this.shareName;
	}
	
	//Getter method for Average Share Name
	public double getAverageSharePrice() {
		return this.averageSharePrice;
	}
	
	//Getter method for Share Quantity
	public int getShareQuantity() {
		return this.shareQuantity;
	}

	//Setter method for Account number
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
		return;
	}
	
	//Setter method for Share Name
	public void setShareName(String shareName) {
		this.shareName = shareName;
		return;
	}

	//Setter method for Average price
	public void setAverageSharePrice(double averageSharePrice) {
		this.averageSharePrice = averageSharePrice;
		return;
	}

	//Setter method for Share Quantity
	public void setShareQuantity(int shareQuantity) {
		this.shareQuantity = shareQuantity;
		return;
	}

	//Method to return the User Share Details
	public String toString() {
		return this.accountNumber+","+this.shareName+","+this.averageSharePrice+","+this.shareQuantity;
	}
}