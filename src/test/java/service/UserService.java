package service;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import model.User;
import utils.AppUrls;
import utils.Logger;

import static io.restassured.RestAssured.*;

import org.apache.http.HttpException;
import org.apache.http.HttpStatus;

/**
 * Service class to interact with the User service.
 */
public class UserService {

	private static Header getAuthHeader(User user) throws HttpException {
		return new Header("Authorization",
				"Bearer " + user.getToken().orElseThrow(() -> new HttpException("User must have token")));
	}

	/**
	 * Logs in the user and sets the user token.
	 * 
	 * @param user the user to login.
	 * @throws HttpException if the user login fails.
	 */
	public static void login(User user) throws HttpException {

		Response response = given()
				.baseUri(AppUrls.BASE_URL)
				.basePath(AppUrls.USER_LOGIN_SERVICE_PATH)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(user)
				.post();

		Logger.log("Rest", response.asPrettyString());

		int statusCode = response.statusCode();
		ResponseBody<?> body = response.body();

		// Check if user login was successful
		if (statusCode != HttpStatus.SC_OK) {
			throw new HttpException(
					"Could not login user. Status code: " + statusCode + ". Response body: " + body.asPrettyString());
		}

		// Extract and set the user token from the response
		String userToken = body.jsonPath().get("token").toString();
		user.setToken(userToken);
	}

	/**
	 * Adds a user and sets the user token and id.
	 * 
	 * @param user the user to add.
	 * @return the response from the user creation.
	 * @throws HttpException if the user creation fails.
	 */
	public static Response add(User user) throws HttpException {

		Response response = given()
				.baseUri(AppUrls.BASE_URL)
				.basePath(AppUrls.ADD_USER_SERVICE_PATH)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.body(user)
				.post();

		Logger.log("Add user REST response", response.asPrettyString());

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

		// Extract from the response and set user id
		String userId = body.jsonPath().get("user._id").toString();
		user.setId(userId);

		return response;
	}

	/**
	 * Deletes the user.
	 * 
	 * @param user the user to delete.
	 * @return the response from the user deletion.
	 * @throws HttpException if the user deletion fails.
	 */
	public static Response delete(User user) throws HttpException {
		// Send a DELETE request to delete the user
		Response response = given()
				.baseUri(AppUrls.BASE_URL)
				.basePath(AppUrls.DELETE_USER_SERVICE_PATH)
				.contentType(ContentType.JSON)
				.header(getAuthHeader(user))
				.when()
				.delete();

		int statusCode = response.statusCode();
		ResponseBody<?> body = response.body();

		// Check if user deletion was successful
		if (statusCode != HttpStatus.SC_OK) {
			throw new HttpException(
					"Could not delete user. Status code: " + statusCode + ". Response body: " + body.asPrettyString());
		}

		return response;
	}
}