package contacts.model;

import java.util.Optional;
import java.util.UUID;

/**
 * Represents a user in the application.
 */
public class User {

	private String firstName = "";
	private String lastName = "";
	private String email;
	private String password;
	private Optional<String> token;

	/**
	 * Constructs a new user with the specified email and password.
	 *
	 * @param email    The email address of the user.
	 * @param password The password of the user.
	 */
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	/**
	 * Constructs a new user with the specified first name, last name, email, and
	 * password.
	 *
	 * @param firstName The first name of the user.
	 * @param lastName  The last name of the user.
	 * @param email     The email address of the user.
	 * @param password  The password of the user.
	 */
	public User(String firstName, String lastName, String email, String password) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
	}

	/**
	 * Gets the first name of the user.
	 *
	 * @return The first name of the user.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the first name of the user.
	 *
	 * @param firstName The first name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name of the user.
	 *
	 * @return The last name of the user.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Sets the last name of the user.
	 *
	 * @param lastName The last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Gets the email of the user.
	 *
	 * @return The email of the user.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email of the user.
	 *
	 * @param email The email of the user.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the password of the user.
	 *
	 * @return The password of the user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password The password of the user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the authentication token of the user for the rest API usage.
	 *
	 * @return User authentication token.
	 */
	public Optional<String> getToken() {
		return token;
	}

	/**
	 * Sets the authentication token of the user.
	 *
	 * @param token User authentication token.
	 */
	public void setToken(String token) {
		this.token = Optional.of(token);
	}

	@Override
	public String toString() {
		return "User{" + "email='" + email + '\'' + ", password='" + password + '\'' + ", token='" + token.orElse("") + '\'' + '}';
	}

	/**
	 * Generates a new user with a random email address and password. The first name
	 * and last name are set to default values: "Test" and "User" respectively.
	 *
	 * @return A new User object with random email address and password.
	 */
	public static User generate() {
		String firstName = "Test";
		String lastName = "User";
		String randomEmail = "user" + UUID.randomUUID().toString() + "@example.com";
		String randomPassword = UUID.randomUUID().toString().substring(0, 8);
		return new User(firstName, lastName, randomEmail, randomPassword);
	}
}
