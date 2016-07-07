package Users;

import Items.Item;

//Klasa Admin tworzy obiekty klasy User, które mogą modyfikować
//produkty w sklepie, dodawać i usuwać, ale nie mogą ich kupować
public class Admin extends User{
	static int permision = 2;
	
	public Admin(String name, String password){
		super(name, password);
	}
	
	
	public int getPermision(){
		return Admin.permision;
	}
	
	
	@Override
	public Boolean addBoughtItem(Item item){
		System.out.println("Administratorzy nie moga kupowac swoich produktow");
		return false;
	}
	@Override
	public Item[] getBoughtItem(){
		return null;
	}
	@Override
	public String toString(){
		return "ADMINISTRATOR<br>"+super.toString();
	}
	@Override
	public int getBoughtItemCounter(){
		return -1;
	}
}
