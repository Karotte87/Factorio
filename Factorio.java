public class Factorio {
	
	List<Product> allProducts = new LinkedList<>();
	
	public Factorio(){
		readFile();
	}
	
	public void readFile(){
		File fac = new File("Factorio.txt");
		FileReader fr = new FileReader(fac);
		BufferedReader br = new BufferedReader(fr);
		String[] tmp;
		String[] tmp2;
		String[] tmp3;
		String educts;
		String products;
		float divider = 1;
		while ((str = br.readLine()) != null){
			tmp = str.split(":");
			name = tmp[0];
			if (name.contains("x ")){
				divider = Integer.parse(name.split("x ")[0].trim());
				name = name.split(" ")[1];
			}
			Product p = ProductFactory.getInstance(name);
			allProducts.add(p);
			
			tmp = tmp[1].split(";");
			educts = tmp[0];
			
			p.setTime(Float.parse(tmp[1].trim()));
			
			if (tmp.length == 3){
				products = tmp[2];
			}
			tmp2 = educts.split(",");
			for (i = 0; i < tmp2.length; i++){
				tmp3 = tmp[i].split("x ");
				float multiplier = Float.parse(tmp3[0].trim());
				p.addEduct(new Resource(multiplier / divider, ProductFactory.getInstance(tmp3[1].trim())));
			}
			if (tmp.length == 3){
				tmp2 = products.split(",");
				for (i = 0; i < tmp2.length; i++){
					tmp3 = tmp[i].split("x ");
					float multiplier = Float.parse(tmp3[0].trim());
					p.addProduct(new Resource(multiplier, ProductFactory.getInstance(tmp3[1].trim())));
				}
			}
		}
	}
	
	public void showResources(String name){
		Product overview;
		for (Product p : allProducts){
			if (p.name.equals(name)){
				overview = p;
				break;
			}
		}
		System.out.println();
	}
	
	public static void main(String[] args){
		Factorio fac = new Factorio();
		fac.showOverview("Steel plate");
	}
}

public class Resource{
	
	private float number;
	private Product product;
	
	
	
	public float getNumber(){
		return number;
	}
	
	public void setNumber(float number){
		this.number = number;
	}
	
	public Product getProduct(){
		return product;
	}
	
	public void setProduct(Product product){
		this.product = product;
	}
	
	public String toString(){
		return number + "x " + product.getName();
	}
}

public class ProductFactory{
	
	private static List<Product> singletons = new LinkedList<>();
	
	public static Product getInstance(String name){
		for (Product p : singletons){
			if (name.equals(p.getName())){
				return p;
			}
		}
		Product p = new Product(name);
		singletons.add(p);
		return p;
	}
}

public class Product {
	
	private String name;
	private List<Resource> educts = new LinkedList<>();
	private List<Resource> products = new LinkedList<>();
	private float time;
	
	protected Product(String name){
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public float getTime(){
		return time;
	}
	
	public void setTime(float time){
		this.time = time;
	}
	
	public List<Resource> getEducts(){
		return educts;
	}
	
	public void addEduct(Resource r){
		if(!educts.contains(r))
			educts.add(r);
	}
	
	public List<Resource> getProducts(){
		return products;
	}
	
	public void addProduct(Resource r){
		if(!products.contains(r))
			products.add(r);
	}
}