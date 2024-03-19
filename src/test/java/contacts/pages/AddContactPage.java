package contacts.pages;

import contacts.webelements.ContactForm;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.support.FindBy;

/**
 * Page Object class representing the "Add Contact" page in the application.
 * This page allows users to add a new contact by filling in their details.
 */
@DefaultUrl(BasePage.BASE_URL + "/addContact")
public class AddContactPage extends BasePage<AddContactPage> {

	/**
	 * The title of the page.
	 */
	public static final String PAGE_TITLE = "Add Contact";

	@FindBy(id = "add-contact")
	public ContactForm contactForm;

	@FindBy(id = "submit")
	WebElementFacade submitButton;

	@FindBy(id = "error")
	WebElementFacade errorMessage;

	@Override
	protected String pageTitle() {
		return PAGE_TITLE;
	}

	@Override
	protected AddContactPage self() {
		return this;
	}

	/**
	 * Clicks the submit button.
	 * 
	 * @return The instance of the page.
	 */
	public AddContactPage clickSubmitButton() {
		submitButton.click();
		return this;
	}

	/**
	 * Reads error message on the page.
	 * 
	 * @return Error message text.
	 */
	public String readErrorMessage() {
		return errorMessage.getText();
	}
}
