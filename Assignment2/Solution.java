package com.javaClass;

import java.util.ArrayList;
import java.util.List;

public class Solution {

	/*
	 * 1. Write a java function to calculate the salary of an employee based on
	 * the following rules. i. function takes input of number of hours an
	 * employee worked and returns the salary. ii. The first 36 hours worked are
	 * paid at a rate of 15.0, then the next 5 hours are paid at a rate of 15 *
	 * 1.5. Hours after that up to a max of 48 are paid at a rate of 15 * 2.
	 */

	final double BASESALARY = 15;
	final List<Double> RATE = new ArrayList<Double>() {
		{
			add(1.0);
			add(1.5);
			add(2.0);
		}
	};

	private double fristSalary(double hours) {
		if (hours <= 0) {
			return 0;
		}
		if (hours > 36) {
			hours = 36;
		}
		return BASESALARY * RATE.get(0) * hours;
	}

	private double secondSalary(double hours) {
		hours -= 36;
		if (hours <= 0) {
			return 0;
		} else if (hours > 5) {
			hours = 5;
		}
		return BASESALARY * RATE.get(1) * hours;
	}

	private double thirdSalary(double hours) {
		hours -= 41;
		if (hours <= 0) {
			return 0;
		} else if (hours > 7) {
			hours = 7;
		}
		return BASESALARY * RATE.get(2) * hours;
	}

	public double employeeSalary(double hours) {
		if (hours <= 0)
			return 0;
		return fristSalary(hours) + secondSalary(hours) + thirdSalary((hours));
	}

	/*
	 * 2. Write a java function that adds all the digits of an integer until it
	 * is single digit. i. function takes an integer as input and returns its
	 * sum of digits. ii. for example input = 37, sum = 3+7 = 10, sum = 1+0 = 1.
	 * result = 1.
	 */
	public int addDigits(int input) {

		if (input >= 0 && input < 10) {
			return input;
		}

		int sum = 0;
		while (input != 0) {
			int remainder = input % 10;
			sum += remainder;
			input = input / 10; 
		}
		return addDigits(sum);
	}

	/*
	 * 3. Write a java function to print all perfect number between 1 and n. i.
	 * Perfect number is a positive integer which is equal to the sum of its
	 * proper positive divisors. ii. For example: 6 is the first perfect number,
	 * Proper divisors of 6 are 1, 2, 3. Sum of its proper divisors = 1 + 2 + 3=
	 * 6.
	 */
	public boolean checkPerfectNumber(int num) {
		if (num == 1)
			return false;
		int sum = 0;
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				sum += i + num / i;
			}
		}
		sum++;
		return sum == num;
	}

	public void printPerfectNumbers(int num) {
		for (int i = 2; i < num + 1; i++) {
			if (checkPerfectNumber(i)) {
				System.out.println(i);
			}
		}
	}

	/*
	 * 6. Write a Java program that generates an isosceles right angled triangle
	 * made of asterisks. i. function should take input of one equal side as
	 * integer. Other than the edges the inner part of the triangle should be
	 * empty. ii. For example input is 6. the function should print—
	 */
	public void printIsoscelesTriangle(int n) {

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < i; j++) {
				if (j == 0 || i == (n - 1)) {
					System.out.print("*");
				} else {
					System.out.print(" ");
				}
			}
			System.out.println("*");
		}
	}

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.employeeSalary(47));
		System.out.println(s.addDigits(55));
		s.printPerfectNumbers(10000);
		s.printIsoscelesTriangle(7);
	}
	
}
