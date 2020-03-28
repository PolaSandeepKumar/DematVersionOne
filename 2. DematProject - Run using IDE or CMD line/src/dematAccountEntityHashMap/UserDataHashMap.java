package dematAccountEntityHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dematAccountEntities.UserData;
import fileDatabase.DatabaseMaintainer;


public class UserDataHashMap {
	
	private static Map<Integer, UserData> userDataMap;
	
	//Static block to instantiate userDataHashMap and fetch userData from UserData text file
	static {
		UserDataHashMap.userDataMap = new HashMap <Integer, UserData> ();
		UserDataHashMap.createMap(DatabaseMaintainer.readEntities("UserData"));
	}

	//Private Constructor to avoid creation of object.	
	private UserDataHashMap() {
		
	}
	
	//Method to update the UserData text file with latest information
	private static List <String> storeMap() {
		List <String> dataList = new ArrayList<String> ();			
		for(int accountNumber: UserDataHashMap.userDataMap.keySet()) {				
				dataList.add(UserDataHashMap.userDataMap.get(accountNumber).toString());
		}
		return dataList;
	}
	
	//Method to update userDataMap with userData from UserData text file
	private static void createMap(List <String> dataList) {			
		for(String userData:dataList) {
			String [] userAttributes = userData.split(",");
			UserDataHashMap.userDataMap.put(Integer.parseInt(userAttributes[1]), new UserData(userAttributes[0],Integer.parseInt(userAttributes[1]),Double.parseDouble(userAttributes[2]),userAttributes[3]));
		}
	}
	
	//Method to fetch a particular account details from HashMap.
	public static UserData getAccountDetails(int accountNumber) {			
		return UserDataHashMap.userDataMap.get(accountNumber);
	}
	
	//Method to add a new User to HashMap and UserData file.
	public static void addUser(UserData newUser) {
		UserDataHashMap.userDataMap.put(newUser.getAccountNumber(),newUser);
		DatabaseMaintainer.addEntities(newUser.toString(), "UserData");
	}
	
	//Method to edit existing users in HashMap and UserData file. 
	public static void editUser() {
		DatabaseMaintainer.writeEntities(UserDataHashMap.storeMap(), "UserData");
	}
	
	//Method to validate user login credentials
	public static boolean validateLogin (int userAccountNumber, String userAccountPassword) {
		if(!(UserDataHashMap.userDataMap.containsKey(userAccountNumber) && userAccountPassword.equals(UserDataHashMap.userDataMap.get(userAccountNumber).getAccountPassword()))) {
			System.out.println("Enter valid Account number and valid Password combination");	
		}
		return UserDataHashMap.userDataMap.containsKey(userAccountNumber) && userAccountPassword.equals(UserDataHashMap.userDataMap.get(userAccountNumber).getAccountPassword());
	}
}