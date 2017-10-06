package com.JavaAssign4;

public class Assign4 {
	// Format a license key
	public String format(String S, int K) {
		StringBuilder sb = new StringBuilder();
		char[] chars = S.toCharArray();
		int length = 0;
		for (int i = chars.length - 1; i >= 0; i--) {
			if (chars[i] == '-')
				continue;
			if (chars[i] > 'a')
				chars[i] = (char) (chars[i] - 32);
			sb.append(chars[i]);
			length++;
			if (length == K) {
				sb.append('-');
				length = 0;
			}
		}
		if (sb.charAt(sb.length() - 1) == '-')
			sb.delete(sb.length() - 1, sb.length());
		return sb.reverse().toString();

	}

	public String intToRoman(int num) {
		    String M[] = {"", "M", "MM", "MMM"};
		    String C[] = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
		    String X[] = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
		    String I[] = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
		    return M[num/1000] + C[(num%1000)/100]+ X[(num%100)/10] + I[num%10];
    }
	
	public static void main(String[] args) {
		Assign4 a = new Assign4();
		System.out.println(a.format("2-4A0r7-4k", 3));
	}
	
	

	// Extra credit
//	public double findMedianSortedArrays(int[] nums1, int[] nums2) {
//	}
}
