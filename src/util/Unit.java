package util;

import java.util.LinkedList;
import java.util.List;

import science.SciencePack;

public class Unit {

	private int count;
	private List<SciencePack> ingredients = new LinkedList<>();
	private double time;

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<SciencePack> getIngredients() {
		return ingredients;
	}

	public void addIngredient(SciencePack ingredient) {
		ingredients.add(ingredient);
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Count: " +count + "\nIngredients: ");
		sb.append(ingredients.get(0).toString());
		for (int i = 1; i < ingredients.size(); i++) {
			sb.append(", " + ingredients.get(i).toString());
		}
		sb.append("\nTime: " + time);
		return sb.toString();
	}
}