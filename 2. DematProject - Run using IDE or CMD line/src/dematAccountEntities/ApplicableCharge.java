package dematAccountEntities;

import java.util.ArrayList;
import java.util.List;

import fileDatabase.DatabaseMaintainer;

public class ApplicableCharge {
	
	private static double transactionChargeRate;
	private static double securitiesTransferTaxRate;
	
	//Private constructor to avoid creation of objects.
	private ApplicableCharge() {
		
	}
	
	//Static block to fetch applicable charge details from ApplicationText text file
	static {
		String [] applicableChargeDetails = (DatabaseMaintainer.readEntities("ApplicableCharge")).get(0).split(",");
		ApplicableCharge.transactionChargeRate = Double.parseDouble(applicableChargeDetails[0]);
		ApplicableCharge.securitiesTransferTaxRate = Double.parseDouble(applicableChargeDetails[1]);
	}
	
	//Getter method for Transaction charge
	public static double getTransactionChargeRate() {
		
		return ApplicableCharge.transactionChargeRate;
	}

	//Getter method for STT charge
	public static double getSecuritiesTransferTaxRate() {		
		return ApplicableCharge.securitiesTransferTaxRate;
	}

	//Setter method for Transaction charge
	public static void setTransactionChargeRate(double transactionChargeRate) {
		ApplicableCharge.transactionChargeRate = transactionChargeRate;
		List <String> charges = new ArrayList<String> ();
		charges.add(ApplicableCharge.transactionChargeRate+","+ApplicableCharge.securitiesTransferTaxRate);
		DatabaseMaintainer.writeEntities(charges,"ApplicableCharge");
	}
	
	//Setter method for STT charge
	public static void setSecuritiesTransferTaxRate(double securitiesTransferTaxRate) {
		ApplicableCharge.securitiesTransferTaxRate = securitiesTransferTaxRate;
		List <String> charges = new ArrayList<String> ();
		charges.add(ApplicableCharge.transactionChargeRate+","+ApplicableCharge.securitiesTransferTaxRate);
		DatabaseMaintainer.writeEntities(charges,"ApplicableCharge");
	}
}