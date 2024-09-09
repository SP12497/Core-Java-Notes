import java.util.Arrays;

/*
 Arrays:
	toString(array);
	deepToString(array);	// for 2D array
	fill(array, value);
	copyOf(array, length);
	copyOfRange(array, from, to);
	sort(array);	sort(array, comparator);
	binarySearch(array, key);
	equals(array1, array2);



 */

public class _52_2D_Array {
	
	static int sumOfArray(int arr []) {
		int total = 0;
		for(int num : arr) {
			total += num;
		}
		return total;
	}
	
	public static void main(String[] args) {
		int [] arr[] = new int[][] { { 11, 13, 12 }, { 31, 33, 32 }, { 24, 22, 22, 24 } };	// Jagged Array N*Any // inner array length not fixed
		int[][] arr2 = new int[5][3];	// Rectangular array N*M // inner array length fixed ie 3
		int arr3 [][] = new int[5][3];
		// int arr4 [][] = new int[][];	// Error: Variable must provide either dimension expressions or an array initializer
		
// size of inner arrays will be different
		System.out.println(arr[1][2]);	// 32
		System.out.println(arr.length);	// 3
		System.out.println(arr[2].length);	// 4
		System.out.println(arr[1].length);	// 3
		
// toString/deepToString:
		System.out.println(Arrays.toString(arr));	// [[I@2a139a55, [I@15db9742, [I@6d06d69c]
		System.out.println(Arrays.deepToString(arr));	// [[11, 13, 12], [31, 33, 32], [24, 22, 22, 24]]
// sorting
		// sort inner array elements:
		for(int[] innerArr : arr) {
			Arrays.sort(innerArr);
		}
		System.out.println(Arrays.deepToString(arr));	// [[11, 12, 13], [31, 32, 33], [22, 22, 24, 24]]
		
		// sort based of count of inner elements: 
		
		Arrays.sort(arr, (int [] o1, int [] o2) -> sumOfArray(o1) - sumOfArray(o2) );	// if return -ve, then o1 will come first (ascending order) // -1, 0 : no change, 1: swap
		System.out.println("sort by sumOfArray: " + Arrays.deepToString(arr));	// [[11, 12, 13], [22, 22, 24, 24], [31, 32, 33]]
		

		
// printing
		
		for(int i= 0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		int [][] arr4 = Arrays.copyOf(arr, arr.length);		// outer is deep, but inner is shallow copy
		arr4[1][0] = 00;	// modified arr obj as well

		for(int[] innerArr : arr) {
			for(int value: innerArr)
				System.out.print(value + " ");
			System.out.println();
		}
	}
	
}
