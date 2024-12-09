import java.util.Arrays;

/*
class Arrays:
    .toString(array);               .deepToString(array);               .fill(array, value);    
    .copyOf(array, length);         .copyOfRange(array, from, to);      .stream(array);
    .sort(array);                   .sort(array, comparator);    
    .binarySearch(array, key);      .equals(array1, array2);
    .deepHashCode(array);           .hashCode(array);
*/

class Student {
    int rollno;
    Student(int rollNo) {
        this.rollno = rollNo;
    }
}

public class _51Array {
    public static void main(String[] args) {
    //  Declaration:
        // int arr1[] = new int[]; // error: either provide size or initialize
        int arr1[] = new int[5]; // using primitive datatypes
        Integer arr2[] = new Integer[5]; // Wrapper classes
        // int [] arr2 = new int[5] {11,22,33,44,55}; // error: don't define size // error: Cannot define dimension expressions when an array initializer is provided means, while declaration if we are initalizing, then passing array size not allowed.
        int[] numArr = new int[] { 55, 11, 33, 44, 22 };
        String[] strArr = { "Sagar", "Suraj", "Nilesh", "Yogesh", "Kenny", "Sagar" };
        Boolean[] boolArr = {};

        Student students [] = new Student[5];
        
    //  initialize:
        // way 1:
        arr1[1] = 11;
        arr1[2] = 11;
        arr1[3] = 11;
        students[0] = new Student(1);
        students[1] = new Student(2);

        // way 2:
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = i + 5;
        }

        // way 3:
        Arrays.fill(arr2, 10);
        Arrays.fill(students, new Student(1));
        
    //  update
        numArr[2] = 31; // 33=>31

    //  Printing:
        System.out.println("---array Printing---");
        System.out.println(numArr[2]);
        System.out.println("Arrays.fill: " + Arrays.toString(arr2));
        for (int i = 0; i < arr1.length; i++) {
            System.out.println(arr1[i]);
        }

        for (String str : strArr) {
            System.out.println(str);
        }

        Arrays.stream(strArr).forEach(System.out::println);

    // HashCode:
        System.out.println("---Array hashCode---");
        System.out.println("hashCode: " + arr2.hashCode()); // 705927765
        System.out.println("Arrays.hashCode: " + Arrays.hashCode(arr2)); // 38172201
        arr2[0] = 11;
        System.out.println("hashCode: " + arr2.hashCode()); // 705927765, computes the hash code based on the object's
                                                            // memory address.
        System.out.println("Arrays.hashCode: " + Arrays.hashCode(arr2)); // 39095722,computes the hash code based on the
                                                                         // contents of the array.

        System.out.println("--- Integer value Comparision---");
        System.out.println(arr1[0]); // 5
        System.out.println(arr1[0] == arr1[0]); // true
        System.out.println(arr1[0] == 5); // true
        arr2[0] = 5;
        System.out.println(arr1[0] == arr2[0]); // true
        System.out.println(arr1[0] == arr2[0]); // true

        System.out.println("--- String value Comparision---");
        System.out.println(strArr[0] + "  " + strArr[5]); // "Sagar Sagar"
        System.out.println(strArr[0] == strArr[5]); // true
        System.out.println(strArr[0].equals(strArr[5])); // true

        System.out.println("--- String Object Comparision---");
        int temp1[] = { 1, 2, 3 };
        int temp2[] = { 1, 2, 3 };
        System.out.println(temp1 == temp2); // false
        System.out.println(Arrays.equals(temp1, temp2)); // true
        int temp3[] = { 1, 3, 2 };
        System.out.println(Arrays.equals(temp1, temp3)); // false

        System.out.println("---array sort---");
        Arrays.sort(numArr);
        System.out.println(Arrays.toString(numArr));
        Arrays.sort(strArr);
        System.out.println(Arrays.toString(strArr));

        System.out.println("---array search---");
        System.out.println(Arrays.binarySearch(numArr, 44)); // 3
        System.out.println(Arrays.binarySearch(numArr, 45)); // -5 // 45 should be available at index 5, but not present so returns -5
        // 45 will be after 44: (index(44) starts from 1 => -(4 + 1) => -5  
        // numArr[4] = 45; // insert in sorted for // -5+1

        System.out.println("---Array Shallow copy---");
        int numArr2[] = numArr; // Shallow Copy.
        numArr[1] = 111;
        System.out.println("numArr: " + Arrays.toString(numArr));
        System.out.println("numArr2: " + Arrays.toString(numArr2));

        System.out.println("---Array Deep copy---");

        String strArr2[] = Arrays.copyOf(strArr, strArr.length);    // copyOf(T[] original, int newLength)
        strArr2[0] = "Chetan";
        System.out.println("strArr: " + Arrays.toString(strArr));   // [Sagar, Suraj, Nilesh, Yogesh, Kenny, Sagar]
        System.out.println("strArr2: " + Arrays.toString(strArr2)); // [Chetan, Suraj, Nilesh, Yogesh, Kenny, Sagar]

        /* Array Resizing:
            Java arrays are fixed-size, so resizing directly isn't possible.
             Instead, you create a new array of the desired size and copy the elements
             from the old array to the new one.
        */

        System.out.println("---Reverse Array---");
        reverseArrayByReference(numArr2);
        System.out.println(Arrays.toString(numArr2));

        numArr2 = reverseArrayByValue(numArr2);
        System.out.println(Arrays.toString(numArr2));
        reverseArrayRecursive(numArr2, 0, numArr2.length - 1);
        System.out.println(Arrays.toString(numArr2));
    }

    static void reverseArrayByReference(int arr[]) { // reference type
        // Optimized way:
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            // swap:
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }

    static int[] reverseArrayByValue(int[] num) {
        int size = num.length;
        int arr[] = new int[size];
        // way 1:
        /*
        for (int i = 0, j = num.length - 1; i < arr.length; i++, j--) {
            arr[i] = num[j];
        }
        */

        // way 2:
        for (int i = 0; i < size; i++) {
            arr[i] = num[size - 1 - i];
        }

        return arr;
    }

    static void reverseArrayRecursive(int[] arr, int ascIndex, int descIndex) {
        if (ascIndex >= descIndex)
            return;
        // swap: using X-OR
        arr[ascIndex] = arr[ascIndex] ^ arr[descIndex];
        arr[descIndex] = arr[ascIndex] ^ arr[descIndex];
        arr[ascIndex] = arr[ascIndex] ^ arr[descIndex];

        reverseArrayRecursive(arr, ++ascIndex, --descIndex);
    }

}
