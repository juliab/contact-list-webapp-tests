package tests;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.Contact;
import model.User;
import net.serenitybdd.core.steps.UIInteractions;
import service.ContactResponse;
import service.ContactService;
import utils.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.http.HttpException;
import org.apache.http.HttpStatus;

public class ContactApiActions extends UIInteractions {

	private User user;
	private Contact contact;
	private ContactResponse response;

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
		assertEquals(response.getStatusCode(), HttpStatus.SC_CREATED);
		assertEquals(contact, response.getContact());
		assertEquals(user.getId(), response.getContactDto().getOwner());
	}
}
