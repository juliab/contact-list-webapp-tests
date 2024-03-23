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

import static org.junit.jupiter.api.Assertions.assertFalse;
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
import contacts.service.ContactService;
import contacts.service.UserService;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features")
public class ContactManagementStepDefinitions {

	private MainPage mainPage;
	private ContactListPage contactListPage;
	private AddContactPage addContactPage;
	private ContactDetailsPage contactDetailsPage;
	private EditContactPage editContactPage;

	private Contact contact;
	private User user = User.generateTestUser();
	
	@DataTableType
	public Contact contactEntry(Map<String, String> entry) {
		return Contact.fromMap(entry);
	}

	@Before(value = "@Contacts")
	public void registerUser() throws HttpException {
		UserService.addUser(user);
		Logger.logUserInfo(user.toString());
	}

	@Given("I am on the contact list page")
	public void iAmOnTheContactListPage() {
		mainPage.navigate().login(user);
	}

	@Given("my contact list contains one contact with the following details:")
	public void myContactListContainsOneContactWithTheFollowingDetails(Contact contact) throws HttpException {
		this.contact = contact;

		ContactService.addContact(user, contact);
	}

	@When("I click on the \"Add a New Contact\" button")
	public void iClickOnAddNewContactButton() {
		contactListPage.clickAddNewContactButton();
	}

	@When("I fill in the following contact details:")
	public void iFillInTheFollowingContactDetails(Contact contact) {
		this.contact = contact;

		assertTrue(addContactPage.isOpen(), addContactPage.getTitle() + " page did not open");
		addContactPage.getContactForm().fillContactDetails(contact);
	}

	@When("I click on the \"Submit\" button to add a contact")
	public void iClickOnSubmitButtonToAddContact() {
		addContactPage.clickSubmitButton();
	}

	@When("I click on the \"Submit\" button to edit a contact")
	public void iClickOnSubmitButtonToEditContact() {
		editContactPage.clickSubmitButton();
	}

	@When("I click on the contact row")
	public void iClickOnTheContactRow() {
		contactListPage.clickOnContactRow(contact);
	}

	@Then("I should see added contact in the contact list")
	public void iShouldSeeAddedContactInTheContactList() {
		contactListPage.waitForLoad();
		assertTrue(contactListPage.isOpen(), contactListPage.getTitle() + " page did not open");
		assertTrue(contactListPage.containsContact(contact), "Added contact is not in the contact list");
	}

	@Then("I should see updated contact details on the page")
	public void iShouldSeeUpdatedContactInTheContactList() {
		contactDetailsPage.waitForLoad();
		assertTrue(contactDetailsPage.isOpen(), contactDetailsPage.getTitle() + " page did not open");
		assertEquals(contact, contactDetailsPage.readContactDetails(), "Contact details were not updated correctly");
	}

	@Then("I should not see the deleted contact in my contact list")
	public void iShouldNotSeeDeletedContactInMyContactList() {
		assertFalse(contactListPage.containsContact(contact), "Deleted contact is still in the contact list");
	}

	@Then("I should return to a Contact List page")
	public void iShouldReturnToContactListPage() {
		contactListPage.waitForLoad();

		assertTrue(contactListPage.isOpen(), contactListPage.getTitle() + " page did not open");
	}

	@When("I click on the \"Edit Contact\" button")
	public void iClickOnEditContactButton() {
		contactDetailsPage.waitForLoad().clickEditContactButton();
	}

	@When("I click on the \"Delete Contact\" button")
	public void iClickOnDeleteContactButton() {
		contactDetailsPage.waitForLoad().clickDeleteContactButton();
	}

	@When("I accept the confirmation dialog")
	public void iAcceptConfirmationDialog() {
		contactDetailsPage.acceptAlert();
	}

	@When("I change the contact details to the following:")
	public void iChangeTheContactDetailsToTheFollowing(Contact contact) {
		this.contact = contact;

		assertTrue(editContactPage.isOpen(), editContactPage.getTitle() + " page did not open");
		editContactPage.getContactForm().fillContactDetails(contact);
	}

	@After(value = "@Contacts")
	public void deleteUser() {
		try {
			UserService.deleteUser(user);
			Logger.logUserInfo("Test user successfully deleted");
		} catch (HttpException e) {
			Logger.logUserInfo(e.getMessage());
		}
	}
}