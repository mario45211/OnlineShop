package Items;

//klasa dziedzicząca po klasie ComputerItem, 
//prezentująca obiekty dla dysków komputerowych
public class DiskDrive extends ComputerItem{
	
	//pole przechowujące typ dysku (np. dysk twardy HDD, dysk SSD)
	private String type;
	//pola przechowujące parametry dysku jak pojemność i prędkości zapisu i odczytu danych
	private int capacity, readSpeed, writeSpeed;


	//konstruktor inicjujący pola
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
		return "DYSK TWARDY"+super.toString()+"<br>Typ dysku: "+type+"<br>Pojemność: "+capacity+" GB<br>Prędkość odczytu danych: "+readSpeed+
				"MB/s<br>Prędkość zapisu danych: "+writeSpeed+"MB/s<br>Opis:  "+description+"<br>Ilość dostępnych:  "+numberOfItems+"<br>";
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
