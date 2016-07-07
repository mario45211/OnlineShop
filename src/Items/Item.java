package Items;

import java.io.Serializable;

//abstrakcyjna klasa prezentująca obiekt przedmiot
public abstract class Item implements Serializable{
	//pole przechowujące opis przedmiotu
	protected String description;
	//pola przechowujące cene przedmiotu oraz dostępną ilość przedmiotów
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
	
	//metoda abstrakcyjna zwracająca ciąg znaków, prezentujący dany obiekt
	public abstract String toString();
	
	//metoda zwiększająca ilość dostępnych przedmiotów,
	//używana przy dodawaniu przedmiotu, który już istnieje w bazie
	public void incrementNumberOfItems(int value){
		this.numberOfItems+=value;
	}
	
	//metoda zmniejszająca ilość przedmiotów
	//wykorzystywana przy usuwaniu przedmiotu z bazy sklepu
	public void decrementNumberOfItem(){
		if(this.numberOfItems>0)
			this.numberOfItems--;
		else
			System.out.print("Brak przedmiotów w sklepie");
	}
	
	//zwraca ilość dostępnych sztuk przedmiotu
	public int getNumberOfItems(){
		return this.numberOfItems;
	}
	
	//metoda sprawdzająca, czy przesłany obiekt jest taki sam, jak przesłany w parametrze @obj
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
	
	//metody zwracające zawartość pól ceny i opisu
	public int getPrice(){
		return this.price;
	}
	public String getDescription(){
		return this.description;
	}
}
