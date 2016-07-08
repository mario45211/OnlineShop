package Frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
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
import javax.swing.CellRendererPane;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import MainFrame.MainFrameController;
import MainFrame.MainFrameModel;
import MainFrame.MainFrameView;
import Other.ShopMemory;
import Shop.Shop;
import Users.Admin;
import Users.Customer;
import Users.User;

/*klasa tworząca okienko do zarządzania użytkownikami 
 * widoczne tylko dla zalogowanych administratorów
 */
public class UserManagerFrame extends MyFrame{

	private Shop shop;
	
	private JComboBox userTypeComboBox = new JComboBox<String>();
	private JButton AddButton = new JButton("Dodaj");
	private JButton showAllUsersButton = new JButton("Wyświetl wszystkich");
	private JButton searchUserButton = new JButton("Wyszukaj");
	private JButton clearFormButton = new JButton("Wyczyść");
	private JTextField usernameTextField = new JTextField();
	private JTextField passwordTextField = new JTextField();
	private Font loginFont = new Font(this.getName(),Font.BOLD,12);
	private Font featureFont = new Font(this.getName(),Font.BOLD, 14);
	private JList userList = new JList();
	private JScrollPane scrollPanel = new JScrollPane();
	private JButton deleteUserButton = new JButton("Usuń");
	private User selectedUser;
	private JLabel selectedUserLabel = new JLabel();
	private JButton comfirmAddButton = new JButton("Dodaj");
	private JButton comfirmSearchButton = new JButton("Szukaj");
	private JButton backToShopButton = new JButton("<-- Powrót do sklepu");
	private String filepath;
	private ShopMemory shopMemory;
	
