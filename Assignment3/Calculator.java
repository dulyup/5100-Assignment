package com.Assign3;

import java.text.DecimalFormat;

public class Calculator {

	public double addition(double num1, double num2) {
		return num1 + num2;
	}

	public double subtraction(double num1, double num2) {
		return num1 - num2;
	}

	public double multiplication(double num1, double num2) {
		return num1 * num2;
	}

	public double division(double num1, double num2) {
		return num1 / num2;
	}

	public double squareRoot(double num) {
		return Math.sqrt(num);
	}

	public double square(double num) {
		return num *= num;
	}

	public double cube(double num) {
		return Math.pow(num, 3);
	}

	public String FahrenheitToCelsius(double num) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format((num - 32) * 5 / 9);
	}

	public String CelsiusToFahrenheit(double num) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format((num * 9 / 5 + 32));
	}

	public double FeetToInches(double num) {
		return 12 * num;
	}

	public double InchesToFeet(double num) {
		return num / 12;
	}

	public double[] quadraticEquation(double A, double B, double C) {
		double[] results = null;
		if (A == 0) {
			System.out.println("This is not quadratic equatio");
			return null;
		}
		if (A != 0) {
			double temp = B * B - 4 * A * C;
			if (temp > 0) {
				double result1 = (-B + Math.sqrt(temp)) / (2 * A);
				double result2 = (-B - Math.sqrt(temp)) / (2 * A);
				System.out.println("The roots are real and inequal");
				results = new double[2];
				results[0] = result1;
				results[1] = result2;
			} else if (temp == 0) {
				double result = (-B + Math.sqrt(temp)) / (2 * A);
				System.out.println("The root is real and equal");
				results = new double[1];
				results[0] = result;
			} else {
				System.out.println("The root is nonexistent!");
			}
		}
		return results;
	}

	public static void main(String[] args) {
		Calculator c = new Calculator();
		double[] d = c.quadraticEquation(1, -2, 1);
		for (double e : d) {
			System.out.println(e);
		}
		System.out.println(c.FahrenheitToCelsius(41.23));

	}
}
