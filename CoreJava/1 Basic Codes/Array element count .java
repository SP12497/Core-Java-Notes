
// Java program to count frequencies of array items 
import java.util.*;
import java.util.function.Function;
import java.util.stream.*;

class GFG {

	static void countFreq(int arr[], int n) {
		Map<Integer, Integer> mp = new HashMap<>();

		// Traverse through array elements and
		// count frequencies
		for (int i = 0; i < n; i++) {
			if (mp.containsKey(arr[i])) {
				mp.put(arr[i], mp.get(arr[i]) + 1);
			} else {
				mp.put(arr[i], 1);
			}

			// mp.put(arr[i], mp.containsKey(arr[i]) ? mp.get(arr[i]) + 1 : 1);
		}
		// Traverse through map and print frequencies
		for (Map.Entry<Integer, Integer> entry : mp.entrySet()) {
			System.out.println(entry.getKey() + " " + entry.getValue());
		}
	}

	// Using Stream api:
	static void countFreqByStream(int arr[], int n) {
		Map<Integer, Long> mp = Arrays.stream(arr).boxed()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

		mp.forEach((k, v) -> System.out.println(k + " " + v));
	}

	// Driver code
	public static void main(String args[]) {
		int arr[] = { 10, 20, 20, 10, 10, 20, 5, 20 };
		int n = arr.length;
		countFreq(arr, n);
		// String str;
		// str.toCharArray()

		// -----
		List<String> words = Arrays.asList("hello", "world", "hello", "java", "world");
		Map<String, Long> wordCount = words.stream()
				.collect(Collectors.groupingBy(word -> word, Collectors.counting()));
	}
}
