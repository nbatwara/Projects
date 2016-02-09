package edu.iastate.cs228.hw2;

/**
 * Cheese has two properties: weight (represented by a double) and typeName
 * (represented by a String). In the Wallace and Gromit universe, there are only
 * three types of Cheese: Cheddar, Swiss, and Wensleydale
 * 
 * @author Neh Batwara
 *
 */

public class Cheese implements Comparable<Cheese> {
	private double weight, value;
	private String typeName;

	public Cheese(double weight, String typeName) {
		this.weight = weight;
		if (typeName != null) {
			if (typeName.equalsIgnoreCase("Cheddar")
					|| typeName.equalsIgnoreCase("Swiss")
					|| typeName.equalsIgnoreCase("Wensleydale")) {
				this.typeName = typeName;
			} else {
				throw new IllegalArgumentException("Cheese not of right type");
			}
		}
	}
	/**
	 * Getter method for getting weight of cheese.
	 * @return : weight
	 */
	public double getWeight() {
		return weight;
	}
	/**
	 * Getter method for getting weight type of cheese. 
	 * @return : Name of type of cheese. 
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * This method will compute the value of the Cheese with the given rules •
	 * If the typeName is Swiss, getValue will return 2.50*weight • If the
	 * typeName is Cheddar, getValue will return 9.50*weight • If the typeName
	 * is Wensleydale, getValue will return 13.75*weight
	 * 
	 * @return
	 */
	public double getValue() {
		if (this.typeName.equalsIgnoreCase("Cheddar")) {
			value = this.weight * 2.50;
		} else if (this.typeName.equalsIgnoreCase("Swiss")) {
			value = this.weight * 9.50;
		} else {
			value = this.weight * 13.75;
		}
		return value;
	}
	/**
	 * Returns string in the given format. 
	 */
	public String toString() {
		return typeName + " , " + weight + " , " + value;
	}
	/**
	 * compareTo method is implemented since the Cheese class implements the Comparable interface.
	 */
	@Override
	public int compareTo(Cheese objCheese) {

		if (value > objCheese.getValue()) {
			return 1;
		} else if (value < objCheese.getValue()) {
			return -1;
		} else {
			return 0;
		}
	}
}
