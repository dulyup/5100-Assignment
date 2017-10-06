package com.assign4;

public class Assign4 {
    // 1.Format a license key
    public String format(String S, int K) {
        StringBuilder sb = new StringBuilder();
        char[] chars = S.toCharArray();
        int length = 0;
        for (int i = chars.length - 1; i >= 0; i--) {
            if (chars[i] == '-')
                continue;
            if (chars[i] >= 'a')
                chars[i] = (char) (chars[i] - 32);
            sb.append(chars[i]);
            length++;
            if (length == K) {
                sb.append('-');
                length = 0;
            }
        }
        if (sb.length() != 0 && sb.charAt(sb.length() - 1) == '-')
            sb.delete(sb.length() - 1, sb.length());
        return sb.reverse().toString();

    }

    //5.Int into Roman
    public String intToRoman(int num) {
        String M[] = {"", "M", "MM", "MMM"};
        String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
        String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        return M[num / 1000] + C[(num % 1000) / 100] + X[(num % 100) / 10] + I[num % 10];
    }

    //Extra：Find the median of the two sorted array
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {

        if ((nums1 == null || nums1.length == 0) && (nums2 == null || nums2.length == 0))
            return -1;
        if ((nums1 == null || nums1.length == 0) && (nums2 != null || nums2.length != 0))
            return findMedianInOneArray(nums2);
        if ((nums2 == null || nums2.length == 0) && (nums1 != null || nums1.length != 0))
            return findMedianInOneArray(nums1);

        int i = 0, j = 0, index = 0, len = nums1.length + nums2.length;
        int[] temp = new int[len];
        while (i < nums1.length && j < nums2.length)

        {
            if (nums1[i] <= nums2[j]) {
                temp[index] = nums1[i];
                i++;
            } else {
                temp[index] = nums2[j];
                j++;
            }
            index++;
        }
        while (i < nums1.length) temp[index++] = nums1[i++];
        while (j < nums2.length) temp[index++] = nums2[j++];
        return findMedianInOneArray(temp);
    }

    public double findMedianInOneArray(int[] nums) {
        if (nums == null || nums.length == 0) return -1;
        int len = nums.length;
        if (len % 2 == 1) return (double) nums[len / 2];
        if (len % 2 == 0) return (double) (nums[len / 2 - 1] + nums[len / 2]) / 2;
        return -1;
    }

    public static void main(String[] args) {
        Assign4 a = new Assign4();
        System.out.println(a.format("2-4A0r7-4k", 3));
        int[] nums1 = {3};
        int[] nums2 = {1, 2};
        System.out.println(a.findMedianSortedArrays(nums1, nums2));
    }
}
