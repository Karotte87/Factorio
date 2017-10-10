package products;

import java.util.HashSet;
import java.util.Set;

import util.Resource;

public class Product {
	private String name;
	private Set<Resource> educts = new HashSet<>();
	private Set<Resource> products = new HashSet<>();
	private double time = 0.5;
	private int resultCount = 1;

	protected Product(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time2) {
		this.time = time2;
	}

	public Set<Resource> getEducts() {
		return educts;
	}

	public void setEducts(Set<Resource> educts) {
		this.educts = educts;
	}

	public void addEduct(Resource r) {
		if (!educts.contains(r))
			educts.add(r);
	}

	public Set<Resource> getProducts() {
		return products;
	}

	public void setProducts(Set<Resource> products) {
		this.products = products;
	}

	public void addProduct(Resource r) {
		if (!products.contains(r))
			products.add(r);
	}

	public int getResultCount() {
		return resultCount;
	}

	public void setResultCount(int resultCount) {
		this.resultCount = resultCount;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(name + ": ");
		for (Resource r : educts) {
			sb.append(r.toString() + " ");
		}
		sb.append("; " + time);
		return sb.toString();
	}
}
