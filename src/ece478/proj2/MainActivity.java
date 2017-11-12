package ece478.proj2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class MainActivity {
	public static void main(String[] args) {

		Instance instance = new Instance();

		try {
			File file = new File("relationships.txt"); 	// relationships
			File file2 = new File("routeviews.txt"); 	// routeViews
			
			Scanner scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] split = line.split("\\|");

				instance.populate(split);
			}

			scanner = new Scanner(file2);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	} // end main

}
