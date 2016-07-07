package Users;
import Items.Item;

public class Customer extends User{
	static int permision = 1;
	private Item[] boughtItem;
	private int boughtItemCounter = 0;
	
	
	public Customer(String username, String password){
		super(username, password);
		this.boughtItem = new Item[10];
	}
	
	public int getPermision(){
		return Customer.permision;
	}
	
	@Override
	public Boolean addBoughtItem(Item item){
		if(boughtItemCounter>this.boughtItem.length){
			Item[] tmp = new Item[2*boughtItemCounter];
			System.arraycopy(this.boughtItem, 0, tmp, 0, this.boughtItem.length);
			this.boughtItem = tmp;
		}
		this.boughtItem[boughtItemCounter++] = item;
		return true;
	}
	
	public void cleanBoughtItem(){
		this.boughtItem = new Item[10];
		this.boughtItemCounter = 0;
	}
	
	public int getBoughtItemCounter(){
		return boughtItemCounter;
	}
	@Override
	public Item[] getBoughtItem(){
		return this.boughtItem;
	}
	@Override
	public String toString(){
		return "Klient<br>"+super.toString();
	}
}
