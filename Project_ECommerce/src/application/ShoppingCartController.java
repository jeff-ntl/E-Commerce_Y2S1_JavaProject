package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;


public class ShoppingCartController implements Initializable
{
	//attributes
	@FXML private Button backButton;
	@FXML private Button removeButton;
	@FXML private Button submitButton;
	
	@FXML private VBox vbox;
	
	@FXML private TextArea txtFeedback;
	
	protected EShopModel shop;
	protected CustomerController customer;

	//switch to scene 'Customer.fxml'
	public void handleBackButton(ActionEvent event) throws IOException {
		Parent homeParent = FXMLLoader.load(getClass().getResource("/application/Customer.fxml"));
		Scene homeScene = new Scene(homeParent);
		
		//this line gets the stage info.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(homeScene);
		window.show();
	}
	
	//to remove selected item in shopping cart
	public void handleRemoveButton(ActionEvent event) throws IOException {
		String productName = customer.getCartGroup().getSelectedToggle().getUserData().toString();
		int productIndex = customer.productIndex(productName);
	
		customer.getCartProducts().remove(productIndex);
		
		vbox.getChildren().clear();
		
		for( int i=0; i<customer.getCartProducts().size(); i++) {
				customer.getCartProducts().get(i).setUserData(customer.getCartProducts().get(i).getText());
				customer.getCartProducts().get(i).setToggleGroup(customer.getCartGroup());
		        vbox.getChildren().add(customer.getCartProducts().get(i));
		      }
	
		txtFeedback.setText("Product: " + productName + " is removed.");
	}
	
	//to calculate the total cost of selected items
	public void handleSubmitButton(ActionEvent event) throws IOException {
		double total =0.0;
		if(customer.getCartProducts().size()==0) {
			total=0.0;
		}else {
			for(int i=0;i<customer.getCartProducts().size();i++) {
				//get name of item from arraylist 'cartProducts'
				String productName = customer.getCartProducts().get(i).getText();
				
				//get int of item from arraylist 'stock'
				int productIndex = shop.productIndex(productName);
				
				//add the cost of item to productCost
				//System.out.println(productIndex);
				total += shop.getStock().get(productIndex).getCost();
			}
		}
		txtFeedback.setText("Total cost is: " + total);
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		shop= new EShopModel();
		customer = new CustomerController();
		
		try {
			//load products from xml file when after user logging in.
			shop.loadProducts();
			txtFeedback.setText("Products loaded.");
					
			//tell customer 'no item is added' if they didn't add any item to the cart.
			if(customer.getCartProducts().size()==0) {
				txtFeedback.setText("No any item has been added to the cart.");
			}else {
				//add item to to vbox as radiobutton from array list 'r1'
		   		 for( int i=0; i<customer.getCartProducts().size(); i++) {
		   			customer.getCartProducts().get(i).setUserData(customer.getCartProducts().get(i).getText());
		   			customer.getCartProducts().get(i).setToggleGroup(customer.getCartGroup());
		   	        vbox.getChildren().add(customer.getCartProducts().get(i));
		   	      }
			}
	   		 
		} catch (Exception e) {
			txtFeedback.setText("Failed to load Product. Please try again later.");
		}
	}

}

