/**
 * Page Object class representing the "Contact Details" page in the application.
 * This page contains contact details and allows user to edit or delete contact.
 */

package contacts.pages;

import org.openqa.selenium.support.FindBy;

import contacts.model.Contact;
import utils.AppUrls;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

@DefaultUrl(AppUrls.BASE_URL + AppUrls.CONTACT_DETAILS_WEB_PATH)
public class ContactDetailsPage extends BasePage<ContactDetailsPage> {

	public static final String PAGE_TITLE = "";

	@FindBy(id = "edit-contact")
	private WebElementFacade editContactButton;

	@FindBy(id = "delete")
	private WebElementFacade deleteContactButton;

	@FindBy(id = "firstName")
	private WebElementFacade firstNameLabel;

	@FindBy(id = "lastName")
	private WebElementFacade lastNameLabel;

	@FindBy(id = "birthdate")
	private WebElementFacade dateOfBirthLabel;

	@FindBy(id = "email")
	private WebElementFacade emailLabel;

	@FindBy(id = "phone")
	private WebElementFacade phoneLabel;

	@FindBy(id = "street1")
	private WebElementFacade street1Label;

	@FindBy(id = "street2")
	private WebElementFacade street2Label;

	@FindBy(id = "city")
	private WebElementFacade cityLabel;

	@FindBy(id = "stateProvince")
	private WebElementFacade stateOrProvinceLabel;

	@FindBy(id = "postalCode")
	private WebElementFacade postalCodeLabel;

	@FindBy(id = "country")
	private WebElementFacade countryLabel;

	@Override
	protected ContactDetailsPage self() {
		return this;
	}

	@Override
	protected String getPageTitle() {
		return PAGE_TITLE;
	}

	@Override
	public Boolean isOpen() {
		return getDriver().getCurrentUrl().contains(AppUrls.CONTACT_DETAILS_WEB_PATH);
	}

	/**
	 * Clicks on "Edit Contact" button.
	 */
	public void clickEditContactButton() {
		editContactButton.click();
	}

	/**
	 * Clicks on "Delete Contact" button.
	 */
	public void clickDeleteContactButton() {
		deleteContactButton.click();
	}

	/**
	 * Reads contact details from the current page and creates a new Contact
	 * instance based on the retrieved values.
	 * 
	 * @return A Contact instance populated with the read contact details.
	 */
	public Contact readContactDetails() {
		String firstName = firstNameLabel.getText();
		String lastName = lastNameLabel.getText();
		String dateOfBirth = dateOfBirthLabel.getText();
		String email = emailLabel.getText();
		String phone = phoneLabel.getText();
		String address1 = street1Label.getText();
		String address2 = street2Label.getText();
		String city = cityLabel.getText();
		String stateOrProvince = stateOrProvinceLabel.getText();
		String postalCode = postalCodeLabel.getText();
		String country = countryLabel.getText();

		return new Contact(firstName, lastName, dateOfBirth, email, phone, address1, address2, city, stateOrProvince,
				postalCode, country);
	}
}
