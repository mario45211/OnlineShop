package BuyFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Frame.MyFrame;
import Items.Item;
import MainFrame.MainFrameController;
import MainFrame.MainFrameModel;
import MainFrame.MainFrameView;
import Other.Dimension3D;
import Other.ShopMemory;
import Items.*;
import Shop.Shop;
import Users.Customer;

public class BuyFrame extends MyFrame{

	private Shop shop;
	
	private JLabel mainLabel = new JLabel("Dostępne przedmioty:");
	private JButton buyItemButton = new JButton("Kup przedmiot");
	private JScrollPane scrollPanel = new JScrollPane();
	private JList itemList = new JList();
	private JButton searchItemButton = new JButton("Szukaj");
	private JTextField searchItemTextField = new JTextField();
	private Item selectedItem;
	private String searchItem;
	private JButton logInButton = new JButton("Zaloguj się");
	private JButton signInButton = new JButton("Zarejestruj się");
	private JComboBox userMenu;
	private JButton userDetail = new JButton("Opcje użytk.");
	private JButton signOutButton = new JButton("Wyloguj się");
	private JTextField usernameTextField = new JTextField();
	private JTextField passwordTextField = new JTextField();
	private JLabel userLabel = new JLabel("Zaloguj się: ");
	private Font loginFont = new Font(this.getName(),Font.BOLD,12);
	private Font featureFont = new Font(this.getName(),Font.BOLD, 14);
	private JLabel loginLabel = new JLabel("Nazwa:");
	private JLabel passwordLabel = new JLabel("Hasło:");
	private JButton signInButtonComfirm = new JButton("Zarejestruj");
	private JButton logInButtonComfirm = new JButton("Zaloguj");
	private JButton backToShopButton = new JButton("<-- Powrót do sklepu");
	private String filepath;
	private JComboBox itemTypeComboBox = new JComboBox();
	private JButton refreshButton = new JButton();
	private JLabel boughtItem = new JLabel();
	private ShopMemory shopMemory;
	
