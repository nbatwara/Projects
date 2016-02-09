package edu.iastate.cs228.hw1;

import java.util.Scanner;

/**
 * 
 */

/**
 * @author Neh Batwara
 * this class sorts and simulates the state of a Cage.
 */
public class CageClient {

	/**
	 * Scanner is used to ask user to input capacity of cage. After user enter valid capacity input it is prompted to enter animals in valiid format. 
	 * After entering animals in valid format, user can press "s" key to begin simulation.Finally a sorted array of animals is printed after simlation. 
	 * @param args
	 * @throws CageException
	 */
	public static void main(String[] args) throws CageException {
		Cage<Animal> objCage = null;
		Scanner scan = new Scanner(System.in);
		/*
		 * STays in the while loop till the user enters valid value for cage capcity.
		 */
		while (true) {
			System.out.print("Enter the size of animal cage :");
			int capacity = scan.nextInt();
			if (capacity < 1) {
				System.out.println("Enter a number greater than 1");
				continue;
			}
			objCage = new Cage<Animal>(capacity);
			break;
		}
		/*
		 * If user enters valid cage capacity value then it is directed to enter the animals in the specific format which comes from the static getHelpText method. 
		 */
		System.out.println(getHelpText());
		boolean isFirstTime = true;
		while (true) {

			if (isFirstTime) {
				isFirstTime = false;
			} else {
				System.out.println("Enter 's' to simulate. Enter any other character to enter more animals");
				String inputStr = scan.next();
				if (inputStr.equalsIgnoreCase("s")) {
					break;
				} else {
					// do nothing here. go ahead
				}
			}
			System.out.print("Enter Animal Details : ");
			String inputStr = scan.next();
			Animal objAnimal = parseInputString(inputStr);
			if (objAnimal == null) {
				System.out.println("Please enter a valid string.");
				continue;
			} else {
				// do nothing here. Go ahead.
			}
			try {
				objCage.add(objAnimal);
			} catch (Exception e) {
				System.out.println("Error adding animal to cage : "
						+ e.getMessage());
				continue;
			}

		}
		objCage.simulate();
		System.out
				.println("Simulation Results (animals in increasing order of health): ");
		for (String str : objCage.listAnimalsSorted()) {
			System.out.println(str);
		}

	}
	/**
	 * Method for parsing string input.
	 * @param str
	 * @return
	 */
	private static Animal parseInputString(String str) {
		String[] arrTokens = str.split(",");
		if (arrTokens.length != 3) {
			System.out.println("Error : Invalid format");
			return null;
		} else {
			// do nothing here. Go ahead
		}

		String type = arrTokens[0];
		if (type.length() != 1) {
			System.out.println("Error : Invalid Type");
			return null;
		} else {
			char typeChar = type.charAt(0);
			int health = -1;
			try {
				health = Integer.parseInt(arrTokens[2]);
			} catch (NumberFormatException e) {
				System.out.println("Error : " + e.getMessage());
				return null;
			}
			switch (typeChar) {
			case 't':
			case 'T':
				return new Tiger(arrTokens[1], health);
			case 'b':
			case 'B':
				return new Bengal(arrTokens[1], health);
			case 's':
			case 'S':
				return new Sheep(arrTokens[1], health);
			default:
				return null;

			}
		}
	}
	/**
	 * Prompts the user to enter an animal in specific format. A valid input would be - T,Tiger,20 , where 
	 * T is the type of animal object( Tiger in this case), Tiger is the name with health = 20.
	 * @return 
	 */
	private static String getHelpText() {
		return "Enter Animal information in the following format : "
				+ "<type of animal>,name,health\n" + "\t Valid Types are : \n"
				+ "\t\t T : Tiger\n" + "\t\t B : Bengal\n" + "\t\t S : Sheep\n"
				+ "\nEnter 's' anytime to begin simulation";
	}

}
