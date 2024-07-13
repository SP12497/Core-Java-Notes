// // https://www.javatpoint.com/StringBuffer-class
// // https://www.javatpoint.com/difference-between-string-and-stringbuffer
// // https://www.javatpoint.com/difference-between-stringbuffer-and-stringbuilder

/*
Mutable Strings in Java:
    A mutable string is a string that can be modified or changed. The `StringBuffer` and `StringBuilder` classes are used for creating mutable strings.

StringBuffer:
- Synchronized (thread-safe)
- It means two threads cannot call the methods of `StringBuffer` simultaneously.
- Less efficient than `StringBuilder` due to synchronization overhead.
- Introduced in Java 1.0

StringBuilder:
- Non-synchronized (not thread-safe)
- It means two threads can call the methods of `StringBuilder` simultaneously.
- More efficient than `StringBuffer` when thread safety is not required.
- Introduced in Java 1.5

Both `StringBuffer` and `StringBuilder` create an empty buffer with an initial capacity of 16 characters.

 */
class _10 {
    public static void main(String[] args) {
        StringBuffer sbuffer = new StringBuffer(); // default capacity is 16
        sbuffer.append("Hello");
        sbuffer.append(" World");
        System.out.println(sbuffer.toString()); // Output: Hello World

        StringBuilder sbuilder = new StringBuilder(); // default capacity is 16
        sbuilder.append("Hello");
        sbuilder.append(" World");
        System.out.println(sbuilder.toString()); // Output: Hello World

    }
}