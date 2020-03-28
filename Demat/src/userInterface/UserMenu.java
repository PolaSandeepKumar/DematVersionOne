package userInterface;

import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import dematAccountEntities.ShareMarket;
import dematAccountEntities.ApplicableCharge;
import dematAccountEntities.UserData;
import dematAccountEntityHashMap.ShareMarketHashMap;
import dematAccountEntityHashMap.UserDataHashMap;
import userFunctions.AdminFunctions;
import userFunctions.CustomerFunctions;


public class UserMenu {

	//Method to show  menu options for first page
	public static int applicationLaunch() {
		int choice;
		Scanner userInput = new Scanner(System.in);
		do {
			// Starting Menu
			System.out.println("DEMAT Trading Account Manager");
			System.out.println("=============================");
			System.out.println("1 - Create Account");
			System.out.println("2 - Login");
			System.out.println("3 - Quit");
			System.out.println("=============================");
			System.out.println("Enter valid choice either 1 or 2");	
			// Get choice from user
			choice = userInput.nextInt();
		} while (!(choice == 1 || choice == 2));
		return choice;
	}
	
	//Method to show Customer menu options
	public static int customerMainMenu(){
		Scanner userInput = new Scanner(System.in);
		System.out.println("Main Menu Options");
		System.out.println("==================");
		System.out.println("0 - Quit");
		System.out.println("1 - Display Account details");
		System.out.println("2 - Deposit Money");
		System.out.println("3 - Withdraw Money");
		System.out.println("4 - Buy Share");
		System.out.println("5 - Sell Share");
		System.out.println("6 - View Transaction report");
		System.out.println("Enter a number between 0 to 6 : " );
		int choice = userInput.nextInt();
		return choice;
	}
	
	//Method to show options when Admin login
	public static int adminFirstPage(){
		Scanner inputone = new Scanner(System.in);
		System.out.println("Admin Menu Options");
		System.out.println("==================");
		System.out.println("0 - Quit");
		System.out.println("1 - Add Shares");
		System.out.println("2 - Update Share price");
		System.out.println("3 - Update Share quantity");
		System.out.println("4 - Update Transaction Charge ");
		System.out.println("5 - Update STT");
		System.out.println("6 - Display all available shares");
		System.out.println("7 - Display STT and transaction charge");
		System.out.println("Enter Option : ");
		int adminchoice = inputone.nextInt();
		return adminchoice;
	}
	
	// Method used to display different Main Menu options after login for admin &
	// user respectively
	public static void loginChoicePage(int choice) {
		 Scanner userInput = new Scanner(System.in);

		switch (choice) {
		case 1:
			UserMenu.createAccount();
			break;
		case 2:
			int userAccountNumber = 0;
			String inputPassword;
			do {
				String adminPassword = "admin";
				boolean wrongtypeAccountNumber = false;
				do {
					try {
						wrongtypeAccountNumber = false;
						System.out.println("Enter Account number : ");
						userAccountNumber = userInput.nextInt();
					} catch (InputMismatchException except) {
						System.out.println("Invalid Account Number type is entered, enter integer type account number");
						userInput = new Scanner(System.in);
						wrongtypeAccountNumber = true;
					}
				} while (wrongtypeAccountNumber);
				
				System.out.println("Enter Password : ");
				inputPassword = userInput.next();
				// Check if admin is logged in or the user to provided appropriate menu options
				if (userAccountNumber == 12345678 && inputPassword.equals(adminPassword)) {
					// Admin menu options are provided
					boolean showAdminPage = false;	
	
					do{
						int adminchoice = UserMenu.adminFirstPage();
					
					
						switch(adminchoice)
							{
							case 0:
								int quitchoice = UserMenu.applicationLaunch();
								loginChoicePage(quitchoice);
								userAccountNumber = 0;
								inputPassword = "null";
						
							case 1:
								AdminFunctions.addShare();
								break;
							case 2:
								AdminFunctions.updateSharePrice();
								break;
							case 3:
								AdminFunctions.updateShareQuantity();
								break;
							case 4:
								AdminFunctions.updateTransactionCharge();
								break;
							case 5:
								AdminFunctions.updateSTTCharge();
								break;
							case 6:
								Map<String, ShareMarket> availableShares = ShareMarketHashMap.getShareMarket();
								CustomerFunctions.displayShareMarket(availableShares);
								break;
							case 7:
								System.out.println('\n'+"Current STT charge is " +ApplicableCharge.getSecuritiesTransferTaxRate());
								System.out.println("Current Transaction charge is " +ApplicableCharge.getTransactionChargeRate() + '\n');
							}
					}while(true);
					
				}	
			
			}while(!UserDataHashMap.validateLogin(userAccountNumber,inputPassword));
					
					do {
						int choice2 = UserMenu.customerMainMenu();
						switch(choice2)
						{
						case 0:
								System.out.println("You have quit the session"); 
								int quitchoice = UserMenu.applicationLaunch();
								loginChoicePage(quitchoice);
								userAccountNumber = 100000;
								inputPassword = "Sandu";						
						case 1:
							CustomerFunctions.displayAcountDetails(userAccountNumber);
							break;
						case 2:
							CustomerFunctions.depositMoney(userAccountNumber);
							break;
						case 3:
							CustomerFunctions.withdrawMoney(userAccountNumber);
							break;
						case 4:
							CustomerFunctions.buyShares(userAccountNumber);
							break;
						case 5:
							CustomerFunctions.sellShares(userAccountNumber);
							break;
						case 6:
							CustomerFunctions.viewTransactionReport(userAccountNumber);
							break;
						}
				}while(true);
		case 3:
			System.out.println("Thank you for using our application, you will be missed :(. Your session is quit now.");
			System.exit(0);
}
}

	//Method to create a new customer account
	public static void createAccount(){
		Scanner userInput = new Scanner(System.in);

		System.out.println("Enter below details");
		System.out.println("Enter Username : ");
		String username = userInput.next();
		String password;
		String passwordMatch;
		// Checks for password match
		do {
			System.out.println("Enter Password : ");
			password = userInput.next();
			System.out.println("Re-enter Password : ");
			passwordMatch = userInput.next();
		} while (!password.equals(passwordMatch));

		UserData newUser = new UserData(username, password);
		UserDataHashMap.addUser(newUser);

		System.out.println("Account created successfully, Your Account number is : " + newUser.getAccountNumber());
		int quitchoice1 = UserMenu.applicationLaunch();
		loginChoicePage(quitchoice1);
	}
	
}
