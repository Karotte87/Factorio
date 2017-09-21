package products;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Factorio {
	List<Product> allProducts = new LinkedList<>();

	public Factorio() {
		try {
			readFile();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void readFile() throws NumberFormatException, IOException {
		File recipes = new File("D:\\Spiele\\Factorio\\data\\base\\prototypes\\recipe");
		File[] files = recipes.listFiles();
		for (File file : files) {
			if (file.getName().contains("demo-")) {
				continue;
			}
			System.out.println(file.getName());
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String name;
			String[] tmp;
			String str;
			List<Resource> educts = new LinkedList<>();
			List<Resource> products = new LinkedList<>();
			Product p = null;
			float multiplier;
			float time = 0.5F;
			boolean flag = false;
			boolean ignore = false;
			int whitelines = 0;

			while ((str = br.readLine()) != null) {
				if (str.contains("type = \"recipe\"")) { // ein neues rezept fängt an
					System.out.println("new recipe");
					ignore = false;
					whitelines = 0;
					if (p != null) {
						p.setTime(time);
						p.setEducts(educts);
					}
					time = 0.5F;
					p = null;
				} else if (ignore) {
					continue;
				} else if (str.contains("expensive =")) { // expensive wird vorerst ignoriert
					ignore = true;
				} else if (p != null && str.contains(p.getName()) && str.contains("result =")) {
					continue;
				} else if (flag) { // flag bedeutet, man befindet sich in den ingredients
					str = str.replace("}", "").replace(",", "").replace("{", "").replace("\"", "").trim();

					if (str.contains("type=fluid")) { // type=fluid -> special edukt
						tmp = str.split(" ");
						name = tmp[1].split("=")[1];
						multiplier = Float.parseFloat(tmp[2].split("=")[1].trim());
						educts.add(new Resource(multiplier, ProductFactory.getInstance(name)));
					} else if (whitelines == 1 && str.trim().isEmpty()) { // ingredients vorbei
						flag = false;
						System.out.println("flag = " + flag);
						continue;
					} else if (str.trim().isEmpty()) {
						whitelines++;
						continue;
					} else if (str.contains("type=item")) {
						tmp = str.trim().split(" ");
						educts.add(new Resource(Float.parseFloat(tmp[2].split("=")[1]),
								ProductFactory.getInstance(tmp[1].split("=")[1])));
					} else { // normales edukt
						tmp = str.split(" ");
						// System.out.println("last else: " + tmp[0] + " " + tmp[1]);
						if (p.getName().equals("discharge-defense-remote")) {
							System.out.println("we're headin in");
						}
						educts.add(new Resource(Float.parseFloat(tmp[1]), ProductFactory.getInstance(tmp[0])));
					}
				} else if (str.contains("name = ")) { // der name des produkts
					p = ProductFactory.getInstance(str.split("\"")[1]);
					System.out.println("Product: " + p.getName());
				} else if (str.trim().equals("ingredients =")) { // ingredients fängt an
					flag = true;
					System.out.println("Flag: " + flag);
				} else if (str.contains("ingredients = ")) {
					tmp = str.replace("}", "").replace(",", "").split("= ");
					tmp = tmp[1].trim().split(" ");
					educts.add(new Resource(Float.parseFloat(tmp[1]), ProductFactory.getInstance(tmp[0])));
				} else if (str.contains("result_count = ")) { // anzahl der produkte, die auf einmal produziert werden
					p.setResultCount(Integer.parseInt(str.split("=")[1].replace(",", "").trim()));
				} else if (str.contains("energy_required")) {
					time = Float.parseFloat(str.split("=")[1].replace(",", "").trim());
				}
			}
			br.close();
		}

	}

	public void showResources(String name) {
		Product overview;
		for (Product p : allProducts) {
			if (p.getName().equals(name)) {
				overview = p;
				break;
			}
		}
		System.out.println();
	}

	public static void main(String[] args) {
		Factorio fac = new Factorio();
		fac.showResources("Steel plate");
	}
}
