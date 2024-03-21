/**
 * Page Object class representing the "Add Contact" page in the application.
 * This page allows users to add a new contact by filling in their details.
 */

package contacts.pages;

import contacts.webelements.ContactForm;
import utils.AppUrls;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.support.FindBy;

@DefaultUrl(AppUrls.BASE_URL + AppUrls.ADD_CONTACT_WEB_PATH)
public class AddContactPage extends BasePage<AddContactPage> {

	private static final String PAGE_TITLE = "Add Contact";

	@FindBy(id = "add-contact")
	private ContactForm contactForm;

	@FindBy(id = "submit")
	private WebElementFacade submitButton;

	@FindBy(id = "error")
	private WebElementFacade errorMessage;

	@Override
	protected String getPageTitle() {
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

	/**
	 * Gets contact form widget object.
	 * 
	 * @return Contact form widget object.
	 */
	public ContactForm getContactForm() {
		return contactForm;
	}
}
