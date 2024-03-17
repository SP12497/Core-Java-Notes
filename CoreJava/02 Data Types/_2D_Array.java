import java.util.Arrays;

public class _2D_Array {
	public static void main(String[] args) {
		int [] arr[] = new int[][] { { 11, 13, 12 }, { 24, 22, 22, 24 }, { 31, 33, 32 } };
		int[][] arr2 = new int[5][3];
		int arr3 [][] = new int[5][3];
		
        // size of inner arrays will be different
		System.out.println(arr[1][2]);	// 22
		System.out.println(arr.length);	// 3
		System.out.println(arr[2].length);	// 3
		System.out.println(arr[1].length);	// 4
		
        // sorting
		for(int[] innerArr : arr) {
			Arrays.sort(innerArr);
		}
		
        // printing
		System.out.println(Arrays.toString(arr));	// [[I@2a139a55, [I@15db9742, [I@6d06d69c]
		
		for(int i= 0; i<arr.length; i++) {
			for(int j=0; j<arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
		
		int [][] arr4 = Arrays.copyOf(arr, arr.length);	// outer is deep, but inner is shallow copy
		arr4[1][0] = 00;	// modified arr obj as well
		for(int[] innerArr : arr) {
			for(int value: innerArr)
				System.out.print(value + " ");
			System.out.println();
		}
	}

	
}
