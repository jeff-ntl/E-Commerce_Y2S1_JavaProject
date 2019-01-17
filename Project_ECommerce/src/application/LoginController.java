package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;


public class LoginController implements Initializable
{
	//attributes
	@FXML private TextField txtUsername;
	@FXML private PasswordField txtPassword;
	
	@FXML private Button loginButton;
	@FXML private Button backButton;
	
	@FXML private TextArea txtFeedback;

	protected Account acc;
	
	//methods
	//switch to scene 'Customer.fxml'
	public void handleLoginButton(ActionEvent event) throws IOException {
		try {
			acc.loadUsers();
			txtFeedback.setText("Accounts loaded.");
			
			String username = txtUsername.getText();
			String password = txtPassword.getText();
			
			//to look for account from arraylist 'accounts'
			for(User user:acc.getAccounts()) {
				if((user.getUsername().equals(username))&&(user.getPassword().equals(password))) {
					//if account was found and password was correct, switch to next scene
					Parent customerParent = FXMLLoader.load(getClass().getResource("/application/Customer.fxml"));
					Scene customerScene = new Scene(customerParent);
					
					//this line gets the stage info.
					Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
					
					window.setScene(customerScene);
					window.show();
				}else {
					txtFeedback.setText("Incorrect password, account not found.");
				}
			}
			
		} catch (Exception e1) {
			txtFeedback.setText("Failed to load user accounts. Please try again later.");
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
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		acc =  new Account();
	}

}