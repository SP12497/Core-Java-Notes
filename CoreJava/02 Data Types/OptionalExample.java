// OptionalExample.java
import java.util.Optional;

// In Java, the Optional class, introduced in Java 8, provides a way to handle potentially null values more gracefully and avoids the pitfalls of null pointer exceptions. 
// It’s essentially a container that can either hold a value (non-null) or be empty.

public class OptionalExample {

    public static void main(String[] args) {
        // 1. Creating Optional instances
        Optional<String> nonEmptyOptional = Optional.of("Hello, World!"); // Non-null value
        Optional<String> nullableOptional = Optional.ofNullable(null);   // Nullable value
        Optional<String> emptyOptional = Optional.empty();              // Empty Optional

        // 2. Checking if value is present
        if (nonEmptyOptional.isPresent()) {
            System.out.println("Value is present: " + nonEmptyOptional.get());
        }

        if (emptyOptional.isEmpty()) {
            System.out.println("Optional is empty");
        }

        // 3. Accessing values
        String value = nullableOptional.orElse("Default Value");
        System.out.println("Value with orElse: " + value);

        String lazyValue = nullableOptional.orElseGet(() -> "Generated Default Value");
        System.out.println("Lazy default value: " + lazyValue);

        try {
            String riskyValue = emptyOptional.orElseThrow(() -> new RuntimeException("Value is missing"));
        } catch (RuntimeException e) {
            System.out.println("Exception thrown: " + e.getMessage());
        }

        // 4. Transforming values
        Optional<Integer> length = nonEmptyOptional.map(String::length);
        length.ifPresent(len -> System.out.println("Length of string: " + len));

        Optional<String> flatMapped = nonEmptyOptional.flatMap(val -> Optional.of(val + " (Modified)"));
        System.out.println("FlatMapped value: " + flatMapped.orElse("No Value"));

        // 5. Filtering values
        Optional<String> filtered = nonEmptyOptional.filter(val -> val.startsWith("H"));
        System.out.println("Filtered value: " + filtered.orElse("Filtered out"));

        // 6. Chaining Optional operations
        Optional<String> chainedResult = nonEmptyOptional
            .filter(val -> val.length() > 5)
            .map(String::toUpperCase);
        System.out.println("Chained result: " + chainedResult.orElse("No Result"));

        // 7. Using Optional in methods
        Optional<String> foundUser = findUserById("123");
        System.out.println("Found user: " + foundUser.orElse("User not found"));

        Optional<String> missingUser = findUserById("999");
        System.out.println("Missing user: " + missingUser.orElse("User not found"));
    }

    // Example method returning Optional
    public static Optional<String> findUserById(String id) {
        return id.equals("123") ? Optional.of("John Doe") : Optional.empty();
    }
}
