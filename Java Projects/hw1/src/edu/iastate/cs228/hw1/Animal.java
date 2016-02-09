/**
 * 
 */
package edu.iastate.cs228.hw1;

/**
 * @author Neh Batwara
 * Abstract class for animal. It is an abstract class that implements comparable. 
 */
public abstract class Animal implements Comparable<Animal> {
	/**
	 * Protected variable for animal name.
	 */
	protected String name;
	/**
	 * Protected variable for animal health. 
	 */
	protected int health;
	/**
	 * Getter method for getting animal name. 
	 * @return animal name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Equals method check the equality of two animal objects. Without this method if you try to compare two animal objects it would return true only if both objects have the same memory address,which is not 
	 * possible until it for the same animal object. 
	 * @param obj
	 * @return true if the given object is equal to the animal object being compared with. 
	 */
	@Override
	public boolean equals(Object obj){
		if(obj instanceof Animal){
			Animal objAnimal = (Animal) obj;
			if ( this.getName().equals(objAnimal.getName())
					&& this.getHealth() == objAnimal.getHealth()){
				return true;
			}else{
				return false;
			}
		}else{
			return false;
		}
	}
	/**
	 * Since the animal class implements comparable, it should implement the compareTo method. 
	 * @param objAnimal
	 * @return 1 if health of the first animal object is greater than the second, -1 if the first's health is lesser than second
	 * and 0 if they are equal. 
	 */
	@Override
	public int compareTo(Animal objAnimal) {
		// TODO Auto-generated method stub
		if(this.getHealth() > objAnimal.getHealth()){
			return 1;
		}
		else if( this.getHealth() < objAnimal.getHealth()){
			return -1;
		}
		else {
			return 0;
		}
	}
	/**
	 * Setter for setting animal name. 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * Getter method get health of animal object. 
	 * @return health
	 */
	public int getHealth() {
		return health;
	}
	/**
	 * Setter method for setting health of animal. 
	 * @param health
	 */
	public void setHealth(int health) {
		this.health = health;
	}
	/**
	 * the toString method return a string of animal and health in given format. 
	 * @return
	 */
	@Override
	public String toString() {
		return name + " , " + health;
	}
	/**
	 * abstract method for interacting animals.
	 * @param a
	 */
	public abstract void interact( Animal a);
	/**
	 * Constructor for animal class. 	
	 * @param name
	 * @param health
	 */
	public Animal( String name,  int health ){
		this.name = name;
		this.health = health;
		
	}

}
