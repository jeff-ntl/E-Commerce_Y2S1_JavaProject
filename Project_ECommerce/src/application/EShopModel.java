package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class EShopModel {
	//attributes
	private ArrayList<Product> stock;

	//constructor
	public EShopModel()
	{
		stock = new ArrayList<Product>();
	}
	
	//method
	//to add product
	public void addProduct(int id, String name, String description, double weight, double cost, int quantity, String cat, String subCat)
    { 
      Product product = new Product(id,name,description,weight,cost,quantity,cat,subCat);
      stock.add(product);
    }
	
	//to remove product
	public void removeProduct(int id) {
		stock.remove(id);
	}
	
	//list item in Admin.fxml
	public String listAllProduct() {
		String listOfProducts = "";
		for(Product item:stock) {
			listOfProducts += item.toString() +"\n";
		}
		return listOfProducts;
	}
	
	//look for the actual index of an object with given id
	public int productIndex(int id) {
		for(Product item:stock) {
			if(id == item.getId()) {
				return stock.indexOf(item);
			}
		}
		return -999;
	}
	
	//look for the actual index of an object with given product name
		public int productIndex(String name) {
			for(Product item:stock) {
				if(name.equals(item.getName())) {
					return stock.indexOf(item);
				}
			}
			return -999;
		}
	 
	//to save products to products.xml
	public void saveProducts() throws Exception
    {
   	 XStream xstream = new XStream(new DomDriver());
   	 ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("products.xml"));
   	 out.writeObject(stock);
   	 out.close();
    }
    
	//to load products from products.xml
    @SuppressWarnings("unchecked")
	 public void loadProducts() throws Exception
    {
   	 XStream xstream = new XStream(new DomDriver());
   	 ObjectInputStream is = xstream.createObjectInputStream(new FileReader("products.xml"));
   	 stock = (ArrayList<Product>) is.readObject();
   	 is.close();
    }

    //getters & setters
	public ArrayList<Product> getStock() {
		return stock;
	}

	public void setStock(ArrayList<Product> stock) {
		this.stock = stock;
	}

	
}
