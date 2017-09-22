package util;

import java.util.List;

import science.SciencePack;

public class Unit {

	private int count;
	private List<SciencePack> ingredients;
	private float time;

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

	public float getTime() {
		return time;
	}

	public void setTime(float time) {
		this.time = time;
	}
}