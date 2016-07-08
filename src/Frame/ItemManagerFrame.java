package Frame;

import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Items.ComputerCase;
import Items.DiskDrive;
import Items.GraphicCard;
import Items.Item;
import Items.MotherBoard;
import Items.PowerSupply;
import Items.Processor;
import MainFrame.MainFrameController;
import MainFrame.MainFrameModel;
import MainFrame.MainFrameView;
import Other.Dimension3D;
import Other.ShopMemory;
import Shop.Shop;
import Users.User;

/*klasa tworząca okienko do zarządzania przedmiotami w sklepie*/
public class ItemManagerFrame extends MyFrame{

	private Shop shop;
	
	private JButton addItemButton = new JButton("Dodaj");
	private JButton searchItemButton = new JButton("Szukaj");
	private JButton deleteItemButton = new JButton("Usuń przedmiot");
	private JButton clearFormButton = new JButton("Wyczyść");
	private JButton showAllItemButton = new JButton("Wyświetl przedmioty");
	private JButton backToShopButton = new JButton("<-- Powrót do sklepu");
	private Font normalFont = new Font(this.getName(),Font.BOLD, 14);
	
	private JScrollPane scrollPanel = new JScrollPane();
	private JList itemList = new JList();
	private JTextField manufacturerTextField = new JTextField();
	private JTextField modelTextField = new JTextField();
	private JTextField priceTextField = new JTextField();
	private JTextField computerCaseColorTextField = new JTextField();
	private JTextField diskDriveCapacityTextField = new JTextField();
	private JTextField diskDriveReadSpeedTextField = new JTextField();
	private JTextField diskDriveWriteSpeedTextField = new JTextField();
	private JComboBox diskDriveTypeComboBox = new JComboBox();
	private JComboBox graphicCardPCIBusComboBox = new JComboBox();
	private JComboBox graphicCardRAMMemoryComboBox = new JComboBox();
	private JTextField laptopBatteryCapacityTextField = new JTextField();
	private JComboBox laptopDisplaySizeComboBox = new JComboBox();
	private JComboBox laptopDisplayTypeComboBox = new JComboBox();
	private JTextField  laptopWeightTextField = new JTextField();
	private JComboBox  operatingSystemComboBox = new JComboBox();
	private JComboBox  moboNumberOfPCIeSlotComboBox = new JComboBox();
	private JComboBox  moboNumberOfRAMSlotComboBox= new JComboBox();
	private JComboBox  moboNumberOfUSBSlotComboBox = new JComboBox();
	private JComboBox  moboSocketComboBox = new JComboBox();
	private JComboBox moboRAMTypeComboBox = new JComboBox();
	private JTextField powerSupplyPowerTextField = new JTextField();
	private JTextField processorCoreTimingTextField = new JTextField();
	private JComboBox processorNumberOfCoresComboBox = new JComboBox();
	private JComboBox processorSocketComboBox = new JComboBox();
	private JComboBox itemTypeComboBox = new JComboBox();
	private JTextArea itemDescriptionTextArea = new JTextArea();
	private JButton comfirmAddItemButton = new JButton("Dodaj");
	private JButton comfirmSearchItemButton = new JButton("Szukaj");
	private JLabel itemTypeLabel = new JLabel("Wybierz typ: ");
	private Font formFont = new Font(this.getName(),Font.BOLD, 12);
	
	private JLabel producerLabel = new JLabel("Producent:");
	private JLabel modelLabel = new JLabel("Model:");
	private JLabel priceLabel = new JLabel("Cena:");
	private JLabel colorLabel = new JLabel("Kolor:");
	private JLabel diskDriveCapacityLabel = new JLabel("Pojemność dysku:");
	private JLabel diskDriveReadSpeedLabel = new JLabel("Prędkość odczytu:");
	private JLabel diskDriveWriteSpeedLabel = new JLabel("Prędkość zapisu:");
	private JLabel diskDriveTypeLabel = new JLabel("Typ dysku:");
	private JLabel graphicCardPCIBusLabel = new JLabel("Prędkość magistrali PCI:");
	private JLabel graphicCardRAMLabel= new JLabel("Ilość pamięci RAM:");
	private JLabel laptopBatteryCapacityLabel = new JLabel("Pojemność baterii:");
	private JLabel laptopDisplaySizeLabel = new JLabel("Rozmiar ekranu:");
	private JLabel laptopDisplayTypeLabel = new JLabel("Typ ekranu:");
	private JLabel laptopWeightLabel = new JLabel("Waga:");
	private JLabel operatingSystemLabel = new JLabel("System operacyjny:");
	private JLabel moboPCISlotLabel = new JLabel("Ilość slotów PCI:");
	private JLabel moboRAMSlotLabel = new JLabel("Ilość slotów pamięci RAM:");
	private JLabel moboUSBSlotLabel= new JLabel("Ilość slotów USB:");
	private JLabel moboRAMTypeLabel = new JLabel("Typ pamięci RAM:");
	private JLabel powerSupplyLabel= new JLabel("Moc:");
	private JLabel processorSocketLabel = new JLabel("Rodzaj gniazda:");
	private JLabel processorNumberOfCoresLabel = new JLabel("Ilość rdzeni:");
	private JLabel processorCoreTimingLabel = new JLabel("Taktowanie rdzeni:");
	private JLabel descriptionLabel = new JLabel("Opis:");
	private JTextField numberOfItemTextField = new JTextField();
	private JLabel numberOfItemLabel = new JLabel("Ilość przedmiotów");
	private JLabel computerCaseDimensionLabel = new JLabel("Wymiary:");
	private JTextField computerCaseDimensionX = new JTextField();
	private JTextField computerCaseDimensionY = new JTextField();
	private JTextField computerCaseDimensionZ = new JTextField();
	private JLabel computerCaseXLabel = new JLabel("Długość:");
	private JLabel computerCaseYLabel = new JLabel("Szerokość:");
	private JLabel computerCaseZLabel = new JLabel("Głębokość:");
	
	private JLabel moboSocketLabel = new JLabel("Rodzaj gniazda:");
	private Item selectedItem;
	private JComboBox searchItemTypeComboBox = new JComboBox();
	private String filepath;
	private ShopMemory shopMemory;
	
