package Items;

public class Laptop extends Item{
	private String manufacturer, model, displayType,operatingSystem;
	private int displaySize, batteryCapacity, weight;
	private int numberOfItems = 0;
	
	public Laptop(String description, int price, String manufacturer, String model, String displayType, int displaySize, int batteryCapacity, int weight,int number){
		super(description,price, number);
		this.manufacturer = manufacturer;
		this.model = model;
		this.displayType = displayType;
		this.displaySize = displaySize;
		this.batteryCapacity = batteryCapacity;
		this.weight = weight;
	
	}
	public Laptop(String description, int price, String manufacturer, String model, String displayType, int displaySize, int batteryCapacity, int weight,String operatingSystem,int number){
		super(description,price,number);
		this.manufacturer = manufacturer;
		this.model = model;
		this.displayType = displayType;
		this.displaySize = displaySize;
		this.batteryCapacity = batteryCapacity;
		this.weight = weight;
	
		this.operatingSystem = operatingSystem;
	}
	
	public String getManufacturer(){
		return this.manufacturer;
	}
	public String getModel(){
		return this.model;
	}
	public String getDisplayType(){
		return this.displayType;
	}
	public int getDisplySize(){
		return this.displaySize;
	}
	public int getBatteryCapacity(){
		return this.batteryCapacity;
	}
	public int getWeight(){
		return this.weight;
	}
	public String getOperatingSystem(){
		return operatingSystem;
	}
	@Override
	public String toString(){
		return "Producent:"+manufacturer+"<br>Model:"+model+"<br>Cena:"+super.price+
				"<br>Rozmiar ekranu:"+displaySize+"<br>Pojemność baterii:"+batteryCapacity
				+"<br>Waga:"+weight+"<br>Opis:"+super.description+"<br>Ilość dostępnych:"+numberOfItems;
	}
	
	public Boolean equals(Laptop obj){
		if(super.equals(obj) && manufacturer.equals(obj.manufacturer)&&model.equals(obj.model)&&displaySize==obj.displaySize && displayType.equals(obj.displayType)&&
				batteryCapacity==obj.batteryCapacity && weight==obj.weight && operatingSystem.equals(obj.operatingSystem)){
			return true;
		}
		else{
			return false;
		}
	}
}
