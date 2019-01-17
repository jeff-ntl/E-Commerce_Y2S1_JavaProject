/*
 * Switching scene:https://www.youtube.com/watch?v=XCgcQTQCfJQ&t=730s
 * JavaFX overview:http://tutorials.jenkov.com/javafx/overview.html
 * IOException & try and catch: https://stackoverflow.com/questions/9441094/why-it-is-mandatory-to-use-throws-ioexception
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
import javafx.scene.control.TextArea;


public class HomeController implements Initializable
{

	//attributes
	@FXML private Button loginButton;
	@FXML private Button registerButton;
	@FXML private Button adminButton;
	
	@FXML private TextArea txtFeedback;
	
	
	//switch to scene 'Login.fxml'
	//IOException is used instead of try catch
	//to declare that you're going to re-throw it instead of catch it.
	public void handleLoginButton(ActionEvent event) throws IOException {
		Parent loginParent = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		Scene loginScene = new Scene(loginParent);
		
		//this line gets the stage info.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(loginScene);
		window.show();
	}
	
	//switch to scene 'Register.fxml'
	public void handleRegisterButton(ActionEvent event) throws IOException {
		Parent registerParent = FXMLLoader.load(getClass().getResource("/application/Register.fxml"));
		Scene registerScene = new Scene(registerParent);
		
		//this line gets the stage info.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(registerScene);
		window.show();
	}
	
	//switch to scene 'Admin.fxml'
	public void handleAdminButton(ActionEvent event) throws IOException {
		Parent adminParent = FXMLLoader.load(getClass().getResource("/application/Admin.fxml"));
		Scene adminScene = new Scene(adminParent);
		
		//this line gets the stage info.
		Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
		
		window.setScene(adminScene);
		window.show();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) 
	{
	}

}