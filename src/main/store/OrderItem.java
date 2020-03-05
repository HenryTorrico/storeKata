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
		float itemAmount = getAmount(this);
		if (isAccesory(this)) {
			float booksDiscount = 0;
			if (applyAccesoryDiscount(itemAmount)) {
				booksDiscount = discountForBook(itemAmount);
			}
			totalItem = discountForCloth(itemAmount, booksDiscount);
		}
		if (isBike(this)) {
			// 20% discount for Bikes
			totalItem = discountForBike(itemAmount);
		}
		if (isCloth(this)) {
			float cloathingDiscount = 0;
			if (applyClothDiscount(this)) {
				cloathingDiscount = this.getProduct().getUnitPrice();
			}
			totalItem = discountForCloth(itemAmount, cloathingDiscount);
		}
		return totalItem;
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

