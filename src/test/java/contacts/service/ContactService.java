package contacts.service;

import static io.restassured.RestAssured.basePath;
import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

import org.apache.http.HttpException;
import org.apache.http.HttpStatus;

import contacts.model.Contact;
import contacts.model.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import utils.AppUrls;

/**
 * Service class for interacting with contact-related endpoints of the contact
 * list application.
 */
public class ContactService {

	/**
	 * Adds a new contact for a specified user.
	 * 
	 * @param user    The user object for which a new contact to be added
	 * @param contact The contact object containing the details of the contact to be
	 *                added.
	 * @throws HttpException If an error occurs while adding the contact.
	 */
	public void addContact(User user, Contact contact) throws HttpException {

		// Set base URI and path for the user creation endpoint
		baseURI = AppUrls.BASE_URL;
		basePath = AppUrls.ADD_CONTACT_SERVICE_PATH;

		// Send a POST request to create the user
		Response response = given().contentType("application/json")
				.header("Authorization",
						"Bearer " + user.getToken().orElseThrow(
								() -> new HttpException("User must have a token for the contact to be added")))
				.body(contact).post();
				
		int statusCode = response.statusCode();
		
		ResponseBody<?> body = response.body();

		// Check if user creation was successful
		if (statusCode != HttpStatus.SC_CREATED) {
			throw new HttpException(
					"Could not create contact. Status code: " + statusCode + ". Response body: " + body.asPrettyString());
		}
	}
}
