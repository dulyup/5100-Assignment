package com.javaClass;

import java.math.BigDecimal;

public class Pizza {
	/*
	 * i. At least 3 attributes :pizza type and unit price and points for
	 * collection. More attributes are welcome to have. ii. Constructor is
	 * needed. Extra-credit for 0.5 point if you write more than 1 right
	 * constructor for this class
	 */

	private String type;
	private BigDecimal price;
	private int loyaltyPoints;
	private double calorie;

	public Pizza() {
		super();
	}

	public Pizza(String type) {
		super();
		this.type = type;
	}

	public Pizza(String type, BigDecimal price) {
		super();
		this.type = type;
		this.price = price;
	}

	public Pizza(String type, BigDecimal price, int loyaltyPoints) {
		super();
		this.type = type;
		this.price = price;
		this.loyaltyPoints = loyaltyPoints;
	}

	public Pizza(String type, BigDecimal price, int loyaltyPoints, double calorie) {
		super();
		this.type = type;
		this.price = price;
		this.loyaltyPoints = loyaltyPoints;
		this.calorie = calorie;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(int loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	public double getCalorie() {
		return calorie;
	}

	public void setCalorie(double calorie) {
		this.calorie = calorie;
	}

}
