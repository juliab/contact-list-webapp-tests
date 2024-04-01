package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Contact;
import model.User;
import net.serenitybdd.core.steps.UIInteractions;
import service.ContactService;
import service.dto.ContactErrorResponseDto;
import service.dto.ContactSuccessResponseDto;
import utils.Logger;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.HttpException;
import org.openqa.selenium.internal.Either;

public class ContactApiActions extends UIInteractions {

	private User user;
	private Contact contact;
	private Either<ContactSuccessResponseDto, ContactErrorResponseDto> response;

	@Given("I am registered in the system as a user")
	public void iAmRegisteredInTheSystemAsUser(User user) {
		Logger.logUserInfo(user.toString());
		this.user = user;
	}

	@When("I add new contact into my contact list")
	public void iAddNewContactIntoMyContactList(Contact contact) throws HttpException {
		Logger.log("Contact data", contact.toString());
		this.contact = contact;
		response = ContactService.add(user, contact);
	}

	@Then("I get success in return and response body contains valid data")
	public void iGetSuccessInReturnAndResponseBodyContainsValidData() {
		assertTrue(response.isLeft(), "Expected successful response but got an error");
		assertEquals(contact, response.left().getContact());
		assertEquals(user.getId(), response.left().getContactDto().getOwner());
	}

	@Then("I get error in response")
	public void iGetErrorInResponse(String errorMessage) {
		assertTrue(response.isRight(), "Expected error in response but got a success");
		assertEquals(errorMessage, response.right().getMessage());
	}
}
