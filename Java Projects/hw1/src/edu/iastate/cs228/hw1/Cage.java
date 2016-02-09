/**
 * 
 */
package edu.iastate.cs228.hw1;

import java.util.Arrays;

/**
 * @author Neh Batwara A cage is a generic class that can only contain something
 *         of type Animal or things that inherit from Animal
 */
public class Cage<T> {
	/**
	 * Array which will contain objects of type Animal or from a class of object
	 * that inherits from Animal class.
	 */
	T[] arrayOfAnimals = null;
	/**
	 * int instance variable for number of animals.
	 */
	private int numAnimals = 0;

	/**
	 * Constructor for the cage class. Throws a Cage Exception if the capacity
	 * of the cage is set to zero or lesser.
	 * 
	 * @param numberOfAnimals
	 * @throws CageException
	 */
	public Cage(int numberOfAnimals) throws CageException {
		if (numberOfAnimals <= 0) {
			throw new CageException(
					"number of animals must be greater than zero");
		} else {
			arrayOfAnimals = (T[]) new Animal[numberOfAnimals];
		}
	}

	/**
	 * 
	 * @return returns array of animals.
	 */
	public Animal[] getOccupants() {
		return (Animal[]) arrayOfAnimals;
	}

	/**
	 * 
	 * @return returns length of array of animals.
	 */
	public int getCapacity() {
		return arrayOfAnimals.length;
	}

	/**
	 * This method adds an Animal to your array of Animals in the rst available
	 * cell. There are two errors that can happen here. If an Animal already has
	 * the same name, throw a new CageException with an appropriate error
	 * message. If adding this Animal will exceed the Cage's capacity, throw a
	 * new CageException with an appropriate error message.
	 * 
	 * @param animal
	 * @throws CageException
	 */
	public void add(T animal) throws CageException {
		String newAnimalName = ((Animal) animal).getName();
		int availableCell = -1;
		for (int i = 0; i < getCapacity(); i++) {
			if (arrayOfAnimals[i] == null) {
				availableCell = i;
				continue;
			} else {
				String existingAnimalName = ((Animal) arrayOfAnimals[i])
						.getName();

				if (existingAnimalName.equals(newAnimalName)) {
					throw new CageException("Animal with name exists");
				} else {

				}
			}
		}
		if (availableCell == -1) {
			throw new CageException(" Capcity exceeded ");
		} else {
			arrayOfAnimals[availableCell] = animal;
		}
	}

	/**
	 * This method will return the array of Animal's toString representations in
	 * sorted order. This sorting order is based o of an Animal's health (an
	 * Animal with lower health will come before an Animal with higher health).
	 * 
	 * @return returns sorted array of animals in increasing order of health.
	 */
	public String[] listAnimalsSorted() {
		int totalNoOfAnimals = 0;
		for (int i = 0; i < getCapacity(); i++) {
			if (arrayOfAnimals[i] != null) {
				totalNoOfAnimals++;
			} else {

			}
		}
		Animal[] fullArrayOfAnimals = new Animal[totalNoOfAnimals];
		int animalIndex = 0;
		for (int i = 0; i < getCapacity(); i++) {
			if (arrayOfAnimals[i] != null) {
				fullArrayOfAnimals[animalIndex++] = (Animal) arrayOfAnimals[i];
			} else {
				// do nothing here.
			}
		}
		Arrays.sort(fullArrayOfAnimals);
		String[] sortedAnimals = new String[fullArrayOfAnimals.length];
		for (int i = 0; i < fullArrayOfAnimals.length; i++) {
			sortedAnimals[i] = fullArrayOfAnimals[i].toString();
		}
		return sortedAnimals;
	}

	/**
	 * This method removes an Animal from your array of Animals. Remove the
	 * Animal with the same name as the given string. When an animal is removed,
	 * the cells WILL NOT be shifted over. If the given animal does not exist in
	 * the Cage, throw a new CageException with an appropriate error message.
	 * 
	 * @param animalName
	 * @throws CageException
	 */
	public void remove(String animalName) throws CageException {
		for (int i = 0; i < getCapacity(); i++) {
			if (arrayOfAnimals[i] != null) {
				String existingAnimalName = ((Animal) arrayOfAnimals[i])
						.getName();
				if (existingAnimalName.equals(animalName)) {
					arrayOfAnimals[i] = null;
					return;
				}

			}

		}
		throw new CageException("Animal does not exist");
	}

	/**
	 * This method simulates one round of interaction amongst Animals based o
	 * of the following rules.  Every Animal interacts with the other Animals
	 * except itself  That is: given Animal A, Animal B, and Animal C 1. Animal
	 * A will interact with Animal B and Animal C 2. Animal B will interact with
	 * Animal A and Animal C 3. Animal C will interact with Animal A and Animal
	 * B  Immediately after an interaction, if an Animal's health is below or
	 * equal to 0, that Animal will be removed from the Cage (don't worry, the
	 * zookeepers are just removing it for precautionary reasons).
	 */
	public void simulate() {
		for (int i = 0; i < getCapacity(); i++) {
			if (arrayOfAnimals[i] == null) {
				continue;
			}
			Animal interactingAnimal = (Animal) arrayOfAnimals[i];
			for (int j = 0; j < getCapacity(); j++) {
				if (arrayOfAnimals[j] == null || i == j) {
					continue;
				} else {
					// do nothing here. go ahead
				}
				interactingAnimal.interact((Animal) arrayOfAnimals[j]);
			}
		}

		for (int i = 0; i < getCapacity(); i++) {
			if (arrayOfAnimals[i] == null) {
				continue;
			}
			Animal objAnimal = (Animal) arrayOfAnimals[i];
			if (objAnimal.getHealth() <= 0) {
				try {
					remove(objAnimal.getName());
				} catch (CageException e) {
					// code should never reach here.
					// System.out.println("WARNING : EXCEPTION OCCURED : "
					// +e.getMessage());
				}
			}
		}

	}

}
