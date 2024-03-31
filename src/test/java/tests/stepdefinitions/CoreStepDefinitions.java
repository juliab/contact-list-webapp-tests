package tests.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.junit.runner.RunWith;

import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Then;
import io.cucumber.junit.CucumberOptions;
import model.User;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import ui.pages.ContactListPage;
import ui.pages.MainPage;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features")
public class CoreStepDefinitions {

	MainPage mainPage;
	ContactListPage contactListPage;

	@DataTableType
	public User contactEntry(Map<String, String> entry) {
		return User.fromMap(entry);
	}

	@Then("I should see the error message: {string}")
	public void iShouldSeeErrorMessage(String expectedValidationMessage) {
		String actualMessage = mainPage.readErrorMessage();
		assertEquals(expectedValidationMessage, actualMessage);
	}

	@Then("I should see a Contact List page with a Logout button")
	public void iShouldSeeContactListPageWithLogoutButton() {
		assertTrue(contactListPage.isOpen(), "Contact List page is not open");
		assertTrue(contactListPage.isLogoutButtonVisible(), "Logout button is not on the page");
	}
}
