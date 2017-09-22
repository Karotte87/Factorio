package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import products.ProductFactory;
import technology.Technology;
import technology.TechnologyFactory;
import util.Unit;

public class Parser {

	private List<Technology> allTechnologies = new LinkedList<>();

	public void readFile() throws NumberFormatException, IOException {
		File tech = new File("D:\\Spiele\\Factorio\\data\\base\\prototypes\\technology\\technology.lua");
		FileReader fr = new FileReader(tech);
		BufferedReader br = new BufferedReader(fr);

		boolean effects = false;
		boolean unit = false;
		boolean ingredients = false;
		String str;
		Unit u = null;
		Technology t = null;
		int bracketcounter = 0;

		while ((str = br.readLine()) != null) {
			if (effects || unit || ingredients) {
				char[] chars = str.trim().toCharArray();
				for (char c : chars) {
					if (c == '{') {
						bracketcounter++;
					}
					if (c == '}') {
						bracketcounter--;
					}
				}
				if (bracketcounter == 0) {
					if (unit) {
						t.setUnit(u);
						unit = false;
					}
					if (effects) {
						effects = false;
					}
					if (ingredients) {
						ingredients = false;
					}
				}
			}
			if (str.contains("type = \"technology\"")) { // eine neue technologie fängt an

			} else if (str.contains("name = ")) {
				t = TechnologyFactory.getInstance(str.split("\"")[1]);
			} else if (str.contains("effects =")) {
				effects = true;
			} else if (effects && str.contains("type = \"unlock-recipe\"")) {

			} else if (effects && str.contains("recipe = ")) {
				t.addEffect(ProductFactory.getInstance(str.split("\"")[1]));
			} else if (str.contains("unit =")) {
				unit = true;
				u = new Unit();
			} else if (unit && str.contains("count = ")) {
				u.setCount(Integer.parseInt(str.split("=")[1].replace(",", "").trim()));
			} else if (unit && str.trim().equals("ingredients =")) {
				ingredients = true;
			} else if (unit && str.contains("ingredients = ")) {

			} else if (unit && str.contains("time = ")) {
				u.setTime(Float.parseFloat(str.split("=")[1].trim()));
			} else if (ingredients) {

			}
		}
		allTechnologies.add(t);
		br.close();
	}
}
