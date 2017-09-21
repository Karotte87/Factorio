package products;

import java.util.LinkedList;
import java.util.List;

public class Product {
	private String name;
	private List<Resource> educts = new LinkedList<>();
	private List<Resource> products = new LinkedList<>();
	private float time;
	private int resultCount = 1;

	protected Product(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}

	public List<Resource> getEducts() {
		return educts;
	}

	public void setEducts(List<Resource> educts) {
		this.educts = educts;
	}

	public void addEduct(Resource r) {
		if (!educts.contains(r))
			educts.add(r);
	}

	public List<Resource> getProducts() {
		return products;
	}
	
	public void setProducts(List<Resource> products) {
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

}
