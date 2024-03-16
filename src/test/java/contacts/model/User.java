package contacts.model;

import java.util.UUID;

public class User {
	
	private String firstName = "";
	private String lastName = "";
	private String email;
	private String password;
	private String token;
	
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getToken() {
		return this.token;
	}
	
	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
	
	public static User generate() {
		String firstName = "Test";
		String lastName = "User";
		String randomEmail = "user" + UUID.randomUUID().toString() + "@example.com";
		String randomPassword = UUID.randomUUID().toString().substring(0, 8);
		return new User(firstName, lastName, randomEmail, randomPassword);	
	}
}
