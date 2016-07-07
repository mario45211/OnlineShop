package Items;


//klasa dziedzicząca po klasie ComputerItem, prezentująca procesory komputerowe
public class Processor extends ComputerItem{
	
	//pola przechowujące parametry procesora
	private String socket;
	private int coreTiming, numberOfCores;
	
	public Processor(String description, String manufacturer, String model, int price, String socket, int coreTiming, int numberOfCores,int number){
		super(description, price, manufacturer,model,number);
		this.socket = socket;
		this.coreTiming=coreTiming;
		this.numberOfCores = numberOfCores;
		
	}
	
	public String getSocket(){
		return this.socket;
	}
	public int getCoreTiming(){
		return this.coreTiming;
	}
	public int getNumberOfCores(){
		return this.numberOfCores;
	}
	
	@Override
	public String toString(){
		return "PROCESOR"+super.toString()+"<br>Gniazdo: "+socket+"<br>Ilość rdzeni: "+numberOfCores+"<br>Taktowanie rdzeni: "+coreTiming+
				"<br>Opis: "+description+"<br>Ilość dostępnych: "+numberOfItems+"<br>";
	}
	
	public Boolean equals(Processor obj){
		if(super.equals(obj) && this.socket.equals(obj.socket) && this.numberOfCores==obj.numberOfCores && coreTiming==obj.coreTiming){
			return true;
		}
		else{
			return false;
		}
	}
}
