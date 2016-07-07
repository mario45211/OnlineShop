package MainFrame;

import Items.Item;
import Other.ShopMemory;
import Shop.Shop;
import Users.User;

public class MainFrameModel {
	
	public Shop shop;
	public ShopMemory shopMemory;
	private int totalCost = 0;
	public String filepath;
	
	public MainFrameModel(String filepath){
		shopMemory = new ShopMemory(filepath);
		
		shop = (Shop)shopMemory.loadFromFile();
		this.filepath = filepath;
	}

	
	public int getTotalCost(){
		User user = shop.getLoggedUser();
		Item[] items = user.getBoughtItem();
		
		for(int i=0;i<user.getBoughtItemCounter();i++){
			totalCost+=items[i].getPrice();
		}
		return totalCost;
	}
}
