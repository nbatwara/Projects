package edu.iastate.cs228.hw1;
import static org.junit.Assert.*;

import org.junit.Test;


/**
 * 
 */

/**
 * @author Neh Batwara
 *This class consists of JUnit tests for your Cage class.
 */
public class CageTest {

	@Test(expected = CageException.class)
	public void cageWithCapacityZero() throws CageException {
		new Cage(0);
		
	}
	@Test
	public void cageWithCapacityFive() throws CageException {
		Cage objCage = new Cage(5);
		assertEquals("Actual value does not match expected value", 5, objCage.getCapacity());
	}
	
	@Test
	public void cageWithBengal() throws CageException {
		Cage<Tiger> objCage = new Cage<Tiger>(1);
		Bengal objBengal = new Bengal("BengalTiger", 10);
		objCage.add(objBengal);
		assertEquals("Expected value does not match actual value", objBengal, objCage.getOccupants()[0]);
	}
	
	@Test
	public void cageWithTigerAndSheep() throws CageException {
		Cage<Animal> objCage = new Cage<Animal>(2);
		Bengal objBengal = new Bengal("Bengal Tiger", 10);
		objCage.add(objBengal);
		Sheep objSheep = new Sheep(" Sheep ", 10);
		objCage.add(objSheep);
		assertEquals("Expected value does not match actual value", objBengal, objCage.getOccupants()[1]);
		assertEquals("Expected value does not match actual value", objSheep, objCage.getOccupants()[0]);
	}
	@Test(expected = CageException.class)
	public void removeNonExistantAnimal() throws CageException {
		Cage<Animal> objCage = new Cage<Animal>(1);
		Bengal objBengal = new Bengal("Bengal Tiger", 10);
		objCage.add(objBengal);
		objCage.remove("abc");
		
	}
	
	@Test(expected = CageException.class)
	public void addAnimalWithSameName() throws CageException {
		Cage<Animal> objCage = new Cage<Animal>(1);
		Bengal objBengal = new Bengal("Bengal Tiger", 10);
		objCage.add(objBengal);
		objCage.add(objBengal);
		
	}
	@Test
	public void simulate() throws CageException {
		// In the implementation of cage , first added animal is at the end
		// Therefore, first animal is tiger with 20 , then sheep with 20
		// and then bengal with 10
		/*
		 * With the implementaion of simulate first animal interacts 
		 * with others , followed by second and followed by third
		 * As a result Tiger would have health of 40, Sheep = 0 and thus removed
		 * Bengal would ahave health 8
		 */
		Cage<Animal> objCage = new Cage<Animal>(3);
		Bengal objBengal = new Bengal("Bengal Tiger", 10);
		objCage.add(objBengal);
		Sheep objSheep = new Sheep(" Sheep ", 20);
		objCage.add(objSheep);
		Tiger objTiger = new Tiger(" Tiger", 20);
		objCage.add(objTiger);
		objCage.simulate();
		Animal[] arrAnimals = objCage.getOccupants();
		
		assertEquals("Expected that tiger has health 40", 40, arrAnimals[0].getHealth());
		
		assertNull("Expected that sheep is removed" , arrAnimals[1]);
		
		assertEquals("Expected that BEngal has health = 8 ", 8, arrAnimals[2].getHealth());
	}
	

}
