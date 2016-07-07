package Items;

public class MotherBoard extends ComputerItem{

	private String socket, typeOfRAM;
	private int numberOfRAMSlot, numberOfPCIeSlot, numberOfUSBSlot;
	
	
	public MotherBoard(String manufacturer, String model, String description, int price,String socket, String typeOfRAM, int numberOfRAMSlot,int numberOfPCIeSlot, int numberOfUSBSlot, int number){
		super(description, price,manufacturer,model,number);
		this.socket = socket;
		this.typeOfRAM = typeOfRAM;
		this.numberOfPCIeSlot = numberOfPCIeSlot;
		this.numberOfRAMSlot = numberOfRAMSlot;
		this.numberOfUSBSlot = numberOfUSBSlot;
	
	}
	
	public String getSocket(){
		return this.socket;
	}
	public String getTypeOfRAM(){
		return this.typeOfRAM;
	}
	public int getNumberOfPCIeSlot(){
		return this.numberOfPCIeSlot;
	}
	public int getNumberOfUSBeSlot(){
		return this.numberOfUSBSlot;
	}
	public int getNumberOfRAMSlot(){
		return this.numberOfRAMSlot;
	}
	
	@Override
	public String toString(){
		return super.toString()+"<br>Typ podstawki"+socket+"<br>Typ pamięci RAM:"+typeOfRAM
				+"<br>Ilość slotów PCI:"+numberOfPCIeSlot+"<br>Ilość slotów pamięci RAM:"+numberOfRAMSlot+
				"<br>Ilość slotów USB:"+numberOfUSBSlot+"<br>Opis:"+description+"<br>Ilość dostępnych:"+numberOfItems;
	}
	
	public Boolean equals(MotherBoard obj){
		if(super.equals(obj) && socket.equals(obj.socket) && typeOfRAM.equals(obj.typeOfRAM) && numberOfPCIeSlot==obj.numberOfPCIeSlot && numberOfRAMSlot==obj.numberOfRAMSlot && numberOfUSBSlot==obj.numberOfUSBSlot){
			return true;
		}
		else{
			return false;
		}
	}
}
