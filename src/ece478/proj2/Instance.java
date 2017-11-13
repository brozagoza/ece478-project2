package ece478.proj2;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class Instance {
	HashMap<String, AS> asses; // lol

	public Instance() {
		asses = new HashMap<>();
	}

	public void initialPopulate(String[] split) {
		AS as1 = null; // split[0] = peer1 or provider
		AS as2 = null; // split[1] = peer2 or customer

		if (asses.containsKey(split[0]))
			as1 = asses.get(split[0]);
		else {
			as1 = new AS(split[0]);
			asses.put(split[0], as1);
		}

		if (asses.containsKey(split[1]))
			as2 = asses.get(split[1]);
		else {
			as2 = new AS(split[1]);
			asses.put(split[1], as2);
		}

		if (split[2].equals("0")) { // peer to peer
			if (!as1.containsPeer(as2.getName())) {
				as1.addPeer(as2.getName()); // add to each peer's list
				as2.addPeer(as1.getName());
			}
		} else if (split[2].equals("-1")) { // provider to customer
			if (!as1.containsCustomer(as2.getName())) {
				as1.addCustomer(as2.getName());
				as2.incrementProviderCount();
			}
		} else {
			throw new IllegalArgumentException("This flag doesn't exist brutha");
		}
	} // end populate class

	public void routePopulate(String[] split) {
		AS as = null;

		if (asses.containsKey(split[2]))
			as = asses.get(split[2]);
		else {
			as = new AS(split[2]);
			asses.put(split[2], as);
		}

		as.incrementLengthCount(32 - Integer.parseInt(split[1]));
	} // end routePopulate

	public void printGraph2() {
		int[] graphBins = new int[6];

		for (Entry<String, AS> e : asses.entrySet()) {
			int nodeDegree = e.getValue().getDegree();

			if (nodeDegree < 1) {
			} else if (nodeDegree == 1)
				graphBins[0]++;
			else if (nodeDegree < 6)
				graphBins[1]++;
			else if (nodeDegree < 101)
				graphBins[2]++;
			else if (nodeDegree < 201)
				graphBins[3]++;
			else if (nodeDegree < 1001)
				graphBins[4]++;
			else
				graphBins[5]++;
		}

		System.out.println("**********Graph 2 Bins**********");
		System.out.println("Bin 1:\t\t" + graphBins[0]);
		System.out.println("Bin 2-5:\t\t" + graphBins[1]);
		System.out.println("Bin 5-100:\t" + graphBins[2]);
		System.out.println("Bin 100-200:\t" + graphBins[3]);
		System.out.println("Bin 200-1000:\t" + graphBins[4]);
		System.out.println("Bin > 1000:\t" + graphBins[5]);
		System.out.println();
	}

	public void printGraph3() {
		int binSize = 32, sum = 0;
		int[] graphBins = new int[binSize];

		for (Entry<String, AS> e : asses.entrySet()) {
			int[] prefixLengthCount = e.getValue().getPrefixLengthCounts();

			for (int i = 0; i < binSize; i++) {
				graphBins[i] += prefixLengthCount[i];
			}
		} // end entry for loop

		// Print
		System.out.println("**********Graph 3 Bins**********");
		for (int i = 0; i < binSize; i++) {
			System.out.println("Bin " + i + ":\t\t" + graphBins[i]);
			sum += graphBins[i];
		}
		System.out.println();
		System.out.println("Sum: " + sum);
	}

	public void printGraph4() {
		int enterCount = 0;
		int contentCount = 0;
		int transitCount = 0;
		int other = 0;

		for (Entry<String, AS> e : asses.entrySet()) {
			if (e.getValue().getDegree() <= 2
					&& (e.getValue().getPeerCount() == 0 || e.getValue().getCustomerCount() == 0))
				enterCount++;
			else if (e.getValue().getCustomerCount() == 0 && e.getValue().getPeerCount() >= 1)
				contentCount++;
			else if (e.getValue().getCustomerCount() >= 1)
				transitCount++;
			else
				other++;
		}

		System.out.println("**********Graph 4 Bins**********");
		System.out.println("Enterprise:\t" + enterCount);
		System.out.println("Content:\t\t" + contentCount);
		System.out.println("Transit:\t\t" + transitCount);
		System.out.println("Other:\t\t" + other);
		System.out.println();
	}

	public void inference() {
		TreeSet<AS> set = new TreeSet<>(new Comparator<AS>() {
			@Override
			public int compare(AS o1, AS o2) {
				if (o1.getDegree() > o2.getDegree())
					return -1;
				else
					return 1;
			}
		});

		for (Entry<String, AS> e : asses.entrySet())
			set.add(e.getValue());

		Set<AS> resultSet = new HashSet<>();
		AS as1 = set.pollFirst();
		resultSet.add(as1);
		
		while (true) {
			AS temp = set.pollFirst();
			if (connected(resultSet, temp))
				resultSet.add(temp);
			else
				break;
		}
		int i = 1;
		
		System.out.println("**********Inference Thing**********");
		System.out.println("Size:\t\t"+resultSet.size());
		for (AS as: resultSet) {
			System.out.println(i+":\t\t"+as.getName());
			i++;
			if (i > 10) break;
		}
		System.out.println();
		
	}

	public boolean connected(Set<AS> set, AS as1) {
		
		for (AS as2 : set)
			if (!(as1.containsCustomer(as2.getName()) || as1.containsPeer(as2.getName())
					|| as2.containsCustomer(as1.getName())))
				return false;

		return true;
	}
}
