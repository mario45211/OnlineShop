package Main;

import java.awt.EventQueue;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import Frame.BuyFrame;
import Frame.ItemManagerFrame;
import Frame.MainFrame;
import Frame.UserManagerFrame;
import Items.*;
import Other.Dimension3D;
import Shop.Shop;
import Users.Customer;
import Users.User;

public class Main {

	public static void main(String[] args) {
		
		
		
		EventQueue.invokeLater(new Runnable(){
			@Override
			public void run(){
				new MainFrame("Komputerowo","src/file.dat");
				//new BuyFrame("fff","test.txt","ff");
			}
		});
		
		/*Shop shop = new Shop(10,100,"src/test.txt");
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(shop.getFilepath()));
			shop.addUser(new NormalUser("mario","123"));
			shop.logInUser("mario","123");
			oos.writeObject(shop);
		}
		catch(Exception e){
			System.out.println("ffffffff");
		}
		
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(shop.getFilepath()));
			Shop shop2 = (Shop)ois.readObject();
			
			System.out.println(shop2);
			System.out.println(shop2.getLoggedUser());
			shop2.signOutUser();
			System.out.println(shop2);
		}catch(Exception e){
			System.out.println("aaaaaaa");
		}*/
		
		/*
		class A implements Serializable{
			String s;
			int i;
			
			A(String s, int i){
				this.i=i;
				this.s=s;
			}
			
			public String toString(){
				return s+String.valueOf(i);
			}
		}
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("src/test.txt"));
			oos.writeObject(new Shop(100,10,"s"));
		}
		catch(IOException e){
			System.out.println("gg");
		}
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream("src/test.txt"));
			Shop a = null;
			a = (Shop)ois.readObject();
			System.out.println(a);
		}catch(IOException e){
			System.out.println("hhhh");
		}
		catch(ClassNotFoundException e){}
		*/
	}

}
