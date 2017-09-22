package technology;

import java.util.LinkedList;
import java.util.List;

import products.Product;
import util.Unit;

public class Technology {

	private String name;
	private List<Product> effects = new LinkedList<>();
	private Unit unit;
	private List<Technology> prerequisites = new LinkedList<>();

	protected Technology(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public List<Product> getEffects() {
		return effects;
	}

	public void addEffect(Product effect) {
		effects.add(effect);
	}

	public Unit getUnit() {
		return unit;
	}

	public void setUnit(Unit unit) {
		this.unit = unit;
	}

	public List<Technology> getPrerequisites() {
		return prerequisites;
	}

	public void addPrerequisite(Technology prerequisite) {
		prerequisites.add(prerequisite);
	}
}