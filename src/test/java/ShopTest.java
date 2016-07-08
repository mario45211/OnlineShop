package test.java;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import Items.Item;
import Items.Processor;
import Shop.Shop;
import Users.*;


public class ShopTest {
	
	private Shop shop;
	private Customer customer;
	private Admin admin;
	private Item item;
	
	@Before
	public void setUp(){
		shop = new Shop(10,10,"no matter");
		admin = new Admin("nazwa","haslo");	
		customer = new Customer("nazwak","haslo");
		item = new Processor("","","",0,"",0,0,1);
	}
	
	@After
	public void tearDown(){ 
		shop=null;
		admin=null;
		customer=null;
		item=null;
	}
	
	/**
	 * Test dodawania użytkownika 
	 * do bazy sklepu
	 * pobieramy ilość userów i porównuje z 2
	 * ponieważ każdy sklep ma wbudowanego użytkownika "admin"
	 */
	@Test
	public final void testAddUser(){
		assertEquals(true,this.shop.addUser(admin));
		assertEquals(2,this.shop.getUserCounter());
	}
	
	/**
	 * Aby dodać przedmiot do sklepu
	 * należy dodać użytkownika (test),
	 * zalogować go (test)
	 * i na końcu sprawdzamy uprawnienia i 
	 * samo dodawanie do bazy sklepu
	 */
	@Test
	public void testAddItem() {
		assertEquals(true, shop.addUser(admin));
		assertEquals(true, shop.logInUser(admin.getUsername(),admin.getPassword()));
		assertEquals(true, shop.addItem(item));
	}

	/**Aby sprzetestować usuwanie,
	 * należy najpierw jakiś przedmiot dodać,
	 * musi być zalogowany user z uprawnieniami administratora
	 * i na końcu możemy sprawdzić usuwanie
	 */
	@Test
	public void testDeleteItem() {
		assertEquals(true, shop.addUser(admin));
		assertEquals(true, shop.logInUser(admin.getUsername(),admin.getPassword()));
		assertEquals(true, shop.addItem(item));
		assertEquals(true, shop.deleteItem(item));
	}

	@Test
	public void testDeleteUser() {
		assertEquals(true,this.shop.addUser(customer));
		assertEquals(true,this.shop.addUser(admin));
		assertEquals(3,this.shop.getUserCounter());
		assertEquals(true, shop.logInUser(admin.getUsername(), admin.getPassword()));
		assertEquals(true, shop.deleteUser(customer));
	}

	@Test
	public void testLogInUser() {
		assertEquals(true,this.shop.addUser(customer));
		assertEquals(2,this.shop.getUserCounter());
		assertEquals(true, this.shop.logInUser(customer.getUsername(),customer.getPassword()));
		assertEquals(customer.toString(),this.shop.getLoggedUser().toString());
	}


	@Test
	public void testBuyItem() {
		assertEquals(true,this.shop.addUser(admin));
		assertEquals(true,this.shop.addUser(customer));
		assertEquals(3,this.shop.getUserCounter());
		assertEquals(true, shop.logInUser(admin.getUsername(), admin.getPassword()));
		assertEquals(true, shop.addItem(item));
		shop.signOutUser();
		assertEquals(true, shop.logInUser(customer.getUsername(), customer.getPassword()));
		assertEquals(true, shop.buyItem(item));
	}
}
