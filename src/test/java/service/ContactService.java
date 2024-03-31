package service;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import model.Contact;
import model.User;
import service.dto.ContactDto;
import utils.AppUrls;

import static io.restassured.RestAssured.*;

import org.apache.http.HttpException;

import utils.Logger;

/**
 * Service class to interact with the contact service.
 */
public class ContactService {

	private static Header getAuthHeader(User user) throws HttpException {
		return new Header("Authorization",
				"Bearer " + user.getToken().orElseThrow(() -> new HttpException("User must have token")));
	}

	/**
	 * Adds a contact to the user's contact list.
	 * 
	 * @param user    the user to add the contact to
	 * @param contact the contact to add
	 * @return the response from the service
	 * @throws HttpException if the user does not have a token
	 */
	public static ContactResponse add(User user, Contact contact) throws HttpException {

		Response response = given()
				.baseUri(AppUrls.BASE_URL)
				.basePath(AppUrls.ADD_CONTACT_SERVICE_PATH)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header(getAuthHeader(user))
				.body(contact).post();

		Logger.log("Rest response body", response.asPrettyString());

		ContactDto contactDto = response.body().as(ContactDto.class);
		return new ContactResponse(response.statusCode(), contactDto);
	}
}