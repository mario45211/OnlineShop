package Items;

public class GraphicCard extends ComputerItem{

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
		return super.toString()+RAMMemory+PCIBus+numberOfItems;
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
