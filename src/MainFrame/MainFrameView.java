package MainFrame;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import Frame.MyFrame;
import Shop.Shop;

public class MainFrameView extends MyFrame{
	//komponenty okna
	private JLabel mainLabel = new JLabel("Komputerowo");
	private Dimension frameDimension = this.getSize();
	
	
	public JButton findButton = new JButton("Szukaj");
	public JButton manageItemButton = new JButton("Zarządzaj sklepem");
	public JButton manageUserButton = new JButton("Zarządzaj użytkownikami");
	public JButton logInButton = new JButton("Zaloguj się");
	public JButton signInButton = new JButton("Zarejestruj się");
	public JComboBox userMenu;
	public JButton userDetail = new JButton("Opcje użytk.");
	public JButton signOutButton = new JButton("Wyloguj się");
	public JTextField usernameTextField = new JTextField();
	public JTextField passwordTextField = new JTextField();
	public JTextField findTextField = new JTextField();
	public JButton showAllItemButton = new JButton("Wyświetl wszystkie przedmioty");
	public JLabel userLabel = new JLabel("Zaloguj się: ");
	public Font loginFont = new Font(this.getName(),Font.BOLD,12);
	public Font featureFont = new Font(this.getName(),Font.BOLD, 14);
	public int userLabelStringWidth;
	public JLabel loginLabel = new JLabel("Nazwa:");
	public JLabel passwordLabel = new JLabel("Hasło:");
	public JButton signInButtonComfirm = new JButton("Zarejestruj");
	public JButton logInButtonComfirm = new JButton("Zaloguj");
	public JLabel boughtItem = new JLabel();
	public JLabel totalCostLabel = new JLabel();
	
	public MainFrameView(String title){
		super(title);
		
		this.userMenu = new JComboBox<String>();
		userMenu.addItem("Opcje");
		userMenu.addItem("logout");
		
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

		loginLabel.setBounds(frameDimension.width-userLabelStringWidth-320,25,60,65);
		usernameTextField.setBounds(loginLabel.getBounds().x+loginLabel.getBounds().width+10,45,100,25);
		passwordLabel.setBounds(usernameTextField.getBounds().x+usernameTextField.getBounds().width+10,45,60,25);
		passwordTextField.setBounds(passwordLabel.getBounds().x+ passwordLabel.getBounds().width+10,45,100,25);
		signInButtonComfirm.setBounds(usernameTextField.getBounds().x,80,120,25);
		signInButtonComfirm.setFont(loginFont);
		logInButtonComfirm.setBounds(usernameTextField.getBounds().x,80,120,25);
		logInButtonComfirm.setFont(loginFont);
		
		userLabel.setBounds(getSize().width-450, 10, getSize().width-350,25);
		
		boughtItem.setBounds(userLabel.getBounds().x+10, userLabel.getY()+userLabel.getHeight()+10, 200, 30);
		boughtItem.setFont(loginFont);
		
		boughtItem.setVisible(false);
		totalCostLabel.setBounds(boughtItem.getBounds().x,boughtItem.getBounds().y+boughtItem.getBounds().height,200,30);
		totalCostLabel.setFont(loginFont);
		
		userMenu.setBounds(userLabel.getBounds().x+170,10,130,25);
		
		mainLabel.setFont(new Font(mainLabel.getName(),Font.PLAIN, 50));
		int stringWidth = mainLabel.getFontMetrics(mainLabel.getFont()).stringWidth(mainLabel.getText());
		mainLabel.setBounds(frameDimension.width/2-stringWidth/2,logInButton.getHeight()+100,stringWidth,60);
		
		findTextField.setBounds(frameDimension.width/2-300,mainLabel.getHeight()+150,frameDimension.width/2+100,40);
		findTextField.setFont(new Font(findTextField.getFont().getName(),Font.PLAIN,20));
		findButton.setBounds(frameDimension.width/2-300+findTextField.getWidth(),mainLabel.getHeight()+150,100,findTextField.getHeight());
		
		showAllItemButton.setBounds(frameDimension.width/12,findTextField.getHeight()+mainLabel.getHeight()+200,300,findTextField.getHeight());
		manageItemButton.setBounds(frameDimension.width*7/12,findTextField.getHeight()+mainLabel.getHeight()+200,260,findTextField.getHeight());
		
		manageUserButton.setBounds(frameDimension.width*7/12,findTextField.getHeight()+mainLabel.getHeight()+manageItemButton.getHeight()+220,260,findTextField.getHeight());
		
		logInButton.setBounds(userLabel.getWidth(),10,150,25);
		signInButton.setBounds(userLabel.getWidth()+logInButton.getWidth()+10,10,150,25);
		
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
		add(boughtItem);
		add(totalCostLabel);
		
		setVisible(true);
	}
}
