/**
 * Service class for interacting with user-related endpoints of the contact list
 * application.
 */

package contacts.service;

import contacts.model.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import utils.AppUrls;

import static io.restassured.RestAssured.*;

import org.apache.http.HttpException;
import org.apache.http.HttpStatus;

public class UserService {

	/**
	 * Adds a new user to the system.
	 * 
	 * @param user The user object containing the details of the user to be added.
	 * @throws HttpException If an error occurs while adding the user.
	 */
	public static void addUser(User user) throws HttpException {

		// Set base URI and path for the user creation endpoint
		baseURI = AppUrls.BASE_URL;
		basePath = AppUrls.ADD_USER_SERVICE_PATH;

		// Send a POST request to create the user
		Response response = given().contentType("application/json").body(user).when().post();

		int statusCode = response.statusCode();
		ResponseBody<?> body = response.body();

		// Check if user creation was successful
		if (statusCode != HttpStatus.SC_CREATED) {
			throw new HttpException(
					"Could not create user. Status code: " + statusCode + ". Response body: " + body.asPrettyString());
		}

		// Extract and set the user token from the response
		String userToken = body.jsonPath().get("token").toString();
		user.setToken(userToken);
	}

	/**
	 * Logs in the user to the system.
	 * 
	 * @param user The user object containing the details of the user.
	 * @throws HttpException If an error occurs while logging in.
	 */
	public static void loginUser(User user) throws HttpException {

		// Set base URI and path for the user creation endpoint
		baseURI = AppUrls.BASE_URL;
		basePath = AppUrls.USER_LOGIN_SERVICE_PATH;

		// Send a POST request to log user in
		Response response = given().contentType("application/json").body(user).when().post();

		int statusCode = response.statusCode();
		ResponseBody<?> body = response.body();

		// Check if user creation was successful
		if (statusCode != HttpStatus.SC_OK) {
			throw new HttpException(
					"Could not log in. Status code: " + statusCode + ". Response body: " + body.asPrettyString());
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
	public static void deleteUser(User user) throws HttpException {

		// Set base URI and path for the user deletion endpoint
		baseURI = AppUrls.BASE_URL;
		basePath = AppUrls.DELETE_USER_SERVICE_PATH;

		// Send a DELETE request to delete the user
		Response response = given().contentType("application/json")
				.header("Authorization",
						"Bearer " + user.getToken()
								.orElseThrow(() -> new HttpException("User must have a token to be deleted")))
				.when().delete();

		int statusCode = response.statusCode();
		ResponseBody<?> body = response.body();

		// Check if user deletion was successful
		if (statusCode != HttpStatus.SC_OK) {
			throw new HttpException(
					"Could not delete user. Status code: " + statusCode + ". Response body: " + body.asPrettyString());
		}
	}
}
