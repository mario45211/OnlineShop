package Users;
import java.io.Serializable;

import Items.Item;

public abstract class User implements UserInterface, Serializable{
	private String username, password;
	//tu dodac koszyk
	
	
	public User(String username, String password){
		this.username = username;
		this.password = password;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public abstract Boolean addBoughtItem(Item item);
	public abstract Item[] getBoughtItem();
	public abstract int getBoughtItemCounter();
	
	@Override
	public Boolean equals(String username, String password){
		if(this.username.equals(username)&&this.password.equals(password))
			return true;
		else
			return false;
	}
	
	@Override
	public String toString(){
		return "Login:  "+this.username+"<br>Hasło:  "+this.password;
	}
	
	public static String convertToHtml(String s){
		char[] ch = s.toCharArray();
		for(int i=0;i<s.length();i++){
			if(ch[i]=='\\' && ch[i+1]=='n'){
				String[] tmp = new String[s.length()+2];
				System.arraycopy(s, 0, tmp, 0, i-1);
				tmp[i]="<";
				tmp[i+1]="b";
				tmp[i+2]="r";
				tmp[i+3]=">";
				System.arraycopy(s, i, tmp, i+4, s.length()-i);
				s=String.valueOf(tmp);			
			}
		}
		return s;
	}
	
}
