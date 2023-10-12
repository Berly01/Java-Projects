package com.project.algorithms;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class SortingAlgorithms {
	
	SortingAlgorithms() {}
	
    public enum AlgorithmName {
        BUBBLE_SORT, INSERTION_SORT, SELECTION_SORT, SHELL_SORT, HEAP_SORT, MERGE_SORT, 
        QUICK_SORT, BOGO_SORT, TIM_SORT, TREE_SORT, BUCKET_SORT, RADIX_SORT, COUNTING_SORT,
        CUBE_SORT
    }
	
	public static void mergeSort(List<Integer> array) {
		final int N = array.size();
		
		if (N < 2)
			return;
		
		var leftArray = copyElements(array, 0, N / 2 - 1);
		var rightArray = copyElements(array, N / 2, N - 1);
		
		mergeSort(leftArray);
		mergeSort(rightArray);
		
		final int SIZE_LEFT_ARRAY = leftArray.size();
		final int SIZE_RIGHT_ARRAY = rightArray.size();
		
		int i = 0;
		int j = 0;
		int k = 0;
		
        while (i < SIZE_LEFT_ARRAY && j < SIZE_RIGHT_ARRAY) {
            if (leftArray.get(i) <= rightArray.get(j)) {           	
            	array.set(k, leftArray.get(i));       
                i++;
            }
            else {
            	array.set(k, rightArray.get(j));
                j++;
            }
            k++;
        }

        while (i < SIZE_LEFT_ARRAY) {
        	array.set(k, leftArray.get(i));
            i++;
            k++;
        }

        while (j < SIZE_RIGHT_ARRAY) {
        	array.set(k, rightArray.get(j));
            j++;
            k++;
        }
	}
	
	public static void bubbleSort(List<Integer> array) {
					
		final int N = array.size();
		
		for (int i = 0; i < N - 1; i++) {
			boolean swap = false;
			for (int j = 0; j < N - i - 1; j++) {		
				if (array.get(j) > array.get(j + 1)) {
					Collections.swap(array, j, j + 1);
					swap = true;
				}	
			}	
			if (!swap)
				break;
		}
	}
		
	public static void heapSort(List<Integer> array) {
		
		final int N = array.size();
		
		for (int i = N / 2 - 1; i >= 0; i--) {
			heapify(array, N, i);
		}
		
		for (int i = N - 1; i >= 0; i--) {
			Collections.swap(array, 0, i);
			heapify(array, i, 0);
		}
	}
	
	public static void insertionSort(List<Integer> array) {
		
		final var N = array.size();
		
		for (int i = 1; i < N; i++) {			
			final var key = array.get(i);	
			int j = i - 1;
			
			while (j >= 0 && key < array.get(j)) {				
				array.set(j + 1, array.get(j));
				--j;
			}
			array.set(j + 1, key);
		}
	}
	
	public static void selectionSort(List<Integer> array) {
		
		final int N = array.size();
		
		for (int i = 0; i < N; i++) {
			int indexMinimum = i;
			for (int j = i + 1; j < N; j++) {
				if (array.get(j) < array.get(indexMinimum))
					indexMinimum = j;
			}
			Collections.swap(array, i, indexMinimum);
		}
	}
	
	public static void shellSort(List<Integer> array) {
		
		final int N = array.size();
		int interval = N / 2;
		
		while (interval > 0) {
			
			for (int i = interval; i < N; i++) {
				var temp = array.get(i);
				int j = i;
				
				while (j >= interval && array.get(j - interval) > temp) {
					array.set(j, array.get(j - interval));
					j -= interval;
				}		
				
				array.set(j, temp);
			}
			
			interval /= 2;
		}
	}
	
	public static void quickSort(List<Integer> array) {
		quickSort(array, 0, array.size() - 1);
	}
	
	public static void bogoSort(List<Integer> array) {
		var logger = System.getLogger(SortingAlgorithms.class.getName());
		while(!isSorted(array)) {
			Collections.shuffle(array, new Random());
			logger.log(System.Logger.Level.OFF, array);
		}
	}
	
	public static boolean isSorted(final List<Integer> array) {
		
		final var N = array.size();
		
		for (int i = 0; i < N - 1 ; i++) {
			if (array.get(i) > array.get(i + 1))
				return false;
		}
		return true;
	}

	static void heapify(List<Integer> array, final int heapSize, final int i) {
       
		int largestIndex = i; 
        int leftIndex = 2 * i + 1; 
        int rightIndex = 2 * i + 2; 
        
        if (leftIndex < heapSize && array.get(leftIndex) > array.get(largestIndex)) {
        	largestIndex = leftIndex;
        }

        if (rightIndex < heapSize && array.get(rightIndex) > array.get(largestIndex)) {
        	largestIndex = rightIndex;
        }

        if (largestIndex != i) {           
            Collections.swap(array, i, largestIndex);       
            heapify(array, heapSize, largestIndex);
        }    
	}

	static List<Integer> copyElements(final List<Integer> array, final int begin, final int end) {
			
		var subArray = new ArrayList<Integer>();
		
		for (int i = begin; i <= end; i++) {
			subArray.add(array.get(i));
		}
		
		return subArray;
	}

	static void quickSort(List<Integer> array, final int begin, final int end) {
		
		if (begin > end)
			return;
		
		final int PIVOT_INDEX = quickSortPartition(array, begin, end);
		
		quickSort(array, begin, PIVOT_INDEX - 1);
		quickSort(array, PIVOT_INDEX + 1, end);
	}
	
	static int quickSortPartition(List<Integer> array, final int begin, final int end) {
       
		final int PIVOT_VALUE = array.get(end);
        int i = begin - 1;

        for (int j = begin; j < end; j++) {
            if (array.get(j) <= PIVOT_VALUE) {
                i++;
                Collections.swap(array, i, j);
            }
        }
        Collections.swap(array, i + 1, end);
        return i + 1;
	}
}