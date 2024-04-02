package tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;

import java.util.List;
import java.util.ArrayList;

import org.apache.http.HttpException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.internal.Either;

import data.ContactDataGenerator;
import data.FallbackContactDataGenerator;
import service.UserService;
import service.dto.ContactErrorResponse;
import service.dto.ContactSuccessResponse;
import model.Contact;
import model.User;

/**
 * Positive tests for getting contacts using REST API.
 */
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SerenityJUnit5Extension.class)
public class GetContactsTest {

	private ContactApiActions actions;
	private User user = User.generateTestUser();
	private ContactDataGenerator generator = new FallbackContactDataGenerator();
	private List<Contact> contacts = new ArrayList<Contact>();
	private Either<ContactSuccessResponse, ContactErrorResponse> response;

	{
		for (int i = 0; i < 3; i++) {
			contacts.add(generator.nextRandomContact());
		}
	}

	@BeforeAll
	public void createUser() throws HttpException {
		UserService.add(user);
	}

	@Test
	public void getContacts() throws HttpException {
		actions.iAmRegisteredInTheSystemAsUser(user);
		actions.iHaveContactsInMyContactList(user, contacts);
		response = actions.iSendGetContactsRequest(user);
		actions.verifyThatResponseIsSuccessful(response);
		actions.verifyThatResponseBodyContainsValidData(response, contacts);
		actions.verifyThatResponseContainsExpectedOwner(user, response);
	}

	@AfterAll
	public void deleteUser() throws HttpException {
		UserService.delete(user);
	}
}
