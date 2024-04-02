package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Contact;
import model.User;
import net.serenitybdd.core.steps.UIInteractions;
import service.ContactService;
import service.dto.ContactErrorResponse;
import service.dto.ContactSuccessResponse;
import utils.Logger;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.HttpException;
import org.openqa.selenium.internal.Either;

public class ContactApiActions extends UIInteractions {

	@Given("I am registered in the system as a user")
	public void iAmRegisteredInTheSystemAsUser(User user) {
		Logger.logUserInfo(user);
	}

	@Given("I have contacts in my contact list")
	public void iHaveContactsInMyContactList(User user, List<Contact> contacts) throws HttpException {
		contacts.forEach(Logger::logContactData);
		for (Contact contact : contacts) {
			ContactService.add(user, contact);
		}
	}

	@When("I send POST contact request")
	public Either<ContactSuccessResponse, ContactErrorResponse> iSendPostContactRequest(User user, Contact contact)
			throws HttpException {
		Logger.logContactData(contact);
		return ContactService.add(user, contact);
	}

	@When("I send GET contacts request")
	public Either<ContactSuccessResponse, ContactErrorResponse> iSendGetContactsRequest(User user)
			throws HttpException {
		return ContactService.getAll(user);
	}

	@Then("Verify that response is successful")
	public void verifyThatResponseIsSuccessful(Either<ContactSuccessResponse, ContactErrorResponse> response) {
		assertTrue(response.isLeft(), "Expected successful response but got an error");
	}

	@Then("Verify that response body contains valid data")
	public void verifyThatResponseBodyContainsValidData(Either<ContactSuccessResponse, ContactErrorResponse> response,
			List<Contact> contacts) {
		assertEquals(contacts.size(), response.left().getContacts().size());
		assertTrue(contacts.containsAll(response.left().getContacts()));
		assertTrue(response.left().getContacts().containsAll(contacts));
	}

	@Then("Verify that response contains expected owner")
	public void verifyThatResponseContainsExpectedOwner(User user,
			Either<ContactSuccessResponse, ContactErrorResponse> response) {
		assertEquals(user.getId(), response.left().getContactDto().getOwner());
	}

	@Then("Verify that response is unsuccessful")
	public void verifyThatResponseIsUnsuccessful(Either<ContactSuccessResponse, ContactErrorResponse> response,
			String expectedErrorMessage) {
		assertTrue(response.isRight(), "Expected error in response but got a success");
		assertEquals(expectedErrorMessage, response.right().getMessage());
	}

	@Then("Verify that GET contacts returns two identical contacts")
	public void verifyThatGetContactsReturnsTwoIdenticalContacts(User user, List<Contact> contacts)
			throws HttpException {
		Either<ContactSuccessResponse, ContactErrorResponse> response = ContactService.getAll(user);
		verifyThatResponseIsSuccessful(response);
		verifyThatResponseBodyContainsValidData(response, contacts);
	}
}
