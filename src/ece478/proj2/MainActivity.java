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
		Scanner scanner = null;

		try {
			File file = new File("relationships_09_01.txt"); // relationships
			File file2 = new File("routeviews_11_05.txt"); // routeViews

			scanner = new Scanner(file);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] split = line.split("\\|");

				instance.initialPopulate(split);
			}

			scanner = new Scanner(file2);

			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				String[] split = line.split("\\s+");

				instance.routePopulate(split);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		instance.printGraph2();
		instance.printGraph3();
		instance.printGraph4();
		instance.inference();
	} // end main

}
