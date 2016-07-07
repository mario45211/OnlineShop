package Items;



public class PowerSupply extends ComputerItem{

	private int power;
	
	public PowerSupply(String manufacturer, String model, String description, int price,int power,int number){
		super(description, price,manufacturer,model,number);
		this.power = power;
		
	}
	
	public int getPower(){
		return this.power;
	}
	
	
	@Override
	public String toString(){
		return super.toString()+"<br>Moc:"+power+"<br>Opis:"+description+"Ilość dostępnych:"+numberOfItems;
	}
	
	public Boolean equals(PowerSupply obj){
		if(super.equals(obj) && this.power==obj.power){
			return true;
		}
		else{
			return false;
		}
	}
}
