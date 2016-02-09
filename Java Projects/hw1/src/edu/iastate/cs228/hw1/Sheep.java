/**
 * 
 */
package edu.iastate.cs228.hw1;

/**
 * @author Neh Batwara
 * Sheep class, extends animal class. 
 */
public class Sheep extends Animal {
	/**
	 * Contructor for the Sheep class which extends the animal class. 
	 * @param name
	 * @param health
	 */
	public Sheep(String name, int health) {
		super(name, health);
		
	}

	/* (non-Javadoc)
	 * @see edu.iastate.cs228.hw1.Animal#interact(edu.iastate.cs228.hw1.Animal)
	 */
	/**
	 * Overriding the interact method from the animal class. When an object of Sheep interacts with another animal object it print out " Baaaaah".
	 */
	@Override
	public void interact(Animal a) {
		System.out.println("Baaaaah");
		
	}

}
