package tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;

import java.util.List;

import org.apache.http.HttpException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.internal.Either;

import data.FallbackContactDataGenerator;
import service.UserService;
import service.dto.ContactErrorResponse;
import service.dto.ContactSuccessResponse;
import model.Contact;
import model.User;

@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SerenityJUnit5Extension.class)
public class DuplicatedContactTest {

	private ContactApiActions actions;
	private User user = User.generateTestUser();
	private Either<ContactSuccessResponse, ContactErrorResponse> response;

	@BeforeAll
	public void createUser() throws HttpException {
		UserService.add(user);
	}

	@Test
	public void addDuplicatedContact() throws HttpException {
		actions.iAmRegisteredInTheSystemAsUser(user);
		Contact contact = new FallbackContactDataGenerator().nextRandomContact();
		actions.iHaveContactsInMyContactList(user, List.of(contact));
		response = actions.iSendPostContactRequest(user, contact);
		actions.verifyThatResponseIsSuccessful(response);
		actions.verifyThatResponseBodyContainsValidData(response, List.of(contact));
		actions.verifyThatResponseContainsExpectedOwner(user, response);
		actions.verifyThatGetContactsReturnsTwoIdenticalContacts(user, List.of(contact, contact));
	}

	@AfterAll
	public void deleteUser() throws HttpException {
		UserService.delete(user);
	}
}
