package Shop;

import Items.Item;
import Users.User;

public interface ShopInterface {
	public Boolean addItem(Item item);
	public Boolean deleteItem(Item item);
	
	public Boolean addUser(User user);
	public Boolean deleteUser(User user);
	
	public Boolean logInUser(String s, String s2);
	public void signOutUser();
	public Boolean buyItem(Item item);
	public User getLoggedUser();
	
	public String getFilepath();
	
	public Item[] getItems();
	public User[] getUsers();
}
