package contacts.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import utils.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.apache.http.HttpException;
import org.junit.runner.RunWith;

import contacts.model.Contact;
import contacts.model.User;
import contacts.pages.AddContactPage;
import contacts.pages.ContactDetailsPage;
import contacts.pages.ContactListPage;
import contacts.pages.EditContactPage;
import contacts.pages.MainPage;
import contacts.service.UserService;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features")
public class ContactManagementStepDefinitions {

	private MainPage mainPage;
	private ContactListPage contactListPage;
	private AddContactPage addContactPage;
	private ContactDetailsPage contactDetailsPage;
	private EditContactPage editContactPage;

	private UserService userService = new UserService();
	private Contact contact;
	private User user = User.generate();

	@DataTableType
	public Contact contactEntry(Map<String, String> entry) {
		return Contact.fromMap(entry);
	}

	@Before
	public void registerUser() throws HttpException {
		userService.addUser(user);
		Logger.logUserInfo(user.toString());
	}

	@Given("^I am on the contact list page$")
	public void iAmOnTheContactListPage() {
		mainPage.navigate().login(user);
	}

	@Given("^my contact list contains one contact with the following details:$")
	public void myContactListContainsOneContactWithTheFollowingDetails(Contact contact) {
		this.contact = contact;

		contactListPage.clickAddNewContactButton();
		addContactPage.contactForm.fillContactDetails(contact);
		addContactPage.clickSubmitButton();
		contactListPage.waitForLoad();
	}

	@When("^I click on the \"Add a New Contact\" button$")
	public void iClickOnAddNewContactButton() {
		contactListPage.clickAddNewContactButton();
	}

	@When("^I fill in the following details:$")
	public void iFillInTheFollowingDetails(Contact contact) {
		this.contact = contact;

		assertTrue(addContactPage.isOpen(), addContactPage.getTitle() + " page did not open");
		addContactPage.contactForm.fillContactDetails(contact);
	}

	@When("^I click on the \"Submit\" button$")
	public void iClickOnSubmitButton() {
		addContactPage.clickSubmitButton();
	}

	@When("^I click on the contact row$")
	public void iClickOnTheContactRow() {
		contactListPage.clickOnContactRow(contact);
	}

	@Then("^I should see added contact in the contact list$")
	public void iShouldSeeAddedContactInTheContactList() {
		contactListPage.waitForLoad();
		assertTrue(contactListPage.isOpen(), contactListPage.getTitle() + " page did not open");
		assertTrue(contactListPage.containsContact(contact), "Added contact is not in the contact list");
	}

	@Then("^I should see updated contact details on the page$")
	public void iShouldSeeUpdatedContactInTheContactList() {
		contactDetailsPage.waitForLoad();
		assertTrue(contactDetailsPage.isOpen(), contactDetailsPage.getTitle() + " page did not open");
		assertEquals(contact, contactDetailsPage.readContactDetails(), "Contact details were not updated correctly");
	}

	@Then("I should see a validation message: {string}")
	public void verifyValidationMessage(String expectedValidationMessage) {
		String actualMessage = addContactPage.readErrorMessage();
		assertEquals(expectedValidationMessage, actualMessage);
	}

	@When("^I click on the \"Edit Contact\" button$")
	public void iClickOnEditContactButton() {
		contactDetailsPage.waitForLoad().clickEditContactButton();
	}

	@When("^I change the contact details to the following:$")
	public void iChangeTheContactDetailsToTheFollowing(Contact contact) {
		this.contact = contact;

		assertTrue(editContactPage.isOpen(), editContactPage.getTitle() + " page did not open");
		editContactPage.contactForm.fillContactDetails(contact);
	}

	@After
	public void deleteUser() {
		try {
			userService.deleteUser(user);
			Logger.logUserInfo("Test user successfully deleted");
		} catch (HttpException e) {
			Logger.logUserInfo(e.getMessage());
		}
	}
}