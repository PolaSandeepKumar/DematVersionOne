package dematAccountEntityHashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dematAccountEntities.ShareMarket;
import fileDatabase.DatabaseMaintainer;

/**
 * @author kddeepan
 *
 */
public class ShareMarketHashMap {
	
	private static Map<String, ShareMarket> shareMarketMap;

	//Static block to instantiate ShareMarketMap and fetch shareMarket from ShareMarket text file
	static {
		ShareMarketHashMap.shareMarketMap = new HashMap <String, ShareMarket>();
		ShareMarketHashMap.createMap(DatabaseMaintainer.readEntities("ShareMarket"));
	}
	
	//Private Constructor to avoid creation of object.	
	private ShareMarketHashMap() {
		
	}

	//Method to update the ShareMarket text file with latest information
	private static List <String> storeMap() {
		List <String> dataList = new ArrayList<String> ();
		for(String shareName : ShareMarketHashMap.shareMarketMap.keySet()) {
			dataList.add(ShareMarketHashMap.shareMarketMap.get(shareName).toString());
		}
		return dataList;
	}
	
	//Method to update ShareMarketMap with shareMarket from ShareMarket text file
	private static void createMap(List <String> dataList) {		
		for(String shareData:dataList) {
			String [] shareAttributes = shareData.split(",");
			ShareMarketHashMap.shareMarketMap.put(shareAttributes[0], new ShareMarket(shareAttributes[0],Double.parseDouble(shareAttributes[1]),Integer.parseInt(shareAttributes[2])));
		}		
	}
	
	//Method to fetch HashMap of ShareMarket
	public static Map <String, ShareMarket> getShareMarket(){
		return ShareMarketHashMap.shareMarketMap;
	}
	
	//Method to add new share to HashMap and ShareMarket text file
	public static void addShare(ShareMarket newShare) {
		ShareMarketHashMap.shareMarketMap.put(newShare.getShareName(),newShare);
		DatabaseMaintainer.addEntities(newShare.toString(), "ShareMarket");
	}
	
	//Method to edit existing share in HashMap and ShareMarket text file
	public static void editShare() {
		DatabaseMaintainer.writeEntities(ShareMarketHashMap.storeMap(), "ShareMarket");
	}
}