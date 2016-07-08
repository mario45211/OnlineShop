package BuyFrame;

import Items.Item;
import Other.ShopMemory;
import Shop.Shop;
import Users.User;

/*klasa tworząca model dla okienka pozwalające kupowanie przedmiotów*/
public class BuyFrameModel {
	
	public Shop shop;
	public ShopMemory shopMemory;
	private int totalCost = 0;
	public String filepath;
	public String searchItem;
	
	public BuyFrameModel(String filepath){
		shopMemory = new ShopMemory(filepath);
		shop = (Shop)shopMemory.loadFromFile();
		this.filepath = filepath;
		searchItem= "";
	}
	
	public BuyFrameModel(String filepath,String s){
		shopMemory = new ShopMemory(filepath);
		shop = (Shop)shopMemory.loadFromFile();
		this.filepath = filepath;
		searchItem =s;
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
