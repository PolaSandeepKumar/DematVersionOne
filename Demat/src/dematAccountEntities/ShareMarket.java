package dematAccountEntities;

/**
 * @author kddeepan
 *
 */
public class ShareMarket {
	
	private String shareName;
	private double sharePrice;
	private int shareQuantity;
	
	//Constructor for ShareMarket with no parameters
	public ShareMarket() {
		
	}
	
	//Constructor for ShareMarket with three parameters
	public ShareMarket(String shareName, double sharePrice, int shareQuantity) {
		this.shareName = shareName;
		this.sharePrice = sharePrice;
		this.shareQuantity = shareQuantity;
	}

	//Getter method for Share Name
	public String getShareName() {
		return this.shareName;
	}
	
	//Getter method for Share Price
	public double getSharePrice() {
		return this.sharePrice;
	}
	
	//Getter method for Share Quantity
	public int getShareQuantity() {
		return this.shareQuantity;
	}
	
	//Setter method for Share Name
	public void setShareName(String shareName) {
		this.shareName = shareName;
	}
	
	//Setter method for Share Name
	public void setSharePrice(double sharePrice) {
		this.sharePrice = sharePrice;
	}

	//Setter method for Share Quantity
	public void setShareQuantity(int shareQuantity) {
		this.shareQuantity = shareQuantity;
	}
	
	//Method to return the Share Details
	public String toString() {
		return this.shareName+","+this.sharePrice+","+this.shareQuantity;
	}	
}