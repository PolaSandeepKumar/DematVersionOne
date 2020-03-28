/**
 * 
 */
package dematAccountEntityHashMap;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dematAccountEntities.UserTransaction;
import fileDatabase.DatabaseMaintainer;

public class UserTransactionHashMap {
	
	private static Map<Integer, List <UserTransaction>> userTransactionMap;
	
	//Static block to instantiate userTransactionMap and fetch userTransactionData from UserTransaction text file
	static {
		UserTransactionHashMap.userTransactionMap = new HashMap<Integer, List<UserTransaction>>();
		UserTransactionHashMap.createUserTransactionMap(DatabaseMaintainer.readEntities("UserTransaction"));
	}

	//Private Constructor to avoid creation of objects
	private UserTransactionHashMap(){

	}
	
	//Method to update userTransactionMap with userTransactionData from UserTransaction text file
	private static void createUserTransactionMap(List <String> dataList) {
		String [] userTransactionAttribute;
		for(String userTransactionData: dataList) {
			userTransactionAttribute = userTransactionData.split(",");
			if(UserTransactionHashMap.userTransactionMap.containsKey(Integer.parseInt(userTransactionAttribute[0]))){
				UserTransactionHashMap.userTransactionMap.get(Integer.parseInt(userTransactionAttribute[0])).add(new UserTransaction(Integer.parseInt(userTransactionAttribute[0]),Integer.parseInt(userTransactionAttribute[1]),userTransactionAttribute[2],userTransactionAttribute[3],userTransactionAttribute[4],userTransactionAttribute[5],Double.parseDouble(userTransactionAttribute[6]),Integer.parseInt(userTransactionAttribute[7]),Double.parseDouble(userTransactionAttribute[8]),Double.parseDouble(userTransactionAttribute[9])));
			}
			else {
				UserTransactionHashMap.userTransactionMap.put(Integer.parseInt(userTransactionAttribute[0]), new ArrayList <UserTransaction> ());
				UserTransactionHashMap.userTransactionMap.get(Integer.parseInt(userTransactionAttribute[0])).add(new UserTransaction(Integer.parseInt(userTransactionAttribute[0]),Integer.parseInt(userTransactionAttribute[1]),userTransactionAttribute[2],userTransactionAttribute[3],userTransactionAttribute[4],userTransactionAttribute[5],Double.parseDouble(userTransactionAttribute[6]),Integer.parseInt(userTransactionAttribute[7]),Double.parseDouble(userTransactionAttribute[8]),Double.parseDouble(userTransactionAttribute[9])));
			}
		}		
	}
	
	//Method to fetch a particular User's transactions of a specific share from HashMap
	public static List <UserTransaction> getUserTransactionMap(int accountNumber, String shareName){
		List <UserTransaction> userTransactionList = new ArrayList <UserTransaction>();
		if(UserTransactionHashMap.userTransactionMap.containsKey(accountNumber)) {
			for(UserTransaction userTransaction: UserTransactionHashMap.userTransactionMap.get(accountNumber)) {
				if (shareName.equals(userTransaction.getshareName())) {
				userTransactionList.add(userTransaction);
				}
			}
		}
		return userTransactionList;
	}
	
	//Method to fetch a particular User's transactions of a date range from HashMap
	public static List <UserTransaction> getUserTransactionMap(int accountNumber, LocalDate startDate, LocalDate endDate){
		DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate transactionDate;
		List <UserTransaction> userTransactionList = new ArrayList <UserTransaction>();
		if(UserTransactionHashMap.userTransactionMap.containsKey(accountNumber)) {
			for(UserTransaction userTransaction: UserTransactionHashMap.userTransactionMap.get(accountNumber)) {
				transactionDate = (LocalDate.parse(userTransaction.getTransactionDate(), dateFormatter));
				if (transactionDate.isAfter(startDate) && transactionDate.isBefore(endDate) || transactionDate.isEqual(startDate) || transactionDate.isEqual(endDate)) {
				userTransactionList.add(userTransaction);
				}
			}
		}
		return userTransactionList;
	}
	
	// Method to get list of user Share Names
	public static List <UserTransaction> getUserTransactionList(int accountNumber){
		return UserTransactionHashMap.userTransactionMap.get(accountNumber);
	}
	
	//Method to update latest transaction to UserTransaction text file.
	public static void updateUserTransactionMap(UserTransaction newUserTransaction){
		if(UserTransactionHashMap.userTransactionMap.containsKey(newUserTransaction.getAccountNumber())) {
			UserTransactionHashMap.userTransactionMap.get(newUserTransaction.getAccountNumber()).add(newUserTransaction);			
		}
		else {
			UserTransactionHashMap.userTransactionMap.put(newUserTransaction.getAccountNumber(), new ArrayList <UserTransaction> ());
			UserTransactionHashMap.userTransactionMap.get(newUserTransaction.getAccountNumber()).add(newUserTransaction);
		}
		DatabaseMaintainer.addEntities(newUserTransaction.toString(), "UserTransaction");
	}
}
