package tests.stepdefinitions;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.http.HttpException;
import org.junit.runner.RunWith;

import service.UserService;
import ui.pages.ContactListPage;
import ui.pages.MainPage;
import utils.Logger;
import io.cucumber.java.After;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.junit.CucumberOptions;
import model.User;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features")
public class UserAuthenticationStepDefinitions {

	private MainPage mainPage;
	private ContactListPage contactListPage;
	private User user;

	@Given("I am a registered user in the application:")
	public void iAmRegisteredUserInTheApplication(User user) throws HttpException {
		this.user = user;
		UserService.add(user);
	}

	@Given("I am not a registered user in the application:")
	public void iAmNotRegisteredUserInTheApplication(User user) throws HttpException {
		this.user = user;
	}

	@Given("I am signed in to the application")
	public void iAmSignedInToTheApplication() {
		mainPage.navigate().login(user);
	}

	@Given("I am on the main page")
	public void iAmOnTheMainPage() {
		mainPage.navigate();
	}

	@When("I fill in my email and password into the login form")
	public void iFillInMyEmailAndPasswordIntoLoginForm() {
		mainPage.fillLoginForm(user);
	}

	@When("I click on the \"Submit\" button to login")
	public void iClickSubmitButtonToLogin() {
		mainPage.clickSubmitButton();
	}

	@When("I click on the \"Logout\" button")
	public void iClickLogoutButton() {
		contactListPage.clickLogoutButton();
	}

	@Then("I should see a Main Page with the login form")
	public void iShouldSeeMainPageWithTheLoginForm() {
		assertTrue(mainPage.isOpen(), "Main page is not open after logout");
		assertTrue(mainPage.isLoginFormDisplayed(), "Login form is not displayed on the main page");
	}

	@After(value = "@SigninPositive")
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
