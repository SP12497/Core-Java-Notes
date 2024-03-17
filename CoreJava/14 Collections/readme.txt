- Note:
    - Collection only use wrapper classes.

interface Collection<E>
    - interface List
        - class ArrayList
            -> dynamic array for storing elements. 
            -> maintain insertion order.
            -> start at fix size, once array full, it increase size by 50%. formula: (oldCapacity * 3) / 2 + 1
            -> asynchronous
            - Integer get();
        - class LinkedList
        - class Stack
        - class Vector
            - synchronous
            - start at fix size, once array full, it increase double the size 
    - interface Set
        - class HashSet
        - class LinkedHashSet
        - class TreeSet
        - class EnumSet
    - interface Queue
        - class ArrayDeque
        - class LinkedList
        - class PriorityQueue

Interface Map:
    -> The Map interface is not a subtype of the Collection interface.
    -> Therefore it behaves a bit differently from the rest of the collection types.
    - class HashMap
    - class LinkedHashMap
    - TreeMap

Interface Collections:
    int size()
    boolean isEmpty()
    boolean contains(Object element)
    boolean add(E element)
    boolean remove(Object element)
    Iterator<E> iterator()
- It also contains methods that operate on entire collections,
    boolean containsAll(Collection<?> c)
    boolean addAll(Collection<?> c)
    boolean removeAll(Collection<?> c)
    boolean retainAll(Collection<?> c)
    void clear()