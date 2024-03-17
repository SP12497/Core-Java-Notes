import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SameElementsCount {
    public static void main(String args[]) {
        // int arr1[] = new int[] { 1, 2, 3, 4, 2, 4 };
        Integer arr1[] = new Integer[] { 1, 2, 3, 4, 2, 4 };
        Integer arr2[] = { 4, 2, 6, 7, 4, 8 }; // 2,4 count: 2

        Set<Integer> set = new HashSet<>(Arrays.asList(arr1));  // way 1 to insert array into set.

        // Set<Integer> set = new HashSet<>()
        // set.addAll(Arrays.asList(arr1));     // way 2 to insert array into set
        // for (int no : arr1) {                // way 3
        // set.add(no); // arr1 unique elements
        // }
        int count = 0;
        for (int no : arr2) {
            if (set.contains(no)) {
                count++;
                set.remove(no); // remove count of duplicate number twice
            }
        }
        System.out.println("Count of same unique numbers are:" + count);
    }
}
