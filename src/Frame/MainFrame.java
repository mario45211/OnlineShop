package Frame;

import java.awt.Color;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

import Shop.Shop;
import Users.Admin;
import Users.Customer;
import Users.User;

import java.awt.event.ActionEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class MainFrame extends MyFrame{
	
	private JLabel mainLabel = new JLabel("Komputerowo");
	private Dimension frameDimension = this.getSize();
	private Shop shop;
	
	private JButton findButton = new JButton("Szukaj");
	private JButton manageItemButton = new JButton("Zarządzaj sklepem");
	private JButton manageUserButton = new JButton("Zarządzaj użytkownikami");
	private JButton logInButton = new JButton("Zaloguj się");
	private JButton signInButton = new JButton("Zarejestruj się");
	private JComboBox userMenu;
	private JButton userDetail = new JButton("Opcje użytk.");
	private JButton signOutButton = new JButton("Wyloguj się");
	private JTextField usernameTextField = new JTextField();
	private JTextField passwordTextField = new JTextField();
	private JTextField findTextField = new JTextField();
	private JButton showAllItemButton = new JButton("Wyświetl wszystkie przedmioty");
	private JLabel userLabel = new JLabel("Zaloguj się: ");
	private Font loginFont = new Font(this.getName(),Font.BOLD,12);
	private Font featureFont = new Font(this.getName(),Font.BOLD, 14);
	private int userLabelStringWidth;
	private JLabel loginLabel = new JLabel("Nazwa:");
	private JLabel passwordLabel = new JLabel("Hasło:");
	private JButton signInButtonComfirm = new JButton("Zarejestruj");
	private JButton logInButtonComfirm = new JButton("Zaloguj");
	
	private String filepath;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private JLabel boughtItem = new JLabel();
	
	
	public MainFrame(String title, String filepath){
		super(title);
		this.shop = new Shop(100,10,filepath);
		this.filepath = filepath;
		File file= new File(filepath);
		try{
			oos = new ObjectOutputStream(new FileOutputStream(file,true));
			ois = new ObjectInputStream(new FileInputStream(file));
		}catch(Exception e){
			System.out.println("Init file streams error");
		}
		
		//if(!file.exists())
		//	shop.addUser(new Admin("admin","123"));
		
		loadFromFile();
		this.userMenu = new JComboBox<String>();
		userMenu.addItem("Opcje");
		userMenu.addItem("logout");
		
		//userMenu.setBackground(Color.white);
		manageUserButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				saveToFile();
				dispose();
				new UserManagerFrame("Zarządzanie użytkownikami",filepath);
			}
		});
		
		manageItemButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				saveToFile();
				dispose();
				new ItemManagerFrame("Zarządzanie sklepem",filepath);
			}
		});
		
		showAllItemButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				saveToFile();
				dispose();		
				new BuyFrame("Kup przedmiot - Komputerowo",filepath);
				
			}
		});
		
		paintComponents();
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				saveToFile();
				dispose();
			}
		});
	}
	
	private void paintComponents(){
	
		this.setLayout(null);
		
		userLabel.setFont(loginFont);
		logInButton.setFont(loginFont);
		signInButton.setFont(loginFont);
		
		usernameTextField.setFont(loginFont);
		passwordTextField.setFont(loginFont);
		
		manageUserButton.setFont(featureFont);
		manageItemButton.setFont(featureFont);

		showAllItemButton.setFont(featureFont);

		userLabelStringWidth = userLabel.getFontMetrics(userLabel.getFont()).stringWidth(userLabel.getText());
		
	
		loginLabel.setBounds(MainFrame.this.frameDimension.width-MainFrame.this.userLabelStringWidth-320,25,60,65);
		usernameTextField.setBounds(loginLabel.getBounds().x+loginLabel.getBounds().width+10,45,100,25);
		
		passwordLabel.setBounds(usernameTextField.getBounds().x+MainFrame.this.usernameTextField.getBounds().width+10,45,60,25);
		passwordTextField.setBounds(passwordLabel.getBounds().x+ passwordLabel.getBounds().width+10,45,100,25);
		
		signInButtonComfirm.setBounds(usernameTextField.getBounds().x,80,120,25);
		signInButtonComfirm.setFont(loginFont);
		logInButtonComfirm.setBounds(MainFrame.this.usernameTextField.getBounds().x,80,120,25);
		logInButtonComfirm.setFont(MainFrame.this.loginFont);
		
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
			
			//userMenu.setVisible(true);
			if(shop.getLoggedUser().getPermision()==1){
				boughtItem.setVisible(true);
				boughtItem.setText("Kupionych przedmiotów: "+shop.getLoggedUser().getBoughtItemCounter());
				manageItemButton.setVisible(false);
				manageUserButton.setVisible(false);
			}
			else{
				manageItemButton.setVisible(true);
				manageUserButton.setVisible(true);
			}
		}
		boughtItem.setBounds(userLabel.getBounds().x+10, userLabel.getY()+userLabel.getHeight()+10, 200, 30);
		boughtItem.setFont(loginFont);
		
		userMenu.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				if(userMenu.getSelectedItem().equals("logout")){
					shop.signOutUser();
					paintComponents();
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
									//paintComponents
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
									//userMenu.setVisible(true);
									if(shop.getLoggedUser().getPermision()==1){
										boughtItem.setVisible(true);
										boughtItem.setText("Kupionych przedmiotów: "+shop.getLoggedUser().getBoughtItemCounter());
										manageItemButton.setVisible(false);
										manageUserButton.setVisible(false);
									}
									else{
										manageItemButton.setVisible(true);
										manageUserButton.setVisible(true);
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
							else {
								
								if(shop.addUser(new Customer(username, password))){
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
									userMenu.setVisible(true);
								
									if(shop.getLoggedUser().getPermision()==1){
										boughtItem.setVisible(true);
										boughtItem.setText("Kupionych przedmiotów: "+shop.getLoggedUser().getBoughtItemCounter());
										manageItemButton.setVisible(false);
										manageUserButton.setVisible(false);
									}
									else{
										manageItemButton.setVisible(true);
										manageUserButton.setVisible(true);
									}
								}
								boughtItem.setBounds(userLabel.getBounds().x+10, userLabel.getY()+userLabel.getHeight()+10, 200, 30);
								boughtItem.setFont(loginFont);
						
							}
						}
					});
					
			}
		});
		
		
		mainLabel.setFont(new Font(mainLabel.getName(),Font.PLAIN, 50));
		int stringWidth = mainLabel.getFontMetrics(mainLabel.getFont()).stringWidth(mainLabel.getText());
		mainLabel.setBounds(frameDimension.width/2-stringWidth/2,logInButton.getHeight()+100,stringWidth,60);
		
		findTextField.setBounds(frameDimension.width/2-300,mainLabel.getHeight()+150,frameDimension.width/2+100,40);
		findTextField.setFont(new Font(findTextField.getFont().getName(),Font.PLAIN,20));
		findButton.setBounds(frameDimension.width/2-300+findTextField.getWidth(),mainLabel.getHeight()+150,100,findTextField.getHeight());
		
		showAllItemButton.setBounds(frameDimension.width/12,findTextField.getHeight()+mainLabel.getHeight()+200,300,findTextField.getHeight());
		manageItemButton.setBounds(frameDimension.width*7/12,findTextField.getHeight()+mainLabel.getHeight()+200,260,findTextField.getHeight());
		
		manageUserButton.setBounds(frameDimension.width*7/12,findTextField.getHeight()+mainLabel.getHeight()+manageItemButton.getHeight()+220,260,findTextField.getHeight());
		
		
		if(shop.getLoggedUser()==null || shop.getLoggedUser().getPermision()==1){
			manageItemButton.setVisible(false);
			manageUserButton.setVisible(false);
			//System.out.println(shop.getLoggedUser().getPermision());
		}
		else{
			manageItemButton.setVisible(true);
			manageUserButton.setVisible(true);
		}
		
		
		
		add(userLabel);
		add(loginLabel);
		add(passwordLabel);
		add(usernameTextField);
		add(passwordTextField);
		add(signInButton);
		add(logInButton);
		add(signInButton);
		add(userMenu);
		add(mainLabel);
		add(findTextField);
		add(findButton);
		add(showAllItemButton);
		add(manageItemButton);
		add(manageUserButton);
		add(logInButtonComfirm);
		add(signInButtonComfirm);
		
		repaint();
		this.setVisible(true);
	}
	
	
	private void saveToFile(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(new File(filepath)));
			oos.writeObject(this.shop);
			System.out.println("Save done!");
		}catch(IOException e){
			System.out.println("Write file error");
		}
	}

	private void loadFromFile(){
		try {
			this.shop = (Shop)ois.readObject();
			System.out.println("Load done!");
		}catch(Exception e){
			System.out.println("Load file error");
		}
	}
}
