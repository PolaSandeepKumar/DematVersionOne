package fileDatabase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.List;
import java.net.URISyntaxException;
import java.security.CodeSource;
/**
 * @author kddeepan
 *
 */
public class DatabaseMaintainer {
	
	static String binAddress;
	
	//Static block to fetch the bin address of the project
	static {
	//	binAddress = System.getProperty("java.class.path");
		
		CodeSource codeSource = DatabaseMaintainer.class.getProtectionDomain().getCodeSource();
		File jarFile;
		try {
			jarFile = new File(codeSource.getLocation().toURI().getPath());
			binAddress = jarFile.getParentFile().getPath();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Method to write entity details into the file
	public static void writeEntities(List <String> dataList, String entityType) {
		BufferedWriter myWriter;
		
		try {
			myWriter = new BufferedWriter(new FileWriter(binAddress+"\\"+entityType+".txt"));
			for(String data: dataList) {
				myWriter.write(data);
				myWriter.newLine();
			}
			myWriter.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Method to read entity details from the file
	public static List <String> readEntities(String entityType) {
		BufferedReader myReader;
		List <String> dataList = new ArrayList <String>();
		String data;
		try {
			myReader = new BufferedReader(new FileReader (binAddress+"\\"+entityType+".txt"));
			while((data = myReader.readLine())!=null) {
				dataList.add(data);
			}
			myReader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return dataList;
	}
	
	//Method to add details into the file upon creation of a new entity
	public static void addEntities(String entityData, String entityType) {
		BufferedWriter myWriter;
		try {
			myWriter = new BufferedWriter(new FileWriter (binAddress+"\\"+entityType+".txt", true));
			myWriter.write(entityData);
			myWriter.newLine();
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}