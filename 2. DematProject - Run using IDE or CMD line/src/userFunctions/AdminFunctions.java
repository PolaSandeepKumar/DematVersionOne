package userFunctions;
 
import java.util.*;

import dematAccountEntities.ApplicableCharge;
import dematAccountEntities.ShareMarket;
import dematAccountEntityHashMap.ShareMarketHashMap;

public class AdminFunctions {
	
	static Scanner input = new Scanner(System.in);
	
	//Method to add new share by Admin
	public static void addShare(){
		Map<String, ShareMarket> allShareDetails = ShareMarketHashMap.getShareMarket();
		ShareMarket addUniqueShare = new ShareMarket();
		CustomerFunctions.displayShareMarket(allShareDetails);
		System.out.println("Enter Share Name : ");
		String shareName = input.next();
		if(allShareDetails.containsKey(shareName))	{
			System.out.println("This Share is already available, try to add different Share :( .You will be returned to main menu" +'\n');
			return;
		}
		else if(isNumber(shareName)){
			System.out.println("Share should be alphabets only :( .You will be returned to main menu" +'\n');
			return;
		}
		System.out.println("Enter Share Quantity : ");
		if(!input.hasNextInt()) {
			System.out.println("Quantity entered in invalid, it should be a postive number.You will be returned to main menu" +'\n');
			return;
		}	
		int shareQuantity = input.nextInt();
		System.out.println("Enter Share Price : ");
		if(!input.hasNextInt()) {
			System.out.println("Price entered in invalid, it should be a postive number.You will be returned to main menu" +'\n');
			return;
		}	
		int sharePrice = input.nextInt();
		addUniqueShare.setShareName(shareName.trim());
		addUniqueShare.setShareQuantity(shareQuantity);
		addUniqueShare.setSharePrice(sharePrice);
		ShareMarketHashMap.addShare(addUniqueShare);
		Map<String, ShareMarket> shareAfterAdding = ShareMarketHashMap.getShareMarket();
		System.out.println("Share Name :" + shareAfterAdding.get(shareName).getShareName() + ',' + " Share Price:" + shareAfterAdding.get(shareName).getSharePrice() + ',' + " Share Quantity: " + shareAfterAdding.get(shareName).getShareQuantity());
		System.out.println("Successfully added the share" + '\n');
	}
	
	//Method to update quantity of share by Admin
	public static void updateShareQuantity(){
		Map<String, ShareMarket> everyShareDetails = ShareMarketHashMap.getShareMarket();
		CustomerFunctions.displayShareMarket(everyShareDetails);
		ShareMarket editUniqueSharePrice = new ShareMarket();	
		System.out.println('\n' + "Enter Share Name for updating quantity: ");
		String shareName = input.next();
		if(!everyShareDetails.containsKey(shareName))	{
			System.out.println("Share doesn't exist :( .You will be returned to main menu" +'\n');
			return;
		}
		System.out.println("Enter Share Quantiy : ");
		if(!input.hasNextInt()) {
			System.out.println("Quantity entered in invalid, it should be a postive integer.You will be returned to main menu" + '\n');
			return;
		}	
		int updateshareQuantity = input.nextInt();			
		editUniqueSharePrice.setShareName(shareName);
		editUniqueSharePrice.setShareQuantity(updateshareQuantity);
		editUniqueSharePrice.setSharePrice(everyShareDetails.get(shareName).getSharePrice());
		everyShareDetails.put(editUniqueSharePrice.getShareName(),editUniqueSharePrice);
		ShareMarketHashMap.editShare();
		Map<String, ShareMarket> everyShareDetailsupdate = ShareMarketHashMap.getShareMarket();
		System.out.println(" Share Name: " + everyShareDetailsupdate.get(shareName).getShareName() +',' + " Share Price: " + everyShareDetailsupdate.get(shareName).getSharePrice() +',' + " Share Quantity: " + everyShareDetailsupdate.get(shareName).getShareQuantity());
		System.out.println("Successfully updated quantiy, Please find above available shares" + '\n');
	}

	//Method to update price of share by Admin
	public static void updateSharePrice(){
		Map<String, ShareMarket> allShareDetails = ShareMarketHashMap.getShareMarket();
		CustomerFunctions.displayShareMarket(allShareDetails);
		ShareMarket editUniqueShareQuantity = new ShareMarket();
		System.out.println('\n'+"Enter Share Name for updating price : ");
		String updateShareName = input.next();
		if(!allShareDetails.containsKey(updateShareName))	{
			System.out.println("Share doesn't exist :( .You will be returned to main menu" +'\n');
			return;
		}
		System.out.println("Enter Share Price : ");
		if(!input.hasNextInt()) {
			System.out.println("Price entered in invalid, it should be a postive integer.You will be returned to main menu" + '\n');
			return;
		}	
		int updateSharePrice = input.nextInt();			
		editUniqueShareQuantity.setShareName(updateShareName);
		editUniqueShareQuantity.setSharePrice(updateSharePrice);
		editUniqueShareQuantity.setShareQuantity(allShareDetails.get(updateShareName).getShareQuantity());
		allShareDetails.put(editUniqueShareQuantity.getShareName(),editUniqueShareQuantity);
		ShareMarketHashMap.editShare();
		Map<String, ShareMarket> allShareDetailsupdated = ShareMarketHashMap.getShareMarket();
		System.out.println("Share Name: " + allShareDetailsupdated.get(updateShareName).getShareName() +',' + " Share Price:" + allShareDetailsupdated.get(updateShareName).getSharePrice() +',' + " Share Quantity:" + allShareDetailsupdated.get(updateShareName).getShareQuantity());
		System.out.println("Successfully updated price, please find above available shares" +'\n');	
	}

	//Method to update transaction charge by Admin
	public static void updateTransactionCharge(){
		Scanner userInput = new Scanner(System.in);
		System.out.println("Current STT charge is" +ApplicableCharge.getSecuritiesTransferTaxRate());
		System.out.println("Current Transaction charge is" +ApplicableCharge.getTransactionChargeRate());
		System.out.println("Enter Transaction Charge ");
		if(!userInput.hasNextDouble()) {
			System.out.println("Transaction charge entered in invalid, it should be a numeric value. You will be returned to main menu" + '\n');
			return;
		}	
		double transactionCharge = userInput.nextDouble();
		ApplicableCharge.setTransactionChargeRate(transactionCharge);
		System.out.println("Updated Transaction charge is" +ApplicableCharge.getTransactionChargeRate() +'\n');
	}

	//Method to update STT charge
	public static void updateSTTCharge(){
		Scanner userInputStt = new Scanner(System.in);
	
		System.out.println("Current STT charge is" +ApplicableCharge.getSecuritiesTransferTaxRate());
		System.out.println("Current Transaction charge is" +ApplicableCharge.getTransactionChargeRate());
		System.out.println("Enter STT Charge ");
		
		if(!userInputStt.hasNextDouble()) {
			System.out.println("STT charge entered in invalid, it should be a numeric value. You will be returned to main menu" + '\n');
			return;
		}
		
		double STT = userInputStt.nextDouble();
		ApplicableCharge.setSecuritiesTransferTaxRate(STT);
		System.out.println("Updated STT charge is" +ApplicableCharge.getSecuritiesTransferTaxRate() +'\n');	
	}

	//Method to check whether entered string is Integer
	public static boolean isNumber(String s) 
	{ 
	    for (int i = 0; i < s.length(); i++) 
	    if (Character.isDigit(s.charAt(i))  
	        == false) 
	        return false; 
	
	    return true; 
	} 

}
