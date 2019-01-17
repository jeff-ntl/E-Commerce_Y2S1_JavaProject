/*
 * email address validation:https://stackoverflow.com/questions/624581/what-is-the-best-java-email-address-validation-method
 * */
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


public class RegisterController implements Initializable
{
	//attributes
	@FXML private TextField txtUsername;
	@FXML private TextField txtEmail;
	@FXML private PasswordField txtPassword;
	@FXML private PasswordField txtConfirmedPassword;
	
	@FXML private Button submitButton;
	@FXML private Button backButton;
	
	@FXML private TextArea txtFeedback;
	
	protected Account acc;

	/* methods
	 * check validation
	 * switch to scene 'Home.fxml'*/
	public void handleSubmitButton(ActionEvent event) throws IOException {
		try {
			String username = txtUsername.getText();
			String passwordOne = txtPassword.getText();
			String passwordTwo = txtConfirmedPassword.getText();
			String email = txtEmail.getText();
			
			//validation...
			if(username.length()<6){
				txtFeedback.setText("Username must be more than 5 characters.");
			}
			else if(!passwordOne.equals(passwordTwo)) {
				txtFeedback.setText("Please make sure your password is correct.");
			}else if(passwordOne.length()<6){
				txtFeedback.setText("Password must be more than 5 characters.");
			}
			else if(!isValidEmailAddress(email)) {
				txtFeedback.setText("Please make sure your email address is in the right format.");
			}
			else {
				acc.addUser(username, passwordOne, passwordTwo, email);
				try {
					acc.saveUsers();
					txtFeedback.setText("Account created.");
				} catch (Exception e1) {
					txtFeedback.setText("Failed to save user account. Please try again later.");
				}
			}
			
		}catch(Exception e1) {
			txtFeedback.setText("Failed to create account. Please try again later.");
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
	
	//to check the email address format is valid
	public boolean isValidEmailAddress(String email) {
	    String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
	    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
	    java.util.regex.Matcher m = p.matcher(email);
	    return m.matches();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
		acc =  new Account();
	}

}