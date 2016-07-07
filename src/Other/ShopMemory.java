package Other;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import Shop.Shop;

public class ShopMemory {
	private File file;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	
	public ShopMemory(String filepath){
		file = new File(filepath);
		try{
			oos = new ObjectOutputStream(new FileOutputStream(file,true));
			ois = new ObjectInputStream(new FileInputStream(file));
		}catch(Exception e){
			System.out.println("Init file streams error");
		}
	}
	
	public void saveToFile(Object obj){
		try {
			oos = new ObjectOutputStream(new FileOutputStream(this.file));
			oos.writeObject(obj);
			System.out.println("Save done!");
		}catch(IOException e){
			System.out.println("Write file error");
		}
	}

	public Object loadFromFile(){
		Object obj = null;
		try {
			obj = ois.readObject();
			System.out.println("Load done!");
		}catch(Exception e){
			System.out.println("Load file error");
		}
		return obj;
	}
}
