package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import products.ProductFactory;
import science.SciencePack;
import science.SciencePackFactory;
import technology.Technology;
import technology.TechnologyFactory;
import util.Unit;

public class Parser {

	private List<Technology> allTechnologies = new LinkedList<>();

	public Parser() {
		try {
			readFile();
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFile() throws NumberFormatException, IOException {
		File tech = new File("D:\\Spiele\\Factorio\\data\\base\\prototypes\\technology\\technology.lua");
		FileReader fr = new FileReader(tech);
		BufferedReader br = new BufferedReader(fr);

		boolean effects = false;
		boolean unit = false;
		boolean ingredients = false;
		boolean robot = false;
		String str;
		Unit u = null;
		Technology t = null;
		int bracketcounter = 0;
		int robotcount = 0;

		while ((str = br.readLine()) != null) {
			if (robotcount == 2) {
				robot = false;
			}
			if (robot && str.contains("type = \"technology\"")) {
				robotcount++;
			}
			if (robot)
				continue;
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
			} else if (str.contains("function")) {
				robot = true;
			} else if (str.contains("name = ")) {
				// String b = str.split("\"")[1];
				// if(b.contains("follower-robot-count"))
				// System.out.println(b);
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
			} else if (unit && str.trim().equals("ingredients =")) { // mehrzeilige Ingredients
				ingredients = true;
			} else if (unit && str.contains("ingredients = ")) { // einzeilige Ingredients
				u.addIngredient(SciencePackFactory.getInstance(str.split("\"")[1]));
			} else if (unit && str.contains("time = ")) {
				u.setTime(Float.parseFloat(str.split("=")[1].replaceAll(",", "").trim()));
			} else if (ingredients) {
				if (str.contains("\"")) {
					u.addIngredient(SciencePackFactory.getInstance(str.split("\"")[1]));
				}
			} else if (str.contains("prerequisites =")) {
				String[] tmp = str.split("\"");
				for (int i = 0; i < tmp.length / 2; i++) {
					String a = tmp[2 * i + 1];
					Technology b = TechnologyFactory.getInstance(a);
					t.addPrerequisite(b);
				}
			}
			allTechnologies.add(t);
			// System.out.println(t.toString());
		}
		br.close();
	}

	private List<Technology> getAllTechnologies() {
		return allTechnologies;
	}

	public static void main(String[] args) {
		List<Technology> tmp = new Parser().getAllTechnologies();
		Set<Technology> set = new HashSet<>(tmp);
		set.remove(null);
		tmp = new LinkedList<>(set);
		for (Technology t : tmp) {
			System.out.println(t.toString());
		}
	}
}