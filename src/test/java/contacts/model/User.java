package contacts.model;

public class User {
	private String email;
	private String password;
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public String email() {
		return email;
	}
	
	public String password() {
		return password;
	}
}
