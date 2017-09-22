
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
	
	public void monitorP(String name) {
		Product p = ProductFactory.getInstance(name);
//		if(allProducts.contains(p)) {
			System.out.println(p.toString());
//		}
	}

	public void readFile() throws NumberFormatException, IOException {
		File recipes = new File(
				"E:\\Program Files (x86)\\Steam\\steamapps\\common\\Factorio\\data\\base\\prototypes\\recipe");
		File[] files = recipes.listFiles();
		for (File file : files) {
			if (file.getName().contains("demo-")) {
				continue;
			}
			FileReader fr = new FileReader(file);
			BufferedReader br = new BufferedReader(fr);

			String name;
			String[] tmp;
			String str;
			List<Resource> products = new LinkedList<>();
			Product p = null;
			float multiplier;
			float time = 0.5F;
			boolean flag = false;
			boolean ignore = false;
			int whitelines = 0;

			while ((str = br.readLine()) != null) {
				monitorP("steel-chest");
				if (str.contains("type = \"recipe\"")) { // ein neues rezept fängt an
					ignore = false;
					whitelines = 0;
					if (p != null) {
						p.setTime(time);
						allProducts.add(p);
					}
					products.clear();
					time = 0.5F;
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
						multiplier = Float.parseFloat(tmp[2].split("=")[1].trim());
						p.addEduct(new Resource(multiplier, ProductFactory.getInstance(name)));
					} else if (whitelines == 1 && str.trim().isEmpty()) { // ingredients vorbei
						flag = false;
						continue;
					} else if (str.trim().isEmpty()) {
						whitelines++;
						continue;
					} else if (str.contains("type=item")) {
						tmp = str.trim().split(" ");
						p.addEduct(new Resource(Float.parseFloat(tmp[2].split("=")[1]),
								ProductFactory.getInstance(tmp[1].split("=")[1])));
					} else { // normales edukt
						tmp = str.split(" ");
						p.addEduct(new Resource(Float.parseFloat(tmp[1]), ProductFactory.getInstance(tmp[0])));
					}
				} else if (str.contains("name = ")) { // der name des produkts
					p = ProductFactory.getInstance(str.split("\"")[1]);
					if(p.getName().equals("steel-chest")) {
						System.out.println("Fire in the hole");
					}
				} else if (str.trim().equals("ingredients =")) { // ingredients fängt an
					flag = true;
				} else if (str.contains("ingredients = ")) {
					tmp = str.replace("}", "").replace("{", "").replace(",", "").replace("\"", "").split("= ");
					tmp = tmp[1].trim().split(" ");
					for (int i = 0; i < tmp.length / 2; i++) {
						p.addEduct(
								new Resource(Float.parseFloat(tmp[2 * i + 1]), ProductFactory.getInstance(tmp[2 * i])));
					}
				} else if (str.contains("result_count = ")) { // anzahl der produkte, die auf einmal produziert werden
					p.setResultCount(Integer.parseInt(str.split("=")[1].replace(",", "").trim()));
				} else if (str.contains("energy_required")) {
					time = Float.parseFloat(str.split("=")[1].replace(",", "").trim());
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
				sb.append(r.toString()+"\n");
				for (Resource r2 : r.getProduct().getEducts()) {
					sb.append(r2.toString()+"\n");
				}
				
			}
			System.out.println(sb.toString());
		}
		
	}
	
	public void researchJulian() {
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
		if (expanded+) {
			sb.append("\n");
			for (Resource r : overview.getEducts()) {
				sb.append(r.toString()+"\n");
				for (Resource r2 : r.getProduct().getEducts()) {
					sb.append(r2.toString()+"\n");
				}
				
			}
			System.out.println(sb.toString());
		}
	}

	public static void main(String[] args) {
		Factorio fac = new Factorio();
		fac.showResource("steel-chest", true);
	}
}
