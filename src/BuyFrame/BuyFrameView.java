package BuyFrame;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Frame.MyFrame;
import Items.Item;

/*klasa tworząca widok dla okienka umożliwiająca kupowanie przedmiotu*/
public class BuyFrameView extends MyFrame{
	
	public JLabel mainLabel = new JLabel("Dostępne przedmioty:");
	public JButton buyItemButton = new JButton("Kup przedmiot");
	public JScrollPane scrollPanel = new JScrollPane();
	public JList itemList = new JList();
	public JButton searchItemButton = new JButton("Szukaj");
	public JTextField searchItemTextField = new JTextField();
	public Item selectedItem;
	
	public JButton logInButton = new JButton("Zaloguj się");
	public JButton signInButton = new JButton("Zarejestruj się");
	public JComboBox userMenu;
	public JButton userDetail = new JButton("Opcje użytk.");
	public JButton signOutButton = new JButton("Wyloguj się");
	public JTextField usernameTextField = new JTextField();
	public JTextField passwordTextField = new JTextField();
	public JLabel userLabel = new JLabel("Zaloguj się: ");
	public Font loginFont = new Font(this.getName(),Font.BOLD,12);
	public Font featureFont = new Font(this.getName(),Font.BOLD, 14);
	public JLabel loginLabel = new JLabel("Nazwa:");
	public JLabel passwordLabel = new JLabel("Hasło:");
	public JButton signInButtonComfirm = new JButton("Zarejestruj");
	public JButton logInButtonComfirm = new JButton("Zaloguj");
	public JButton backToShopButton = new JButton("<-- Powrót do sklepu");
	
	public JComboBox itemTypeComboBox = new JComboBox();
	public JButton refreshButton = new JButton();
	public JLabel boughtItem = new JLabel();
	public JLabel totalCostLabel = new JLabel();
	
	
	public BuyFrameView(String title){
		super(title);
		
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
		
		
		boughtItem.setBounds(userLabel.getBounds().x+10, userLabel.getY()+userLabel.getHeight()+10, 200, 30);
		boughtItem.setFont(loginFont);
		
		totalCostLabel.setBounds(boughtItem.getBounds().x,boughtItem.getBounds().y+boughtItem.getBounds().height,200,30);
		totalCostLabel.setFont(loginFont);
		
		logInButton.setBounds(userLabel.getWidth(),10,150,25);
		signInButton.setBounds(userLabel.getWidth()+logInButton.getWidth()+10,10,150,25);
		
		
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
		add(totalCostLabel);
		
		
		setVisible(true);
	}
}
