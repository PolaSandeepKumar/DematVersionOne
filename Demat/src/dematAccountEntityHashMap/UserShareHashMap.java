package dematAccountEntityHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dematAccountEntities.UserShare;
import fileDatabase.DatabaseMaintainer;

public class UserShareHashMap {	
	
	private static Map <Integer, Map <String, UserShare>> userShareMap;

	//Static block to instantiate userShareHashMap and fetch userShareData from UserShare text file
	static {
		UserShareHashMap.userShareMap = new HashMap <Integer, Map <String, UserShare>>();
		UserShareHashMap.createUserShareMap(DatabaseMaintainer.readEntities("UserShare"));
	}
	
	//Private Constructor to avoid creation of object.	
	private UserShareHashMap () {
	}
	
	//Method to update the UserShare text file with latest information
	private static List <String> storeUserShareMap(){
		List <String> dataList = new ArrayList <String>();
		for (int accountNumber:UserShareHashMap.userShareMap.keySet()) {
			for (String shareName:UserShareHashMap.userShareMap.get(accountNumber).keySet()){
				dataList.add(UserShareHashMap.userShareMap.get(accountNumber).get(shareName).toString());
			}			
		}		
		return dataList;
	}
	
	//Method to update userShareHashMap with userShareData from UserShare text file
	private static void createUserShareMap(List <String> dataList) {
		for(String userShareDetails: dataList) {
			String [] userShareAttribute = userShareDetails.split(",");
			if(UserShareHashMap.userShareMap.containsKey(Integer.parseInt(userShareAttribute[0]))) {
				UserShareHashMap.userShareMap.get(Integer.parseInt(userShareAttribute[0])).put(userShareAttribute[1], new UserShare(Integer.parseInt(userShareAttribute[0]), userShareAttribute[1], Double.parseDouble(userShareAttribute[2]), Integer.parseInt(userShareAttribute[3])));
			}
			else {
				UserShareHashMap.userShareMap.put(Integer.parseInt(userShareAttribute[0]), new HashMap <String, UserShare> ());
				UserShareHashMap.userShareMap.get(Integer.parseInt(userShareAttribute[0])).put(userShareAttribute[1], new UserShare(Integer.parseInt(userShareAttribute[0]), userShareAttribute[1], Double.parseDouble(userShareAttribute[2]), Integer.parseInt(userShareAttribute[3])));
			}
		}		
	}
	
	//Method to fetch a particular User's shares from HashMap
	public static Map <String, UserShare> getUserShareMap(int accountNumber){
		Map <String, UserShare> userShareList = new HashMap <String, UserShare> ();
		if (UserShareHashMap.userShareMap.containsKey(accountNumber)) {
			for(String userShareName: UserShareHashMap.userShareMap.get(accountNumber).keySet()) {
				userShareList.put(userShareName,UserShareHashMap.userShareMap.get(accountNumber).get(userShareName));
			}
		}
		return userShareList;
	}
	
	//Method to update a particular User share in the HashMap and UserShare text file
	public static void updateUserShareMap(UserShare newUserShare, String transactionType) {
		if (transactionType.equals("Bought")) {
			if (UserShareHashMap.userShareMap.containsKey(newUserShare.getAccountNumber()) && UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).containsKey(newUserShare.getShareName())) {
				double currentAverageSharePrice = UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).get(newUserShare.getShareName()).getAverageSharePrice();
				int currentShareQuantity= UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).get(newUserShare.getShareName()).getShareQuantity();
				currentAverageSharePrice = ((currentAverageSharePrice*currentShareQuantity)+(newUserShare.getAverageSharePrice()*newUserShare.getShareQuantity()))/(currentShareQuantity+newUserShare.getShareQuantity());
				currentShareQuantity = currentShareQuantity+newUserShare.getShareQuantity();
				UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).get(newUserShare.getShareName()).setAverageSharePrice(currentAverageSharePrice);
				UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).get(newUserShare.getShareName()).setShareQuantity(currentShareQuantity);
				DatabaseMaintainer.writeEntities(UserShareHashMap.storeUserShareMap(), "UserShare");
			}
			else if(!UserShareHashMap.userShareMap.containsKey(newUserShare.getAccountNumber())) {
				UserShareHashMap.userShareMap.put(newUserShare.getAccountNumber(), new HashMap<String,UserShare>());
				UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).put(newUserShare.getShareName(), newUserShare);
				DatabaseMaintainer.addEntities(newUserShare.toString(), "UserShare");
			}
			else {
				UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).put(newUserShare.getShareName(), newUserShare);
				DatabaseMaintainer.addEntities(newUserShare.toString(), "UserShare");
			}
		}
		else {
			int currentShareQuantity= UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).get(newUserShare.getShareName()).getShareQuantity();
			currentShareQuantity = currentShareQuantity-newUserShare.getShareQuantity();
			if (currentShareQuantity==0) {
				UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).remove(newUserShare.getShareName());
				DatabaseMaintainer.writeEntities(UserShareHashMap.storeUserShareMap(), "UserShare");
				return;
			}
			else {
				UserShareHashMap.userShareMap.get(newUserShare.getAccountNumber()).get(newUserShare.getShareName()).setShareQuantity(currentShareQuantity);
				DatabaseMaintainer.writeEntities(UserShareHashMap.storeUserShareMap(), "UserShare");
			}
		}
	}
}
