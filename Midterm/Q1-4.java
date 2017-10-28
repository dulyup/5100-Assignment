package com.midterm;

public class Midterm {

    /**
     * Question 1
     * reverse even indices
     *
     * @param nums original array
     * @return an array that even indices are in reverse order and odd indices stay in the same position
     */
    public int[] reverseEvenIndices(int[] nums) { // score 6

        if (nums == null || nums.length == 0) {
            return null;
        }

        int start = 0;
        int end = nums.length - 1;

        if (end % 2 == 1) {
            end--;
        }

        while (start <= end) {
            if (start % 2 == 0 && end % 2 == 0) {
                swap(nums, start, end);
            }
            start++;
            end--;
        }
        return nums;
    }

    private int[] swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
        return nums;
    }

    /**
     * Question 2
     * The difference series
     *
     * @param n
     * @return
     */
    public int arrangeCoins(int n) { // score 7
        return (int) ((Math.sqrt(8 * n + 1) - 1) / 2);
    }

    /**
     * Question 3
     * sum + m * (n - 1) = x * n
     * x = minOfNums + m
     * m = sum - n * minOfNums
     *
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) { // score 7
        if (nums == null || nums.length == 0 || nums.length == 1) {
            return 0;
        }
        int sum = 0;
        int min = nums[0];
        for (int n : nums) {
            sum += n;
            if (min > n) {
                min = n;
            }
        }
        return sum - nums.length * min;
    }


    /**
     * Question 4
     *
     * @param m each dice has m faces
     * @param n there's n dices
     * @param x summation of each face of m dices,
     * @return number of possible ways to acquire sum as x
     */
    public int countNumberOfPossibleWays(int m, int n, int x) { // score 7
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            if (x >= 1 && x <= m) {
                return 1;
            } else {
                return 0;
            }
        }
        int result = 0;
        for (int i = 1; i <= m; ++i) {
            result += countNumberOfPossibleWays(m, n - 1, x - i);
        }
        return result;
    }

}
