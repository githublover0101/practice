package ood.opentable;

public class Table {

	private int id;
	private int restaurantId;
	private int capacity;
	
	
	public Table() {
		
	}
	
	public Table(int id, int restaurantId, int capacity) {
		this.id = id;
		this.restaurantId = restaurantId;
		this.capacity = capacity;
	}
	
	public int getId() {
		return this.id;
	}
	
	
	public int getRestaurantId() {
		return this.restaurantId;
	}
	
	public int getCapacity() {
		return this.capacity;
	}
	
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setRestaurantId(int restaurantId) {
		this.restaurantId = restaurantId;
	}
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
}
