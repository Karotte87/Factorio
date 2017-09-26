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

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Name: " + name + "\n");
		sb.append("Effects: ");
		if (effects.size() > 0) {
			sb.append(effects.get(0).getName());
			for (int i = 1; i < effects.size(); i++) {
				sb.append(", " + effects.get(i).getName());
			}
		}
		if (unit != null) {
			sb.append("\nUnit:\n" + unit.toString());
		}
		sb.append("\nPrerequisites: ");
		if (prerequisites.size() > 0) {
			sb.append(prerequisites.get(0).getName());
			for (int i = 1; i < prerequisites.size(); i++) {
				sb.append(", " + prerequisites.get(i).getName());
			}
		}
		sb.append("\n");
		return sb.toString();
	}
}