package hw2;
/**
 * @author Neh Batwara
 */

import util.PermutationGenerator;

/**
 * Utility class for rearranging the characters in a string
 * using a <code>PermutationGenerator</code>.  The two 
 * <code>scramble</code> methods are required to use the
 * given <code>PermutationGenerator</code> exactly as described.
 * <p>
 * The methods are static.  This class should not be instantiated.
 */
public class WordScrambler
{
  // private constructor prevents accidentally constructing instances
  private WordScrambler(){}
  
  /**
   * Permutes the characters of the given word according 
   * to the next n-element permutation produced by the given
   * permutation generator, where n is the length of the word.
   * <p>
   * For example, if the word is "CDEFG" and the next 5-element
   * permutation is [2, 0, 4, 1, 3], then the method returns the 
   * string "ECGDF".
   * @param word
   *   word to be scrambled
   * @return
   *   scrambled word arranged according to the next appropriately sized permutation
   */
  public static String scramble(String word, PermutationGenerator gen)
  {
	  // Using the permutation generator, we store the given array in an array called index, then I append the scrambledWord string 
	  // by adding the letter at the position which we get from each element of index
//	  String scrambleWord = ""; 
//	  String tempWord = word;
//
//	  int index[] = gen.generate(word.length());
//	  for (int i = 0; i < index.length; i++){
//		  scrambleWord += tempWord.charAt(index[i]);
//	  }
//	  return scrambleWord;
	  
	  return scramble(word,0,gen);
  }

  
   /**
   * Permutes the characters of the given word according 
   * to the next n-element permutation produced by the given
   * permutation generator, where n is the length of the word
   * minus <code>fixedCount</code>.  Characters with
   * indices less than <code>fixedCount</code> are not moved.
   * <p>
   * For example,
   * if the word is "ABCDEFG" and <code>fixedCount</code> is 2
   * and the generator returns the five-element permutation
   * [2, 0, 4, 1, 3], then the method returns the string "ABECGDF".
   * @param word
   *   word to be scrambled
   * @return
   *   scrambled word with the first <code>fixedCount</code> letters unchanged
   *   and remaining letters arranged according to the next appropriately sized 
   *   permutation
   */
  // Using the permutation generator, we store the given array in an array called index, then I append the scrambledWord string 
  // by adding the letter at the position which we get from each element of index. Here I first take the substring of scrambled word from 0 to the given fixed count
  public static String scramble(String word, int fixedCount, PermutationGenerator gen)
  {
	  String scrambleWord = "";
	  String tempWord = word;
	  int fixed = fixedCount;
	  int index[] = gen.generate(word.length());
	  scrambleWord += tempWord.substring(0,fixed) ;
	  for (int i = 0; i < tempWord.length() - fixed;i++){
		  scrambleWord += tempWord.charAt(index[i]);
	  }

	  return scrambleWord;
  }

}
