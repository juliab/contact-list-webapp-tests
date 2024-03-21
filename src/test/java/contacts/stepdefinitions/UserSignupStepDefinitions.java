package contacts.stepdefinitions;

import org.apache.http.HttpException;
import org.junit.runner.RunWith;

import contacts.model.User;
import contacts.pages.AddUserPage;
import contacts.service.UserService;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import utils.Logger;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/user_signup.feature")
public class UserSignupStepDefinitions {

	private AddUserPage addUserPage;
	private UserService userService = new UserService();
	private User user;

	@Given("I am on the Add User page")
	public void iAmOnTheAddUserPage() {
		addUserPage.navigate();
	}

	@When("I fill in the following user details:")
	public void iFillInTheFollowingUserDetails(User user) {
		this.user = user;

		addUserPage.fillUserData(user);
	}

	@When("I click on the \"Submit\" button to register a user")
	public void iClickOnSubmitButtonToRegisterUser() {
		addUserPage.clickSubmitButton();
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
