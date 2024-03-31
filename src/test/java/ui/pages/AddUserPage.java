/**
 * This class represents the page object for the "Add User" page.
 * It provides methods to interact with the elements on this page.
 */

package ui.pages;

import org.openqa.selenium.support.FindBy;

import model.User;
import utils.AppUrls;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import ui.webelements.TextFormField;

@DefaultUrl(AppUrls.BASE_URL + AppUrls.ADD_USER_WEB_PATH)
public class AddUserPage extends BasePage<AddUserPage> {

	public static final String PAGE_TITLE = "Add User";

	@FindBy(id = "firstName")
	private TextFormField firstNameField;

	@FindBy(id = "lastName")
	private TextFormField lastNameField;

	@FindBy(id = "email")
	private TextFormField emailField;

	@FindBy(id = "password")
	private TextFormField passwordField;

	@FindBy(id = "submit")
	private WebElementFacade submitButton;

	@Override
	protected AddUserPage self() {
		return this;
	}

	@Override
	protected String getPageTitle() {
		return PAGE_TITLE;
	}

	/**
	 * Fills the user data into the corresponding form fields.
	 * 
	 * @param user The user object containing the data to be filled.
	 * @return A reference to this page object.
	 */
	public AddUserPage fillUserData(User user) {
		firstNameField.fill(user.getFirstName());
		lastNameField.fill(user.getLastName());
		emailField.fill(user.getEmail());
		passwordField.fill(user.getPassword());
		return this;
	}

	/**
	 * Clicks the submit button on the page.
	 * 
	 * @return A reference to this page object.
	 */
	public AddUserPage clickSubmitButton() {
		submitButton.click();
		return this;
	}
}
