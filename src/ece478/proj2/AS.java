package ece478.proj2;

import java.util.HashSet;

public class AS {
	String name;
	int peerCount;
	int customerCount;
	int providerCount;
	int [] prefixLengthCount;	// counts of specific prefixLengths at each size 0 - 32
	HashSet<String> peerList;
	HashSet<String> customerList;
	
	public AS(String name) {
		this.name = name;
		peerCount = 0;
		customerCount = 0;
		providerCount = 0;	// probably not neccessary
		prefixLengthCount = new int[32];
		peerList = new HashSet<String>();
		customerList = new HashSet<String>();
	}
} // end AS class
