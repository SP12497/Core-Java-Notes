import java.util.Arrays;

public class _01LinearSearch {
    public static void main(String[] args) {
        int arr[] = { 33, 222, 3344, 446, 11111, 555 };
        int value = 11;
        System.out.println(String.valueOf(11).length());
        System.out.println(getIndexBylinearSearch(arr, value));
        System.out.println(isPresentBylinearSearch(arr, value));

        System.out.println("_____String______");

        String str = "Sagar Patil";
        char c = 'i';
        System.out.println(isCharPresentInStr(str, c));
        System.out.println(getIndexInStr2(str, c));

        System.out.println("findMin:" + findMin(arr));
        System.out.println("_____2D Array______");

        int[][] arrays = { { 4, 3, 6, 2 }, { 2, 6, 5, 8, 20 }, { 4, 5, 6 } };
        int[] presentIndex = isPresent2dArray(arrays, 8);
        System.out.println("isPresent2dArray: " + Arrays.toString(presentIndex));
        System.out.println("isPresent2dArray: " + findMax2dArray(arrays));

        // 1295. Find Numbers with Even Number of Digits: odd: 2,457,65757 | even: 23,
        // 4575, 568798
        System.out.println("findEvenLengthNumbers: " + findEvenLengthNumbers(arr));

        // 1672. Richest Customer Wealth
        /*
        1st customer has wealth = 6
        2nd customer has wealth = 10 
        3rd customer has wealth = 8
        The 2nd customer is the richest with a wealth of 10.
         */
        int wealth[][] = { { 1, 5 }, { 7, 3 }, { 3, 5 } };
        System.out.println("maximumWealth: " + maximumWealth(wealth));

    }

    static int getIndexBylinearSearch(int[] arr, int target) {
        if (arr.length <= 0)
            return -1;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == target)
                return i;

        return -1;
    }

    static boolean isPresentBylinearSearch(int[] arr, int target) {
        if (arr.length <= 0)
            return false;
        for (int val : arr)
            if (val == target)
                return true;
        return false;
    }

    static boolean isCharPresentInStr(String str, char target) {
        if (str.length() == 0)
            return true;
        for (char c : str.toCharArray())
            if (c == target)
                return true;
        return false;
    }

    static int getIndexInStr2(String str, char target) {
        if (str.length() == 0)
            return -1;
        for (int i = 0; i < str.length(); i++)
            if (str.charAt(i) == target)
                return i;
        return -1;
    }

    static int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++)
            if (arr[i] < min) // swap using X-OR
                min = arr[i];
        return min;
    }

    static int[] isPresent2dArray(int arr[][], int target) {
        for (int row = 0; row < arr.length; row++)
            for (int col = 0; col < arr[row].length; col++)
                if (arr[row][col] == target)
                    return new int[] { row, col };
        return new int[] { -1, -1 };
    }

    static int findMax2dArray(int arr[][]) {
        if (arr.length == 0)
            return -1;

        int max = Integer.MIN_VALUE;
        for (int[] row : arr)
            for (int value : row)
                if (value > max)
                    max = value;

        return max;
    }

    static int findEvenLengthNumbers(int[] nums) {
        int count = 0;
        // 42.35 MB : 1ms
        // for(int i=0; i<nums.length; i++) {
        for (int num : nums) {
            if ((int) Math.log10(num) % 2 == 1)
                count++;
        }
        // 44 MB : 2ms
        // for(int i=0; i<nums.length; i++) {
        // if(String.valueOf(nums[i]).length() %2 ==0)
        // count++;
        // }

        // Memory: 42.43 : 1ms
        // for(int i=0; i<nums.length; i++) {
        // int size = 0;
        // while(nums[i] > 0) {
        // ++size;
        // nums[i] = nums[i]/10;
        // }
        // if(size%2 == 0)
        // count++;
        // }

        // Memory: 42.81 : 1ms
        // for(int num : nums) {
        // int size = 0;
        // while(num > 0) {
        // ++size;
        // num = num/10;
        // }
        // if(size%2 == 0)
        // count++;
        // }
        return count;
    }

    static int maximumWealth(int[][] accounts) {
        int maxWealth = Integer.MIN_VALUE;
        for (int[] account : accounts) {
            int currentWealth = 0;
            for (int wealth : account)
                currentWealth += wealth;
            if (currentWealth > maxWealth)
                maxWealth = currentWealth;
        }
        return maxWealth;
    }
}
