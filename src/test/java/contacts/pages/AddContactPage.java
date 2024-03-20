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
