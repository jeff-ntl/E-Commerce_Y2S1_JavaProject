//RadioButton: http://www.java2s.com/Tutorials/Java/JavaFX/0420__JavaFX_RadioButton.htm
//adding children: https://stackoverflow.com/questions/29247131/how-do-i-add-components-from-top-to-bottom-in-vbox
//getting selected radio button from toggle group: https://stackoverflow.com/questions/32424915/how-to-get-selected-radio-button-from-togglegroup
// radiobutton methods: https://www.geeksforgeeks.org/javafx-radiobutton-with-examples/
//update selection changes (combobox) : http://www.java2s.com/Code/Java/JavaFX/AddchangelistenertoComboBoxvalueProperty.htm
//update selection changes (radioButton) :http://www.java2s.com/Code/Java/JavaFX/RadioButtonselectedTogglePropertyListener.htm
//remove all children:https://stackoverflow.com/questions/27066484/remove-all-children-from-a-group-without-knowing-the-containing-nodes
//user data: https://stackoverflow.com/questions/24615911/proper-uses-of-javafx-setuserdata
package application;
 
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class CustomerController implements Initializable
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
	
	@FXML private Button addButton;
	@FXML private Button backButton;
	@FXML private Button cartButton;
	
	@FXML private VBox vbox;
	@FXML private ImageView productImage;
	
	@FXML private TextArea txtFeedback;
	
	protected EShopModel shop;
	
	private ToggleGroup group = new ToggleGroup();
	private ArrayList<RadioButton> r1 = new ArrayList<RadioButton>();
	private ToggleGroup cartGroup = new ToggleGroup();
	//use static to make sure ShoppingCartController is able to read the record in cartProducts at the instance of CustomerController 'customer'
	private static ArrayList<RadioButton> cartProducts = new ArrayList<RadioButton>();
	
	//methods
	//add item to shopping cart
	public void handleAddButton(ActionEvent event) throws IOException {
		if (group.getSelectedToggle() != null) {
			try {
	        	String productName = group.getSelectedToggle().getUserData().toString();
	        	
	        	cartProducts.add(new RadioButton(productName));
	        	for(int i=0;i<cartProducts.size();i++) {
	        		//setUserData is used for toggling between selected and non-selected states of controls(ie:radio button).
	        		cartProducts.get(i).setUserData(cartProducts.get(i).getText());
	        		cartProducts.get(i).setToggleGroup(cartGroup);
	        	}
	        	
	        	//Test: to view what is in the arraylist "cartProducts"
	        	System.out.println(cartProducts);
	        	txtFeedback.setText("Product '" + productName + "' is Added.");
			}
        	catch (Exception e) {
    			txtFeedback.setText("Failed to add Product. Please try again later.");
    		}
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
	
	//switch to scene 'ShoppingCart.fxml'
	public void handleCartButton(ActionEvent event) throws IOException {
		Parent cartParent = FXMLLoader.load(getClass().getResource("/application/ShoppingCart.fxml"));
		Scene cartScene = new Scene(cartParent);
		
		//this line gets the stage info.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(cartScene);
		window.show();
	}
	
	//to be used in ShoppingCartController, to convert the text of radio button to index of the radio button in the array
	public int productIndex(String name) {
		for(RadioButton item:cartProducts) {
			if(name == item.getText()) {
				return cartProducts.indexOf(item);
			}
		}
		return -999;
	}	
	
	//to update vbox (changing radio button)
	public void updateVbox() {
		String productCategory = comboCategory.getValue();
		String productSubCategory = comboSubCategory.getValue();
		
		//to clear all element in arraylist 'r1'
    	r1.clear();
    	//remove all children of vbox
    	vbox.getChildren().clear();
    	
    	//add item loaded from xml file to arraylist 'r1'
    	//continue is used to skip the current loop after the result is achieved
    	for(Product item:shop.getStock()) {
    		
    		if( productCategory.equals("All")) {
    			if(productSubCategory.equals("All")) 
    			{
    			String productName = item.getName();
   				 r1.add(new RadioButton(productName));
   				 continue;
    			}
    			else if( productSubCategory.equals(item.getSubCategory()) ) {
        			String productName = item.getName();
      				 r1.add(new RadioButton(productName));
      				continue;
    			} 
    		} 
    		
    		if( productCategory.equals(item.getCategory())) {
    			if(productSubCategory.equals("All")) 
    			{
    			String productName = item.getName();
   				 r1.add(new RadioButton(productName));
   				 continue;
    			}
    			else if( productSubCategory.equals(item.getSubCategory()) ) {
        			String productName = item.getName();
      				 r1.add(new RadioButton(productName));
      				continue;
    			} 
    		} 
    		
        }
   		 
   		 //add item to to vbox as radiobutton from array list 'r1'
   		 for( int i=0; i<r1.size(); i++) {
   			 	r1.get(i).setUserData(r1.get(i).getText());
   			 	r1.get(i).setToggleGroup(group);
   	            vbox.getChildren().add(r1.get(i));
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
		
		//to set image
		Image image = new Image(getClass().getResource("../img/JavaFX_Logo.png").toExternalForm());
		productImage.setImage(image);
		
		try {
			//load products from xml file when after user logging in.
			shop.loadProducts();
			txtFeedback.setText("Products loaded.");
			
			//initiate children of vbox to category & subcategory = all
			//add item loaded from xml file to arraylist 'r1'
	    	for(Product item:shop.getStock()) {
	    			String productName = item.getName();
	   				 r1.add(new RadioButton(productName));
		        }
	 
	   		 //add item to to vbox as radiobutton from array list 'r1'
	   		 for( int i=0; i<shop.getStock().size(); i++) {
	   			 	r1.get(i).setUserData(r1.get(i).getText());
	   			 	r1.get(i).setToggleGroup(group);
	   	            vbox.getChildren().add(r1.get(i));
	   	        }
			 
	   		 
	   		 //call updateVbox() when category and subcategory value have been changed
	   		 comboCategory.valueProperty().addListener(new ChangeListener<String>() {
		        @Override public void changed(ObservableValue ov, String t, String t1) {
		        	updateVbox();
			        }});
			 
        	comboSubCategory.valueProperty().addListener(new ChangeListener<String>() {
        		@Override public void changed(ObservableValue ov, String t, String t1) {
        			updateVbox();		
			       }});
			       
			 
			 //do smtg when radiobutton selection has changed
			 group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
			      public void changed(ObservableValue<? extends Toggle> ov,  Toggle old_toggle, Toggle new_toggle) {
			        if (group.getSelectedToggle() != null) {
			          //System.out.println(group.getSelectedToggle().getUserData().toString());
			        	//retrieve product name from selection change
			        	String productName = group.getSelectedToggle().getUserData().toString();
			        	//use productIndex to look for the index of product in arraylist 'stocks'
			        	int productIndex = shop.productIndex(productName);
			        	//System.out.print(productIndex);
			        	
			        	//get product from the index of product
			        	Product currentProduct = shop.getStock().get(productIndex);
			        	
			        	//update all text field, use valueOf to convert data type to String			        	
			        	txtID.setText(String.valueOf(currentProduct.getId()));
			        	txtName.setText(currentProduct.getName());
			        	txtDescription.setText(currentProduct.getDescription());
			        	txtWeight.setText(String.valueOf(currentProduct.getWeight()));
			        	txtCost.setText(String.valueOf(currentProduct.getCost()));
			        	txtQuantity.setText(String.valueOf(currentProduct.getQuantity()));
			        }
			      }
			    });
			 
		} catch (Exception e) {
			txtFeedback.setText("Failed to load Product. Please try again later.");
		}
	}

	//getters and setters
	public ToggleGroup getCartGroup() {
		return cartGroup;
	}

	public void setCartGroup(ToggleGroup cartGroup) {
		this.cartGroup = cartGroup;
	}

	public ArrayList<RadioButton> getCartProducts() {
		return cartProducts;
	}

	public void setCartProducts(ArrayList<RadioButton> cartProducts) {
		CustomerController.cartProducts = cartProducts;
	}


	


	
	
}