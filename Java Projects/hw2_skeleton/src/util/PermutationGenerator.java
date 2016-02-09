package util;
import java.util.Random;

/**
 * Utility for generating random integer permutations.  A permutation 
 * is an array containing each number 0 through n - 1 exactly once, 
 * where n is the length of the array.
 */
public class PermutationGenerator
{
  /**
   * Random number generator.
   */
  private Random rand;

  /**
   * Constructs a PermutationGenerator using a default instance of <code>Random</code>.
   */
  public PermutationGenerator()
  {
    rand = new Random();
  }
  
  /**
   * Constructs a PermutationGenerator using the given instance of <code>Random</code>.
   * @param rand
   *   random number generator to be used by this permutation generator
   */
  public PermutationGenerator(Random rand)
  {
    this.rand = rand;
  }
  
  /**
   * Constructs a random permutation of the integers 0 through n - 1.
   * The given argument must be positive.
   * @param n
   *   size of the permutation
   * @return
   *   integer array with each element 0 through n - 1 occurring exactly once
   */
  public int[] generate(int n)
  {
    // initial array contains value i at position i
    int[] ret = new int[n];
    for (int i = 0; i < n; ++i)
    {
      ret[i] = i;
    }
    
    // swap random element into last position
    for (int i = n - 1; i > 0; --i)
    {
      int index = rand.nextInt(i + 1);
      int temp = ret[index];
      ret[index] = ret[i];
      ret[i] = temp;
    }
    return ret;
  }
}
