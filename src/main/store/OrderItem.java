package store;

public class OrderItem {
	
	private Product product;
	private int quantity;

	/*
	 * Order Item Constructor
	 */
	public OrderItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}
	
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}
	
	float calculateTotalForItem() {
		float totalItem=0;
		float discount = 0;
		float itemAmount = calculateTotalAmount();
		if (isAccesory(this)) {
			if (applyAccesoryDiscount(itemAmount)) {
				discount = discountForBook(itemAmount);
			}
			totalItem = discountForCloth(itemAmount, discount);
		}
		if (isBike(this)) {
			// 20% discount for Bikes
			totalItem = discountForBike(itemAmount);
		}
		if (isCloth(this)) {
			if (applyClothDiscount(this)) {
				discount = this.getProduct().getUnitPrice();
			}
			totalItem = discountForCloth(itemAmount, discount);
		}
		return totalItem;	
	}
	public float discountForCloth(float itemAmount, float cloathingDiscount) {
		return itemAmount - cloathingDiscount;
	}

	public float discountForBike(float itemAmount) {
		return itemAmount - itemAmount * 20 / 100;
	}

	public float discountForBook(float itemAmount) {
		return itemAmount * 10 / 100;
	}


	public boolean applyClothDiscount(OrderItem item) {
		return item.getQuantity() > 2;
	}

	public boolean isCloth(OrderItem item) {
		return item.getProduct().getCategory() == ProductCategory.Cloathing;
	}

	public boolean isBike(OrderItem item) {
		return item.getProduct().getCategory() == ProductCategory.Bikes;
	}

	public boolean applyAccesoryDiscount(float itemAmount) {
		return itemAmount >= 100;
	}

	public boolean isAccesory(OrderItem item) {
		return item.getProduct().getCategory() == ProductCategory.Accessories;
	}
	float calculateTotalAmount() {
		return getProduct().getUnitPrice() * getQuantity();
	}
}

