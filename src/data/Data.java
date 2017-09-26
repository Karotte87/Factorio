package data;

import java.util.List;

import parser.Factorio;
import products.Product;

public class Data {

	public Data() {
		List<Product> a = new Factorio().getAllProducts();
		
	}
}
