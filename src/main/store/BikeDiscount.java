package store;

public class BikeDiscount implements DiscountCalculator {

	public float calculateDiscount(OrderItem orderItem) {
		return orderItem.calculateTotalAmount() * 20 / 100;
	}

}
