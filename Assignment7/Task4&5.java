package com.assign7;

public class Assign7 {

    /**
     * task 4
     *
     * @param n
     */
    public void printPascalTriangle(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n - i; j++) {
                System.out.print(" ");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print(pascal(i, j) + " ");
            }
            System.out.println();
        }
    }

    private int pascal(int i, int j) {
        if (j == 0 || j == i) {
            return 1;
        } else {
            return pascal(i - 1, j - 1) + pascal(i - 1, j);
        }
    }

    /**
     * Extra Credit
     *
     * @param arr
     * @return whether a given array can be partitioned into two subsets
     * such that the sum of elements in both subsets is same
     */
    public boolean findPartition(int[] arr) {
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }
        if (sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        boolean[] dp = new boolean[sum + 1];
        for (int i = 0; i <= sum; i++) {
            dp[i] = false;
        }
        dp[0] = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = sum; j >= arr[i]; j--) {
                dp[j] = dp[j] || dp[j - arr[i]];
            }
        }
        return dp[sum];
    }
}
