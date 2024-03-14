package contacts.stepdefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.runner.RunWith;

import contacts.model.Contact;
import contacts.model.User;
import contacts.pages.AddContactPage;
import contacts.pages.ContactListPage;
import contacts.pages.MainPage;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features="src/test/resources/features")
public class AddNewContactStepDefinitions {
    
	private MainPage mainPage;
	private ContactListPage contactListPage;
	private AddContactPage addContactPage;
	private Contact contact;

    @Given("^I am on the contact list page$")
    public void iAmOnTheContactListPage() {
        contactListPage.navigate();
        
        // TODO move this into precondition
        if (contactListPage.isOpen()) {
        	contactListPage.logout();
        	mainPage.login(new User("davestar@email.com", "1234567"));
        }
    }

    @When("^I click on the \"Add a New Contact\" button$")
    public void iClickOnAddNewContactButton() {
        contactListPage.clickAddNewContactButton();
    }

    @When("^I fill in the following details:$")
    public void iFillInTheFollowingDetails(DataTable dataTable) {
    	assertTrue(addContactPage.isOpen(), addContactPage.title() + " page did not open");
    	
    	Map<String, String> data = dataTable.asMaps(String.class, String.class).get(0);
    	
    	contact = Contact.fromMap(data);
        addContactPage.fillContactDetails(contact);
    }

    @When("^I click on the \"Submit\" button$")
    public void iClickOnSubmitButton() {
    	addContactPage.clickSubmitButton();
    	addContactPage.waitForContactToBeAdded();
    }

    @Then("^I should see added contact in the contact list$")
    public void iShouldSeeAddedContactInTheContactList() {
    	assertTrue(contactListPage.isOpen(), contactListPage.title() + " page did not open");
        assertTrue(contactListPage.containsContact(contact), "Added contact is not in the contact list");
    }
}