package tests.stepdefinitions;

import org.apache.http.HttpException;
import org.junit.runner.RunWith;

import service.UserService;
import ui.pages.AddUserPage;
import utils.Logger;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import model.User;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features")
public class UserSignupStepDefinitions {

	private AddUserPage addUserPage;
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
			UserService.login(user);
			UserService.delete(user);
			Logger.logUserInfo("Test user successfully deleted");
		} catch (HttpException e) {
			Logger.logUserInfo(e.getMessage());
		}
	}
}
