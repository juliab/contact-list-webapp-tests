/**
 * Page object representing the main page of the contact list application. This
 * page allows users to log in using their credentials.
 */

package contacts.pages;

import org.openqa.selenium.support.FindBy;

import contacts.model.User;
import utils.AppUrls;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

@DefaultUrl(AppUrls.BASE_URL)
public class MainPage extends BasePage<MainPage> {

	/**
	 * The title of the main page.
	 */
	public static final String PAGE_TITLE = "Contact List App";

	@FindBy(id = "email")
	private WebElementFacade emailField;

	@FindBy(id = "password")
	private WebElementFacade passwordField;

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
		emailField.sendKeys(user.getEmail());
		passwordField.sendKeys(user.getPassword());
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
}
