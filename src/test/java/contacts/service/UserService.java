package contacts.service;

import contacts.model.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;

import org.apache.http.HttpException;
import org.apache.http.HttpStatus;

/**
 * Service class for interacting with user-related endpoints of the contact list application.
 */
public class UserService {
	
	public final static String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";
	
	/**
     * Adds a new user to the system.
     * 
     * @param user The user object containing the details of the user to be added.
     * @throws HttpException If an error occurs while adding the user.
     */
	public void addUser(User user) throws HttpException {
		
		// Set base URI and path for the user creation endpoint
		baseURI = BASE_URL;
		basePath = "/users";
		
		// Send a POST request to create the user
		Response response = given()
				.contentType("application/json")
				.body(user).
			when().post();
		
		int statusCode = response.statusCode();
		ResponseBody<?> body = response.body();
		
		// Check if user creation was successful
		if (statusCode != HttpStatus.SC_CREATED) {
			throw new HttpException("Could not create user. Status code: " + statusCode +  ". Response body: " + body.asPrettyString());
		}
		
		// Extract and set the user token from the response
		String userToken = body.jsonPath().get("token").toString();
		user.setToken(userToken);
	}
	
	/**
     * Deletes a user from the system.
     * 
     * @param user The user object representing the user to be deleted.
     * @throws HttpException If an error occurs while deleting the user.
     */
	public void deleteUser(User user) throws HttpException {
		
		if (user.getToken().isEmpty()) {
			throw new HttpException("User must have a token to be deleted");
		}
		
		// Set base URI and path for the user deletion endpoint
		baseURI = BASE_URL;
		basePath = "/users/me";
		
		// Send a DELETE request to delete the user
		Response response = given()
				.contentType("application/json")
				.header("Authorization", "Bearer " + user.getToken().orElseThrow()).
			when().delete();
		
		int statusCode = response.statusCode();
		ResponseBody<?> body = response.body();
		
		// Check if user deletion was successful
		if (statusCode != HttpStatus.SC_OK) {
			throw new HttpException("Could not delete user. Status code: " + statusCode +  ". Response body: " + body.asPrettyString());
		}
	}
}
