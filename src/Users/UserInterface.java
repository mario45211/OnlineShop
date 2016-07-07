package Users;

import Items.Item;

public interface UserInterface {
	public String getUsername();
	public void setUsername(String username);
	public void setPassword(String password);
	public String getPassword();
	public int getPermision();
	Boolean equals(String username, String password);
	public abstract Boolean addBoughtItem(Item item);
	public abstract Item[] getBoughtItem();
	public abstract int getBoughtItemCounter();
}
