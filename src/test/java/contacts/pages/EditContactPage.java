package contacts.pages;

import org.openqa.selenium.support.FindBy;

import contacts.webelements.ContactForm;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

@DefaultUrl(BasePage.BASE_URL + "/editContact")
public class EditContactPage extends BasePage<EditContactPage> {

	/**
	 * The title of the page.
	 */
	public static final String PAGE_TITLE = "";

	@FindBy(id = "edit-contact")
	public ContactForm contactForm;

	@FindBy(id = "submit")
	WebElementFacade submitButton;

	@FindBy(id = "error")
	WebElementFacade errorMessage;

	@Override
	protected EditContactPage self() {
		return this;
	}

	@Override
	protected String pageTitle() {
		return PAGE_TITLE;
	}

	@Override
	public Boolean isOpen() {
		return getDriver().getCurrentUrl().endsWith("/editContact");
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

}
