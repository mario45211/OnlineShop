package Items;

public class PC extends ComputerItem{
	private Processor processor;
	private MotherBoard motherboard;
	private GraphicCard graphicCard;
	private DiskDrive diskDrive;
	private ComputerCase computerCase;
	private PowerSupply powerSupply;
	
	private String operatingSystem;
	
	
	public PC(String description, int price, String manufacturer, String model,Processor processor,MotherBoard motherboard, GraphicCard graphicCard, DiskDrive diskDrive, ComputerCase computerCase, PowerSupply powerSupply, String operatingSystem,int number){
		super(description, price,manufacturer,model,number);
		this.processor=processor;
		this.motherboard=motherboard;
		this.graphicCard=graphicCard;
		this.diskDrive=diskDrive;
		this.computerCase=computerCase;
		this.powerSupply=powerSupply;
		
		this.operatingSystem = operatingSystem;
	}
	public PC(String description, int price, String manufacturer, String model,Processor processor,MotherBoard motherboard, GraphicCard graphicCard, DiskDrive diskDrive, ComputerCase computerCase, PowerSupply powerSupply,int number){
		super(description, price,manufacturer,model,number);
		this.processor=processor;
		this.motherboard=motherboard;
		this.graphicCard=graphicCard;
		this.diskDrive=diskDrive;
		this.computerCase=computerCase;
		this.powerSupply=powerSupply;
		this.operatingSystem = "None";
		
				
				
	}
	
	@Override
	public String toString(){
		return "PC"+super.toString()+"<br>Procesor: "+processor.toString()+"<Płyta główna: "+motherboard.toString()+
		"<br>Karta graficzna: "+graphicCard.toString()+"<br>Dysk: "+diskDrive.toString()
		+"<br>Obudowa: "+computerCase.toString()+"<br>Zasilacz: "+powerSupply.toString()+"<br>OS: "+operatingSystem+
		"<br>Opis:  "+description+"<br>Ilość dostępnych:  "+numberOfItems+"<br>";
		
	}
	
	public Boolean equals(PC obj){
		if(this.processor.equals(obj.processor)&&motherboard.equals(obj.motherboard)&&graphicCard.equals(obj.graphicCard)&&
				diskDrive.equals(obj.diskDrive)&&computerCase.equals(obj.computerCase) && powerSupply.equals(obj.powerSupply) && operatingSystem.equals(obj.operatingSystem)){
			return true;
		}
		else{
			return false;
		}
	}
}
