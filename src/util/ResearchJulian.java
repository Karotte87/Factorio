package util;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;

import products.Product;

public class ResearchJulian {

	private NewMap<Product, Double> sammler = new NewMap<>();
	private NewMap<Product, Double> newSammler = new NewMap<>();

	public void initiateSammler(Product p, double n) {
		sammler.put(p, n);
	}

	public void addToSammler(Product p, double n) {
		double d = n;
		if (newSammler.containsKey(p)) {
			d += newSammler.get(p);
		}
		newSammler.put(p, d);
	}

	public void recycleProducts() {
		Set<Entry<Product, Double>> entries = sammler.entrySet();
		Iterator<Entry<Product, Double>> i = entries.iterator();
		Entry<Product, Double> e;
		Product p;
		while (i.hasNext()) {
			e = (Entry<Product, Double>) i.next();
			p = e.getKey();
			if (p.getName().equals("iron-plate") || p.getName().equals("copper-plate")) {
				addToSammler(p, e.getValue());

			} else {
				Set<Resource> tmp = p.getEducts();
				for (Resource r : tmp) {
					addToSammler(r.getProduct(), r.getNumber() * e.getValue());
				}
			}
		}
		sammler = newSammler.clone();
		newSammler.clear();
	}

	public String toString() {
		Set<Entry<Product, Double>> s = sammler.entrySet();
		StringBuilder sb = new StringBuilder();
		for (Entry<Product, Double> e : s) {
			sb.append(e.getValue() + "x " + e.getKey() + "\n");
		}
		return sb.toString();
	}
}
