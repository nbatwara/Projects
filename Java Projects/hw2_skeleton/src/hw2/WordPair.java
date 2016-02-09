package hw2;
/**
 * @author Neh Batwara
 */
import util.PermutationGenerator;

/**
 * A WordPair encapsulates two representations
 * of a given word - its "real" form, which does not change,
 * and a "scrambled" form in which the letters are rearranged.
 * The scrambled form of the word may be updated by calls to the
 * methods <code>moveLeft</code>, <code>moveRight</code>,
 * <code>doLetterHint</code>, and <code>rescramble</code>.
 */
public class WordPair
{
private String realWord;

private String scrambledWord;

private int correct;
  /**
   * Constructs an instance of WordPair with the given strings.
   * This constructor does
   * not check whether the given scrambled word is a valid
   * rearrangement of the given real word, but this can be
   * checked by a subsequent call to the method
   * <code>isSolutionPossible</code>.
   * @param givenRealWord
   *   the real word
   * @param givenScrambledWord
   *   the word with characters rearranged
   */
  public WordPair(String givenRealWord, String givenScrambledWord) 
  {
   realWord = givenRealWord;
   scrambledWord = givenScrambledWord;
   correct = 0;
  }
  
  /**
   * Returns the current scrambled form of the word
   * in upper case.
   * @return
   *   scrambled form of the word.
   */
  public String getScrambledWord()
  {
    
    return scrambledWord.toUpperCase();
  }
  
  /**
   * Returns the real word in upper case.
   * @return
   *   the real word
   */
  public String getRealWord()
  {
    
    return realWord.toUpperCase();
  }
  
  /**
   * Modifies the scrambled word by moving the character at position 
   * <code>index</code> to the right the given number of spaces. 
   * Intervening characters are shifted left.  For example, if the
   * scrambled word is "ABCDEFG", then after moveRight(2, 3) the
   * scrambled word should be "ABDEFCG".  This method should do 
   * nothing if the index is out of range, if <code>howFar</code>
   * is negative, if the index plus <code>howFar</code> is out of range,
   * or if the index is less than <code>getNumLetterHints()</code>.
   * @param index
   *   zero-based index of the character to be moved
   * @param howFar
   *   number of spaces to the right to move the character
   */
  public void moveRight(int index, int howFar)
  {
	// using substring fuction of strings I update the scrambled word with every move ie., as the user moves the letter 
	 	//from index to the other the scrambled word is updated.
	  if ( index < correct || index > realWord.length()-1 || (index + howFar) > realWord.length()-1 || howFar < 0 ||(index + howFar)< correct){
		  return;
	  }
	  scrambledWord = scrambledWord.substring(0,index) + scrambledWord.substring((index+1),(index +1 + howFar))
				+scrambledWord.charAt(index) +scrambledWord.substring(index+1+howFar);
  }
 
  /**
   * Modifies the scrambled word by moving the character at position 
   * <code>index</code> to the left the given number of spaces. 
   * Intervening characters are shifted right.  For example, if the
   * scrambled word is "ABCDEFG", then after moveLeft(5, 3) the
   * scrambled word should be "ABFCDEG".  This method should do 
   * nothing if the index is out of range, if <code>howFar</code>
   * is negative, if the index minus <code>howFar</code> is out of range,
   * or if the index index minus <code>howFar</code> is less than 
   * <code>getNumLetterHints()</code>.
   * @param index
   *   zero-based index of the character to be moved
   * @param howFar
   *   number of spaces to the left to move the character
   */
  public void moveLeft(int index, int howFar)
  {
	// using substring fuction of strings I update the scrambled word with every move ie., as the user moves the letter 
	 	//from index to the other the scrambled word is updated.
	  if ( index < correct || index > realWord.length()-1 || (index - howFar) > realWord.length()-1|| howFar < 0 || (index -howFar)<getNumLetterHints()){
		  return;
	  }
	  scrambledWord = scrambledWord.substring(0,(index-howFar)) + scrambledWord.charAt(index) +
				scrambledWord.substring((index-howFar),(index)) + scrambledWord.substring(index+1);
	  
  }

  /**
   * Returns the number of letters at the beginning of the scrambled word whose
   * positions are currently fixed by previous calls to <code>letterHint()</code>.
   * @return
   *   number of fixed characters at the beginning of the scrambled word
   */
  public int getNumLetterHints()
  {
    
//    for (int i = 0; i<realWord.length(); i ++ ){
//    	if (scrambledWord.charAt(i) == realWord.charAt(i)){
//    		correct = correct +1;
//    	}
 //   }
    return correct;
  }
  
