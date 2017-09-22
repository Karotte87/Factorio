package util;

import products.Product;

public class Resource {
	private float number;
	private Product product;

	public Resource(float multiplier, Product instance) {
		number = multiplier;
		product = instance;
	}

	public float getNumber() {
		return number;
	}

	public void setNumber(float number) {
		this.number = number;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String toString() {
		return number + "x " + product.getName();
	}
}
