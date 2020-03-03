package store;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Order {

	private Customer customer;
	private Salesman salesman;
	private Date orderedOn;
	private String deliveryStreet;
	private String deliveryCity;
	private String deliveryCountry;
	private Set<OrderItem> items;

	public Order(Customer customer, Salesman salesman, String deliveryStreet, String deliveryCity, String deliveryCountry, Date orderedOn) {
		this.customer = customer;
		this.salesman = salesman;
		this.deliveryStreet = deliveryStreet;
		this.deliveryCity = deliveryCity;
		this.deliveryCountry = deliveryCountry;
		this.orderedOn = orderedOn;
		this.items = new HashSet<OrderItem>();
	}

	public Customer getCustomer() {
		return customer;
	}

	public Salesman getSalesman() {
		return salesman;
	}

	public Date getOrderedOn() {
		return orderedOn;
	}

	public String getDeliveryStreet() {
		return deliveryStreet;
	}

	public String getDeliveryCity() {
		return deliveryCity;
	}

	public String getDeliveryCountry() {
		return deliveryCountry;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public float total() {
		float totalItems = calculateTotalForItems();

		if (isDeliveryInUsa()){
			// total=totalItems + tax + 0 shipping
			return totalItems + totalItems * 5 / 100;
		}

		// total=totalItemst + tax + 15 shipping
		return totalItems + totalItems * 5 / 100 + 15;
	}

	private float calculateTotalForItems() {
		float totalItems = 0;
		for (OrderItem item : items) {
			float totalItem=0;
			float itemAmount = getAmount(item);
			if (isAccesory(item)) {
				float booksDiscount = 0;
				if (applyAccesoryDiscount(itemAmount)) {
					booksDiscount = discountForBook(itemAmount);
				}
				totalItem = discountForCloth(itemAmount, booksDiscount);
			}
			if (isBike(item)) {
				// 20% discount for Bikes
				totalItem = discountForBike(itemAmount);
			}
			if (isCloth(item)) {
				float cloathingDiscount = 0;
				if (applyClothDiscount(item)) {
					cloathingDiscount = item.getProduct().getUnitPrice();
				}
				totalItem = discountForCloth(itemAmount, cloathingDiscount);
			}
			totalItems += totalItem;
		}
		return totalItems;
	}

	private float discountForCloth(float itemAmount, float cloathingDiscount) {
		return itemAmount - cloathingDiscount;
	}

	private float discountForBike(float itemAmount) {
		return itemAmount - itemAmount * 20 / 100;
	}

	private float discountForBook(float itemAmount) {
		return itemAmount * 10 / 100;
	}

	private boolean isDeliveryInUsa() {
		return this.deliveryCountry == "USA";
	}

	private boolean applyClothDiscount(OrderItem item) {
		return item.getQuantity() > 2;
	}

	private boolean isCloth(OrderItem item) {
		return item.getProduct().getCategory() == ProductCategory.Cloathing;
	}

	private boolean isBike(OrderItem item) {
		return item.getProduct().getCategory() == ProductCategory.Bikes;
	}

	private boolean applyAccesoryDiscount(float itemAmount) {
		return itemAmount >= 100;
	}

	private boolean isAccesory(OrderItem item) {
		return item.getProduct().getCategory() == ProductCategory.Accessories;
	}

	private float getAmount(OrderItem item) {
		return item.getProduct().getUnitPrice() * item.getQuantity();
	}
}
