/**
 * 
 */
package edu.iastate.cs228.hw1;

/**
 * @author Neh Batwara
 *A cage exception is (extends) Exception. The constructor for a CageException will take in an error message
as a String and will pass that String to the super constructor of Exception.
 */
public class CageException extends Exception {

public CageException(String message){
	super(message);
}
	
}