	public BuyFrame(String title,String filepath){
		super(title);
		this.filepath = filepath;
		this.shop = new Shop(100,10,filepath);
		shopMemory = new ShopMemory(filepath);

		shop = (Shop)shopMemory.loadFromFile();
	
		paintComponents();
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
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				shopMemory.saveToFile(shop);
				dispose();

			}
		});
	}
	
	
	public BuyFrame(String title,String filepath, String searchItemString){
		super(title);
		this.shop = new Shop(100,10,filepath);
		this.filepath = filepath;
		this.searchItem = searchItemString;

		shopMemory = new ShopMemory(filepath);
		
		shop = (Shop)shopMemory.loadFromFile();

		paintComponents();
		
		searchItemButton.doClick();
		
		backToShopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				shopMemory.saveToFile(shop);;
				dispose();
				MainFrameModel model = new MainFrameModel("src/file.dat");
				MainFrameView view = new MainFrameView("Komputerowo");
				MainFrameController controller = new MainFrameController(model,view);
			}
		});
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				shopMemory.saveToFile(shop);;
				dispose();
				
			}
		});
	}
	
	
	private void paintComponents(){

		this.userMenu = new JComboBox<String>();
		userMenu.addItem("Opcje");
		userMenu.addItem("logout");
		
		itemTypeComboBox.addItem("Wybierz typ");
		itemTypeComboBox.addItem("Procesor");
		itemTypeComboBox.addItem("Zasilacz");
		itemTypeComboBox.addItem("Obudowa");
		itemTypeComboBox.addItem("Płyta główna");
		itemTypeComboBox.addItem("Karta graficzna");
		itemTypeComboBox.addItem("Dysk");
		itemTypeComboBox.addItem("Laptop");
		itemTypeComboBox.addItem("PC");
		
		this.setLayout(null);
		
		userLabel.setFont(loginFont);
		logInButton.setFont(loginFont);
		signInButton.setFont(loginFont);
		
		usernameTextField.setFont(loginFont);
		passwordTextField.setFont(loginFont);
		
		loginLabel.setBounds(getSize().width-420,25,60,65);
		usernameTextField.setBounds(loginLabel.getBounds().x+loginLabel.getBounds().width+10,45,100,25);
		
		passwordLabel.setBounds(usernameTextField.getBounds().x+usernameTextField.getBounds().width+10,45,60,25);
		passwordTextField.setBounds(passwordLabel.getBounds().x+ passwordLabel.getBounds().width+10,45,100,25);
		
		signInButtonComfirm.setBounds(usernameTextField.getBounds().x,80,120,25);
		signInButtonComfirm.setFont(loginFont);
		logInButtonComfirm.setBounds(usernameTextField.getBounds().x,80,120,25);
		logInButtonComfirm.setFont(loginFont);
		backToShopButton.setBounds(30, 10, 200, 70);
		searchItemTextField.setBounds(backToShopButton.getBounds().x+backToShopButton.getBounds().width+30,120,400,30);
		searchItemTextField.setFont(featureFont);
		searchItemButton.setBounds(searchItemTextField.getBounds().x+searchItemTextField.getBounds().width, 120, 100, 30);
		searchItemButton.setFont(featureFont);
		itemTypeComboBox.setBounds(searchItemTextField.getBounds().x-160, searchItemTextField.getBounds().y,140,30);
		itemTypeComboBox.setFont(loginFont);
		
		mainLabel.setBounds(backToShopButton.getBounds().x+15,searchItemTextField.getBounds().y+searchItemTextField.getBounds().height+10,200,25);
		scrollPanel.setBounds(backToShopButton.getBounds().x+15,searchItemTextField.getBounds().y+searchItemTextField.getBounds().height+40,500,400);
		
		buyItemButton.setBounds(scrollPanel.getX()+scrollPanel.getWidth()+20,scrollPanel.getY(),200,50);
		buyItemButton.setFont(featureFont);
		buyItemButton.setVisible(false);
		boughtItem.setVisible(false);
		userLabel.setBounds(getSize().width-450, 10, getSize().width-350,25);
		userLabel.setVisible(true);
		
		if(shop.getLoggedUser()==null){
			
			userLabel.setText("Zaloguj się: ");
			logInButton.setVisible(true);
			signInButton.setVisible(true);
			userMenu.setVisible(false);
			usernameTextField.setVisible(false);
			loginLabel.setVisible(false);
			passwordLabel.setVisible(false);
			passwordTextField.setVisible(false);
			logInButtonComfirm.setVisible(false);
			signInButtonComfirm.setVisible(false);
			userMenu.setVisible(false);
		}
		else{
			userLabel.setText("Witaj, "+shop.getLoggedUser().getUsername());
			logInButton.setVisible(false);
			signInButton.setVisible(false);
			loginLabel.setVisible(false);
			passwordLabel.setVisible(false);
			usernameTextField.setVisible(false);
			passwordTextField.setVisible(false);
			logInButtonComfirm.setVisible(false);
			signInButtonComfirm.setVisible(false);
			userLabel.setBounds(getSize().width-300, 10, getSize().width-350,25);
			userMenu.setBounds(userLabel.getBounds().x+110,10,130,25);

			if(shop.getLoggedUser().getPermision()==1){
				boughtItem.setVisible(true);
				boughtItem.setText("Kupionych przedmiotów: "+shop.getLoggedUser().getBoughtItemCounter());
			}
		}
		boughtItem.setBounds(userLabel.getBounds().x+10, userLabel.getY()+userLabel.getHeight()+10, 200, 30);
		boughtItem.setFont(loginFont);

		
		userMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(userMenu.getSelectedItem().equals("logout")){
					shop.signOutUser();
					userMenu.setVisible(false);
					paintComponents();
				}
				if(userMenu.getSelectedItem().equals("Opcje")){
					//
				}
			}
		});
		
		refreshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Item> list = new ArrayList<Item>();
				for(int i=0;i<shop.getItemCounter();i++)
					list.add(shop.getItems()[i]);
				
				itemList.setListData(new Vector<Item>(list));
				
				itemList.setFixedCellHeight(160);
				itemList.setCellRenderer(new DefaultListCellRenderer(){
					
					@Override
					public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
						Component renderer  = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
						String pre = "<html><body style='width: 200px;margin: 20px'>";
						((JLabel)renderer).setText(pre + ((Item)value).toString());

						return renderer;
					}
				});
				
				scrollPanel.setViewportView(itemList);

			}
		});
		
		searchItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String mySearchItem ;
				if(searchItem.equals("")){
					mySearchItem = searchItemTextField.getText();
				}
				else{
					mySearchItem = searchItem;
				}
				searchItem="";
				String itemType = itemTypeComboBox.getSelectedItem().toString();
				
				if(mySearchItem.equals("") && itemType.equals("Wybierz typ")){
					refreshButton.doClick();
				}
				else {
					List<Item> list = new ArrayList<Item>();
					for(int i=0;i<shop.getItemCounter();i++){
						if(itemType.equals("Wybierz typ")){
							if(shop.getItems()[i].getManufacturer().equals(mySearchItem)){
								list.add(shop.getItems()[i]);
							}
						}
						else if(mySearchItem.equals("")){
							switch(itemType){
							case "Procesor":
								if(shop.getItems()[i] instanceof Processor){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Zasilacz":
								if(shop.getItems()[i] instanceof PowerSupply){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Obudowa":
								if(shop.getItems()[i] instanceof ComputerCase){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Dysk":
								if(shop.getItems()[i] instanceof DiskDrive){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Karta graficzna":
								if(shop.getItems()[i] instanceof GraphicCard){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Laptop":
								if(shop.getItems()[i] instanceof Laptop){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Płyta główna":
								if(shop.getItems()[i] instanceof ComputerCase){
									list.add(shop.getItems()[i]);
								}
								break;
							case "PC":
								if(shop.getItems()[i] instanceof PC){
									list.add(shop.getItems()[i]);
								}
								break;
							}
						}
						else {
							switch(itemType){
							case "Procesor":
								if(shop.getItems()[i] instanceof Processor && shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Zasilacz":
								if(shop.getItems()[i] instanceof PowerSupply && shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Obudowa":
								if(shop.getItems()[i] instanceof ComputerCase && shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Dysk":
								if(shop.getItems()[i] instanceof DiskDrive && shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Karta graficzna":
								if(shop.getItems()[i] instanceof GraphicCard && shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Laptop":
								if(shop.getItems()[i] instanceof Laptop && shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(shop.getItems()[i]);
								}
								break;
							case "Płyta główna":
								if(shop.getItems()[i] instanceof ComputerCase && shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(shop.getItems()[i]);
								}
								break;
							case "PC":
								if(shop.getItems()[i] instanceof PC && shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(shop.getItems()[i]);
								}
								break;
							}
						}
					}
					
					itemList.setListData(new Vector<Item>(list));
					
					itemList.setFixedCellHeight(160);
					itemList.setCellRenderer(new DefaultListCellRenderer(){
						
						@Override
						public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
							Component renderer  = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
							String pre = "<html><body style='width: 200px;margin: 20px'>";
							((JLabel)renderer).setText(pre + ((Item)value).toString());

							return renderer;
						}
					});
					
					scrollPanel.setViewportView(itemList);
					
					searchItemTextField.setText("");
					itemTypeComboBox.setSelectedIndex(0);
				}
			}
		});
		
		itemList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				buyItemButton.setVisible(true);
				
			}
		});
		
		buyItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(shop.getLoggedUser().getBoughtItemCounter());
				selectedItem = (Item)itemList.getSelectedValue();
				if(shop.getLoggedUser()!=null){
					if(selectedItem!=null){
						shop.buyItem(selectedItem);
						selectedItem=null;
					}
					
					System.out.println("klik");
					System.out.println(shop.getLoggedUser().getBoughtItemCounter());

					refreshButton.doClick();
					boughtItem.setText("Kupionych przedmiotów: "+shop.getLoggedUser().getBoughtItemCounter());
				}
			}
		});
		
		
		logInButton.setBounds(userLabel.getWidth(),10,150,25);
		logInButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					signInButtonComfirm.setVisible(false);
					loginLabel.setVisible(true);
					passwordLabel.setVisible(true);
					logInButtonComfirm.setVisible(true);
					usernameTextField.setVisible(true);
					passwordTextField.setVisible(true);
	
					logInButtonComfirm.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e){
							String username = usernameTextField.getText();
							String password = passwordTextField.getText();
							if(username.equals("") || password.equals("")){
								if(username.equals("")){
									usernameTextField.setBorder(BorderFactory.createLineBorder(Color.red));
								}
								else{
									usernameTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								}
								if(password.equals("")){
									passwordTextField.setBorder(BorderFactory.createLineBorder(Color.red));
								}
								else{
									passwordTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								}
							}
							else{
								shop.logInUser(username, password);
								if(shop.getLoggedUser()!=null){
									loginLabel.setVisible(false);
									passwordLabel.setVisible(false);
									logInButtonComfirm.setVisible(false);
									usernameTextField.setText("");
									usernameTextField.setBorder(BorderFactory.createLineBorder(Color.black));
									passwordTextField.setText("");
									passwordTextField.setBorder(BorderFactory.createLineBorder(Color.black));
									usernameTextField.setVisible(false);
									passwordTextField.setVisible(false);
									
									userLabel.setText("Witaj, "+shop.getLoggedUser().getUsername());
									logInButton.setVisible(false);
									signInButton.setVisible(false);
									loginLabel.setVisible(false);
									passwordLabel.setVisible(false);
									usernameTextField.setVisible(false);
									passwordTextField.setVisible(false);
									logInButtonComfirm.setVisible(false);
									signInButtonComfirm.setVisible(false);
									
									userLabel.setBounds(getSize().width-300, 10, getSize().width-350,25);
									userMenu.setBounds(userLabel.getBounds().x+110,10,130,25);
									userMenu.setVisible(true);
									
									if(shop.getLoggedUser().getPermision()==1){
										boughtItem.setVisible(true);
										boughtItem.setText("Kupionych przedmiotów: "+shop.getLoggedUser().getBoughtItemCounter());
									}
								}
								boughtItem.setBounds(userLabel.getBounds().x+10, userLabel.getY()+userLabel.getHeight()+10, 200, 30);
								boughtItem.setFont(loginFont);
							}
						}
					});
			}
		});
		
		signInButton.setBounds(userLabel.getWidth()+logInButton.getWidth()+10,10,150,25);
		signInButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
					logInButtonComfirm.setVisible(false);
					loginLabel.setVisible(true);
					passwordLabel.setVisible(true);
					signInButtonComfirm.setVisible(true);
					usernameTextField.setVisible(true);
					passwordTextField.setVisible(true);
					
					signInButtonComfirm.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e){
							String username = usernameTextField.getText();
							String password = passwordTextField.getText();
							if(username.equals("") || password.equals("")){
								if(username.equals("")){
									usernameTextField.setBorder(BorderFactory.createLineBorder(Color.red));
								}
								else{
									usernameTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								}
								if(password.equals("")){
									passwordTextField.setBorder(BorderFactory.createLineBorder(Color.red));
								}
								else{
									passwordTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								}
							}
							else if(shop.addUser(new Customer(username, password))){
								shop.logInUser(username, password);
								
								loginLabel.setVisible(false);
								passwordLabel.setVisible(false);
								
								usernameTextField.setText("");
								usernameTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								passwordTextField.setText("");
								passwordTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								usernameTextField.setVisible(false);
								passwordTextField.setVisible(false);
								signInButtonComfirm.setVisible(false);
								
								userLabel.setText("Witaj, "+shop.getLoggedUser().getUsername());
								logInButton.setVisible(false);
								signInButton.setVisible(false);
								loginLabel.setVisible(false);
								passwordLabel.setVisible(false);
								usernameTextField.setVisible(false);
								passwordTextField.setVisible(false);
								logInButtonComfirm.setVisible(false);
								signInButtonComfirm.setVisible(false);
								
								userLabel.setBounds(getSize().width-300, 10, getSize().width-350,25);
								
								userMenu.setBounds(userLabel.getBounds().x+110,10,130,25);
								
							
								if(shop.getLoggedUser().getPermision()==1){
									boughtItem.setVisible(true);
									boughtItem.setText("Kupionych przedmiotów: "+shop.getLoggedUser().getBoughtItemCounter());
								}
							}
							boughtItem.setBounds(userLabel.getBounds().x+10, userLabel.getY()+userLabel.getHeight()+10, 200, 30);
							boughtItem.setFont(loginFont);
						}
					});	
				}	
		});
		
		add(logInButton);
		add(logInButtonComfirm);
		add(loginLabel);
		add(signInButton);
		add(signInButtonComfirm);
		add(signOutButton);
		add(userDetail);
		add(userLabel);
		add(userMenu);
		add(usernameTextField);
		add(passwordLabel);
		add(passwordTextField);
		add(backToShopButton);
		add(searchItemButton);
		add(searchItemTextField);
		add(mainLabel);
		add(itemList);
		add(scrollPanel);
		add(buyItemButton);
		add(itemTypeComboBox);
		refreshButton.setVisible(false);
		refreshButton.doClick();
		add(boughtItem);
		
		setVisible(true);
	}
	
	
}
