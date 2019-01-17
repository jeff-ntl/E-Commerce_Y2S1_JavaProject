package application;

public class Product {

	//attributes
	private int id;
	private String name;
	private String description;
	private double weight;
	private double cost;
	private int quantity;
	private boolean inStock;
	private String category;
	private String subCategory;

	//constructors
	public Product(int id, String name, String description, double weight,
			double cost, int quantity, String cat, String subCat) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.weight = weight;
		this.cost = cost;
		this.quantity = quantity;
		if (quantity >0){
			this.inStock = true;
		}else {
			this.inStock = false;
		}
		category =cat;
		subCategory =subCat;
	}
	
	//getters & setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isInStock() {
		return inStock;
	}

	public void setInStock(boolean inStock) {
		this.inStock = inStock;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getSubCategory() {
		return subCategory;
	}

	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description="
				+ description + ", weight=" + weight + ", cost=" 
				+ cost +", quantity=" + quantity+", Category=" + category
				+", Sub-Category=" + subCategory
				+ ", inStock=" + inStock + "]";
	}
}