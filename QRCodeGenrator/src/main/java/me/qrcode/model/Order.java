package me.qrcode.model;

public class Order {
	
	final private int id;
	final private String name;
	final private int quantity;
	
	public Order(int id, String name, int quantity) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
	}

	
	@Override
	public String toString() {
		return "Order [id=" + id + ", name=" + name + ", quantity=" + quantity + "]";
	}
	

}
