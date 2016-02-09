package edu.iastate.cs228.hw1;

public class TestClass {

	public static void main(String[] args) {
		//addBengalToTiger();
		//addTigerSheepToAnimal();
		testSimulateTwoRounds();
	}
	
	public static void testSimulateTwoRounds(){
		try{
			Cage<Animal> objCage = new Cage<Animal>(4);
			objCage.add(new Tiger("test", 5));
			objCage.add(new Tiger("ABC", 5));
			objCage.add(new Sheep("Sheep", 10));
			objCage.add(new Bengal("B1", 15));
			objCage.simulate();
			for (String str : objCage.listAnimalsSorted()) {
				System.out.println(str);
			}
			objCage.simulate();
			for (String str : objCage.listAnimalsSorted()) {
				System.out.println(str);
			}
		}catch(CageException e){
			e.printStackTrace();
		}
	}
	
	public static void addTigerSheepToAnimal(){
		try{
			Cage<Animal> objCage = new Cage<Animal>(2);
			Tiger objTiger = new Tiger("Tiger1", 100);
			Sheep objSheep = new Sheep("Sheep1", 10);
			objCage.add(objTiger);
			objCage.add(objSheep);
			for(Animal objAnimal : objCage.arrayOfAnimals){
				System.out.println(objAnimal.toString());
			}
		}catch(CageException e){
			e.printStackTrace();
		}
	}
	
	public static void addBengalToTiger(){
		Bengal obj1 = new Bengal("Bengal1", 10);
		Bengal obj2 = new Bengal("Bengal2", 10);
		try {
			Cage objCage = new Cage<Tiger>(2);
			objCage.add(obj1);
			objCage.add(obj2);
			obj1.interact(obj2);
			System.out.println(obj1.getHealth());
			System.out.println(obj2.getHealth());
		} catch (CageException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
