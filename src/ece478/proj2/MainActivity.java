package ece478.proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MainActivity {

	private class AS {
		String name;
		int peerCount;
		int customerCount;
		int providerCount;
		int [] prefixLengthCount;
		HashSet<String> peerList;
		HashSet<String> customerList;
		
		public AS(String name) {
			this.name = name;
			peerCount = 0;
			customerCount = 0;
			providerCount = 0;
			prefixLengthCount = new int[32];
			peerList = new HashSet<String>();
			customerList = new HashSet<String>();
		}
	} // end AS class
	HashMap<String, AS> asses = new HashMap<>();
	
	public void populate(String[] split) {
		AS as1 = null;
		AS as2 = null;
		
		if (asses.containsKey(split[0]))
			as1 = asses.get(split[0]);
		else
			as1 = new AS(split[0]);
		
		if (asses.containsKey(split[1]))
			as2 = asses.get(split[1]);
		else
			as2 = new AS(split[1]);
		
		if (split[2].equals("0")) {
			if (!as1.peerList.contains(split[1])) {
				as1.peerList.add(split[1]);
				as2.peerList.add(split[0]);
				
				as1.peerCount++;
				as2.peerCount++;
			}
			
		} else {
			if (!as1.customerList.contains(split[1])) {
				as1.customerList.add(split[1]);
				as1.customerCount++;
				as2.providerCount++;
			}
		}
	} // end populate class
	
	
	
	public static void main (String [] args) {
		
		MainActivity instance = new MainActivity();
		
		try {
			File file = new File("relationships.txt");	// relationships
			File file2 = new File("routeviews.txt");	// routeViews
			//PrintWriter writer = new PrintWriter("out.txt", "UTF-8");
			Scanner scanner = new Scanner(file);
			
			while (scanner.hasNextLine()) {
				/////// do work
				String line = scanner.nextLine();
				String[] split = line.split("\\|");
				
				instance.populate(split);
				
			}
			
			scanner = new Scanner(file2);
			
			
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
		   // do something
		}
		
		
		
	}
	
}
