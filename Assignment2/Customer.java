// score 2 + extra credit 1
package com.javaClass;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map.Entry;

/*5. Write a java class called customer (Add detail as needed) : 
              i. Create Attributes: customer name and which pizzas customer has ordered. 
              ii. Think about what kind of data structure can be used to record the pizza name and number of each kind of pizza.( Give me your thought, Extra credit of 1 point)
              iii. In main method , sum up how much money the customer spent. */
public class Customer {

	private String name;
	private HashMap<Pizza, Integer> orderMap = new HashMap<Pizza, Integer>();

	public Customer(String name) {
		super();
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void addOrder(Pizza pizza, int number) {
		orderMap.put(pizza, number);
	}

	public BigDecimal count() {
		BigDecimal totalPrice = new BigDecimal(0);
		for (Entry<Pizza, Integer> order : orderMap.entrySet()) {
			Pizza pizza = order.getKey();
			BigDecimal number = new BigDecimal(order.getValue());
			totalPrice = totalPrice.add(pizza.getPrice().multiply(number));
		}
		return totalPrice;
	}

	public static void main(String[] args) {
		Customer customer = new Customer("Peter");
		Pizza seafood = new Pizza("seafood", new BigDecimal(15));
		Pizza beef = new Pizza("beef", new BigDecimal(12));
		Pizza pork = new Pizza("pork", new BigDecimal(10));

		customer.addOrder(seafood, 3);
		customer.addOrder(beef, 1);
		customer.addOrder(pork, 2);

		System.out.println(customer.getName() + "'s order costs: " + customer.count());
	}

}
