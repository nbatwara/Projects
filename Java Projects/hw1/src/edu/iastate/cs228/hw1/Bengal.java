/**
 * 
 */
package edu.iastate.cs228.hw1;

/**
 * @author Neh Batwara
 *Bengal class which extends Tiger class. 
 */
public class Bengal extends Tiger {
	/**
	 * Constructor for Bengal class which extends Tiger. 
	 * @param name
	 * @param health
	 */
	public Bengal(String name, int health) {
		super(name, health);
		
	}
	
	 
	/* (non-Javadoc)
	 * @see edu.iastate.cs228.hw1.Animal#interact(edu.iastate.cs228.hw1.Animal)
	 */
	/**
	 * Overriding the abstract interact method from the Animal class. 
	 * When a Bengal object interacts with a Sheep, the Sheep object's health is reduced to zero.If it interacts with a Bengal object, the health of the object whose health is lesser is reduced by 10%.
	 * Else, if it interacts with another Bengal object the growl method is called. 
	 */
	@Override
	public void interact(Animal objAnimal) {
		if (objAnimal instanceof Sheep){
			health = health + objAnimal.getHealth();
			objAnimal.setHealth(0);
		}
		else if(objAnimal instanceof Bengal){
			growl();
		}
		else if (objAnimal instanceof Tiger){
			if (objAnimal.getHealth() > this.health){
				this.health = (int) (this.health * 0.9);
			}
			else if(objAnimal.getHealth() < this.health){
				objAnimal.setHealth((int)(objAnimal.getHealth() * 0.9));
			}
			else {
				
			}
		}
		else{
			
		}

	}
	/**
	 *  If the method is called "RAWR" is printed as output.
	 */
	@Override 
	public void growl(){
		System.out.println("RAWR");
		health++;
	}

}