	public UserManagerFrame(String title, String filepath){
		super(title);
		this.filepath = filepath;
		this.shop=new Shop(100,10,filepath);
		
		shopMemory = new ShopMemory(filepath);
		
		shop = (Shop)shopMemory.loadFromFile();
		
		
		this.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e){
				shopMemory.saveToFile(shop);
				dispose();
				MainFrameModel model = new MainFrameModel("src/file.dat");
				MainFrameView view = new MainFrameView("Komputerowo");
				new MainFrameController(model,view);
			}
		});

		userTypeComboBox.addItem("Zwykły użytkownik");
		userTypeComboBox.addItem("Administrator");
		
		paintComponents();
		showAllUsersButton.doClick();
	}
	
	private void paintComponents(){
		this.setLayout(null);
		
		JLabel usernameLabel = new JLabel("Nazwa użytkownika:");
		JLabel passwordLabel = new JLabel("Hasło:");
		
		usernameLabel.setFont(featureFont);
		passwordLabel.setFont(featureFont);
		usernameTextField.setFont(featureFont);
		passwordTextField.setFont(featureFont);
		
		clearFormButton.setFont(featureFont);
		clearFormButton.setBounds(20,40,150,75);
		AddButton.setFont(featureFont);
		AddButton.setBounds(clearFormButton.getBounds().x+clearFormButton.getBounds().width+10,clearFormButton.getBounds().y,130,75);
		showAllUsersButton.setFont(featureFont);
		showAllUsersButton.setBounds(clearFormButton.getBounds().x+clearFormButton.getBounds().width+AddButton.getBounds().width+20,clearFormButton.getBounds().y,200,75);
		searchUserButton.setFont(featureFont);
		searchUserButton.setBounds(clearFormButton.getBounds().x+clearFormButton.getBounds().width+AddButton.getBounds().width+showAllUsersButton.getBounds().width+30,clearFormButton.getBounds().y,155,75);
		deleteUserButton.setFont(featureFont);
		deleteUserButton.setBounds(searchUserButton.getBounds().x+searchUserButton.getBounds().width+10,searchUserButton.getBounds().y,130,75);
		backToShopButton.setFont(featureFont);
		backToShopButton.setBounds(searchUserButton.getBounds().x+20,searchUserButton.getBounds().y+searchUserButton.getBounds().height+30,230,75);
		
		usernameLabel.setBounds(40,clearFormButton.getBounds().y+clearFormButton.getBounds().height+20,200,25);
		usernameTextField.setBounds(usernameLabel.getBounds().width+10,usernameLabel.getBounds().y,200,25);
		passwordLabel.setBounds(usernameLabel.getBounds().x, usernameLabel.getBounds().y+usernameLabel.getBounds().height+10,200,25);
		passwordTextField.setBounds(passwordLabel.getBounds().width+10,passwordLabel.getBounds().y,200,25);
		userTypeComboBox.setFont(loginFont);
		userTypeComboBox.setBounds(passwordTextField.getBounds().x,passwordTextField.getBounds().y+passwordTextField.getBounds().height+10,180,25);
		
		comfirmAddButton.setBounds(userTypeComboBox.getBounds().x,userTypeComboBox.getBounds().y+userTypeComboBox.getBounds().height+10,200,25);
		comfirmSearchButton.setBounds(userTypeComboBox.getBounds().x,userTypeComboBox.getBounds().y+userTypeComboBox.getBounds().height+10,200,25);
		usernameLabel.setVisible(false);
		usernameTextField.setVisible(false);
		passwordLabel.setVisible(false);
		passwordTextField.setVisible(false);
		comfirmAddButton.setVisible(false);
		comfirmSearchButton.setVisible(false);
		userTypeComboBox.setVisible(false);
		
		
		userList.setFont(featureFont);
		scrollPanel.setFont(featureFont);
		
		scrollPanel.setBounds(50,comfirmAddButton.getBounds().y+comfirmAddButton.getBounds().height+30,searchUserButton.getBounds().x,getSize().height-350);
		scrollPanel.setVisible(false);
			
		selectedUserLabel.setFont(featureFont);
		selectedUserLabel.setBounds(scrollPanel.getBounds().x+scrollPanel.getBounds().width+10,scrollPanel.getBounds().y,200,100);
		selectedUserLabel.setVisible(false);
		
		
		clearFormButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				usernameTextField.setText("");
				passwordTextField.setText("");
				usernameTextField.setBorder(new LineBorder(Color.black));
				passwordTextField.setBorder(new LineBorder(Color.black));
				
			}
		});
		
		backToShopButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				shopMemory.saveToFile(shop);
				
				dispose();
				MainFrameModel model = new MainFrameModel("src/file.dat");
				MainFrameView view = new MainFrameView("Komputerowo");
				new MainFrameController(model,view);
			}
		});
		AddButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				usernameLabel.setVisible(true);
				usernameTextField.setVisible(true);
				passwordLabel.setVisible(true);
				passwordTextField.setVisible(true);
				comfirmAddButton.setVisible(true);
				comfirmSearchButton.setVisible(false);
				userTypeComboBox.setVisible(true);
			}
		});
		comfirmAddButton.addActionListener(new ActionListener(){
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
					if(userTypeComboBox.getSelectedItem().equals("Zwykły użytkownik")){
						shop.addUser(new Customer(username,password));
					}
					else{
						shop.addUser(new Admin(username,password));
						for(ActionListener a: showAllUsersButton.getActionListeners()){
							a.actionPerformed(e);
						}
					}
					usernameTextField.setText("");
					passwordTextField.setText("");
					usernameTextField.setBorder(new LineBorder(Color.black));
					passwordTextField.setBorder(new LineBorder(Color.black));
				}
				showAllUsersButton.doClick();
			}
		});
		
		searchUserButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				comfirmAddButton.setVisible(false);
				usernameLabel.setVisible(true);
				usernameTextField.setVisible(true);
				passwordLabel.setVisible(false);
				passwordTextField.setVisible(false);
				userTypeComboBox.setVisible(true);
				comfirmSearchButton.setVisible(true);
			}
		});
		comfirmSearchButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
				String username = usernameTextField.getText();
				String userType = userTypeComboBox.getSelectedItem().toString();
				
			
				List<User> list = new ArrayList<User>();
				for(int i=0;i<shop.getUserCounter();i++){
					int realUserType = userType.equals("Zwykły użytkownik")?1:2;
					if(username.equals("")){
						if(shop.getUsers()[i].getPermision()==realUserType){
							list.add(shop.getUsers()[i]);
						}
					}else{
						if(shop.getUsers()[i].getUsername().equals(username) && shop.getUsers()[i].getPermision()==realUserType){		
							list.add(shop.getUsers()[i]);
						}
					}
				}
				if(list.isEmpty())
					System.out.println("Brak znalezionych uzytkownikow");
	
				userList.setListData(new Vector<User>(list));
				
				userList.setFixedCellHeight(60);
				userList.setCellRenderer(new DefaultListCellRenderer(){
					
				@Override
				public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
					Component renderer  = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
					String pre = "<html><body style='width: 200px;margin: 15px'>";
					((JLabel)renderer).setText(pre + ((User)value).convertToText());
					
					
					return renderer;
					}
				});
	
				scrollPanel.setViewportView(userList);
				scrollPanel.setVisible(true);
				
				usernameTextField.setText("");
				passwordTextField.setText("");
				usernameTextField.setBorder(new LineBorder(Color.black));
				passwordTextField.setBorder(new LineBorder(Color.black));
				repaint();
			}
		});
		
		showAllUsersButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e){
					
					List<User> list = new ArrayList<User>();
					for(int i=0;i<shop.getUserCounter();i++)
						list.add(shop.getUsers()[i]);
					userList.setListData(new Vector<User>(list));
					
					userList.setFixedCellHeight(60);
					userList.setCellRenderer(new DefaultListCellRenderer(){
						
						@Override
						public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
							Component renderer  = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
							String pre = "<html><body style='width: 200px;margin: 15px'>";
							((JLabel)renderer).setText(pre + ((User)value).convertToText());

							return renderer;
						}
					});
	
					scrollPanel.setViewportView(userList);
					scrollPanel.setVisible(true);

					repaint();
			}
		});
		
		userList.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedUser = (User)userList.getSelectedValue();
				String uprawnienia;
				if(selectedUser!=null){
					 uprawnienia = selectedUser.getPermision()==2?"Admin":"Klient";
					// selectedUserLabel.setText("<html><body style='width: 200px;'>Wybrany użytkownik:<br>Login: "+selectedUser.getUsername()+"<br>Uprawnienia: "+uprawnienia+"</body></html>");
					 //selectedUserLabel.setVisible(true);
				}
				
			}
		});
		deleteUserButton.addActionListener(new ActionListener() {
			Boolean ok = false;
			@Override
			public void actionPerformed(ActionEvent e) {
				if(selectedUser!=null){
					shop.deleteUser(selectedUser);
				}
				else{
					comfirmAddButton.setVisible(false);
					searchUserButton.setVisible(true);
					usernameLabel.setVisible(true);
					usernameTextField.setVisible(true);
					userTypeComboBox.setVisible(true);
					
					passwordLabel.setVisible(false);
					passwordTextField.setVisible(false);
					searchUserButton.setVisible(true);
					String username = usernameTextField.getText();
					if(username.equals("")){
						if(username.equals("")&& ok==true){
							usernameTextField.setBorder(new LineBorder(Color.red));
						}
						ok=true;
						System.out.println("Wybierz usera");
					}
					else{
						shop.deleteUser(selectedUser);
						usernameTextField.setText("");
						passwordTextField.setText("");
						usernameTextField.setBorder(new LineBorder(Color.black));
						passwordTextField.setBorder(new LineBorder(Color.black));
						selectedUser=null;
						showAllUsersButton.doClick();
					}
					
				}
				showAllUsersButton.doClick();
			}
		});
	
		add(userTypeComboBox);
		add(usernameLabel);
		add(usernameTextField);
		add(passwordLabel);
		add(passwordTextField);
		add(clearFormButton);
		add(AddButton);
		add(showAllUsersButton);
		add(searchUserButton);
		add(userList);
		add(scrollPanel);
		add(deleteUserButton);
		add(selectedUserLabel);
		add(comfirmAddButton);
		add(comfirmSearchButton);
		add(backToShopButton);
		
		this.setVisible(true);
	}

}