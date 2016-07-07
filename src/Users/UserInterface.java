package Users;

public interface UserInterface {
	public String getUsername();
	public void setUsername(String username);
	public void setPassword(String password);
	public String getPassword();
	public int getPermision();
	Boolean equals(String username, String password);
}
