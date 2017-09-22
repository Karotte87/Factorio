import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ResearchJulian {

	private float alltime;
	private Product product;
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
		Iterator i = entries.iterator();
		Object o;
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
