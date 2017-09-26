package util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import products.Product;

public class ResearchJulian {

	private Map<Product, Float> sammler = new HashMap<>();

	public void addToSammler(Product p, float n) {
		if (sammler.containsKey(p)) {
			Float in = sammler.get(p);
			in += n;
			sammler.put(p, in);
		} else {
			sammler.put(p, n);
		}
	}

	public void recycleProducts() {
		Set<Entry<Product, Float>> entries = sammler.entrySet();
		Iterator<Entry<Product, Float>> i = entries.iterator();
		Entry<Product, Float> e;
		Product p;
		while (i.hasNext()) {
			e = (Entry<Product, Float>) i.next();
			p = e.getKey();
			if(p.getName().equals("iron-plate") || p.getName().equals("copper-plate")) {
				continue;
			}
			List<Resource> tmp = p.getEducts();
			for (Resource r : tmp) {
				addToSammler(r.getProduct(),r.getNumber()*e.getValue());
			}
			i.remove();
		}
	}
	
}
