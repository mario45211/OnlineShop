package Items;

//klasa przechowująca obiekty dla komputerowych kart graficznych 
public class GraphicCard extends ComputerItem{
	//pola przechowujące szerokość magistrali oraz ilość graficznej pamięci RAM 
	private int RAMMemory, PCIBus;
	
	public GraphicCard(String manufacturer, String model, String description, int price, int amountOfRAM, int wideOfPCIBus, int number){
		super(description, price,manufacturer,model,number);
		this.RAMMemory = amountOfRAM;
		this.PCIBus = wideOfPCIBus;
		
	}
	
	public int getAmountOfGraphicRAM(){
		return this.RAMMemory;
	}
	
	public int getPCIBus(){
		return this.PCIBus;
	}
	
	@Override
	public String toString(){
		return "KARTA GRAFICZNA"+super.toString()+"<br>Ilość graficznej pamięci RAM:  "+RAMMemory+
				"<br>Szerokość magistrali:  "+PCIBus+"<br>Opis:  "+description+"<br>Ilość dostępnych:  "+numberOfItems+"<br>";
	}
	
	public Boolean equals(GraphicCard obj){
		if(super.equals(obj)&& RAMMemory==obj.RAMMemory && PCIBus==obj.PCIBus){
			return true;
		}
		else{
			return false;
		}
	}
}
