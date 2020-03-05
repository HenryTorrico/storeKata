package store;

import java.util.HashMap;
import java.util.Map;

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
		DiscountCalculator discountCalculator = createDiscountCalculator();
		discount = discountCalculator.calculateDiscount(this);
		totalItem = calculateTotalAmount() - discount;
		return totalItem;
	}

	private DiscountCalculator createDiscountCalculator() {
		Map<ProductCategory, DiscountCalculator> mapProductDiscount = new HashMap<ProductCategory, DiscountCalculator>();
		mapProductDiscount.put(ProductCategory.Accessories, new AccessoryDiscount());
		mapProductDiscount.put(ProductCategory.Bikes, new BikeDiscount());
		mapProductDiscount.put(ProductCategory.Cloathing, new ClothDiscount());
		
		DiscountCalculator discountCalculator = null;
		
		
		if (getProduct().getCategory() == ProductCategory.Accessories) {
			discountCalculator = new AccessoryDiscount();
		}
		if (getProduct().getCategory() == ProductCategory.Bikes) {
			discountCalculator = new BikeDiscount();
		}
		if (getProduct().getCategory() == ProductCategory.Cloathing) {
			discountCalculator = new ClothDiscount();
		}
		return mapProductDiscount.get(getProduct().getCategory());
	}
	float calculateTotalAmount() {
		return getProduct().getUnitPrice() * getQuantity();
	}
}

