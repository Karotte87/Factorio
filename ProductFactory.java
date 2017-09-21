package products;

import java.util.LinkedList;
import java.util.List;

public class ProductFactory {
	private static List<Product> singletons = new LinkedList<>();

	public static Product getInstance(String name) {
		for (Product p : singletons) {
			if (name.equals(p.getName())) {
				return p;
			}
		}
		Product p = new Product(name);
		singletons.add(p);
		return p;
	}
}
