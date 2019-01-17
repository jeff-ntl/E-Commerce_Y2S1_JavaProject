package application;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class Account {
	
	//attributes
	private ArrayList<User> accounts;

	//constructors
	public Account() {
		accounts = new ArrayList<User>();
	}
	
	//methods
	public void addUser(String username, String passwordOne, String passwordTwo, String email) {
		User user = new User(username,passwordOne,email);
		accounts.add(user);
		
	}
	
	//methods
	//save users account to users.xml
	public void saveUsers() throws Exception
    {
   	 XStream xstream = new XStream(new DomDriver());
   	 ObjectOutputStream out = xstream.createObjectOutputStream(new FileWriter("users.xml"));
   	 out.writeObject(accounts);
   	 out.close();
    }
    
	//load users account from users.xml
    @SuppressWarnings("unchecked")
	 public void loadUsers() throws Exception
    {
   	 XStream xstream = new XStream(new DomDriver());
   	 ObjectInputStream is = xstream.createObjectInputStream(new FileReader("users.xml"));
   	 accounts = (ArrayList<User>) is.readObject();
   	 is.close();
    }

	//getters and setters
	public ArrayList<User> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<User> accounts) {
		this.accounts = accounts;
	}
	
	
}
