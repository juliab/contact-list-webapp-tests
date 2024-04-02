package service;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.response.Response;
import model.Contact;
import model.User;
import service.dto.ContactDto;
import service.dto.ContactErrorResponse;
import service.dto.ContactSuccessResponse;
import utils.AppUrls;
import java.util.List;

import static io.restassured.RestAssured.*;

import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.openqa.selenium.internal.Either;

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
	 * Adds a new contact to the user's contact list.
	 * 
	 * @param user    the user to add the contact to
	 * @param contact the contact to add
	 * @return a response object containing either a success response or an error
	 *         response
	 * @throws HttpException if the user does not have a token
	 */
	public static Either<ContactSuccessResponse, ContactErrorResponse> add(User user, Contact contact)
			throws HttpException {

		Response response = given()
				.baseUri(AppUrls.BASE_URL)
				.basePath(AppUrls.ADD_CONTACT_SERVICE_PATH)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header(getAuthHeader(user))
				.body(contact).post();

		Logger.log("Add contact REST response", response.asPrettyString());

		if (response.statusCode() == HttpStatus.SC_CREATED) {
			ContactDto contactDto = response.body().as(ContactDto.class);
			return Either.left(new ContactSuccessResponse(response.statusCode(), contactDto));
		}

		ContactErrorResponse errorResponse = response.body().as(ContactErrorResponse.class);
		return Either.right(errorResponse);
	}

	/**
	 * Retrieves all contacts from the user's contact list.
	 * 
	 * @param user the user to retrieve contacts for
	 * @return a response object containing either a success response or an error
	 *         response
	 * @throws HttpException if the user does not have a token
	 */
	public static Either<ContactSuccessResponse, ContactErrorResponse> getAll(User user) throws HttpException {
		Response response = given()
				.baseUri(AppUrls.BASE_URL)
				.basePath(AppUrls.GET_CONTACTS_SERVICE_PATH)
				.accept(ContentType.JSON)
				.contentType(ContentType.JSON)
				.header(getAuthHeader(user))
				.get();

		Logger.log("Get contacts REST response", response.asPrettyString());

		if (response.statusCode() == HttpStatus.SC_OK) {
			List<ContactDto> contactDtos = response.body().jsonPath().getList(".", ContactDto.class);
			return Either.left(new ContactSuccessResponse(response.statusCode(), contactDtos));
		}

		ContactErrorResponse errorResponse = response.body().as(ContactErrorResponse.class);
		return Either.right(errorResponse);
	}

}