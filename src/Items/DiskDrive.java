package Items;

public class DiskDrive extends ComputerItem{
	private String type;
	private int capacity, readSpeed, writeSpeed;

	
	public DiskDrive(String manufacturer, String model, String description, int price, String type, int capacity, int readSpeed, int writeSpeed,int number){
		super(description, price,manufacturer,model,number);
		this.type = type;
		this.capacity=capacity;
		this.readSpeed = readSpeed;
		this.writeSpeed = writeSpeed;
	
	}
	
	public String getType(){
		return this.type;
	}
	
	public int getCapacity(){
		return this.capacity;
	}
	
	public int getReadSpeed(){
		return this.readSpeed;
	}
	public int getWriteSpeed(){
		return this.writeSpeed;
	}

	
	@Override
	public String toString(){
		return super.toString()+"<br>"+type+"<br>Opis:"+description+"<br>Ilość dostępnych:"+numberOfItems;
	}
	
	public Boolean equals(DiskDrive obj){
		if(super.equals(obj) && this.type.equals(obj.type) && this.capacity==obj.capacity && readSpeed==obj.readSpeed && writeSpeed==obj.writeSpeed){
			return true;
		}
		else{
			return false;
		}
	}
}
