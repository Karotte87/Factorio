package util;

import products.Product;

public class Resource {
	private double number;
	private Product product;

	public Resource(double multiplier, Product instance) {
		number = multiplier;
		product = instance;
	}

	public double getNumber() {
		return number;
	}

	public void setNumber(double number) {
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
