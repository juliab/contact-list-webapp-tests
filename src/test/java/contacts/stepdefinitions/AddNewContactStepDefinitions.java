package contacts.stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.apache.http.HttpException;
import org.junit.runner.RunWith;

import contacts.model.Contact;
import contacts.model.User;
import contacts.pages.AddContactPage;
import contacts.pages.ContactListPage;
import contacts.pages.MainPage;
import contacts.service.UserService;
import helpers.Logger;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features")
public class AddNewContactStepDefinitions {
    
	private MainPage mainPage;
	private ContactListPage contactListPage;
	private AddContactPage addContactPage;
	
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
    	mainPage.navigate()
    		.login(user);
    }

    @When("^I click on the \"Add a New Contact\" button$")
    public void iClickOnAddNewContactButton() {
        contactListPage.clickAddNewContactButton();
    }

    @When("^I fill in the following details:$")
    public void iFillInTheFollowingDetails(Contact contact) {
    	this.contact = contact;
    	
    	assertTrue(addContactPage.isOpen(), addContactPage.title() + " page did not open");
    	addContactPage.fillContactDetails(contact);
    }

    @When("^I click on the \"Submit\" button$")
    public void iClickOnSubmitButton() {
    	addContactPage.clickSubmitButton()
    		.waitForContactToBeAdded();
    }

    @Then("^I should see added contact in the contact list$")
    public void iShouldSeeAddedContactInTheContactList() {
    	assertTrue(contactListPage.isOpen(), contactListPage.title() + " page did not open");
    	assertTrue(contactListPage.containsContact(contact), "Added contact is not in the contact list");
    }
    
    @After
    public void deleteUser() {
    	try {
    		userService.deleteUser(user);
    	} catch (HttpException e) {
    		Logger.logUserInfo(e.getMessage());
		}
    	
    	Logger.logUserInfo("Test user successfully deleted");
    }
}