  /**
   * Applies a hint by placing a correct character at the position
   * <code>p</code>, where <code>p = getNumLetterHints()</code> is the number
   * of characters that have already been placed in the correct position
   * by previous calls to this method.
   * Specifically, this method searches to the right starting at index <code>p</code>
   * until it finds the first occurrence of the correct character, 
   * and then swaps that character into position <code>p</code>.
   * After this method has been called n times, the first n letters
   * of the scrambled word are correct and are not moved again by any
   * subsequent method calls. 
   * <p>
   * For example: if the real word is "AARDVARK" and the scrambled word
   * is "DVRAKARA", then after one call to <code>doLetterHint</code>,
   * the scrambled word is "AVRDKARA"  (D is swapped with first A).
   * After two calls to <code>doLetterHint</code>, the scrambled word is "AARDKVRA"
   * (V is swapped with the second A).  After three calls to <code>doLetterHint</code>,
   * the scrambled word is still "AARDKVRA" (R is swapped with itself).  However
   * <code>getNumLetterHints</code> will now return 3, and the characters
   * at indices 0, 1, and 2 will be not be moved.
   */
  public void doLetterHint()
  {
	if(realWord.length() <= correct ){
		return;
	}
    char temp = realWord.charAt(correct);
	char tempScramble;
    for (int i = correct; i < realWord.length(); i++){
    	if (scrambledWord.charAt(i)== temp){
    		StringBuilder temp1 = new StringBuilder(scrambledWord);
    		tempScramble = scrambledWord.charAt(correct);
    		temp1.setCharAt(correct, temp);
    		temp1.setCharAt(i,tempScramble);
    		
    		scrambledWord = temp1.toString();
    		correct += 1;
    		break;
    	}
    	
    }
    
  }
  
  /**
   * Rescrambles the non-fixed letters in the scrambled using the 
   * given permutation generator.  Letters to the left of the index
   * <code>getNumLetterHints()</code> are not moved. This method should
   * update the scrambled word with the value of
   * <pre>
   * WordScrambler.scramble(getScrambledWord(), getNumLetterHints(), gen);
   * </pre>
   * )
   * @param gen
   *   permutation generator for scrambling the non-fixed letters
   */
  public void rescramble(PermutationGenerator gen)
  {
	  scrambledWord =   WordScrambler.scramble(scrambledWord,correct,gen);
  }

  /**
   * Determines whether the scrambled word is a valid rearrangement
   * of the real word (disregarding case).
   * @return
   *   true if it is possible to rearrange the characters in the
   *   scrambled word to obtain the real word
   */
  public boolean isSolutionPossible()
  {
	  //I compare each the real word with the scrambled word. If the scrambled word has the given letter it deletes that letter 
	  //from the temp scramble variable. If in the end the tempScramble varible has a lenth of 0, it returns true else it returns false.
	 
	  String temp1 = realWord;
	  String tempScramble = scrambledWord;
	  if(tempScramble.length() != realWord.length()){
			 return false;
		 }
	 
	 for(int i = 0; i < realWord.length();i++){
		for (int j = 0;j < tempScramble.length(); j++){
			if (realWord.charAt(i) == tempScramble.charAt(j)){
				
				if(j!=0){
					tempScramble = tempScramble.substring(0,j) + tempScramble.substring(j+1);
					break;
				}
				else if(tempScramble.length()!=1){
					tempScramble = tempScramble.substring(j+1);
					break;
				}
				else
					tempScramble = "";
			}
		}
	 }
	 
	 if (tempScramble.length()==0){
		 return true;
	 }
	 else return false;
	
  }

  

  /**
   * Determines whether the current scrambled word is 
   * equal to the real word (disregarding case).
   * @return
   *   true if scrambled word and real word are the same,
   *   false otherwise
   */
  public boolean checkSolution()
  {
//	    if (scrambledWord.equalsIgnoreCase(realWord)){
//	    	return true;
//	    }
//	    
//	    else return false;
 return checkSolution(scrambledWord);
  }
  
  
  /**
   * Determines whether the given string is 
   * equal to the real word (disregarding case).
   * Does not modify the scrambled word.
   * @return
   *   true if given word and real word are the same,
   *   false otherwise
   */
  public boolean checkSolution(String solution)
  {
	  if (solution.equalsIgnoreCase(realWord)){
	    	return true;
	    }
	    
	    else return false;
  }
}
