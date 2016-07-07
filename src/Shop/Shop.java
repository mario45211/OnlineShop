package Shop;

import java.io.Serializable;

import Items.Item;
import Users.Admin;
import Users.User;

public class Shop implements ShopInterface, Serializable{
	private Item[] items; 
	private User[] users;
	private int itemCounter = 0;
	private int userCounter = 0;
	
	private String filepath;
	
	private User loggedUser = null;
	
	
	/////////////////////////////////////////////////////////////
	
	public Shop(int itemAmount,int userAmount, String filepath){
		if(itemAmount<0 || userAmount<0){
			System.out.println("Incorrect amount!");
			System.exit(-1);
		}
		else {
			this.items = new Item[itemAmount];
			this.users = new User[userAmount];
			this.filepath = filepath;
			addUser(new Admin("admin","123")); //domyslne konto
		}
	}
	
	
	public Boolean addItem(Item item){
		if(loggedUser!=null){
			if(loggedUser.getPermision()==2){
				if(itemCounter > this.items.length){
					Item[] tmp = new Item[2*itemCounter];
					System.arraycopy(this.items, 0, tmp, 0, this.items.length);
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
	
	public Boolean deleteItem(int index){
		if(loggedUser!=null){
			if(loggedUser.getPermision()==2){
				
				for(int j=index;j<itemCounter;j++){
					this.items[j]=this.items[j+1];
				}
				itemCounter--;
				return true;
		
			}else{
					System.out.println("Nie masz uprawnien do tej operacji");
					return false;
			}
		}
		else{
			System.out.println("Musisz wczesniej sie zalogowac");
			return false;
		}
	}
	
	/*private Boolean deleteItem(int index, Boolean permision){
		if(loggedUser!=null){
				
			for(int j=index;j<itemCounter;j++){
				this.items[j]=this.items[j+1];
			}
			itemCounter--;
			return true;
	
		}
		else{
			System.out.println("Musisz wczesniej sie zalogowac");
			return false;
		}
	}*/
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
	public Boolean addUser(User user){
			if(userCounter > this.users.length){
				User[] tmp = new User[2*userCounter];
				System.arraycopy(this.items, 0, tmp, 0, this.users.length);
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
	public void signOutUser(){
		this.loggedUser = null;
	}
	
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
