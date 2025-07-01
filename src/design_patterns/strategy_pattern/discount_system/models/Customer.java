package design_patterns.strategy_pattern.discount_system.models;

public class Customer {
	private String name;
	private boolean isNewCustomer;
	private int orderCount;
	public Customer(String name, boolean isNewCustomer, int orderCount) {
		this.name = name;
		this.isNewCustomer = isNewCustomer;
		this.orderCount = orderCount;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isNewCustomer() {
		return isNewCustomer;
	}
	public void setNewCustomer(boolean isNewCustomer) {
		this.isNewCustomer = isNewCustomer;
	}
	public int getOrderCount() {
		return orderCount;
	}
	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}
	
	

}
