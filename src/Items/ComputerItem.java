package Items;



public abstract class ComputerItem extends Item implements ComputerItemInterface{
	protected String manufacturer, model;

	
	public ComputerItem(String description, int price, String manufacturer, String model, int numberOfItems){
		super(description, price, numberOfItems);
		this.manufacturer = manufacturer;
		this.model = model;
	}
	
	/*public ComputerItem(String description, int price, String manufacturer, String model, int amount){
		super(description, price);
		this.manufacturer = manufacturer;
		this.model = model;
		
	}*/
	
	public String getManufacturer(){
		return this.manufacturer;
	}
	public String getModel(){
		return this.model;
	}

	@Override
	public String toString(){
		return "Producent:  "+manufacturer+"<br>Model:  "+model+"<br>Cena:  "+price+" zł";
	}

	public Boolean equals(ComputerItem obj) {
		if(super.equals(obj) && this.manufacturer.equals(obj.manufacturer) && this.model.equals(obj.model)){
			return true;
		}
		else{
			return false;
		}
	}
	
}