	public ItemManagerFrame(String title, String filepath){
		super(title);
		this.filepath = filepath;
		this.shop = new Shop(100,10,filepath);
		
		shopMemory = new ShopMemory(filepath);
		
		shop = (Shop)shopMemory.loadFromFile();
		
		diskDriveTypeComboBox.addItem("HDD");
		diskDriveTypeComboBox.addItem("SDD");
		graphicCardPCIBusComboBox.addItem(128);
		graphicCardPCIBusComboBox.addItem(256);
		laptopDisplayTypeComboBox.addItem("Błyszcząca");
		laptopDisplayTypeComboBox.addItem("Matowa");
		graphicCardRAMMemoryComboBox.addItem(256);
		graphicCardRAMMemoryComboBox.addItem(512);
		graphicCardRAMMemoryComboBox.addItem(1024);
		graphicCardRAMMemoryComboBox.addItem(2048);
		graphicCardRAMMemoryComboBox.addItem(4096);
		laptopDisplaySizeComboBox.addItem(8);
		laptopDisplaySizeComboBox.addItem(10);
		laptopDisplaySizeComboBox.addItem(14);
		laptopDisplaySizeComboBox.addItem(16);
		laptopDisplaySizeComboBox.addItem(18);
		operatingSystemComboBox.addItem("Brak");
		operatingSystemComboBox.addItem("Windows 7");
		operatingSystemComboBox.addItem("Windows 8.1");
		operatingSystemComboBox.addItem("Windows 10");
		operatingSystemComboBox.addItem("Linux");
		moboNumberOfPCIeSlotComboBox.addItem(1);
		moboNumberOfPCIeSlotComboBox.addItem(2);
		moboNumberOfPCIeSlotComboBox.addItem(3);
		moboNumberOfPCIeSlotComboBox.addItem(4);
		moboNumberOfPCIeSlotComboBox.addItem(5);
		moboNumberOfPCIeSlotComboBox.addItem(6);
		moboNumberOfRAMSlotComboBox.addItem(1);
		moboNumberOfRAMSlotComboBox.addItem(2);
		moboNumberOfRAMSlotComboBox.addItem(4);
		moboNumberOfUSBSlotComboBox.addItem(2);
		moboNumberOfUSBSlotComboBox.addItem(4);
		moboSocketComboBox.addItem("Intel 775");
		moboSocketComboBox.addItem("Intel 1150");
		moboSocketComboBox.addItem("Intel 1055");
		moboRAMTypeComboBox.addItem("DDR");
		moboRAMTypeComboBox.addItem("DDR2");
		moboRAMTypeComboBox.addItem("DDR3");
		processorNumberOfCoresComboBox.addItem(1);
		processorNumberOfCoresComboBox.addItem(2);
		processorNumberOfCoresComboBox.addItem(3);
		processorNumberOfCoresComboBox.addItem(4);
		processorSocketComboBox.addItem("Intel 775");
		processorSocketComboBox.addItem("Intel 1150");
		processorSocketComboBox.addItem("Intel 1055");
		
		itemTypeComboBox.addItem("Wybierz typ");
		itemTypeComboBox.addItem("Procesor");
		itemTypeComboBox.addItem("Zasilacz");
		itemTypeComboBox.addItem("Obudowa");
		itemTypeComboBox.addItem("Płyta główna");
		itemTypeComboBox.addItem("Karta graficzna");
		itemTypeComboBox.addItem("Dysk");
		itemTypeComboBox.addItem("Laptop");
		itemTypeComboBox.addItem("PC");
		
		searchItemTypeComboBox.addItem("Wybierz typ");
		searchItemTypeComboBox.addItem("Procesor");
		searchItemTypeComboBox.addItem("Zasilacz");
		searchItemTypeComboBox.addItem("Obudowa");
		searchItemTypeComboBox.addItem("Płyta główna");
		searchItemTypeComboBox.addItem("Karta graficzna");
		searchItemTypeComboBox.addItem("Dysk");
		searchItemTypeComboBox.addItem("Laptop");
		searchItemTypeComboBox.addItem("PC");
		
		producerLabel.setVisible(false);
		 modelLabel.setVisible(false);
		 priceLabel.setVisible(false);;
		 colorLabel.setVisible(false);
		 diskDriveCapacityLabel.setVisible(false);
		diskDriveReadSpeedLabel.setVisible(false);
		 diskDriveWriteSpeedLabel.setVisible(false);
		 diskDriveTypeLabel.setVisible(false);
		 graphicCardPCIBusLabel.setVisible(false);
		 graphicCardRAMLabel.setVisible(false);;
		 laptopBatteryCapacityLabel.setVisible(false);
		 laptopDisplaySizeLabel.setVisible(false);;
		 laptopDisplayTypeLabel.setVisible(false);
		 laptopWeightLabel.setVisible(false);
		 operatingSystemLabel.setVisible(false);
		 moboPCISlotLabel.setVisible(false);
		 moboRAMSlotLabel.setVisible(false);
		moboUSBSlotLabel.setVisible(false);
		 moboRAMTypeLabel.setVisible(false);;
		 powerSupplyLabel.setVisible(false);;
		 processorSocketLabel.setVisible(false);
		 processorNumberOfCoresLabel.setVisible(false);
		 processorCoreTimingLabel.setVisible(false);;
		 descriptionLabel.setVisible(false);
		 computerCaseDimensionLabel.setVisible(false);
		computerCaseDimensionX.setVisible(false);
		computerCaseDimensionY.setVisible(false);
		computerCaseDimensionZ.setVisible(false);
		computerCaseXLabel.setVisible(false);
		computerCaseYLabel.setVisible(false);
		computerCaseZLabel.setVisible(false);
		moboSocketLabel.setVisible(false);
		searchItemTypeComboBox.setVisible(false);
		
		
		 producerLabel.setFont(formFont);
		 modelLabel.setFont(formFont);
		 priceLabel.setFont(formFont);;
		 colorLabel.setFont(formFont);
		 diskDriveCapacityLabel.setFont(formFont);
		diskDriveReadSpeedLabel.setFont(formFont);
		 diskDriveWriteSpeedLabel.setFont(formFont);
		 diskDriveTypeLabel.setFont(formFont);
		 graphicCardPCIBusLabel.setFont(formFont);
		 graphicCardRAMLabel.setFont(formFont);;
		 laptopBatteryCapacityLabel.setFont(formFont);
		 laptopDisplaySizeLabel.setFont(formFont);;
		 laptopDisplayTypeLabel.setFont(formFont);
		 laptopWeightLabel.setFont(formFont);
		 operatingSystemLabel.setFont(formFont);
		 moboPCISlotLabel.setFont(formFont);
		 moboRAMSlotLabel.setFont(formFont);
		moboUSBSlotLabel.setFont(formFont);
		 moboRAMTypeLabel.setFont(formFont);;
		 powerSupplyLabel.setFont(formFont);;
		 processorSocketLabel.setFont(formFont);
		 processorNumberOfCoresLabel.setFont(formFont);
		 processorCoreTimingLabel.setFont(formFont);;
		 descriptionLabel.setFont(formFont);
		 moboSocketLabel.setFont(formFont);
		 searchItemTypeComboBox.setFont(formFont);
		 
		 
		 manufacturerTextField.setVisible(false);
		modelTextField.setVisible(false);
		graphicCardPCIBusComboBox.setVisible(false);
		graphicCardPCIBusLabel.setVisible(false);
		graphicCardRAMLabel.setVisible(false);
		graphicCardRAMMemoryComboBox.setVisible(false);
		diskDriveCapacityTextField.setVisible(false);
		diskDriveReadSpeedTextField.setVisible(false);
		diskDriveTypeComboBox.setVisible(false);
		diskDriveWriteSpeedTextField.setVisible(false);
		computerCaseColorTextField.setVisible(false);
		moboNumberOfPCIeSlotComboBox.setVisible(false);
		moboNumberOfRAMSlotComboBox.setVisible(false);
		moboNumberOfUSBSlotComboBox.setVisible(false);
		moboRAMTypeComboBox.setVisible(false);
		moboSocketComboBox.setVisible(false);
		laptopBatteryCapacityTextField.setVisible(false);
		laptopDisplaySizeComboBox.setVisible(false);
		laptopDisplayTypeComboBox.setVisible(false);
		laptopWeightTextField.setVisible(false);
		operatingSystemComboBox.setVisible(false);
		powerSupplyPowerTextField.setVisible(false);
		comfirmAddItemButton.setVisible(false);
		comfirmSearchItemButton.setVisible(false);
		numberOfItemTextField.setVisible(false);
		numberOfItemLabel.setVisible(false);
		processorCoreTimingTextField.setVisible(false);
		processorNumberOfCoresComboBox.setVisible(false);
		processorSocketComboBox.setVisible(false);
		itemDescriptionTextArea.setVisible(false);
		priceTextField.setVisible(false);
			
			
		manufacturerTextField.setFont(formFont);
		modelTextField.setFont(formFont);
		graphicCardPCIBusComboBox.setFont(formFont);
		graphicCardPCIBusLabel.setFont(formFont);
		graphicCardRAMLabel.setFont(formFont);
		graphicCardRAMMemoryComboBox.setFont(formFont);
		diskDriveCapacityTextField.setFont(formFont);
		diskDriveReadSpeedTextField.setFont(formFont);
		diskDriveTypeComboBox.setFont(formFont);
		diskDriveWriteSpeedTextField.setFont(formFont);
		computerCaseColorTextField.setFont(formFont);
		moboNumberOfPCIeSlotComboBox.setFont(formFont);
		moboNumberOfRAMSlotComboBox.setFont(formFont);
		moboNumberOfUSBSlotComboBox.setFont(formFont);
		moboRAMTypeComboBox.setFont(formFont);
		moboSocketComboBox.setFont(formFont);
		laptopBatteryCapacityTextField.setFont(formFont);
		laptopDisplaySizeComboBox.setFont(formFont);
		laptopDisplayTypeComboBox.setFont(formFont);
		laptopWeightTextField.setFont(formFont);
		operatingSystemComboBox.setFont(formFont);
		powerSupplyPowerTextField.setFont(formFont);
		comfirmAddItemButton.setFont(formFont);
		comfirmSearchItemButton.setFont(formFont);
		numberOfItemTextField.setFont(formFont);
		 numberOfItemLabel.setFont(formFont);
		 processorCoreTimingTextField.setFont(formFont);
		processorNumberOfCoresComboBox.setFont(formFont);
		processorSocketComboBox.setFont(formFont);
		itemDescriptionTextArea.setFont(formFont);
		priceTextField.setFont(formFont);
		computerCaseDimensionLabel.setFont(formFont);
		computerCaseDimensionX.setFont(formFont);
		computerCaseDimensionY.setFont(formFont);
		computerCaseDimensionZ.setFont(formFont);
		computerCaseXLabel.setFont(formFont);
		computerCaseYLabel.setFont(formFont);
		computerCaseZLabel.setFont(formFont);
		
		paintComponents();
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				shopMemory.saveToFile(shop);
				dispose();
			}
		});
		showAllItemButton.doClick();
	}
	
	private void paintComponents(){
		
		this.setLayout(null);
		
		addItemButton.setFont(normalFont);
		searchItemButton.setFont(normalFont);
		deleteItemButton.setFont(normalFont);
		clearFormButton.setFont(normalFont);
		showAllItemButton.setFont(normalFont);
		backToShopButton.setFont(normalFont);
		
		int buttonWidth = 150;
		int buttonHeight = 75;
		clearFormButton.setBounds(20,20,buttonWidth,buttonHeight);
		showAllItemButton.setBounds(clearFormButton.getBounds().x+clearFormButton.getBounds().width+10,clearFormButton.getBounds().y,buttonWidth+50,buttonHeight);
		addItemButton.setBounds(showAllItemButton.getBounds().x+showAllItemButton.getBounds().width+10,showAllItemButton.getBounds().y,buttonWidth-30,buttonHeight);
		searchItemButton.setBounds(addItemButton.getBounds().x+addItemButton.getBounds().width+10,addItemButton.getBounds().y,buttonWidth-30,buttonHeight);
		deleteItemButton.setBounds(searchItemButton.getBounds().x+searchItemButton.getBounds().width+10,searchItemButton.getBounds().y,buttonWidth+30,buttonHeight);
		backToShopButton.setBounds(searchItemButton.getBounds().x+10,searchItemButton.getBounds().y+searchItemButton.getBounds().height+30,buttonWidth+100,buttonHeight);
		
		scrollPanel.setBounds(addItemButton.getBounds().x,backToShopButton.getBounds().y+backToShopButton.getBounds().height+30,450,getSize().height-270);
		scrollPanel.setVisible(false);
		
		final int formFieldWidth = 180;
		final int formFieldHeight = 30;
		itemTypeLabel.setFont(formFont);
		itemTypeLabel.setBounds(clearFormButton.getBounds().x,backToShopButton.getBounds().y,formFieldWidth,formFieldHeight);
		itemTypeComboBox.setFont(formFont);
		itemTypeComboBox.setBounds(itemTypeLabel.getBounds().x+itemTypeLabel.getBounds().width,itemTypeLabel.getBounds().y,formFieldWidth,formFieldHeight);
		itemTypeLabel.setVisible(false);
		itemTypeComboBox.setVisible(false);
		producerLabel.setBounds(itemTypeLabel.getBounds().x, itemTypeLabel.getBounds().y+itemTypeLabel.getBounds().height+10,formFieldWidth, formFieldHeight);
		manufacturerTextField.setBounds(producerLabel.getBounds().x+producerLabel.getBounds().width,producerLabel.getBounds().y,formFieldWidth,formFieldHeight);
		modelLabel.setBounds(producerLabel.getBounds().x,producerLabel.getBounds().y+producerLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
		modelTextField.setBounds(modelLabel.getBounds().x+modelLabel.getBounds().width,modelLabel.getBounds().y,formFieldWidth,formFieldHeight);
		searchItemTypeComboBox.setBounds(itemTypeLabel.getBounds().x+itemTypeLabel.getBounds().width,itemTypeLabel.getBounds().y,formFieldWidth,formFieldHeight);
		comfirmSearchItemButton.setBounds(modelTextField.getBounds().x,modelTextField.getBounds().y+modelTextField.getBounds().height+10,100,30);
		
		
		backToShopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shopMemory.saveToFile(shop);
				dispose();
				MainFrameModel model = new MainFrameModel("src/file.dat");
				MainFrameView view = new MainFrameView("Komputerowo");
				MainFrameController controller = new MainFrameController(model,view);
			}
		});
		itemList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedItem=(Item)itemList.getSelectedValue();
				
			}
		});
		
		showAllItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Item> list = new ArrayList<Item>();
				for(int i=0;i<shop.getItemCounter();i++)
					list.add(shop.getItems()[i]);
				itemList.setListData(new Vector<Item>(list));
				
				itemList.setFixedCellHeight(200);
				itemList.setCellRenderer(new DefaultListCellRenderer(){
					
					@Override
					public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
						Component renderer  = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
						String pre = "<html><body style='width: 200px;margin: 20px'>";
						((JLabel)renderer).setText(pre + ((Item)value).convertToText());

						return renderer;
					}
				});

				scrollPanel.setViewportView(itemList);
				scrollPanel.setVisible(true);

				repaint();
				
			}
		});
		
		searchItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				producerLabel.setVisible(false);
				 modelLabel.setVisible(false);
				 priceLabel.setVisible(false);;
				 colorLabel.setVisible(false);
				 diskDriveCapacityLabel.setVisible(false);
				 diskDriveReadSpeedLabel.setVisible(false);
				 diskDriveWriteSpeedLabel.setVisible(false);
				 diskDriveTypeLabel.setVisible(false);
				 graphicCardPCIBusLabel.setVisible(false);
				 graphicCardRAMLabel.setVisible(false);;
				 laptopBatteryCapacityLabel.setVisible(false);
				 laptopDisplaySizeLabel.setVisible(false);;
				 laptopDisplayTypeLabel.setVisible(false);
				 laptopWeightLabel.setVisible(false);
				 operatingSystemLabel.setVisible(false);
				 moboPCISlotLabel.setVisible(false);
				 moboRAMSlotLabel.setVisible(false);
				moboUSBSlotLabel.setVisible(false);
				 moboRAMTypeLabel.setVisible(false);;
				 powerSupplyLabel.setVisible(false);;
				 processorSocketLabel.setVisible(false);
				 processorNumberOfCoresLabel.setVisible(false);
				 processorCoreTimingLabel.setVisible(false);;
				 descriptionLabel.setVisible(false);
				 
				 manufacturerTextField.setVisible(false);
				modelTextField.setVisible(false);
				graphicCardPCIBusComboBox.setVisible(false);
				graphicCardPCIBusLabel.setVisible(false);
				graphicCardRAMLabel.setVisible(false);
				graphicCardRAMMemoryComboBox.setVisible(false);
				diskDriveCapacityTextField.setVisible(false);
				diskDriveReadSpeedTextField.setVisible(false);
				diskDriveTypeComboBox.setVisible(false);
				diskDriveWriteSpeedTextField.setVisible(false);
				computerCaseColorTextField.setVisible(false);
				moboNumberOfPCIeSlotComboBox.setVisible(false);
				moboNumberOfRAMSlotComboBox.setVisible(false);
				moboNumberOfUSBSlotComboBox.setVisible(false);
				moboRAMTypeComboBox.setVisible(false);
				moboSocketComboBox.setVisible(false);
				laptopBatteryCapacityTextField.setVisible(false);
				laptopDisplaySizeComboBox.setVisible(false);
				laptopDisplayTypeComboBox.setVisible(false);
				laptopWeightTextField.setVisible(false);
				operatingSystemComboBox.setVisible(false);
				powerSupplyPowerTextField.setVisible(false);
				comfirmAddItemButton.setVisible(false);
				comfirmSearchItemButton.setVisible(false);
				numberOfItemTextField.setVisible(false);
				 numberOfItemLabel.setVisible(false);
				 
				processorCoreTimingTextField.setVisible(false);
				processorNumberOfCoresComboBox.setVisible(false);
				processorSocketComboBox.setVisible(false);
				itemDescriptionTextArea.setVisible(false);
				priceTextField.setVisible(false);
				searchItemTypeComboBox.setVisible(true);
				itemTypeComboBox.setVisible(false);
				itemTypeLabel.setVisible(true);
				computerCaseDimensionLabel.setVisible(false);
				computerCaseDimensionX.setVisible(false);
				computerCaseDimensionY.setVisible(false);
				computerCaseDimensionZ.setVisible(false);
				computerCaseXLabel.setVisible(false);
				computerCaseYLabel.setVisible(false);
				computerCaseZLabel.setVisible(false);
				producerLabel.setVisible(true);
			
				modelLabel.setVisible(true);
				manufacturerTextField.setVisible(true);
				modelTextField.setVisible(true);
				moboSocketLabel.setVisible(false);
				comfirmSearchItemButton.setVisible(true);
	
			}
		});
		
		comfirmSearchItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String producer = manufacturerTextField.getText();
				String model = modelTextField.getText();
				String itemType = searchItemTypeComboBox.getSelectedItem().toString();
				switch(itemType){
					case "Płyta główna:":
						itemType = "MotherBoard";
						break;
					case "Dysk":
						itemType = "DiskDrive";
						break;
					case "Karta graficzna":
						itemType = "GraphicCard";
						break;
					case "Zasilacz":
						itemType = "PowerSupply";
						break;
					case "Obudowa":
						itemType = "ComputerCase";
						break;
					case "Procesor":
						itemType = "Processor";
				}	
				System.out.println(itemType);
				List<Item> list = new ArrayList<Item>();
				Class clazz =null;
				Item tmp;
				for(int i=0;i<shop.getItemCounter();i++){
					tmp = shop.getItems()[i];
					try {
						clazz = Class.forName(itemType);
						
					}catch(ClassNotFoundException en){
						en.printStackTrace();
						System.out.println("Error");
					}
					
					if(tmp.getManufacturer().equals(producer) && tmp.getModel().equals(model) && tmp.getClass().equals(clazz)){
						list.add(tmp);
					}
				}
				itemList.setListData(new Vector<Item>(list));
				
				itemList.setFixedCellHeight(200);
				itemList.setCellRenderer(new DefaultListCellRenderer(){
					
					@Override
					public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
						Component renderer  = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
						String pre = "<html><body style='width: 200px;margin: 20px'>";
						((JLabel)renderer).setText(pre + ((Item)value).convertToText());

						return renderer;
					}
				});

				scrollPanel.setViewportView(itemList);
				scrollPanel.setVisible(true);

				showAllItemButton.doClick();
				
			}
			
		});
		
		deleteItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				producerLabel.setVisible(false);
				 modelLabel.setVisible(false);
				 priceLabel.setVisible(false);;
				 colorLabel.setVisible(false);
				 diskDriveCapacityLabel.setVisible(false);
				diskDriveReadSpeedLabel.setVisible(false);
				 diskDriveWriteSpeedLabel.setVisible(false);
				 diskDriveTypeLabel.setVisible(false);
				 graphicCardPCIBusLabel.setVisible(false);
				 graphicCardRAMLabel.setVisible(false);;
				 laptopBatteryCapacityLabel.setVisible(false);
				 laptopDisplaySizeLabel.setVisible(false);;
				 laptopDisplayTypeLabel.setVisible(false);
				 laptopWeightLabel.setVisible(false);
				 operatingSystemLabel.setVisible(false);
				 moboPCISlotLabel.setVisible(false);
				 moboRAMSlotLabel.setVisible(false);
				moboUSBSlotLabel.setVisible(false);
				 moboRAMTypeLabel.setVisible(false);;
				 powerSupplyLabel.setVisible(false);;
				 processorSocketLabel.setVisible(false);
				 processorNumberOfCoresLabel.setVisible(false);
				 processorCoreTimingLabel.setVisible(false);;
				 descriptionLabel.setVisible(false);
				 
				 manufacturerTextField.setVisible(false);
				modelTextField.setVisible(false);
				graphicCardPCIBusComboBox.setVisible(false);
				graphicCardPCIBusLabel.setVisible(false);
				graphicCardRAMLabel.setVisible(false);
				graphicCardRAMMemoryComboBox.setVisible(false);
				diskDriveCapacityTextField.setVisible(false);
				diskDriveReadSpeedTextField.setVisible(false);
				diskDriveTypeComboBox.setVisible(false);
				diskDriveWriteSpeedTextField.setVisible(false);
				computerCaseColorTextField.setVisible(false);
				moboNumberOfPCIeSlotComboBox.setVisible(false);
				moboNumberOfRAMSlotComboBox.setVisible(false);
				moboNumberOfUSBSlotComboBox.setVisible(false);
				moboRAMTypeComboBox.setVisible(false);
				moboSocketComboBox.setVisible(false);
				laptopBatteryCapacityTextField.setVisible(false);
				laptopDisplaySizeComboBox.setVisible(false);
				laptopDisplayTypeComboBox.setVisible(false);
				laptopWeightTextField.setVisible(false);
				operatingSystemComboBox.setVisible(false);
				powerSupplyPowerTextField.setVisible(false);
				comfirmAddItemButton.setVisible(false);
				comfirmSearchItemButton.setVisible(false);
				numberOfItemTextField.setVisible(false);
				 numberOfItemLabel.setVisible(false);
				 
				processorCoreTimingTextField.setVisible(false);
				processorNumberOfCoresComboBox.setVisible(false);
				processorSocketComboBox.setVisible(false);
				itemDescriptionTextArea.setVisible(false);
				priceTextField.setVisible(false);
				
				itemTypeComboBox.setVisible(false);
				
				computerCaseDimensionLabel.setVisible(false);
				computerCaseDimensionX.setVisible(false);
				computerCaseDimensionY.setVisible(false);
				computerCaseDimensionZ.setVisible(false);
				computerCaseXLabel.setVisible(false);
				computerCaseYLabel.setVisible(false);
				computerCaseZLabel.setVisible(false);
				if(selectedItem==null){
					producerLabel.setVisible(true);
					itemTypeLabel.setVisible(true);
					modelLabel.setVisible(true);
					manufacturerTextField.setVisible(true);
					modelTextField.setVisible(true);
					moboSocketLabel.setVisible(false);
					comfirmSearchItemButton.setVisible(true);
					searchItemTypeComboBox.setVisible(true);
				
					String producer = manufacturerTextField.getText();
					String model = modelTextField.getText();
					String itemType = searchItemTypeComboBox.getSelectedItem().toString();
					
					
				}
				else{
					shop.deleteItem(selectedItem);
					showAllItemButton.doClick();
				}
			}
		});
		addItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				producerLabel.setVisible(false);
				 modelLabel.setVisible(false);
				 priceLabel.setVisible(false);;
				 colorLabel.setVisible(false);
				 diskDriveCapacityLabel.setVisible(false);
				diskDriveReadSpeedLabel.setVisible(false);
				 diskDriveWriteSpeedLabel.setVisible(false);
				 diskDriveTypeLabel.setVisible(false);
				 graphicCardPCIBusLabel.setVisible(false);
				 graphicCardRAMLabel.setVisible(false);;
				 laptopBatteryCapacityLabel.setVisible(false);
				 laptopDisplaySizeLabel.setVisible(false);;
				 laptopDisplayTypeLabel.setVisible(false);
				 laptopWeightLabel.setVisible(false);
				 operatingSystemLabel.setVisible(false);
				 moboPCISlotLabel.setVisible(false);
				 moboRAMSlotLabel.setVisible(false);
				moboUSBSlotLabel.setVisible(false);
				 moboRAMTypeLabel.setVisible(false);;
				 powerSupplyLabel.setVisible(false);;
				 processorSocketLabel.setVisible(false);
				 processorNumberOfCoresLabel.setVisible(false);
				 processorCoreTimingLabel.setVisible(false);;
				 descriptionLabel.setVisible(false);
				 
				 manufacturerTextField.setVisible(false);
				modelTextField.setVisible(false);
				graphicCardPCIBusComboBox.setVisible(false);
				graphicCardPCIBusLabel.setVisible(false);
				graphicCardRAMLabel.setVisible(false);
				graphicCardRAMMemoryComboBox.setVisible(false);
				diskDriveCapacityTextField.setVisible(false);
				diskDriveReadSpeedTextField.setVisible(false);
				diskDriveTypeComboBox.setVisible(false);
				diskDriveWriteSpeedTextField.setVisible(false);
				computerCaseColorTextField.setVisible(false);
				moboNumberOfPCIeSlotComboBox.setVisible(false);
				moboNumberOfRAMSlotComboBox.setVisible(false);
				moboNumberOfUSBSlotComboBox.setVisible(false);
				moboRAMTypeComboBox.setVisible(false);
				moboSocketComboBox.setVisible(false);
				laptopBatteryCapacityTextField.setVisible(false);
				laptopDisplaySizeComboBox.setVisible(false);
				laptopDisplayTypeComboBox.setVisible(false);
				laptopWeightTextField.setVisible(false);
				operatingSystemComboBox.setVisible(false);
				powerSupplyPowerTextField.setVisible(false);
				comfirmAddItemButton.setVisible(false);
				comfirmSearchItemButton.setVisible(false);
				numberOfItemTextField.setVisible(false);
				 numberOfItemLabel.setVisible(false);
				 
				processorCoreTimingTextField.setVisible(false);
				processorNumberOfCoresComboBox.setVisible(false);
				processorSocketComboBox.setVisible(false);
				itemDescriptionTextArea.setVisible(false);
				priceTextField.setVisible(false);
				itemTypeComboBox.setVisible(true);
				itemTypeLabel.setVisible(true);
				computerCaseDimensionLabel.setVisible(false);
				computerCaseDimensionX.setVisible(false);
				computerCaseDimensionY.setVisible(false);
				computerCaseDimensionZ.setVisible(false);
				computerCaseXLabel.setVisible(false);
				computerCaseYLabel.setVisible(false);
				computerCaseZLabel.setVisible(false);
				moboSocketLabel.setVisible(false);
				searchItemTypeComboBox.setVisible(false);
			}
		});
		
		itemTypeComboBox.addItemListener(new ItemListener() {
			
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(itemTypeComboBox.getSelectedIndex()!=0){
					producerLabel.setVisible(false);
					 modelLabel.setVisible(false);
					 priceLabel.setVisible(false);;
					 colorLabel.setVisible(false);
					 diskDriveCapacityLabel.setVisible(false);
					diskDriveReadSpeedLabel.setVisible(false);
					 diskDriveWriteSpeedLabel.setVisible(false);
					 diskDriveTypeLabel.setVisible(false);
					 graphicCardPCIBusLabel.setVisible(false);
					 graphicCardRAMLabel.setVisible(false);;
					 laptopBatteryCapacityLabel.setVisible(false);
					 laptopDisplaySizeLabel.setVisible(false);;
					 laptopDisplayTypeLabel.setVisible(false);
					 laptopWeightLabel.setVisible(false);
					 operatingSystemLabel.setVisible(false);
					 moboPCISlotLabel.setVisible(false);
					 moboRAMSlotLabel.setVisible(false);
					moboUSBSlotLabel.setVisible(false);
					 moboRAMTypeLabel.setVisible(false);;
					 powerSupplyLabel.setVisible(false);;
					 processorSocketLabel.setVisible(false);
					 processorNumberOfCoresLabel.setVisible(false);
					 processorCoreTimingLabel.setVisible(false);;
					 descriptionLabel.setVisible(false);
					 
					 manufacturerTextField.setVisible(false);
					modelTextField.setVisible(false);
					graphicCardPCIBusComboBox.setVisible(false);
					graphicCardPCIBusLabel.setVisible(false);
					graphicCardRAMLabel.setVisible(false);
					graphicCardRAMMemoryComboBox.setVisible(false);
					diskDriveCapacityTextField.setVisible(false);
					diskDriveReadSpeedTextField.setVisible(false);
					diskDriveTypeComboBox.setVisible(false);
					diskDriveWriteSpeedTextField.setVisible(false);
					computerCaseColorTextField.setVisible(false);
					moboNumberOfPCIeSlotComboBox.setVisible(false);
					moboNumberOfRAMSlotComboBox.setVisible(false);
					moboNumberOfUSBSlotComboBox.setVisible(false);
					moboRAMTypeComboBox.setVisible(false);
					moboSocketComboBox.setVisible(false);
					laptopBatteryCapacityTextField.setVisible(false);
					laptopDisplaySizeComboBox.setVisible(false);
					laptopDisplayTypeComboBox.setVisible(false);
					laptopWeightTextField.setVisible(false);
					operatingSystemComboBox.setVisible(false);
					powerSupplyPowerTextField.setVisible(false);
					comfirmAddItemButton.setVisible(false);
					comfirmSearchItemButton.setVisible(false);
					numberOfItemTextField.setVisible(false);
					 numberOfItemLabel.setVisible(false);
					 searchItemTypeComboBox.setVisible(false);
					 
					processorCoreTimingTextField.setVisible(false);
					processorNumberOfCoresComboBox.setVisible(false);
					processorSocketComboBox.setVisible(false);
					itemDescriptionTextArea.setVisible(false);
					priceTextField.setVisible(false);
					itemTypeComboBox.setVisible(true);
					itemTypeLabel.setVisible(true);
					computerCaseDimensionLabel.setVisible(false);
					computerCaseDimensionX.setVisible(false);
					computerCaseDimensionY.setVisible(false);
					computerCaseDimensionZ.setVisible(false);
					computerCaseXLabel.setVisible(false);
					computerCaseYLabel.setVisible(false);
					computerCaseZLabel.setVisible(false);
					producerLabel.setVisible(true);
				
					modelLabel.setVisible(true);
					manufacturerTextField.setVisible(true);
					modelTextField.setVisible(true);
					moboSocketLabel.setVisible(false);
				}
				if(itemTypeComboBox.getSelectedItem().equals("Procesor")){
					processorSocketLabel.setBounds(modelLabel.getBounds().x, modelLabel.getBounds().y+modelLabel.getBounds().height+20, formFieldWidth, formFieldHeight);
					processorSocketLabel.setVisible(true);
					processorSocketComboBox.setBounds(processorSocketLabel.getBounds().x+processorSocketLabel.getBounds().width,processorSocketLabel.getBounds().y,formFieldWidth,formFieldHeight);;
					processorSocketComboBox.setVisible(true);
					processorNumberOfCoresLabel.setBounds(processorSocketLabel.getBounds().x, processorSocketLabel.getBounds().y+processorSocketLabel.getBounds().height+20, formFieldWidth, formFieldHeight);
					processorNumberOfCoresLabel.setVisible(true);
					processorNumberOfCoresComboBox.setBounds(processorNumberOfCoresLabel.getBounds().x+processorNumberOfCoresLabel.getBounds().width,processorNumberOfCoresLabel.getBounds().y,formFieldWidth,formFieldHeight);
					processorNumberOfCoresComboBox.setVisible(true);
					processorCoreTimingLabel.setBounds(processorNumberOfCoresLabel.getBounds().x, processorNumberOfCoresLabel.getBounds().y+processorNumberOfCoresLabel.getBounds().height+20, formFieldWidth, formFieldHeight);
					processorCoreTimingLabel.setVisible(true);
					processorCoreTimingTextField.setBounds(processorCoreTimingLabel.getBounds().x+processorCoreTimingLabel.getBounds().width,processorCoreTimingLabel.getBounds().y,formFieldWidth,formFieldHeight);
					processorCoreTimingTextField.setVisible(true);
					priceLabel.setBounds(processorCoreTimingLabel.getBounds().x, processorCoreTimingLabel.getBounds().y+processorCoreTimingLabel.getBounds().height+20, formFieldWidth, formFieldHeight);
					priceLabel.setVisible(true);
					priceTextField.setBounds(priceLabel.getBounds().x+priceLabel.getBounds().width,priceLabel.getBounds().y,formFieldWidth,formFieldHeight);
					priceTextField.setVisible(true);
					descriptionLabel.setBounds(priceLabel.getBounds().x, priceLabel.getBounds().y+priceLabel.getBounds().height+20, formFieldWidth, formFieldHeight);
					descriptionLabel.setVisible(true);
					itemDescriptionTextArea.setBounds(descriptionLabel.getBounds().x+descriptionLabel.getBounds().width,descriptionLabel.getBounds().y,formFieldWidth,formFieldHeight*2);
					itemDescriptionTextArea.setVisible(true);
					numberOfItemLabel.setBounds(descriptionLabel.getBounds().x,descriptionLabel.getBounds().y+itemDescriptionTextArea.getBounds().height+10,formFieldWidth,formFieldHeight);
					numberOfItemLabel.setVisible(true);
					numberOfItemTextField.setBounds(itemDescriptionTextArea.getBounds().x,itemDescriptionTextArea.getBounds().y+itemDescriptionTextArea.getBounds().height+20,100,30);
					numberOfItemTextField.setVisible(true);
					
					comfirmAddItemButton.setBounds(numberOfItemTextField.getBounds().x,numberOfItemTextField.getBounds().y+numberOfItemTextField.getBounds().height+10,100,30);
					comfirmAddItemButton.setVisible(true);
				}
				else if(itemTypeComboBox.getSelectedItem().equals("Obudowa")){
					
					computerCaseDimensionLabel.setBounds(modelLabel.getBounds().x,modelLabel.getBounds().y+modelLabel.getBounds().height,formFieldWidth,formFieldHeight);
					computerCaseDimensionLabel.setVisible(true);
					computerCaseXLabel.setBounds(computerCaseDimensionLabel.getBounds().x+10,computerCaseDimensionLabel.getBounds().y+computerCaseDimensionLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					computerCaseXLabel.setVisible(true);
					computerCaseDimensionX.setBounds(computerCaseXLabel.getBounds().x+computerCaseXLabel.getBounds().width,computerCaseXLabel.getBounds().y,formFieldWidth,formFieldHeight);
					computerCaseDimensionX.setVisible(true);
					computerCaseYLabel.setBounds(computerCaseXLabel.getBounds().x,computerCaseXLabel.getBounds().y+computerCaseXLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					computerCaseYLabel.setVisible(true);
					computerCaseDimensionY.setBounds(computerCaseYLabel.getBounds().x+computerCaseYLabel.getBounds().width,computerCaseYLabel.getBounds().y,formFieldWidth,formFieldHeight);
					computerCaseDimensionY.setVisible(true);
					computerCaseZLabel.setBounds(computerCaseYLabel.getBounds().x,computerCaseYLabel.getBounds().y+computerCaseYLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					computerCaseZLabel.setVisible(true);
					computerCaseDimensionZ.setBounds(computerCaseZLabel.getBounds().x+computerCaseZLabel.getBounds().width,computerCaseZLabel.getBounds().y,formFieldWidth,formFieldHeight);
					computerCaseDimensionZ.setVisible(true);
					colorLabel.setBounds(computerCaseZLabel.getBounds().x-10, computerCaseZLabel.getBounds().y+computerCaseZLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					colorLabel.setVisible(true);
					computerCaseColorTextField.setBounds(colorLabel.getBounds().x+colorLabel.getBounds().width,colorLabel.getBounds().y,formFieldWidth,formFieldHeight);
					computerCaseColorTextField.setVisible(true);
					priceLabel.setBounds(colorLabel.getBounds().x,colorLabel.getBounds().y+colorLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					priceLabel.setVisible(true);
					priceTextField.setBounds(priceLabel.getBounds().x+priceLabel.getBounds().width,priceLabel.getBounds().y,formFieldWidth,formFieldHeight);
					priceTextField.setVisible(true);
					descriptionLabel.setBounds(priceLabel.getBounds().x, priceLabel.getBounds().y+priceLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					descriptionLabel.setVisible(true);
					itemDescriptionTextArea.setBounds(descriptionLabel.getBounds().x+descriptionLabel.getBounds().width,descriptionLabel.getBounds().y,formFieldWidth,formFieldHeight*2);
					itemDescriptionTextArea.setVisible(true);
					numberOfItemLabel.setBounds(descriptionLabel.getBounds().x,descriptionLabel.getBounds().y+itemDescriptionTextArea.getBounds().height+10,formFieldWidth,formFieldHeight);
					numberOfItemLabel.setVisible(true);
					numberOfItemTextField.setBounds(itemDescriptionTextArea.getBounds().x,itemDescriptionTextArea.getBounds().y+itemDescriptionTextArea.getBounds().height+20,100,30);
					numberOfItemTextField.setVisible(true);
					
					comfirmAddItemButton.setBounds(numberOfItemTextField.getBounds().x,numberOfItemTextField.getBounds().y+numberOfItemTextField.getBounds().height+10,100,30);
					comfirmAddItemButton.setVisible(true);
				}
				else if(itemTypeComboBox.getSelectedItem().equals("Zasilacz")){
					powerSupplyLabel.setBounds(modelLabel.getBounds().x,modelLabel.getBounds().y+modelLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					powerSupplyLabel.setVisible(true);
					powerSupplyPowerTextField.setBounds(powerSupplyLabel.getBounds().x+powerSupplyLabel.getBounds().width,powerSupplyLabel.getBounds().y,formFieldWidth,formFieldHeight);
					powerSupplyPowerTextField.setVisible(true);
					priceLabel.setBounds(powerSupplyLabel.getBounds().x,powerSupplyLabel.getBounds().y+powerSupplyLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					priceLabel.setVisible(true);
					priceTextField.setBounds(priceLabel.getBounds().x+priceLabel.getBounds().width,priceLabel.getBounds().y,formFieldWidth,formFieldHeight);
					priceTextField.setVisible(true);
					descriptionLabel.setBounds(priceLabel.getBounds().x, priceLabel.getBounds().y+priceLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					descriptionLabel.setVisible(true);
					itemDescriptionTextArea.setBounds(descriptionLabel.getBounds().x+descriptionLabel.getBounds().width,descriptionLabel.getBounds().y,formFieldWidth,formFieldHeight*2);
					itemDescriptionTextArea.setVisible(true);
					numberOfItemLabel.setBounds(descriptionLabel.getBounds().x,descriptionLabel.getBounds().y+itemDescriptionTextArea.getBounds().height+10,formFieldWidth,formFieldHeight);
					numberOfItemLabel.setVisible(true);
					numberOfItemTextField.setBounds(itemDescriptionTextArea.getBounds().x,itemDescriptionTextArea.getBounds().y+itemDescriptionTextArea.getBounds().height+20,100,30);
					numberOfItemTextField.setVisible(true);
					
					comfirmAddItemButton.setBounds(numberOfItemTextField.getBounds().x,numberOfItemTextField.getBounds().y+numberOfItemTextField.getBounds().height+10,100,30);
					comfirmAddItemButton.setVisible(true);
				}
				else if(itemTypeComboBox.getSelectedItem().equals("Płyta główna")){
					moboRAMTypeLabel.setBounds(modelLabel.getBounds().x,modelLabel.getBounds().y+modelLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					moboRAMTypeLabel.setVisible(true);
					moboRAMTypeComboBox.setBounds(moboRAMTypeLabel.getBounds().x+moboRAMTypeLabel.getBounds().width,moboRAMTypeLabel.getBounds().y,formFieldWidth,formFieldHeight);
					moboRAMTypeComboBox.setVisible(true);
					moboSocketLabel.setBounds(moboRAMTypeLabel.getBounds().x,moboRAMTypeLabel.getBounds().y+moboRAMTypeLabel.getBounds().height,formFieldWidth,formFieldHeight);
					moboSocketLabel.setVisible(true);
					moboSocketComboBox.setBounds(moboSocketLabel.getBounds().x+moboSocketLabel.getBounds().width,moboSocketLabel.getBounds().y,formFieldWidth,formFieldHeight);
					moboSocketComboBox.setVisible(true);
					moboRAMSlotLabel.setBounds(moboSocketLabel.getBounds().x,moboSocketLabel.getBounds().y+moboSocketLabel.getBounds().height,formFieldWidth,formFieldHeight);
					moboRAMSlotLabel.setVisible(true);
					moboNumberOfRAMSlotComboBox.setBounds(moboRAMSlotLabel.getBounds().x+moboRAMSlotLabel.getBounds().width,moboRAMSlotLabel.getBounds().y,formFieldWidth,formFieldHeight);
					moboNumberOfRAMSlotComboBox.setVisible(true);
					moboPCISlotLabel.setBounds(moboRAMSlotLabel.getBounds().x,moboRAMSlotLabel.getBounds().y+moboRAMSlotLabel.getBounds().height,formFieldWidth,formFieldHeight);
					moboPCISlotLabel.setVisible(true);
					moboNumberOfPCIeSlotComboBox.setBounds(moboPCISlotLabel.getBounds().x+moboPCISlotLabel.getBounds().width,moboPCISlotLabel.getBounds().y,formFieldWidth,formFieldHeight);
					moboNumberOfPCIeSlotComboBox.setVisible(true);
					moboUSBSlotLabel.setBounds(moboPCISlotLabel.getBounds().x,moboPCISlotLabel.getBounds().y+moboPCISlotLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					moboUSBSlotLabel.setVisible(true);
					moboNumberOfUSBSlotComboBox.setBounds(moboUSBSlotLabel.getBounds().x+moboUSBSlotLabel.getBounds().width,moboUSBSlotLabel.getBounds().y,formFieldWidth,formFieldHeight);
					moboNumberOfUSBSlotComboBox.setVisible(true);
					
					priceLabel.setBounds(moboUSBSlotLabel.getBounds().x,moboUSBSlotLabel.getBounds().y+moboUSBSlotLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					priceLabel.setVisible(true);
					priceTextField.setBounds(priceLabel.getBounds().x+priceLabel.getBounds().width,priceLabel.getBounds().y,formFieldWidth,formFieldHeight);
					priceTextField.setVisible(true);
					descriptionLabel.setBounds(priceLabel.getBounds().x, priceLabel.getBounds().y+priceLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					descriptionLabel.setVisible(true);
					itemDescriptionTextArea.setBounds(descriptionLabel.getBounds().x+descriptionLabel.getBounds().width,descriptionLabel.getBounds().y,formFieldWidth,formFieldHeight*2);
					itemDescriptionTextArea.setVisible(true);
					numberOfItemLabel.setBounds(descriptionLabel.getBounds().x,descriptionLabel.getBounds().y+itemDescriptionTextArea.getBounds().height+10,formFieldWidth,formFieldHeight);
					numberOfItemLabel.setVisible(true);
					numberOfItemTextField.setBounds(itemDescriptionTextArea.getBounds().x,itemDescriptionTextArea.getBounds().y+itemDescriptionTextArea.getBounds().height+20,100,30);
					numberOfItemTextField.setVisible(true);
					
					comfirmAddItemButton.setBounds(numberOfItemTextField.getBounds().x,numberOfItemTextField.getBounds().y+numberOfItemTextField.getBounds().height+10,100,30);
					comfirmAddItemButton.setVisible(true);
				}
				else if(itemTypeComboBox.getSelectedItem().equals("Dysk")){
					diskDriveTypeLabel.setBounds(modelLabel.getBounds().x,modelLabel.getBounds().y+modelLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					diskDriveTypeLabel.setVisible(true);
					diskDriveTypeComboBox.setBounds(diskDriveTypeLabel.getBounds().x+diskDriveTypeLabel.getBounds().width,diskDriveTypeLabel.getBounds().y,formFieldWidth,formFieldHeight);
					diskDriveTypeComboBox.setVisible(true);
					diskDriveCapacityLabel.setBounds(diskDriveTypeLabel.getBounds().x,diskDriveTypeLabel.getBounds().y+diskDriveTypeLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					diskDriveCapacityLabel.setVisible(true);
					diskDriveCapacityTextField.setBounds(diskDriveCapacityLabel.getBounds().x+diskDriveCapacityLabel.getBounds().width,diskDriveCapacityLabel.getBounds().y,formFieldWidth,formFieldHeight);
					diskDriveCapacityTextField.setVisible(true);
					diskDriveReadSpeedLabel.setBounds(diskDriveCapacityLabel.getBounds().x,diskDriveCapacityLabel.getBounds().y+diskDriveCapacityLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					diskDriveReadSpeedLabel.setVisible(true);
					diskDriveReadSpeedTextField.setBounds(diskDriveReadSpeedLabel.getBounds().x+diskDriveReadSpeedLabel.getBounds().width,diskDriveReadSpeedLabel.getBounds().y,formFieldWidth,formFieldHeight);
					diskDriveReadSpeedTextField.setVisible(true);
					diskDriveWriteSpeedLabel.setBounds(diskDriveReadSpeedLabel.getBounds().x,diskDriveReadSpeedLabel.getBounds().y+diskDriveReadSpeedLabel.getBounds().height,formFieldWidth,formFieldHeight);
					diskDriveWriteSpeedLabel.setVisible(true);
					diskDriveWriteSpeedTextField.setBounds(diskDriveWriteSpeedLabel.getBounds().x+diskDriveWriteSpeedLabel.getBounds().width,diskDriveWriteSpeedLabel.getBounds().y,formFieldWidth,formFieldHeight);
					diskDriveWriteSpeedTextField.setVisible(true);
					
					priceLabel.setBounds(diskDriveWriteSpeedLabel.getBounds().x,diskDriveWriteSpeedLabel.getBounds().y+diskDriveWriteSpeedLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					priceLabel.setVisible(true);
					priceTextField.setBounds(priceLabel.getBounds().x+priceLabel.getBounds().width,priceLabel.getBounds().y,formFieldWidth,formFieldHeight);
					priceTextField.setVisible(true);
					descriptionLabel.setBounds(priceLabel.getBounds().x, priceLabel.getBounds().y+priceLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					descriptionLabel.setVisible(true);
					itemDescriptionTextArea.setBounds(descriptionLabel.getBounds().x+descriptionLabel.getBounds().width,descriptionLabel.getBounds().y,formFieldWidth,formFieldHeight*2);
					itemDescriptionTextArea.setVisible(true);
					numberOfItemLabel.setBounds(descriptionLabel.getBounds().x,descriptionLabel.getBounds().y+itemDescriptionTextArea.getBounds().height+10,formFieldWidth,formFieldHeight);
					numberOfItemLabel.setVisible(true);
					numberOfItemTextField.setBounds(itemDescriptionTextArea.getBounds().x,itemDescriptionTextArea.getBounds().y+itemDescriptionTextArea.getBounds().height+20,100,30);
					numberOfItemTextField.setVisible(true);
					
					comfirmAddItemButton.setBounds(numberOfItemTextField.getBounds().x,numberOfItemTextField.getBounds().y+numberOfItemTextField.getBounds().height+10,100,30);
					comfirmAddItemButton.setVisible(true);
				}
				else if(itemTypeComboBox.getSelectedItem().equals("Karta graficzna")){
					graphicCardPCIBusLabel.setBounds(modelLabel.getBounds().x,modelLabel.getBounds().y+modelLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					graphicCardPCIBusLabel.setVisible(true);
					graphicCardPCIBusComboBox.setBounds(graphicCardPCIBusLabel.getBounds().x+graphicCardPCIBusLabel.getBounds().width,graphicCardPCIBusLabel.getBounds().y,formFieldWidth,formFieldHeight);
					graphicCardPCIBusComboBox.setVisible(true);
					graphicCardRAMLabel.setBounds(graphicCardPCIBusLabel.getBounds().x,graphicCardPCIBusLabel.getBounds().y+graphicCardPCIBusLabel.getBounds().height+10,formFieldWidth,formFieldHeight);
					graphicCardRAMLabel.setVisible(true);
					graphicCardRAMMemoryComboBox.setBounds(graphicCardRAMLabel.getBounds().x+graphicCardRAMLabel.getBounds().width,graphicCardRAMLabel.getBounds().y,formFieldWidth,formFieldHeight);
					graphicCardRAMMemoryComboBox.setVisible(true);
					priceLabel.setBounds(graphicCardRAMLabel.getBounds().x,graphicCardRAMLabel.getBounds().y+graphicCardRAMLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					priceLabel.setVisible(true);
					priceTextField.setBounds(priceLabel.getBounds().x+priceLabel.getBounds().width,priceLabel.getBounds().y,formFieldWidth,formFieldHeight);
					priceTextField.setVisible(true);
					descriptionLabel.setBounds(priceLabel.getBounds().x, priceLabel.getBounds().y+priceLabel.getBounds().height+10, formFieldWidth, formFieldHeight);
					descriptionLabel.setVisible(true);
					itemDescriptionTextArea.setBounds(descriptionLabel.getBounds().x+descriptionLabel.getBounds().width,descriptionLabel.getBounds().y,formFieldWidth,formFieldHeight*2);
					itemDescriptionTextArea.setVisible(true);
					numberOfItemLabel.setBounds(descriptionLabel.getBounds().x,descriptionLabel.getBounds().y+itemDescriptionTextArea.getBounds().height+10,formFieldWidth,formFieldHeight);
					numberOfItemLabel.setVisible(true);
					numberOfItemTextField.setBounds(itemDescriptionTextArea.getBounds().x,itemDescriptionTextArea.getBounds().y+itemDescriptionTextArea.getBounds().height+20,100,30);
					numberOfItemTextField.setVisible(true);
					
					comfirmAddItemButton.setBounds(numberOfItemTextField.getBounds().x,numberOfItemTextField.getBounds().y+numberOfItemTextField.getBounds().height+10,100,30);
					comfirmAddItemButton.setVisible(true);
				}
				
			}
		});
		
		comfirmAddItemButton.addActionListener(new ActionListener() {
			Boolean ok = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(itemTypeComboBox.getSelectedItem().equals("Procesor")){
					String producer = manufacturerTextField.getText();
					String model = modelTextField.getText();
					String socket = processorSocketComboBox.getSelectedItem().toString();
					int numberCores = (int)processorNumberOfCoresComboBox.getSelectedItem();
					int coreTiming = Integer.parseInt(processorCoreTimingTextField.getText());
					int price = Integer.parseInt(priceTextField.getText());
					String desc = itemDescriptionTextArea.getText();
					int number = Integer.parseInt(numberOfItemTextField.getText());
					ok=false;
					
					Processor tmp = new Processor(desc, producer,model,price,socket,coreTiming,numberCores,number);
					for(int i=0;i<shop.getItemCounter();i++){
						if(shop.getItems()[i].equals(tmp)){
							shop.getItems()[i].incrementNumberOfItems(number);
							ok=true;
						}
					}
					
					if(ok==false && shop.addItem(tmp)){
						System.out.println("Dodano procesor");
					}
					
					itemTypeLabel.setVisible(false);
					itemTypeComboBox.setSelectedIndex(0);
					itemTypeComboBox.setVisible(false);
					producerLabel.setVisible(false);
					manufacturerTextField.setText("");
					manufacturerTextField.setVisible(false);
					modelLabel.setVisible(false);
					modelTextField.setText("");
					modelTextField.setVisible(false);
					processorSocketLabel.setVisible(false);
					processorSocketComboBox.setVisible(false);
					processorNumberOfCoresLabel.setVisible(false);
					processorNumberOfCoresComboBox.setVisible(false);
					priceTextField.setText("");
					processorCoreTimingLabel.setVisible(false);
					processorCoreTimingTextField.setText("");
					processorCoreTimingTextField.setVisible(false);
					
					priceLabel.setVisible(false);
					
					priceTextField.setVisible(false);
					descriptionLabel.setVisible(false);
					itemDescriptionTextArea.setText("");
					itemDescriptionTextArea.setVisible(false);
					
					numberOfItemLabel.setVisible(false);
					numberOfItemTextField.setText("");
					numberOfItemTextField.setVisible(false);
					comfirmAddItemButton.setVisible(false);
				}
				else if(itemTypeComboBox.getSelectedItem().equals("Obudowa")){
					String producer = manufacturerTextField.getText();
					String model = modelTextField.getText();
					
					int x = Integer.parseInt(computerCaseDimensionX.getText());
					int y = Integer.parseInt(computerCaseDimensionY.getText());
					int z = Integer.parseInt(computerCaseDimensionZ.getText());
					String color = computerCaseColorTextField.getText();
					int price = Integer.parseInt(priceTextField.getText());
					String desc = itemDescriptionTextArea.getText();
					int number = Integer.parseInt(numberOfItemTextField.getText());
					
					ok=false;
					ComputerCase tmp = new ComputerCase(producer, model, desc, price, new Dimension3D(x,y,z), color, number);
					for(int i=0;i<shop.getItemCounter();i++){
						if(shop.getItems()[i].equals(tmp)){
							shop.getItems()[i].incrementNumberOfItems(number);
							ok=true;
						}
					}
					if(ok==false)
						shop.addItem(tmp);
					
					itemTypeLabel.setVisible(false);
					itemTypeComboBox.setSelectedIndex(0);
					itemTypeComboBox.setVisible(false);
					producerLabel.setVisible(false);
					manufacturerTextField.setText("");
					manufacturerTextField.setVisible(false);
					modelLabel.setVisible(false);
					modelTextField.setText("");
					modelTextField.setVisible(false);
					
					computerCaseDimensionLabel.setVisible(false);
					computerCaseDimensionX.setVisible(false);
					computerCaseDimensionX.setText("");
					computerCaseDimensionY.setVisible(false);
					computerCaseDimensionY.setText("");
					computerCaseDimensionZ.setVisible(false);
					computerCaseDimensionZ.setText("");
					computerCaseXLabel.setVisible(false);
					computerCaseYLabel.setVisible(false);
					computerCaseZLabel.setVisible(false);
					colorLabel.setVisible(false);
					computerCaseColorTextField.setVisible(false);
					computerCaseColorTextField.setText("");
					
					priceLabel.setVisible(false);
					
					priceTextField.setVisible(false);
					descriptionLabel.setVisible(false);
					itemDescriptionTextArea.setText("");
					itemDescriptionTextArea.setVisible(false);
					
					numberOfItemLabel.setVisible(false);
					numberOfItemTextField.setText("");
					numberOfItemTextField.setVisible(false);
					comfirmAddItemButton.setVisible(false);
				}
				else if(itemTypeComboBox.getSelectedItem().equals("Zasilacz")){
					String producer = manufacturerTextField.getText();
					String model = modelTextField.getText();
					
					int power = Integer.parseInt(powerSupplyPowerTextField.getText());
					int price = Integer.parseInt(priceTextField.getText());
					String desc = itemDescriptionTextArea.getText();
					int number = Integer.parseInt(numberOfItemTextField.getText());
					
					ok=false;
					PowerSupply tmp = new PowerSupply(producer, model, desc, price, power, number);
					
					for(int i=0;i<shop.getItemCounter();i++){
						if(shop.getItems()[i].equals(tmp)){
							shop.getItems()[i].incrementNumberOfItems(number);
							ok=true;
						}
					}
					if(ok==false)
						shop.addItem(tmp);
					
					itemTypeLabel.setVisible(false);
					itemTypeComboBox.setSelectedIndex(0);
					itemTypeComboBox.setVisible(false);
					producerLabel.setVisible(false);
					manufacturerTextField.setText("");
					manufacturerTextField.setVisible(false);
					modelLabel.setVisible(false);
					modelTextField.setText("");
					modelTextField.setVisible(false);
					powerSupplyLabel.setVisible(false);
					powerSupplyPowerTextField.setVisible(false);
					powerSupplyPowerTextField.setText("");
					
					priceLabel.setVisible(false);
					priceTextField.setText("");
					priceTextField.setVisible(false);
					descriptionLabel.setVisible(false);
					itemDescriptionTextArea.setText("");
					itemDescriptionTextArea.setVisible(false);
					
					numberOfItemLabel.setVisible(false);
					numberOfItemTextField.setText("");
					numberOfItemTextField.setVisible(false);
					comfirmAddItemButton.setVisible(false);
				}
				else if(itemTypeComboBox.getSelectedItem().equals("Płyta główna")){
					String producer = manufacturerTextField.getText();
					String model = modelTextField.getText();
					String socket = moboSocketComboBox.getSelectedItem().toString();
					String typeRam = moboRAMTypeComboBox.getSelectedItem().toString();
					int numberRam = Integer.parseInt(moboNumberOfRAMSlotComboBox.getSelectedItem().toString());
					int numberpci = Integer.parseInt(moboNumberOfPCIeSlotComboBox.getSelectedItem().toString());
					int numberusb = Integer.parseInt(moboNumberOfUSBSlotComboBox.getSelectedItem().toString());
					int price = Integer.parseInt(priceTextField.getText());
					String desc = itemDescriptionTextArea.getText();
					int number = Integer.parseInt(numberOfItemTextField.getText());
					
					ok=false;
					
					MotherBoard tmp = new MotherBoard(producer, model, desc, price, socket, typeRam, numberRam, numberpci, numberusb, number);
					for(int i=0;i<shop.getItemCounter();i++){
						if(shop.getItems()[i].equals(tmp)){
							shop.getItems()[i].incrementNumberOfItems(number);
							ok=true;
						}
					}
					if(ok==false)
						shop.addItem(tmp);
					
					itemTypeLabel.setVisible(false);
					itemTypeComboBox.setSelectedIndex(0);
					itemTypeComboBox.setVisible(false);
					producerLabel.setVisible(false);
					manufacturerTextField.setText("");
					manufacturerTextField.setVisible(false);
					modelLabel.setVisible(false);
					modelTextField.setText("");
					modelTextField.setVisible(false);
					moboNumberOfPCIeSlotComboBox.setVisible(false);
					moboNumberOfRAMSlotComboBox.setVisible(false);
					moboNumberOfUSBSlotComboBox.setVisible(false);
					moboPCISlotLabel.setVisible(false);
					moboRAMSlotLabel.setVisible(false);
					moboRAMTypeComboBox.setVisible(false);
					moboRAMTypeLabel.setVisible(false);
					moboSocketComboBox.setVisible(false);
					moboSocketLabel.setVisible(false);
					moboUSBSlotLabel.setVisible(false);
					
					
					priceLabel.setVisible(false);
					priceTextField.setText("");
					priceTextField.setVisible(false);
					descriptionLabel.setVisible(false);
					itemDescriptionTextArea.setText("");
					itemDescriptionTextArea.setVisible(false);
					
					numberOfItemLabel.setVisible(false);
					numberOfItemTextField.setText("");
					numberOfItemTextField.setVisible(false);
					comfirmAddItemButton.setVisible(false);
				}
				else if(itemTypeComboBox.getSelectedItem().equals("Dysk")){
					String producer = manufacturerTextField.getText();
					String model = modelTextField.getText();
					
					String type = diskDriveTypeComboBox.getSelectedItem().toString();
					int capacity = Integer.parseInt(diskDriveCapacityTextField.getText().toString());
					int readspeed = Integer.parseInt(diskDriveReadSpeedTextField.getText().toString());
					int writespeed = Integer.parseInt(diskDriveWriteSpeedTextField.getText().toString());
					
					int price = Integer.parseInt(priceTextField.getText());
					String desc = itemDescriptionTextArea.getText();
					int number = Integer.parseInt(numberOfItemTextField.getText());
					
					ok=false;
					DiskDrive tmp = new DiskDrive(producer, model, desc, price, type, capacity, readspeed, writespeed, number);
					
					for(int i=0;i<shop.getItemCounter();i++){
						if(shop.getItems()[i].equals(tmp)){
							shop.getItems()[i].incrementNumberOfItems(number);
							ok=true;
						}
					}
					if(ok==false)
						shop.addItem(tmp);
					
					itemTypeLabel.setVisible(false);
					itemTypeComboBox.setSelectedIndex(0);
					itemTypeComboBox.setVisible(false);
					producerLabel.setVisible(false);
					manufacturerTextField.setText("");
					manufacturerTextField.setVisible(false);
					modelLabel.setVisible(false);
					modelTextField.setText("");
					modelTextField.setVisible(false);
					diskDriveCapacityLabel.setVisible(false);
					diskDriveCapacityTextField.setVisible(false);
					diskDriveReadSpeedLabel.setVisible(false);
					diskDriveReadSpeedTextField.setVisible(false);
					diskDriveTypeComboBox.setVisible(false);
					diskDriveTypeLabel.setVisible(false);
					diskDriveWriteSpeedLabel.setVisible(false);
					diskDriveWriteSpeedTextField.setVisible(false);
					diskDriveCapacityTextField.setText("");
					diskDriveReadSpeedTextField.setText("");
					diskDriveReadSpeedTextField.setText("");
					diskDriveWriteSpeedTextField.setText("");
					
					priceLabel.setVisible(false);
					priceTextField.setText("");
					priceTextField.setVisible(false);
					descriptionLabel.setVisible(false);
					itemDescriptionTextArea.setText("");
					itemDescriptionTextArea.setVisible(false);
					
					numberOfItemLabel.setVisible(false);
					numberOfItemTextField.setText("");
					numberOfItemTextField.setVisible(false);
					comfirmAddItemButton.setVisible(false);
				}
				if(itemTypeComboBox.getSelectedItem().equals("Karta graficzna")){
					String producer = manufacturerTextField.getText();
					String model = modelTextField.getText();
					
					int pci = Integer.parseInt(graphicCardPCIBusComboBox.getSelectedItem().toString());
					int numberRam = Integer.parseInt(graphicCardRAMMemoryComboBox.getSelectedItem().toString());
					int price = Integer.parseInt(priceTextField.getText());
					String desc = itemDescriptionTextArea.getText();
					int number = Integer.parseInt(numberOfItemTextField.getText());
					ok=false;
					
					GraphicCard tmp = new GraphicCard(producer, model, desc, price, numberRam, pci, number);
					for(int i=0;i<shop.getItemCounter();i++){
						if(shop.getItems()[i].equals(tmp)){
							shop.getItems()[i].incrementNumberOfItems(number);
							ok=true;
						}
					}
					
					if(ok==false)
						shop.addItem(tmp);
					
					itemTypeLabel.setVisible(false);
					itemTypeComboBox.setSelectedIndex(0);
					itemTypeComboBox.setVisible(false);
					producerLabel.setVisible(false);
					manufacturerTextField.setText("");
					manufacturerTextField.setVisible(false);
					modelLabel.setVisible(false);
					modelTextField.setText("");
					modelTextField.setVisible(false);
					graphicCardPCIBusComboBox.setVisible(false);
					graphicCardPCIBusLabel.setVisible(false);
					graphicCardRAMLabel.setVisible(false);
					graphicCardRAMMemoryComboBox.setVisible(false);
					
					priceLabel.setVisible(false);
					
					priceTextField.setVisible(false);
					descriptionLabel.setVisible(false);
					itemDescriptionTextArea.setText("");
					itemDescriptionTextArea.setVisible(false);
					
					numberOfItemLabel.setVisible(false);
					numberOfItemTextField.setText("");
					numberOfItemTextField.setVisible(false);
					comfirmAddItemButton.setVisible(false);
				}
				showAllItemButton.doClick();
			}
		});
		
		
		
		add(addItemButton);
		add(searchItemButton);
		add(deleteItemButton);
		add(clearFormButton);
		add(showAllItemButton);
		add(backToShopButton);
		add(itemList);
		add(scrollPanel);
		add(itemTypeComboBox);
		add(itemTypeLabel);
		add(producerLabel);
		add( modelLabel);
		add( priceLabel);;
		add( colorLabel);
		add( diskDriveCapacityLabel);
		add(diskDriveReadSpeedLabel);
		add( diskDriveWriteSpeedLabel);
		add( diskDriveTypeLabel);
		add( graphicCardPCIBusLabel);
		add( graphicCardRAMLabel);;
		add( laptopBatteryCapacityLabel);
		add( laptopDisplaySizeLabel);;
		add( laptopDisplayTypeLabel);
		add( laptopWeightLabel);
		add( operatingSystemLabel);
		add(moboPCISlotLabel);
		 add(moboRAMSlotLabel);
		 add(moboUSBSlotLabel);
		 add( moboRAMTypeLabel);;
		 add( powerSupplyLabel);;
		 add(processorSocketLabel);
		 add( processorNumberOfCoresLabel);
		 add( processorCoreTimingLabel);;
		 add( descriptionLabel);
		add(manufacturerTextField);
		add(modelTextField);
		add(graphicCardPCIBusComboBox);
		add(graphicCardPCIBusLabel);
		add(graphicCardRAMLabel);
		add(graphicCardRAMMemoryComboBox);
		add(diskDriveCapacityTextField);
		add(diskDriveReadSpeedTextField);
		add(diskDriveTypeComboBox);
		add(diskDriveWriteSpeedTextField);
		add(computerCaseColorTextField);
		add(moboNumberOfPCIeSlotComboBox);
		add(moboNumberOfRAMSlotComboBox);
		add(moboNumberOfUSBSlotComboBox);
		add(moboRAMTypeComboBox);
		add(moboSocketComboBox);
		add(laptopBatteryCapacityTextField);
		add(laptopDisplaySizeComboBox);
		add(laptopDisplayTypeComboBox);
		add(laptopWeightTextField);
		add(operatingSystemComboBox);
		add(powerSupplyPowerTextField);
		add(comfirmAddItemButton);
		add(comfirmSearchItemButton);
		add(searchItemTypeComboBox);
		
		add(processorCoreTimingTextField);
		add(processorNumberOfCoresComboBox);
		add(processorSocketComboBox);
		add(itemDescriptionTextArea);
		add(priceTextField);
		add(numberOfItemLabel);
		add(numberOfItemTextField);
		add(computerCaseDimensionLabel);
		add(computerCaseDimensionX);
		add(computerCaseDimensionY);
		add(computerCaseDimensionZ);
		add(computerCaseXLabel);
		add(computerCaseYLabel);
		add(computerCaseZLabel);
		add(moboUSBSlotLabel);
		add(moboSocketLabel);
		
		setVisible(true);
		
	}
}
