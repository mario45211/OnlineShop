package Items;



import Other.Dimension3D;

public class ComputerCase extends ComputerItem {
	
	private Dimension3D dimension;
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
		return "<br>"+super.toString()+"<br>Wymiary:  "+dimension.width+" x "+dimension.height+" x "+dimension.depth+"<br>Kolor:  "+color+"<br>Opis:  "+description+"<br>Ilosc dostepnych:  "+numberOfItems;
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
