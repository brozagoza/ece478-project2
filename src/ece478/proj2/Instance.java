package ece478.proj2;

import java.util.HashMap;

public class Instance {
	HashMap<String, AS> asses;	// lol
	
	public Instance() {
		asses = new HashMap<>();
	}

	public void populate(String[] split) {
		AS as1 = null; // split[0]
		AS as2 = null; // split[1]

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
}
