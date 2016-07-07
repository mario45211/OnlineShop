package Items;

import Other.Dimension3D;

//klasa przechowując obiekty dla obudowy komputera
public class ComputerCase extends ComputerItem {
	//pole obiekt przechowujące 3 wymiary obudowy
	private Dimension3D dimension;
	//pole przechowujące kolor obudowy
	private String color;
	
	
	public ComputerCase(String manufacturer, String model, String description, int price,Dimension3D dimension, String color, int number){
		super(description, price,manufacturer,model,number);
		this.dimension = dimension;
		this.color = color;
	
	}
	
	public Dimension3D getDimension(){
		return this.dimension;
	}
	
	public String getColor(){
		return this.color;
	}
	
	@Override
	public String toString(){
		return "OBUDOWA KOMPUTERA PC"+super.toString()+"<br>Wymiary:  "+dimension.width+" x "+dimension.height+" x "+dimension.depth+
				"<br>Kolor:  "+color+"<br>Opis:  "+description+"<br>Ilość dostępnych:  "+numberOfItems+"<br>";
	}
	
	
	public Boolean equals(ComputerCase obj){
		if(super.equals(obj) && this.color.equals(obj.color)){
			return true;
		}
		else{
			return false;
		}
	}
	
}
