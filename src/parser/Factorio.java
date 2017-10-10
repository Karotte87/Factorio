package parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import products.Product;
import products.ProductFactory;
import util.Resource;

public class Factorio {
	List<Product> allProducts = new LinkedList<>();

	private final String pathJulian = "";
	private String pathPhilipp = "D:\\\\Spiele\\\\Factorio\\\\data\\\\base\\\\prototypes\\\\recipe";

	public Factorio() {
		try {
			readFile();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void readFile() throws NumberFormatException, IOException {
		File recipes = new File(pathPhilipp);
		File[] files = recipes.listFiles();
		if (files == null) {
			recipes = new File(pathJulian);
			files = recipes.listFiles();
		}
		for (File file : files) {
//			if (file.getName().contains("demo-")) {
//				continue;
//			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String name;
			String[] tmp;
			String str;
			Set<Resource> products = new HashSet<>();
			Product p = null;
			double multiplier;
			double time = 0.5;
			boolean flag = false;
			boolean ignore = false;
			int whitelines = 0;

			while ((str = br.readLine()) != null) {
				if (str.contains("type = \"recipe\"")) { // ein neues rezept fängt an
					ignore = false;
					whitelines = 0;
					if (p != null) {
						p.setTime(time);
						allProducts.add(p);
					}
					products.clear();
					time = 0.5;
					p = null;
				} else if (ignore) {
					continue;
				} else if (str.contains("expensive =")) { // expensive wird vorerst ignoriert
					ignore = true;
				} else if (p != null && str.contains(p.getName()) && str.contains("result =")) {
					continue;
				} else if (flag) { // flag bedeutet, man befindet sich in den ingredients
					str = str.replace("}", "").replace(",", "").replace("{", "").replace("\"", "").replace(" = ", "=")
							.replace("= ", "=").trim();

					if (str.contains("type=fluid")) { // type=fluid -> special edukt
						tmp = str.split(" ");
						name = tmp[1].split("=")[1];
						multiplier = Double.parseDouble(tmp[2].split("=")[1].trim());
						p.addEduct(new Resource(multiplier, ProductFactory.getInstance(name)));
					} else if (whitelines == 1 && str.trim().isEmpty()) { // ingredients vorbei
						flag = false;
						continue;
					} else if (str.trim().isEmpty()) {
						whitelines++;
						continue;
					} else if (str.contains("type=item")) {
						tmp = str.trim().split(" ");
						p.addEduct(new Resource(Double.parseDouble(tmp[2].split("=")[1]),
								ProductFactory.getInstance(tmp[1].split("=")[1])));
					} else { // normales edukt
						tmp = str.split(" ");
						p.addEduct(new Resource(Double.parseDouble(tmp[1]), ProductFactory.getInstance(tmp[0])));
					}
				} else if (str.contains("name = ")) { // der name des produkts
					p = ProductFactory.getInstance(str.split("\"")[1]);
//					if (p.getName().equals("electronic-circuit")) {
//						System.out.println("Fire in the hole");
//					}
				} else if (str.trim().equals("ingredients =")) { // ingredients fängt an
					flag = true;
				} else if (str.contains("ingredients = ")) {
					tmp = str.replace("}", "").replace("{", "").replace(",", "").replace("\"", "").split("= ");
					tmp = tmp[1].trim().split(" ");
					for (int i = 0; i < tmp.length / 2; i++) {
						p.addEduct(
								new Resource(Double.parseDouble(tmp[2 * i + 1]), ProductFactory.getInstance(tmp[2 * i])));
					}
				} else if (str.contains("result_count = ")) { // anzahl der produkte, die auf einmal produziert werden
					p.setResultCount(Integer.parseInt(str.split("=")[1].replace(",", "").trim()));
				} else if (str.contains("energy_required")) {
					time = Double.parseDouble(str.split("=")[1].replace(",", "").trim());
				}
			}
			p.setTime(time);
			p.setProducts(products);
			allProducts.add(p);
			br.close();
		}
	}

	public void showResource(String name, boolean expanded) {
		Product overview = null;
		StringBuilder sb = new StringBuilder();
		for (Product p : allProducts) {
			System.out.println(p.getName());
			if (p.getName().equals(name)) {
				overview = p;
				break;
			}
		}
		if (overview == null) {
			System.out.println("Produkt nicht gefunden");
			return;
		}
		sb.append(overview.toString());
		System.out.println(sb.toString());
		if (expanded) {
			sb.append("\n");
			for (Resource r : overview.getEducts()) {
				sb.append(r.toString() + "\n");
				for (Resource r2 : r.getProduct().getEducts()) {
					sb.append(r2.toString() + "\n");
				}

			}
			System.out.println(sb.toString());
		}

	}

	public Product getResource(String name) {
		for (Product p : allProducts) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	public static void main(String[] args) {
		Factorio fac = new Factorio();
		fac.showResource("electronic-circuit", true);

	}

	public List<Product> getAllProducts() {
		return allProducts;
	}
}
