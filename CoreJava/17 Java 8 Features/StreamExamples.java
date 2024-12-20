/*
- In Java, a Stream is a sequence of elements supporting functional-style operations for processing data.
- It allows operations like filtering, mapping, and reducing on a collection or array without modifying the source.

list..stream().collect(Collectors.toList()):
    Filter: Select elements based on a condition.
    Map: Transform elements into a new form.
    Reduce: Combine elements into a single result.
    Sorted: Arrange elements in a specified order.
    Distinct: Remove duplicate elements.
    Limit: Restrict to a fixed number of elements.
    Skip: Skip the first n elements.
    ForEach: Perform an action for each element.
    AllMatch: Check if all elements satisfy a condition.
    AnyMatch: Check if any element satisfies a condition.
    NoneMatch: Check if no element satisfies a condition.
    Count: Count the number of elements.
    Collect: Gather elements into a collection (e.g., List, Set).
    FlatMap: Flatten nested collections into a single stream.
    Peek: Inspect elements at intermediate stages.
Collectors:
    toList: Collect elements into a List.
    toSet: Collect elements into a Set.
    toMap: Collect elements into a Map.
    toCollection: Collect elements into a specified collection.
    toConcurrentMap: Collect elements into a concurrent Map.
    toUnmodifiableList: Collect elements into an unmodifiable List.
    toUnmodifiableSet: Collect elements into an unmodifiable Set

    joining: Concatenate elements into a single String.
    counting: Count the number of elements.
    summingInt: Sum the elements as an int.
    averagingInt: Average the elements as an int.
    partitioningBy: Partition elements based on a predicate.
    groupingBy: Group elements based on a classifier.
    mapping: Apply a transformation before collecting.
    reducing: Combine elements using a BinaryOperator.
    filtering: Select elements based on a predicate.
    maxBy: Find the maximum element based on a comparator.
    minBy: Find the minimum element based on a comparator.
    collectingAndThen: Apply a finisher function after collecting.
    
*/

import java.util.*; // Comparator => java.util.Comparator
import java.util.stream.*;  // import java.util.stream.Collectors;

public class StreamExamples {

    public static void main(String[] args) {
        // Example dataset
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // 1. Filter: Retain even numbers
        List<Integer> evenNumbers = numbers.stream()
                .filter(num -> num % 2 == 0)
                .collect(Collectors.toList());
        System.out.println("Filtered Even Numbers: " + evenNumbers);

        // 2. Map: Square each number
        List<Integer> squaredNumbers = numbers.stream()
                .map(num -> num * num)
                .collect(Collectors.toList());
        System.out.println("Squared Numbers: " + squaredNumbers);

        // 3. Sorted: Sort in reverse order
        List<Integer> reverseSorted = numbers.stream()
                // .sorted()    // Ascending order
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
        System.out.println("Reverse Sorted Numbers: " + reverseSorted);

        // 4. Distinct: Remove duplicates
        List<Integer> duplicates = Arrays.asList(1, 2, 2, 3, 3, 3, 4, 4, 4, 4);
        List<Integer> uniqueNumbers = duplicates.stream()
                .distinct()
                .collect(Collectors.toList());
        System.out.println("Unique Numbers: " + uniqueNumbers);

        // 5. Limit: Limit the stream to first 3 elements
        List<Integer> firstThree = numbers.stream()
                .limit(3)
                .collect(Collectors.toList());
        System.out.println("First 3 Numbers: " + firstThree);       // [1, 2, 3]

        // 6. Skip: Skip the first 5 elements
        List<Integer> skipped = numbers.stream()
                .skip(5)
                .collect(Collectors.toList());
        System.out.println("Skipped First 5 Numbers: " + skipped);  // [6, 7, 8, 9, 10]

        // 7. Reduce: Sum all numbers
        int sum = numbers.stream()
                .reduce(0, Integer::sum);
                // .reduce(0, (num1, num2) -> num1 + num2);
        System.out.println("Sum of Numbers: " + sum);             // 55

        // example: Cound of repeated words in a list
        List<String> words = Arrays.asList("hello", "world", "hello", "java", "world");
        Map<String, Long> wordCount = words.stream()
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
        

        // 8. ForEach: Print each number
        System.out.print("ForEach Numbers: ");
        numbers.stream()
                // .forEach(System.out::print);
                .forEach(num -> System.out.print(num + " "));
        System.out.println();                              // 1 2 3 4 5 6 7 8 9 10

        // 9. AllMatch: Check if all numbers are less than 20 (All elements satisfy the condition)
        boolean allLessThan20 = numbers.stream()
                .allMatch(num -> num < 20);
        System.out.println("Are all numbers less than 20? " + allLessThan20);   // true

        // 10. AnyMatch: Check if any number is greater than 5
        boolean anyGreaterThan5 = numbers.stream()
                .anyMatch(num -> num > 5);
        System.out.println("Is any number greater than 5? " + anyGreaterThan5); // true

        // 11. NoneMatch: Check if no number is negative
        boolean noneNegative = numbers.stream()
                .noneMatch(num -> num < 0);
        System.out.println("Are there no negative numbers? " + noneNegative);   // true

        // 12. Count: Count how many numbers are greater than 5
        long countGreaterThan5 = numbers.stream()
                .filter(num -> num > 5)
                .count();
        System.out.println("Count of Numbers Greater Than 5: " + countGreaterThan5);    // 5

        // 13. Collect: Collect stream elements into a Set
        Set<Integer> numberSet = numbers.stream()
                .collect(Collectors.toSet());
        System.out.println("Collected as Set: " + numberSet);   // [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

        // 14. FlatMap: Flatten a nested list of integers
        List<List<Integer>> nestedLists = Arrays.asList(    // [[1, 2, 3], [4, 5, 6], [7, 8, 9]]
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5, 6),
                Arrays.asList(7, 8, 9)
        );
        List<Integer> flattenedList = nestedLists.stream()
                .flatMap(Collection::stream)
                // .flatMap(list -> list.stream())
                .collect(Collectors.toList());
        System.out.println("Flattened List: " + flattenedList);  // [1, 2, 3, 4, 5, 6, 7, 8, 9]

        // 15. Peek: Inspect elements during the stream operations
        System.out.print("Peek Demonstration: ");
        List<Integer> peeked = numbers.stream()
                .filter(num -> num % 2 == 0) // Filter even numbers
                .peek(num -> System.out.print(num + " ")) // Print each number
                .collect(Collectors.toList());
        System.out.println("\nPeeked Even Numbers: " + peeked); // [2, 4, 6, 8, 10]
    }
}

/*
Filtered Even Numbers: [2, 4, 6, 8, 10]
Squared Numbers: [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]
Reverse Sorted Numbers: [10, 9, 8, 7, 6, 5, 4, 3, 2, 1]
Unique Numbers: [1, 2, 3, 4]
First 3 Numbers: [1, 2, 3]
Skipped First 5 Numbers: [6, 7, 8, 9, 10]
Sum of Numbers: 55
ForEach Numbers: 1 2 3 4 5 6 7 8 9 10 
Are all numbers less than 20? true
Is any number greater than 5? true
Are there no negative numbers? true
Count of Numbers Greater Than 5: 5
Collected as Set: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]
Flattened List: [1, 2, 3, 4, 5, 6, 7, 8, 9]
Peek Demonstration: 2 4 6 8 10 
Peeked Even Numbers: [2, 4, 6, 8, 10]
*/