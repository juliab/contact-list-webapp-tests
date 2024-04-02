package ui.pages;

import org.openqa.selenium.support.FindBy;

import model.User;
import utils.AppUrls;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import ui.webelements.TextFormField;

/**
 * Page object representing the main page of the contact list application. This
 * page allows users to log in using their credentials.
 */
@DefaultUrl(AppUrls.BASE_URL)
public class MainPage extends BasePage<MainPage> {

	/**
	 * The title of the main page.
	 */
	public static final String PAGE_TITLE = "Contact List App";

	@FindBy(id = "email")
	private TextFormField emailField;

	@FindBy(id = "password")
	private TextFormField passwordField;

	@FindBy(id = "submit")
	private WebElementFacade submitButton;

	/**
	 * Returns the expected title of the main page.
	 * 
	 * @return The title of the main page.
	 */
	@Override
	protected String getPageTitle() {
		return PAGE_TITLE;
	}

	/**
	 * Logs in to the application using the provided user credentials.
	 * 
	 * @param user The user object containing login credentials.
	 * @return The instance of the main page.
	 */
	public MainPage login(User user) {
		fillLoginForm(user);
		clickSubmitButton();
		return this;
	}
	
	/**
	 * Fills the login form with the email and password of the specified user.
	 * 
	 * @param user The user object containing the email and password.
	 * @return The MainPage object representing the main page.
	 */
	public MainPage fillLoginForm(User user) {
		emailField.fill(user.getEmail());;
		passwordField.fill(user.getPassword());
		return this;
	}
	
	/**
	 * Clicks the submit button.
	 * 
	 * @return The instance of the page.
	 */
	public MainPage clickSubmitButton() {
		submitButton.click();
		return this;
	}

	/**
	 * Returns the instance of the current page.
	 * 
	 * @return The instance of the current page.
	 */
	@Override
	protected MainPage self() {
		return this;
	}
	
	/**
	 * Checks if the login form is displayed on the main page.
	 * 
	 * @return True if the login form elements (email field, password field, and submit button) are visible, false otherwise.
	 */
	public Boolean isLoginFormDisplayed() {
		return emailField.isVisible() && passwordField.isVisible() && submitButton.isVisible();
	}
}
