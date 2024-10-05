What is hashing? How does it work?
    - Hashing is a technique used to store and retrieve data in a fast and efficient manner. 
    - It works by taking an input value and generating a unique output value based on a predefined algorithm. This output value is known as a hash code, and it is used to index and retrieve the data from a data structure such as a hash table.
Deadlock in Java? How to resolve it?
    - Deadlock in Java occurs when two or more threads wait for each other to release the lock, but they never do.
    - To resolve deadlock in Java, you can use the following techniques:
        - Avoid nested locks: Try to acquire locks in a consistent order to prevent circular dependencies.
        - Use timeouts: Set a timeout for acquiring locks and release them if the timeout is reached.
        - Use lock ordering: Acquire locks in a consistent order to prevent deadlock.
        - Use deadlock detection: Monitor the threads and detect deadlock situations to resolve them.
        - Use lock-free data structures: Use data structures that do not require locks to prevent deadlock.
