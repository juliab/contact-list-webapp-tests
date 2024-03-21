package contacts.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import org.apache.http.HttpException;
import org.junit.runner.RunWith;

import contacts.model.User;
import contacts.pages.AddUserPage;
import contacts.pages.ContactListPage;
import contacts.service.UserService;
import io.cucumber.java.After;
import io.cucumber.java.DataTableType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import utils.Logger;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/user_signup.feature")
public class UserSignupStepDefinitions {

	private AddUserPage addUserPage;
	private ContactListPage contactListPage;
	private UserService userService = new UserService();
	private User user;

	@DataTableType
	public User contactEntry(Map<String, String> entry) {
		return User.fromMap(entry);
	}

	@Given("^I am on the Add User page$")
	public void iAmOnTheAddUserPage() {
		addUserPage.navigate();
	}

	@When("^I fill in the following user details:$")
	public void iFillInTheFollowingUserDetails(User user) {
		this.user = user;

		addUserPage.fillUserData(user);
	}

	@When("^I click on the \"Submit\" button to register a user$")
	public void iClickOnSubmitButtonToRegisterUser() {
		addUserPage.clickSubmitButton();
	}

	@Then("^I should see a Contact List page with a Logout button$")
	public void iShouldSeeContactListPageWithLogoutButton() {
		assertTrue(contactListPage.isOpen(), "Contact List page is not open");
		assertTrue(contactListPage.isLogoutButtonVisible(), "Logout button is not on the page");
	}

	@Then("I should see the error message: {string}")
	public void iShouldSeeErrorMessage(String expectedErrorMessage) {
		String actualMessage = addUserPage.readErrorMessage();
		assertEquals(expectedErrorMessage, actualMessage);
	}

	@After(value = "@SignupPositive")
	public void deleteUser() {
		try {
			userService.loginUser(user);
			userService.deleteUser(user);
			Logger.logUserInfo("Test user successfully deleted");
		} catch (HttpException e) {
			Logger.logUserInfo(e.getMessage());
		}
	}
}
