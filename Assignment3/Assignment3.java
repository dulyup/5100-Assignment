package com.Assign3;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Assignment3 {
	// 3.remove vowels in a string
	public String removeVowelsFromString(String input) {
		StringBuilder result = new StringBuilder();
		Set<Character> vowels = new HashSet<Character>();
		vowels.add('a');
	    vowels.add('A');
	    vowels.add('e');
	    vowels.add('E');
	    vowels.add('i');
	    vowels.add('I');
	    vowels.add('o');
	    vowels.add('O');
	    vowels.add('u');
	    vowels.add('U');
		for (int i = 0; i < input.length(); i++) {
			if (vowels.contains(input.charAt(i))) {
				continue;
			}
			result.append(input.charAt(i));
		}
		return result.toString();
	}

	// 4.check if two strings are Anagrams or not (1.two same strings are
	// anagrams 2.consider lowercase and capital)
	public boolean checkIfTwoStringsAreAnagrams(String s1, String s2) {
		if (s1.length() != s2.length())
			return false;
		// ->array->sort->string->equal
		char[] char1 = s1.toCharArray();
		Arrays.sort(char1);
		String str1 = String.valueOf(char1);

		char[] char2 = s2.toCharArray();
		Arrays.sort(char2);
		String str2 = String.valueOf(char2);

		return str1.equals(str2);
	}

	public static void main(String[] args) {
		String string = "This Is Life";
		Assignment3 a = new Assignment3();
		System.out.println(a.removeVowelsFromString(string));

		String s1 = "Hello";
		String s2 = "eHlol";
		String s3 = "hallo";
		System.out.println(a.checkIfTwoStringsAreAnagrams(s1, s2));
		System.out.println(a.checkIfTwoStringsAreAnagrams(s1, s3));
	}
}
