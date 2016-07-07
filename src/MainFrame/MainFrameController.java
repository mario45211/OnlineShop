package MainFrame;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;

import BuyFrame.*;
import Frame.ItemManagerFrame;
import Frame.UserManagerFrame;
import Shop.Shop;
import Users.Customer;

public class MainFrameController {

	private MainFrameModel model;
	private MainFrameView view;
	
	public MainFrameController(MainFrameModel modell, MainFrameView vieww){
		this.model = modell;
		this.view = vieww;
		
		//metoda nasłuchująca na kliknięcie przycisku do zarządzania użytkownikami
		view.manageUserButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				model.shopMemory.saveToFile(model.shop);
				view.dispose();
				new UserManagerFrame("Zarządzanie użytkownikami",model.filepath);
			}
		});
		
		//metoda nasłuchująca kliknięcia przycisku do zarządzania sklepem
		view.manageItemButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				model.shopMemory.saveToFile(model.shop);
				view.dispose();
				//otwarcie okienka do zarządzania sklepem
				new ItemManagerFrame("Zarządzanie sklepem",model.filepath);
			}
		});
		
		view.showAllItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				model.shopMemory.saveToFile(model.shop);;
				view.dispose();		
				new BuyFrameController(new BuyFrameModel(model.filepath),new BuyFrameView("Kup przedmiot - Komputerowo"));
			}
		});
		
		view.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				model.shopMemory.saveToFile(model.shop);
				view.dispose();
			}
		});
		
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
			view.boughtItem.setVisible(true);
			view.totalCostLabel.setVisible(true);
			
			if(model.shop.getLoggedUser().getPermision()==1){
				view.boughtItem.setVisible(true);
				view.boughtItem.setText("Kupionych przedmiotów: "+model.shop.getLoggedUser().getBoughtItemCounter());
				view.totalCostLabel.setText("Całkowity koszt: "+model.getTotalCost()+" zł");
				view.manageItemButton.setVisible(false);
				view.manageUserButton.setVisible(false);
			}
			else{
				view.manageItemButton.setVisible(true);
				view.manageUserButton.setVisible(true);
			}
		}
		
		view.userMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(view.userMenu.getSelectedItem().equals("logout")){
					model.shop.signOutUser();
					//view.paintComponents();
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
							String username = view.usernameTextField.getText();
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
										view.totalCostLabel.setVisible(true);
										view.totalCostLabel.setText("Całkowity koszt: "+model.getTotalCost()+" zł");
										view.manageItemButton.setVisible(false);
										view.manageUserButton.setVisible(false);
									}
									else{
										view.manageItemButton.setVisible(true);
										view.manageUserButton.setVisible(true);
									}
								}
								
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
							else {
								
								if(model.shop.addUser(new Customer(username, password))){
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
									view.userMenu.setVisible(true);
								
									if(model.shop.getLoggedUser().getPermision()==1){
										view.boughtItem.setVisible(true);
										view.boughtItem.setText("Kupionych przedmiotów: "+model.shop.getLoggedUser().getBoughtItemCounter());
										view.totalCostLabel.setVisible(true);
										view.totalCostLabel.setText("Całkowity koszt: "+model.getTotalCost()+" zł");
										view.manageItemButton.setVisible(false);
										view.manageUserButton.setVisible(false);
									}
									else{
										view.manageItemButton.setVisible(true);
										view.manageUserButton.setVisible(true);
									}
								}
								view.boughtItem.setBounds(view.userLabel.getBounds().x+10, view.userLabel.getY()+view.userLabel.getHeight()+10, 200, 30);
								view.boughtItem.setFont(view.loginFont);
						
							}
						}
					});
					
			}
		});
		if(model.shop.getLoggedUser()==null || model.shop.getLoggedUser().getPermision()==1){
			view.manageItemButton.setVisible(false);
			view.manageUserButton.setVisible(false);
		}
		else{
			view.manageItemButton.setVisible(true);
			view.manageUserButton.setVisible(true);
		}
		
		view.userMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(view.userMenu.getSelectedItem().equals("logout")){
					model.shop.signOutUser();
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
					view.userLabel.setBounds(view.getSize().width-450, 10, view.getSize().width-350,25);
				}
			}
		});
	}
}
