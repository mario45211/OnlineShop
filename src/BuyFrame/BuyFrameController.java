package BuyFrame;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Items.ComputerCase;
import Items.DiskDrive;
import Items.GraphicCard;
import Items.Item;
import Items.Laptop;
import Items.PC;
import Items.PowerSupply;
import Items.Processor;
import MainFrame.MainFrameController;
import MainFrame.MainFrameModel;
import MainFrame.MainFrameView;
import Users.Customer;

public class BuyFrameController {
	private BuyFrameModel model;
	private BuyFrameView view;
	
	public BuyFrameController(BuyFrameModel model, BuyFrameView view){
		this.model = model;
		this.view = view;
		
		paintComponents();
		
		view.backToShopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.shopMemory.saveToFile(model.shop);
				view.dispose();
				MainFrameModel model = new MainFrameModel("src/file.dat");
				MainFrameView view = new MainFrameView("Komputerowo");
				MainFrameController controller = new MainFrameController(model,view);
			}
		});
		
		view.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				model.shopMemory.saveToFile(model.shop);
				view.dispose();

			}
		});
	}
	
	public BuyFrameController(BuyFrameModel model, BuyFrameView view,String searchString){
		this.model = model;
		this.view = view;
		
		model.searchItem = searchString;
		
		paintComponents();
		
		view.backToShopButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.shopMemory.saveToFile(model.shop);
				view.dispose();
				MainFrameModel model = new MainFrameModel("src/file.dat");
				MainFrameView view = new MainFrameView("Komputerowo");
				MainFrameController controller = new MainFrameController(model,view);
			}
		});
		
		view.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				model.shopMemory.saveToFile(model.shop);
				view.dispose();

			}
		});
		
	}
	private void paintComponents(){
	
		
		
		if(model.shop.getLoggedUser()==null){
			
			view.userLabel.setText("Zaloguj się: ");
			view.logInButton.setVisible(true);
			view.signInButton.setVisible(true);
			view.userMenu.setVisible(false);
			view.usernameTextField.setVisible(false);
			view.loginLabel.setVisible(false);
			view.passwordLabel.setVisible(false);
			view.passwordTextField.setVisible(false);
			view.logInButtonComfirm.setVisible(false);
			view.signInButtonComfirm.setVisible(false);
			view.userMenu.setVisible(false);
			view.boughtItem.setVisible(false);
			view.totalCostLabel.setVisible(false);
		}
		else{
			view.userLabel.setText("Witaj, "+model.shop.getLoggedUser().getUsername());
			view.logInButton.setVisible(false);
			view.signInButton.setVisible(false);
			view.loginLabel.setVisible(false);
			view.passwordLabel.setVisible(false);
			view.usernameTextField.setVisible(false);
			view.passwordTextField.setVisible(false);
			view.logInButtonComfirm.setVisible(false);
			view.signInButtonComfirm.setVisible(false);
			view.userLabel.setBounds(view.getSize().width-300, 10, view.getSize().width-350,25);
			view.userMenu.setBounds(view.userLabel.getBounds().x+110,10,130,25);

			if(model.shop.getLoggedUser().getPermision()==1){
				view.boughtItem.setVisible(true);
				view.boughtItem.setText("Kupionych przedmiotów: "+model.shop.getLoggedUser().getBoughtItemCounter());
				view.totalCostLabel.setVisible(true);
				view.totalCostLabel.setText("Całkowity koszt: "+model.getTotalCost()+" zł");
			}
		}
		
		view.userMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(view.userMenu.getSelectedItem().equals("logout")){
					model.shop.signOutUser();
					view.userMenu.setVisible(false);
					view.boughtItem.setVisible(false);
					paintComponents();
				}
				if(view.userMenu.getSelectedItem().equals("Opcje")){
					//
				}
			}
		});
		
		view.refreshButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				List<Item> list = new ArrayList<Item>();
				for(int i=0;i<model.shop.getItemCounter();i++)
					list.add(model.shop.getItems()[i]);
				
				view.itemList.setListData(new Vector<Item>(list));
				
				view.itemList.setFixedCellHeight(160);
				view.itemList.setCellRenderer(new DefaultListCellRenderer(){
					
					@Override
					public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
						Component renderer  = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
						String pre = "<html><body style='width: 200px;margin: 20px'>";
						((JLabel)renderer).setText(pre + ((Item)value).toString());

						return renderer;
					}
				});
				
				view.scrollPanel.setViewportView(view.itemList);

			}
		});
		
		view.searchItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String mySearchItem ;
				if(model.searchItem.equals("")){
					mySearchItem = view.searchItemTextField.getText();
				}
				else{
					mySearchItem = model.searchItem;
				}
				model.searchItem="";
				String itemType = view.itemTypeComboBox.getSelectedItem().toString();
				
				if(mySearchItem.equals("") && itemType.equals("Wybierz typ")){
					view.refreshButton.doClick();
				}
				else {
					List<Item> list = new ArrayList<Item>();
					for(int i=0;i<model.shop.getItemCounter();i++){
						if(itemType.equals("Wybierz typ")){
							if(model.shop.getItems()[i].getManufacturer().equals(mySearchItem)){
								list.add(model.shop.getItems()[i]);
							}
						}
						else if(mySearchItem.equals("")){
							switch(itemType){
							case "Procesor":
								if(model.shop.getItems()[i] instanceof Processor){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Zasilacz":
								if(model.shop.getItems()[i] instanceof PowerSupply){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Obudowa":
								if(model.shop.getItems()[i] instanceof ComputerCase){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Dysk":
								if(model.shop.getItems()[i] instanceof DiskDrive){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Karta graficzna":
								if(model.shop.getItems()[i] instanceof GraphicCard){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Laptop":
								if(model.shop.getItems()[i] instanceof Laptop){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Płyta główna":
								if(model.shop.getItems()[i] instanceof ComputerCase){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "PC":
								if(model.shop.getItems()[i] instanceof PC){
									list.add(model.shop.getItems()[i]);
								}
								break;
							}
						}
						else {
							switch(itemType){
							case "Procesor":
								if(model.shop.getItems()[i] instanceof Processor && model.shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Zasilacz":
								if(model.shop.getItems()[i] instanceof PowerSupply && model.shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Obudowa":
								if(model.shop.getItems()[i] instanceof ComputerCase && model.shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Dysk":
								if(model.shop.getItems()[i] instanceof DiskDrive && model.shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Karta graficzna":
								if(model.shop.getItems()[i] instanceof GraphicCard && model.shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Laptop":
								if(model.shop.getItems()[i] instanceof Laptop && model.shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "Płyta główna":
								if(model.shop.getItems()[i] instanceof ComputerCase && model.shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(model.shop.getItems()[i]);
								}
								break;
							case "PC":
								if(model.shop.getItems()[i] instanceof PC && model.shop.getItems()[i].getManufacturer().equals(mySearchItem)){
									list.add(model.shop.getItems()[i]);
								}
								break;
							}
						}
					}
					
					view.itemList.setListData(new Vector<Item>(list));
					
					view.itemList.setFixedCellHeight(160);
					view.itemList.setCellRenderer(new DefaultListCellRenderer(){
						
						@Override
						public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
							Component renderer  = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
							String pre = "<html><body style='width: 200px;margin: 20px'>";
							((JLabel)renderer).setText(pre + ((Item)value).toString());

							return renderer;
						}
					});
					
					view.scrollPanel.setViewportView(view.itemList);
					
					view.searchItemTextField.setText("");
					view.itemTypeComboBox.setSelectedIndex(0);
				}
			}
		});
		
		view.itemList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				view.buyItemButton.setVisible(true);
				
			}
		});
		
		view.buyItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println(model.shop.getLoggedUser().getBoughtItemCounter());
				view.selectedItem = (Item)view.itemList.getSelectedValue();
				if(model.shop.getLoggedUser()!=null){
					if(view.selectedItem!=null){
						model.shop.buyItem(view.selectedItem);
						view.selectedItem=null;
					}
					
					System.out.println("klik");
					System.out.println(model.shop.getLoggedUser().getBoughtItemCounter());

					view.refreshButton.doClick();
					view.boughtItem.setText("Kupionych przedmiotów: "+model.shop.getLoggedUser().getBoughtItemCounter());
				}
			}
		});
		
		
		
		view.logInButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				view.signInButtonComfirm.setVisible(false);
				view.loginLabel.setVisible(true);
				view.passwordLabel.setVisible(true);
				view.logInButtonComfirm.setVisible(true);
				view.usernameTextField.setVisible(true);
				view.passwordTextField.setVisible(true);
	
				view.logInButtonComfirm.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e){
						String username =view. usernameTextField.getText();
						String password = view.passwordTextField.getText();
						if(username.equals("") || password.equals("")){
							if(username.equals("")){
								view.usernameTextField.setBorder(BorderFactory.createLineBorder(Color.red));
							}
							else{
								view.usernameTextField.setBorder(BorderFactory.createLineBorder(Color.black));
							}
							if(password.equals("")){
								view.passwordTextField.setBorder(BorderFactory.createLineBorder(Color.red));
							}
							else{
								view.passwordTextField.setBorder(BorderFactory.createLineBorder(Color.black));
							}
						}
						else{
							model.shop.logInUser(username, password);
							if(model.shop.getLoggedUser()!=null){
								view.loginLabel.setVisible(false);
								view.passwordLabel.setVisible(false);
								view.logInButtonComfirm.setVisible(false);
								view.usernameTextField.setText("");
								view.usernameTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								view.passwordTextField.setText("");
								view.passwordTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								view.usernameTextField.setVisible(false);
								view.passwordTextField.setVisible(false);
								
								view.userLabel.setText("Witaj, "+model.shop.getLoggedUser().getUsername());
								view.logInButton.setVisible(false);
								view.signInButton.setVisible(false);
								view.loginLabel.setVisible(false);
								view.passwordLabel.setVisible(false);
								view.usernameTextField.setVisible(false);
								view.passwordTextField.setVisible(false);
								view.logInButtonComfirm.setVisible(false);
								view.signInButtonComfirm.setVisible(false);
								
								view.userLabel.setBounds(view.getSize().width-300, 10, view.getSize().width-350,25);
								view.userMenu.setBounds(view.userLabel.getBounds().x+110,10,130,25);
								view.userMenu.setVisible(true);
								
								if(model.shop.getLoggedUser().getPermision()==1){
									view.boughtItem.setVisible(true);
									view.boughtItem.setText("Kupionych przedmiotów: "+model.shop.getLoggedUser().getBoughtItemCounter());
								}
							}
							view.boughtItem.setBounds(view.userLabel.getBounds().x+10, view.userLabel.getY()+view.userLabel.getHeight()+10, 200, 30);
							view.boughtItem.setFont(view.loginFont);
							view.totalCostLabel.setVisible(true);
							view.totalCostLabel.setText("Całkowity koszt: "+model.getTotalCost()+" zł");
						}
					}
				});
			}
		});
		
		
		view.signInButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				view.logInButtonComfirm.setVisible(false);
				view.loginLabel.setVisible(true);
				view.passwordLabel.setVisible(true);
				view.signInButtonComfirm.setVisible(true);
				view.usernameTextField.setVisible(true);
				view.passwordTextField.setVisible(true);
					
				view.signInButtonComfirm.addActionListener(new ActionListener(){
						@Override
						public void actionPerformed(ActionEvent e){
							String username = view.usernameTextField.getText();
							String password =view. passwordTextField.getText();
							if(username.equals("") || password.equals("")){
								if(username.equals("")){
									view.usernameTextField.setBorder(BorderFactory.createLineBorder(Color.red));
								}
								else{
									view.usernameTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								}
								if(password.equals("")){
									view.passwordTextField.setBorder(BorderFactory.createLineBorder(Color.red));
								}
								else{
									view.passwordTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								}
							}
							else if(model.shop.addUser(new Customer(username, password))){
								model.shop.logInUser(username, password);
								
								view.loginLabel.setVisible(false);
								view.passwordLabel.setVisible(false);
								
								view.usernameTextField.setText("");
								view.usernameTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								view.passwordTextField.setText("");
								view.passwordTextField.setBorder(BorderFactory.createLineBorder(Color.black));
								view.usernameTextField.setVisible(false);
								view.passwordTextField.setVisible(false);
								view.signInButtonComfirm.setVisible(false);
								
								view.userLabel.setText("Witaj, "+model.shop.getLoggedUser().getUsername());
								view.logInButton.setVisible(false);
								view.signInButton.setVisible(false);
								view.loginLabel.setVisible(false);
								view.passwordLabel.setVisible(false);
								view.usernameTextField.setVisible(false);
								view.passwordTextField.setVisible(false);
								view.logInButtonComfirm.setVisible(false);
								view.signInButtonComfirm.setVisible(false);
								
								view.userLabel.setBounds(view.getSize().width-300, 10, view.getSize().width-350,25);
								
								view.userMenu.setBounds(view.userLabel.getBounds().x+110,10,130,25);
								
							
								if(model.shop.getLoggedUser().getPermision()==1){
									view.boughtItem.setVisible(true);
									view.boughtItem.setText("Kupionych przedmiotów: "+model.shop.getLoggedUser().getBoughtItemCounter());
									view.totalCostLabel.setVisible(true);
									view.totalCostLabel.setText("Całkowity koszt: "+model.getTotalCost()+" zł");
								}
							}
							view.boughtItem.setBounds(view.userLabel.getBounds().x+10,view. userLabel.getY()+view.userLabel.getHeight()+10, 200, 30);
							view.boughtItem.setFont(view.loginFont);
						}
					});	
				}	
		});
	}
	
}
