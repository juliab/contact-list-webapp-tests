package ui.pages;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import ui.webelements.ContactForm;
import utils.AppUrls;

import org.openqa.selenium.support.FindBy;

/**
 * Page Object class representing the "Add Contact" page in the application.
 * This page allows users to add a new contact by filling in their details.
 */
@DefaultUrl(AppUrls.BASE_URL + AppUrls.ADD_CONTACT_WEB_PATH)
public class AddContactPage extends BasePage<AddContactPage> {

	private static final String PAGE_TITLE = "Add Contact";

	@FindBy(id = "add-contact")
	private ContactForm contactForm;

	@FindBy(id = "submit")
	private WebElementFacade submitButton;

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
	 * Gets contact form widget object.
	 * 
	 * @return Contact form widget object.
	 */
	public ContactForm getContactForm() {
		return contactForm;
	}
}
