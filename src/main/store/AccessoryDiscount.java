package store;

public class AccessoryDiscount implements DiscountCalculator  {

	public float calculateDiscount(OrderItem orderItem) {
		float booksDiscount = 0;
		if (orderItem.calculateTotalAmount() >= 100) {
			booksDiscount = orderItem.calculateTotalAmount() * 10 / 100;
		}
		return booksDiscount;
	}

}
