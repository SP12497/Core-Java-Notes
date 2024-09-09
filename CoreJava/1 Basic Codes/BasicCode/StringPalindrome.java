// Java implementation of the approach

// 454 === 454 => Palindrom
// NON === NON => Palindrom
// NOT === TON => Non-Palindrom

public class StringPalindrome {
    // Function that returns true if
    // str is a palindrome
    static boolean isPalindrome(String str)
    {
        // Pointers pointing to the beginning
        // and the end of the string
        int i = 0, j = str.length() - 1; // array starts from 0 so, length -1.
		
 
        // While there are characters to compare
		// int checkTillHalfOfLength = j/2;
        // while (i < checkTillHalfOfLength) {
        while (i < j) {
 
            // If there is a mismatch
            if (str.charAt(i) != str.charAt(j))
                return false;
 
            // Increment first pointer and
            // decrement the other
            i++;
            j--;
        }
 
        // Given string is a palindrome
        return true;
    }
 
    // Driver code
    public static void main(String[] args)
    {
        String str = "geeks";
 
        if (isPalindrome(str))
            System.out.print("Yes");
        else
            System.out.print("No");
    }
}
