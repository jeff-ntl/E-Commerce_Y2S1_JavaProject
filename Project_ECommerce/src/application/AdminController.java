////imageview: https://stackoverflow.com/questions/40239934/change-imageview-image-in-code-using-javafx-scene-builder
package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;


public class AdminController implements Initializable
{
	//attributes
	ObservableList<String> categories = FXCollections.observableArrayList("All","Male", "Female", "Boy","Girl");
	ObservableList<String> subCategories = FXCollections.observableArrayList("All","Sport", "Fashion", "Work");
	
	@FXML private ComboBox<String> comboCategory = new ComboBox<String>();
	@FXML private ComboBox<String> comboSubCategory = new ComboBox<String>();

	@FXML private TextField txtID;
	@FXML private TextField txtName;
	@FXML private TextField txtDescription;
	@FXML private TextField txtWeight;
	@FXML private TextField txtCost;
	@FXML private TextField txtQuantity;
	
	
	@FXML private Button readButton;
	@FXML private Button createButton;
	@FXML private Button editButton;
	@FXML private Button removeButton;
	@FXML private Button saveButton;
	@FXML private Button loadButton;
	@FXML private Button backButton;
	
	@FXML private ImageView productImage;
	
	@FXML private TextArea txtFeedback;
	
	protected EShopModel shop;

	//methods
	public void handleReadBtn(ActionEvent e) throws Exception
	{
		txtFeedback.setText(shop.listAllProduct());		
	}
	
	/*
	 * to add new product
	 * use try and catch to show user error messages if product wasn't added successfully.
	 * */
	public void handleCreateBtn(ActionEvent e) throws Exception
	{
		try {
			//if id was repeated, set the id to -999 instead.
			int productID = Integer.valueOf(txtID.getText());
			int id = productID;
			
			for(Product item:shop.getStock()) {
				if (item.getId()==productID) {
					id = -999;
				}
			}
			
			String name = txtName.getText();
			String desc = txtDescription.getText();
			double weight = Double.valueOf(txtWeight.getText()); //Using the Wrapper class to convert String to double
			double cost = Double.valueOf(txtCost.getText()); //Need to finish -- String cat = comboCategory.getTe........
			int quantity = Integer.valueOf(txtQuantity.getText());
			String category = comboCategory.getValue();
			String subCategory = comboSubCategory.getValue();
			shop.addProduct(id, name, desc, weight, cost, quantity, category, subCategory); //Notice category and SubCategory are static
			txtFeedback.setText("Product Added");
		}
		catch(Exception e1) {
			txtFeedback.setText("Failed to create product. Please make sure you have provided correct details.");
		}
		
	}
	
	//to make changes on existing product
	public void handleEditBtn(ActionEvent e) throws Exception
	{
		int id = Integer.valueOf(txtID.getText());
		int productIndex = shop.productIndex(id);
		
		//if item is not found, ask user to try again.
		if(productIndex != -999) {
			String category = comboCategory.getValue();
			String subCategory = comboSubCategory.getValue();
			
			shop.getStock().get(productIndex).setCategory(category);
			shop.getStock().get(productIndex).setSubCategory(subCategory);
			shop.getStock().get(productIndex).setName(txtName.getText());
			shop.getStock().get(productIndex).setDescription(txtDescription.getText());
			shop.getStock().get(productIndex).setWeight(Double.parseDouble(txtWeight.getText()));
			shop.getStock().get(productIndex).setCost(Double.parseDouble(txtCost.getText()));
			shop.getStock().get(productIndex).setQuantity(Integer.parseInt(txtQuantity.getText()));
			txtFeedback.setText("Product Edited");
		}else {
			txtFeedback.setText("Failed to edit product. Please try again with existing index.");
		}
		
	}	
	
	//to remove existing product
	public void handleRemoveBtn(ActionEvent e) throws Exception
	{
		int id = Integer.valueOf(txtID.getText());
		int productIndex = shop.productIndex(id);
		
		if(productIndex != -999) {
			shop.removeProduct(productIndex);
			txtFeedback.setText("Product Removed");
		}else {
			txtFeedback.setText("Failed to remove product. Please try again with existing index.");
		}
		
	}
	
	//switch to scene 'Home.fxml'
	public void handleBackButton(ActionEvent event) throws IOException {
		Parent homeParent = FXMLLoader.load(getClass().getResource("/application/Home.fxml"));
		Scene homeScene = new Scene(homeParent);
		
		//this line gets the stage info.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(homeScene);
		window.show();
	}
	
	//to save product
	public void handleSaveBtn(ActionEvent e) throws Exception
	{
		try {
			shop.saveProducts();
			txtFeedback.setText("Products saved.");
		} catch (Exception e1) {
			txtFeedback.setText("Failed to save Product. Please try again later.");
		}
	}
	
	//to load product
	public void handleLoadBtn(ActionEvent e) throws Exception
	{
		try {
			shop.loadProducts();
			txtFeedback.setText("Products loaded.");
		} catch (Exception e1) {
			txtFeedback.setText("Failed to load Product. Please try again later.");
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		shop= new EShopModel();
		comboCategory.setItems(categories);
		comboSubCategory.setItems(subCategories);
		comboCategory.setValue("All");
		comboSubCategory.setValue("All");
		//to set images
		Image image = new Image(getClass().getResource("../img/JavaFX_Logo.png").toExternalForm());
		productImage.setImage(image);
	}

}