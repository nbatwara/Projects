package edu.iastate.cs228.hw2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.Scanner;
import java.util.regex.Pattern;

public class SortingClient {
	
	private final static String USAGE = "USAGE : java SortingClient <fileName1> <fileName2> ...";
	
	/**
	 * Main method of Sorting Client. 
	 * @param args File names containing the cheese information that has to be sorted
	 */
	public static void main(String[] args) {
		//If args are null or does not have any data. print usage and throw exception
		if(args == null || args.length <= 0){
			System.out.println(USAGE);
			throw new IllegalArgumentException("Null or no file names as arguments");
		}else{
			//do nothing here. Continue
		}
		//Iterate over all the arguments
		for(String fileName : args){
			File inputFile = new File(fileName);
			if(!inputFile.exists()){
				System.out.println("File : " +fileName +" does not exist. It will not be processed");
				continue;
			}else{
				//do nothing here. continue
			}
			
			//Reading file line by line and processing
			System.out.println("Reading file at : " +inputFile.getAbsolutePath());
			try {
				Scanner fileScanner = new Scanner(inputFile);
				int numOfLines = 0;
				while(fileScanner.hasNextLine()){
					numOfLines++;
					fileScanner.nextLine();
				}
				System.out.println("Number of lines : " +numOfLines);
				
				//Resetting scanner to initial position
				fileScanner.reset();
				fileScanner = new Scanner(inputFile);
				//Initializing array of pairs
				Pair<Cheese, Cheese>[] pairs = (Pair<Cheese, Cheese>[]) new Pair<? , ?>[numOfLines];
				int pairIter = 0;
				while(fileScanner.hasNextLine()){
					String line = fileScanner.nextLine();
					String[] arrCheeseObjData = line.split(Pattern.quote("|"));
					if(arrCheeseObjData.length != 2){
						System.out.println("File not in correct format. Expected : CheeseTypeName1, Weight1 | CheeseTypeNam2, Weight2. Program will terminate.");
						return;
					}else{
						//do nothing here. Go ahead.
					}
					Cheese[] arrCheese = new Cheese[2];
					int iter = 0;
					for(String cheeseObjData : arrCheeseObjData){
						String[] arrCheeseInfo = cheeseObjData.split(Pattern.quote(","));
						if(arrCheeseInfo.length != 2){
							System.out.println("File not in correct format. Expected : CheeseTypeName1, Weight1 | CheeseTypeNam2, Weight2. Program will terminate.");
							return;
						}else{
							//do nothing here. go ahead.
						}
						String typeName = arrCheeseInfo[0];
						double weight = Double.parseDouble(arrCheeseInfo[1]);
						arrCheese[iter++] = new Cheese(weight,typeName);
					}
					pairs[pairIter++] = new Pair<Cheese, Cheese>(arrCheese[0], arrCheese[1]);
				}
				// Got the cheese data processed as pairs
				
				//Creating comparator based on cheese's normal compare method
				Comparator<Pair<Cheese, Cheese>> cp1 = new Comparator<Pair<Cheese, Cheese>>() {
					@Override
					public int compare(Pair<Cheese,Cheese> obj1, Pair<Cheese,Cheese> obj2){
						//Same as cheese's compare method
						return obj1.compareTo(obj2);
					}
				};
				
				// Creating second comparator as anonymous class
				Comparator<Pair<Cheese, Cheese>> cp2 = new Comparator<Pair<Cheese, Cheese>>() {
					@Override
					public int compare(Pair<Cheese,Cheese> obj1, Pair<Cheese,Cheese> obj2){
						if(obj1.getFirstElement().getWeight() < obj2.getFirstElement().getWeight()){
							return -1;
						}else if (obj1.getFirstElement().getWeight() > obj2.getFirstElement().getWeight()){
							return 1;
						}else{
							return obj1.compareTo(obj2);
						}
					}
				};
				
				//Place holder for the array on which actual sorting would be performed
				Pair<Cheese, Cheese>[] sortArr = null;
				
				//Selection Sort with Comparator - 1
				//Copying the array
				sortArr = copy(pairs);
				long numComparisons = Sort.<Pair<Cheese, Cheese>>selectionSort(sortArr, cp1);
				String outString = "Number of Comparisons : " +numComparisons +"\n";
				outString += getFormattedString(sortArr);
				writeToFile("SelectionSortValue_" +fileName, outString);
				
				
				sortArr = copy(pairs);
				numComparisons = Sort.<Pair<Cheese, Cheese>>insertionSort(sortArr, cp1);
				outString = "Number of Comparisons : " +numComparisons +"\n";
				outString += getFormattedString(sortArr);
				writeToFile("InsertionSortValue_" +fileName, outString);
				
				sortArr = copy(pairs);
				numComparisons = Sort.<Pair<Cheese, Cheese>>quickSort(sortArr, cp1, "first");
				outString = "Number of Comparisons : " +numComparisons +"\n";
				outString += getFormattedString(sortArr);
				writeToFile("QuickSort_First_Value_" +fileName, outString);
				
				sortArr = copy(pairs);
				numComparisons = Sort.<Pair<Cheese, Cheese>>quickSort(sortArr, cp1, "median");
				outString = "Number of Comparisons : " +numComparisons +"\n";
				outString += getFormattedString(sortArr);
				writeToFile("QuickSort_Median_Value_" +fileName, outString);
				
				sortArr = copy(pairs);
				numComparisons = Sort.<Pair<Cheese, Cheese>>selectionSort(sortArr, cp2);
				outString = "Number of Comparisons : " +numComparisons +"\n";
				outString += getFormattedString(sortArr);
				writeToFile("SelectionSortWeight_" +fileName, outString);
				
				
				sortArr = copy(pairs);
				numComparisons = Sort.<Pair<Cheese, Cheese>>insertionSort(sortArr, cp2);
				outString = "Number of Comparisons : " +numComparisons +"\n";
				outString += getFormattedString(sortArr);
				writeToFile("InsertionSortWeight_" +fileName, outString);
				
				sortArr = copy(pairs);
				numComparisons = Sort.<Pair<Cheese, Cheese>>quickSort(sortArr, cp2, "first");
				outString = "Number of Comparisons : " +numComparisons +"\n";
				outString += getFormattedString(sortArr);
				writeToFile("QuickSort_First_Weight_" +fileName, outString);
				
				sortArr = copy(pairs);
				numComparisons = Sort.<Pair<Cheese, Cheese>>quickSort(sortArr, cp2, "median");
				outString = "Number of Comparisons : " +numComparisons +"\n";
				outString += getFormattedString(sortArr);
				writeToFile("QuickSort_Median_Weight_" +fileName, outString);
				
			} catch (FileNotFoundException e) {
				System.out.println("File : " +inputFile.getAbsolutePath() +"not found.");
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
	
	/**
	 * Get formatted string of a given array of pair objects. Used specifically 
	 * for formatting of pair objects 
	 * @param sortedArr the array for which the formatted string has to be generated
	 * @return the formatted toString() output of the array as per specifications
	 */
	private static String getFormattedString(Pair<Cheese,Cheese>[] sortedArr){
		StringBuilder strBuilder = new StringBuilder();
		for(Pair<Cheese,Cheese> pair : sortedArr){
			strBuilder.append(pair.getFirstElement().toString() +" , " +pair.getSecondElement().toString() +"\n");
		}
		return strBuilder.toString();
	}
	
	/**
	 * write text to a file
	 * @param fileName path of the file where the text has to be written
	 * @param outputText the content that has to be written
	 */
	private static void writeToFile(String fileName, String outputText){
		try {
			PrintWriter objWriter = new PrintWriter(fileName);
			objWriter.write(outputText);
			objWriter.close();
		} catch (Exception e) {
			//Since this method is private and called internally
			// need not add specific exception handling. as it wud never occur
			e.printStackTrace();
		}
	}
	
	/**
	 * Creates copy of a given array
	 * @param inputArr the array whose copy has to be obtained
	 * @return copy of given array
	 */
	private static Pair<Cheese, Cheese>[] copy(Pair<Cheese, Cheese>[] inputArr){
		//Create a new array of the same length to copy the objects into
		Pair<Cheese, Cheese>[] retArr = (Pair<Cheese, Cheese>[]) new Pair<?, ?>[inputArr.length];
		int pairIter = 0;
		for(Pair<Cheese, Cheese> pair : inputArr){
			retArr[pairIter++] = pair;
		}
		return retArr;
	}

}
