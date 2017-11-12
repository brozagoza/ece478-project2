package ece478.proj2;

import java.util.HashSet;

public class AS {
	private String name;
	private int providerCount;
	private int [] prefixLengthCount;	// counts of specific prefixLengths at each size 0 - 32
	private HashSet<String> peerSet;
	private HashSet<String> customerSet;
	
	public AS(String name) {
		this.name = name;
		providerCount = 0;	// probably not neccessary
		prefixLengthCount = new int[32];
		peerSet = new HashSet<String>();
		customerSet = new HashSet<String>();
	}
	
	public String getName() {
		return name;
	}
	
	public void addPeer(String s) {
		peerSet.add(s);
	}
	
	public void addCustomer(String s) {
		customerSet.add(s);
	}
	
	public boolean containsPeer(String s) {
		return peerSet.contains(s);
	}
	
	public boolean containsCustomer(String s) {
		return customerSet.contains(s);
	}
	
	public int getPeerCount() {
		return peerSet.size();
	}
	
	public int getCustomerCount() {
		return customerSet.size();
	}
	
	public void incrementLengthCount(int length) {
		prefixLengthCount[length]++;
	}
	
	public void incrementProviderCount() {
		providerCount++;
	}
	
	public int getDegree() {
		return providerCount + peerSet.size() + customerSet.size();
	}
	
	public int[] getPrefixLengthCounts() {
		return prefixLengthCount;
	}
	
} // end AS class
