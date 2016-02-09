package edu.iastate.cs228.hw4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class OrderedMapClient {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter filename to input: ");  // should be universities.csv for main test
		String fileName = in.nextLine();
		File file = new File(fileName);
		
		
		Scanner fileScanner = new Scanner(file);
		OrderedMap<String, String> map = new OrderedMap<String, String>();
		while (fileScanner.hasNext()){
			String line = fileScanner.nextLine();
			Scanner lineScan = new Scanner(line);
			lineScan.useDelimiter(",");
			String key = lineScan.next();
			String value = lineScan.next();
			map.put(key, value);
			lineScan.close();
		}
		fileScanner.close();
		
		System.out.println("Data read from file into ordered map.");
		
		EvenOddVisitor<String> v = new EvenOddVisitor<String>();
		map.inOrderTraversal(v);
		System.out.println("Number of even IDs in map:" + v.getNumEvens());
		System.out.println("Number of odd IDs in map:"+ v.getNumOdds());
		
		System.out.print("Enter output filename:");
		String outputFileName = in.nextLine();
		PrintWriter outputFile = new PrintWriter(outputFileName);
		in.close();
		
		// write contents of ordered map to output file
		
		ArrayList<String> keys = map.keysInAscendingOrder();
		for (int i= 0 ; i < keys.size(); i++)
		{
			outputFile.print(keys.get(i));
			outputFile.print(",");
			outputFile.println(map.get(keys.get(i)));
		}
		outputFile.close();
	}

}