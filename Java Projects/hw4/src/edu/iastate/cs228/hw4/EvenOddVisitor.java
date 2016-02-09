package edu.iastate.cs228.hw4;

public class EvenOddVisitor<E extends Comparable<? super E>> implements
		Visitor<E> {

	private int numEvens = 0;
	private int numOdds = 0;

	@Override
	public void visit(BSTNode<E> node) {
//		System.out.println(node.data);
		E data = node.data;
		int intVal = 0;
		try {
			intVal = Integer.parseInt(data.toString());
			if (intVal % 2 == 0) {
				numEvens++;
			} else {
				numOdds++;
			}
		} catch (NumberFormatException e) {
			// ignore exception
		}

	}

	public int getNumEvens() {
		return numEvens;
	}
	
	public int getNumOdds(){
		return numOdds;
	}

}
