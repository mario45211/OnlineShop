package Users;
import java.io.Serializable;

import Items.Item;


//Abstrakcyjna klasa tworząca obiekty użytkowników sklepu, implementująca interfejs UserInterface 
//przechowujący wymagane metody do obsługi każdego użytkownika
public abstract class User implements UserInterface, Serializable{
	private String username, password; //każdy użytkownik posiada nazwę i hasło
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	//metody pozwalające na pobranie danych użytkownika
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	/**
	 * metod realizuje wzorzec projektowy Template
	 * jest to szablon do konwersji obiektu na ciąg znaków
	 * wykorzystuje wywolania metod dla odpowiednich obiektów
	 */
	public String convertToText(){
		StringBuffer string = new StringBuffer();
		string.append(this.toString());
		return string+"";
	}
	
	@Override
	public Boolean equals(String username, String password){
		if(this.username.equals(username)&&this.password.equals(password))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString(){
		return "Login:  "+this.username+"<br>Hasło:  "+this.password;
	}
	
}
