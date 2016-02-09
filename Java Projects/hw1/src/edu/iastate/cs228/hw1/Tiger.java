package edu.iastate.cs228.hw1;

public class Tiger extends Animal {
	/**
	 * Constructor for Tiger class which extends Animal.
	 * @param name
	 * @param health
	 */
	public Tiger(String name, int health) {
		super(name, health);
		
	}
	/**
	 * Overriding the abstract interact method from the Animal class. 
	 * When a Tiger object interacts with a Sheep, the Sheep object's health is reduced to zero.If it interacts with a Bengal object, the health of the object whose health is lesser is reduced by 10%.
	 * Else, if it interacts with another Tiger object the growl method is called. 
	 */
	@Override
	public void interact(Animal objAnimal) {
		if (objAnimal instanceof Sheep){
			health = health + objAnimal.getHealth();
			objAnimal.setHealth(0);
		}
		else if (objAnimal instanceof Bengal){
			if (objAnimal.getHealth() > this.health){
				this.health = (int) (this.health * 0.9);
			}
			else if(objAnimal.getHealth() < this.health){
				objAnimal.setHealth((int)(objAnimal.getHealth() * 0.9));
			}
			else {
				
			}
		}
		else if(objAnimal instanceof Tiger){
			growl();
		}
		else {
			
		}
		
	}
	/**
	 * If the method is called "Prrr." is printed as output. 
	 */
	public void growl(){
		System.out.println("Prrr.");
	}

}
