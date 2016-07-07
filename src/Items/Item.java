package Items;

import java.io.Serializable;

public abstract class Item implements Serializable{
	protected String description;
	protected int price, numberOfItems;
	
	public Item(String description, int price, int number){
		if(price<0){
			System.out.println("Cena przedmiotu nie moze być ujemna!!!");
		}
		else{
			
			this.description = description;
			this.price=price;
			this.numberOfItems=number;
		}
	}
	
	public Item(int price){
		if(price<0){
			System.out.println("Cena przedmiotu nie moze być ujemna!!!");
		}
		else{
			
			this.description = "None";
	
			this.price=price;
		}
	}
	
	public String getDescription(){
		return this.description;
	}

	public int getPrice(){
		return this.price;
	}
	
	public abstract String toString();
	
	public void incrementNumberOfItems(int value){
		this.numberOfItems+=value;
	}
	
	public void decrementNumberOfItem(){
		if(this.numberOfItems>0)
			this.numberOfItems--;
		else
			System.out.print("Brak przedmiotów w sklepie");
	}
	
	public int getNumberOfItems(){
		return this.numberOfItems;
	}
	
	public Boolean equals(Item obj){
		if(price==obj.price){
			return true;
		}
		else{
			return false;	
		}
	}
	public abstract String getModel();
	public abstract String getManufacturer();
}
