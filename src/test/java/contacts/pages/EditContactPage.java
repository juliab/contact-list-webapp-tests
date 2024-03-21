/**
 * This class represents the page object for the "Edit Contact" page.
 * It provides methods to interact with the elements on this page.
 */

package contacts.pages;

import org.openqa.selenium.support.FindBy;

import contacts.webelements.ContactForm;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import utils.AppUrls;

@DefaultUrl(AppUrls.BASE_URL + AppUrls.EDIT_CONTACT_WEB_PATH)
public class EditContactPage extends BasePage<EditContactPage> {

	public static final String PAGE_TITLE = "";

	@FindBy(id = "edit-contact")
	private ContactForm contactForm;

	@FindBy(id = "submit")
	private WebElementFacade submitButton;

	@FindBy(id = "error")
	private WebElementFacade errorMessage;

	@Override
	protected EditContactPage self() {
		return this;
	}

	@Override
	protected String getPageTitle() {
		return PAGE_TITLE;
	}

	@Override
	public Boolean isOpen() {
		return getDriver().getCurrentUrl().endsWith(AppUrls.EDIT_CONTACT_WEB_PATH);
	}

	/**
	 * Clicks the submit button.
	 * 
	 * @return The instance of the page.
	 */
	public EditContactPage clickSubmitButton() {
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
