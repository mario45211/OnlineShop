package Shop;

import java.io.Serializable;

import Items.Item;
import Other.ShopMemory;
import Users.Admin;
import Users.User;

//klasa Shop przechowująca przedmioty dostępne w sklepie oraz użytkowników sklepu
//dostarcza interfejs do dodawania przedmiotów oraz użytkowników
public class Shop implements ShopInterface, Serializable{
	//tablica dynamicznie powiększana przechowująca przedmioty w sklepie
	private Item[] items; 
	//tablica dynamicznie powiększana przechowująca użytkowników sklepu
	private User[] users;
	//liczniki rzeczywistej ilości przedmiotów i użytkowników sklepu
	private int itemCounter = 0;
	private int userCounter = 0;
	
	//przechowuje ścieżkę dostępu do pliku, gdzie zapisywany jest stan sklepu
	private String filepath;
	
	private User loggedUser = null;
	

	//konstruktor inicjujący obiekt sklepu
	public Shop(int itemAmount,int userAmount, String filepath){
		if(itemAmount<0 || userAmount<0){
			System.out.println("Incorrect amount!");
			System.exit(-1);
		}
		else {
			this.items = new Item[itemAmount];
			this.users = new User[userAmount];
			this.filepath = filepath;
			addUser(new Admin("admin","123")); //domyslne początkowe konto administratora
		}
	}
	
	//metoda dodająca przedmiot do sklepu, wymaga uprawnień administratora
	public Boolean addItem(Item item){
		if(loggedUser!=null){
			if(loggedUser.getPermision()==2){
				if(itemCounter > this.items.length-1){
					Item[] tmp = new Item[2*itemCounter];
					for(int i=0;i<itemCounter;i++)
						tmp[i] = items[i];
					this.items = tmp;
				}
				items[itemCounter++] = item;
				return true;
			}
			else{
				System.out.println("Nie masz uprawnien do tej operacji");
				return false;
			}
		}
		else{
			System.out.println("Musisz wczesniej sie zalogowac");
			return false;
		}
	}
	
	//metoda usuwająca przedmiot ze sklepu, wymaga uprawnień administratora
	public Boolean deleteItem(Item item){
		if(loggedUser!=null){

			if(loggedUser.getPermision()==2){
				for(int i=0;i<itemCounter;i++){
					if(this.items[i].equals(item)){
						for(int j=i;j<itemCounter;j++){
							this.items[j]=this.items[j+1];
						}
						itemCounter--;
						return true;
					}
				}
			}
			else{
				System.out.println("nie masz uprawnien do tej operacji");
				return false;
			}
			
		}
		else{
			System.out.println("Musisz wczesniej sie zalogowac");
			return false;
		}
		return null;/////?????????????
		 
	}
	
	//prywatna metoda usuwająca przedmiot, bez sprawdzenia uprawnień użytkownika, który to usuwanie wywołał
	//wykorzystywana w metodzie pozwalającej na kupowanie przedmiotów
	private Boolean deleteItem(Item item,Boolean permision){
		if(loggedUser!=null){

			if(loggedUser.getPermision()==2 || permision==true){
				for(int i=0;i<itemCounter;i++){
					if(this.items[i].equals(item)){
						for(int j=i;j<itemCounter;j++){
							this.items[j]=this.items[j+1];
						}
						itemCounter--;
						return true;
					}
				}
			}
			else{
				System.out.println("nie masz uprawnien do tej operacji");
				return false;
			}
			
		}
		else{
			System.out.println("Musisz wczesniej sie zalogowac");
			return false;
		}
		return null;/////?????????????
		 
	}
	
	//metoda dodająca użytkownika, wywoływana przez klienta, który się zarejestrował
	public Boolean addUser(User user){
			if(userCounter > this.users.length-1){
				User[] tmp = new User[2*userCounter];
				for(int i=0;i<userCounter;i++)
					tmp[i]=users[i];
				this.users = tmp;
			}
			
			for(int i=0;i<userCounter;i++){
				if(userCounter>0 && users[i].getUsername().equals(user.getUsername())){
					System.out.println("Login zajety");
					return false;
				}
			}
			this.users[userCounter++] = user;
			return true;
			
		
	}
	
	//metoda usuwająca użytkownika z bazy sklepu, wymaga uprawnień administratora
	public Boolean deleteUser(User user){
		if(loggedUser!=null){
			if(loggedUser.equals(user)){
				System.out.println("Musisz sie wylogowac, zeby siebie usunac");
			}
			else{
				if(loggedUser.getPermision()==2){
					for(int i=0;i<userCounter;i++){
						if(this.users[i]==user){
							for(int j=i;j<userCounter;j++){
								this.users[j]=this.users[j+1];
							}
							userCounter--;
							return true;
						}
					}
				}
				else{
					System.out.println("nie masz uprawnien do tej operacji");
					return false;
				}
			}
		}
		else{
			System.out.println("Musisz wczesniej sie zalogowac");
			return false;
		}
		return null;/////?????????????
		 
	}
	
	//metoda pozwalająca na zalogowanie się do sklepu
	//jednocześnie do sklepu może być zalogowany tylko jeden użytkownik
	public Boolean logInUser(String username, String password){
		if(this.loggedUser!=null){
			System.out.println("Jestes juz zalogowany");
			return false;
		}
		else{
			for(int i=0;i<userCounter;i++){
				if(this.users[i].equals(username, password)){
					this.loggedUser = this.users[i];
					return true;
				}
			}
			if(this.loggedUser==null){
				System.out.println("Logowanie nie powiodlo sie");
				return false;
			}
			
		}
		return null;////???????????????????
	}
	
	//metoda wylogowująca użytkownika
	public void signOutUser(){
		this.loggedUser = null;
	}
	
	//metoda pozwalająca na kupno przedmiotu,
	//zmniejsza ona ilość przedmiotów sklepie lub usuwa go z bazy, jeśli nie ma już dostępnych sztuk
	public Boolean buyItem(Item item){
		if(loggedUser!=null){
			if(loggedUser.getPermision()==1){ //normal user
								
				if(item.getNumberOfItems()>0){
					if(loggedUser.addBoughtItem(item)){
						item.decrementNumberOfItem();
						if(item.getNumberOfItems()==0){
							if(deleteItem(item,true)){
								return true;
							}
						}
					}
					return true;
				}
			}
		}
		else{
			System.out.println("musisz najpierw sie zalogowac");
			return false;
		}
		return null;//??????????
	}
	
	public User getLoggedUser(){
		return this.loggedUser;
	}
	
	public String getFilepath(){
		return filepath;
	}
	
	public Item[] getItems(){
		return items;
	}
	public User[] getUsers(){
		return users;
	}
	
	public int getUserCounter(){
		return userCounter;
	}
	public int getItemCounter(){
		return itemCounter;
	}
}
