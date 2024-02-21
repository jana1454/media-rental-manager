package mediaRentalManager;

import java.util.ArrayList;

public class Customer implements Comparable<Customer>{
	private String name, address, plan;
	private ArrayList<String> queue, rented;
	private static int limitMax = 2;
	
	public Customer(String name, String address, String plan) {
		this.name = name;
		this.address = address;
		this.plan = plan;
		queue = new ArrayList<String>();
		rented = new ArrayList<String>();
	}
	
	public String toString() {
		String ans;
		
		ans = "Name: " + name + ", Address: " + address + ", Plan: " + plan 
				+ "\nRented: " + rented.toString() + "\nQueue: " 
				+ queue.toString();
		
		return ans;
	}
	
	public int compareTo(Customer customer) {
		return this.name.compareTo(customer.name);
	}
	
	public static void setLimit(int lim) { 
		limitMax = lim;
	}
	
	public static int getLimit() {
		return limitMax;
	}

	public String getName() {
		return name;
	}

	public String getPlan() {
		return plan;
	}
	
	public ArrayList<String> getQueue() {
		return queue;
	}

	public ArrayList<String> getRented() {
		return rented;
	}
	
	public int getRentedNum() {
		return rented.size();
	}
	
	public int getQueueNum() {
		return queue.size();
	}

}
