package model;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * This class represents a user of the application.
 */
public class User {

	@JsonIgnore
	private Optional<String> id = Optional.empty();
	private Optional<String> firstName = Optional.empty();
	private Optional<String> lastName = Optional.empty();
	private Optional<String> email = Optional.empty();
	private Optional<String> password = Optional.empty();
	@JsonIgnore
	private Optional<String> token = Optional.empty();

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
		this.firstName = Optional.of(firstName);
		this.lastName = Optional.of(lastName);
		this.email = Optional.of(email);
		this.password = Optional.of(password);
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
	public User(Optional<String> firstName, Optional<String> lastName, Optional<String> email,
			Optional<String> password) {
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
		return firstName.orElse("");
	}

	/**
	 * Sets the first name of the user.
	 *
	 * @param firstName The first name of the user.
	 */
	public void setFirstName(String firstName) {
		this.firstName = Optional.of(firstName);
	}

	/**
	 * Gets the last name of the user.
	 *
	 * @return The last name of the user.
	 */
	public String getLastName() {
		return lastName.orElse("");
	}

	/**
	 * Sets the last name of the user.
	 *
	 * @param lastName The last name of the user.
	 */
	public void setLastName(String lastName) {
		this.lastName = Optional.of(lastName);
	}

	/**
	 * Gets the email of the user.
	 *
	 * @return The email of the user.
	 */
	public String getEmail() {
		return email.orElse("");
	}

	/**
	 * Sets the email of the user.
	 *
	 * @param email The email of the user.
	 */
	public void setEmail(String email) {
		this.email = Optional.of(email);
	}

	/**
	 * Gets the password of the user.
	 *
	 * @return The password of the user.
	 */
	public String getPassword() {
		return password.orElse("");
	}

	/**
	 * Sets the password of the user.
	 *
	 * @param password The password of the user.
	 */
	public void setPassword(String password) {
		this.password = Optional.of(password);
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
		this.token = Optional.ofNullable(token);
	}

	/**
	 * Gets the id of the user for the rest API usage.
	 *
	 * @return User id.
	 */
	public String getId() {
		return id.orElse("");
	}

	/**
	 * Sets the id of the user.
	 *
	 * @param token User id.
	 */
	public void setId(String id) {
		this.id = Optional.ofNullable(id);
	}

	/**
	 * Creates a user object from the given map of data.
	 *
	 * @param data The map containing user details.
	 * @return A new user object created from the provided data.
	 */
	public static User fromMap(Map<String, String> data) {
		Optional<String> firstName = Optional.ofNullable(data.get("First Name"));
		Optional<String> lastName = Optional.ofNullable(data.get("Last Name"));
		Optional<String> email = Optional.ofNullable(data.get("Email").toLowerCase());
		Optional<String> password = Optional.ofNullable(data.get("Password"));

		return new User(firstName, lastName, email, password);
	}

	/**
	 * Returns a string representation of the user.
	 *
	 * @return A string representation of the user.
	 */
	@Override
	public String toString() {
		String userData = "email: '" + getEmail() + "\n" + "password: " + getPassword();
		if (token.isPresent()) {
			userData += "\n" + "token: " + token.get();
		}
		return userData;
	}

	/**
	 * Generates a new user with a random email address and password. The first name
	 * and last name are set to default values: "Test" and "User" respectively.
	 *
	 * @return A new User object with random email address and password.
	 */
	public static User generateTestUser() {
		String firstName = "Test";
		String lastName = "User";
		String randomEmail = "user" + UUID.randomUUID().toString() + "@example.com";
		String randomPassword = UUID.randomUUID().toString().substring(0, 8);
		return new User(firstName, lastName, randomEmail, randomPassword);
	}
}
