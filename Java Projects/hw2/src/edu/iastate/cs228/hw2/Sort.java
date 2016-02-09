package edu.iastate.cs228.hw2;

import java.lang.reflect.Array;
import java.util.Comparator;

/**
 * @author Neh Batwara
 *The sort class contains three sorting methods - Selection sort Insertions sort, Quick sort.
 */
public class Sort {
	/**
	 * static Long variable for number of comparisons. 
	 */
	private static Long numComparisons = 0L;
	/**
	 * Selections sort algorithm. 
	 * @param arr Generic T type array. 
	 * @param cp  Comparator object
	 * @return	  number of comparisons to sort the given array. 
	 */
	public static <T> long selectionSort(T[] arr, Comparator< ? super T> cp){
		if(arr == null || arr.length == 0 || cp == null)
		{
			throw new IllegalArgumentException("Either the array or comparator is null"); 
		}else{
			//do nothing.
		}
		long numComparisons= 0;
		
		int i, j;
		int minIndex; 
		for( j=0; j < arr.length - 1; j++){
			minIndex = j;
			for( i = j + 1; i < arr.length; i++){
				numComparisons++;
				if(cp.compare(arr[i], arr[minIndex]) < 0){
					minIndex = i;
				}
			}
			if(minIndex != j){
				T temp = arr[j];
				arr[j] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
		return numComparisons;
	}
	/**
	 * Algorithm for insertion sort. 
	 * @param arr Generic T type array variable
	 * @param cp	Comparator object variable
	 * @return	number of comparisons to sort the given array. 
	 */
	public static <T> long insertionSort(T[] arr, Comparator<? super T> cp) {
		if (arr == null || arr.length == 0 || cp == null) {
			throw new IllegalArgumentException(
					"Either the array or comparator is null");
		} else {
			// do nothing.
		}
		long numComparisons = 0;

		for (int i = 1; i < arr.length; i++) {
			T value = arr[i];
			int j = i;
			while (j > 0 && cp.compare(arr[j - 1], value) > 0) {
				numComparisons++;
				arr[j] = arr[j - 1];
				j--;
			}
			if(j > 0){
				numComparisons++;
			}
			arr[j] = value;
		}
		return numComparisons;
	}
	/**
	 * Quick sort algorithm. 
	 * @param arr Generic T type array variable
	 * @param cp  Comparator object variable
	 * @param pType variable for passing type of pivot method to choose from - first, median or random
	 * @return number of comparisons to sort the given array.
	 */
	public static <T> long quickSort(T[] arr, Comparator<? super T> cp, String pType){
		numComparisons = 0L;
		if(arr == null || arr.length == 0 || cp == null)
		{
			throw new IllegalArgumentException("Either the array or comparator is null"); 
		}else{
			//do nothing.
		}
		if(!"first".equals(pType)
				&& !"median".equals(pType)
				&& !"random".equals(pType)){
			throw new IllegalArgumentException("Invalid value of pType. Valid Values are : first, median or random");
		}
		recursiveQuickSort(arr, 0, arr.length - 1, cp, pType);
		return numComparisons;
	}
	/**
	 * Recursive method on quick sort.
	 * @param arr Generic T type array variable
	 * @param low low index of array ( first )
	 * @param high high index of array ( last )
	 * @param cp Comparator object variable
	 * @param pType  variable for passing type of pivot method to choose from - first, median or random
	 */
	private static <T> void recursiveQuickSort(T[] arr, int low , int high, Comparator<? super T> cp, String pType){
		if(low < high){
			int q = partition(arr, low, high, cp, pType);
			recursiveQuickSort(arr, low, q - 1, cp, pType);
			recursiveQuickSort(arr, q + 1, high, cp, pType);
		}
	}
	/**
	 * 
	 * @param arr Generic T type array variable
	 * @param low low index of array ( first )
	 * @param high high index of array ( last )
	 * @param cp Comparator object variable
	 * @param pType  variable for passing type of pivot method to choose from - first, median or random
	 * @return index at which there will be a partition
	 */
	private static <T> int partition(T[] arr, int low, int high, Comparator<? super T> cp, String pType){
		int pivotIndex = choosePivot(arr, low, high, pType, cp);
		T pivotValue = arr[pivotIndex];
		swap(arr, pivotIndex, high);
		int storeIndex = low;
		for(int i = low; i < high; i++){
			if(cp.compare(arr[i], pivotValue) < 0){
				numComparisons++;
				swap(arr, i, storeIndex);
				storeIndex++;
			}
		}
		swap(arr, storeIndex, high);
		return storeIndex;
	}
	
	/**
	 *  method to swap elements.
	 * @param arr Generic T type array variable
	 * @param i 
	 * @param j
	 */
	private static <T> void swap(T[] arr, int i ,  int j){
		T temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	/**
	 * Method to choose pivot- either first, median or random depending on the pType variable passed.
	 * @param arr Generic T type array variable
	 * @param low low index of array ( first )
	 * @param high high high index of array ( last )
	 * @param pType variable for passing type of pivot method to choose from - first, median or random
	 * @param cp cp Comparator object variable
	 * @return
	 */
	private static <T> int choosePivot(T[] arr, int low, int high,
			String pType, Comparator<? super T> cp) {
		if ("first".equals(pType)) {
			return low;
		} else if ("median".equals(pType)) {
			T first, mid, last;
			int i, middleIndex;
			first = arr[low];
			mid = arr[(low + high) / 2];
			last = arr[high];
			middleIndex = (low + high) / 2;
			if (cp.compare(first, mid) < 0) {
				numComparisons++;
				if (cp.compare(first, last) > 0) {
					numComparisons++;
					return low;
				} else if (cp.compare(last, mid) < 0) {
					numComparisons++;
					return high;
				} else {
					return middleIndex;
				}
			} else if (cp.compare(first, last) < 0) {
				numComparisons++;
				return low;
			}
			else if (cp.compare(last, mid) < 0){
				numComparisons++;
				return high;
			}
			else return middleIndex;

		} else {
			return low + (int) (Math.random() * high);
		}
	
	}
}


