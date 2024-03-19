package contacts.pages;

import org.openqa.selenium.support.FindBy;

import contacts.model.Contact;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Page Object class representing the "Contact Details" page in the application.
 * This page contains contact details and allows user to edit or delete contact.
 */
@DefaultUrl(BasePage.BASE_URL + "/contactDetails")
public class ContactDetailsPage extends BasePage<ContactDetailsPage> {

	/**
	 * The title of the page.
	 */
	public static final String PAGE_TITLE = "";

	@FindBy(id = "edit-contact")
	WebElementFacade editContactButton;

	@FindBy(id = "delete")
	WebElementFacade deleteContactButton;

	@FindBy(id = "firstName")
	WebElementFacade firstNameLabel;

	@FindBy(id = "lastName")
	WebElementFacade lastNameLabel;

	@FindBy(id = "birthdate")
	WebElementFacade dateOfBirthLabel;

	@FindBy(id = "email")
	WebElementFacade emailLabel;

	@FindBy(id = "phone")
	WebElementFacade phoneLabel;

	@FindBy(id = "street1")
	WebElementFacade street1Label;

	@FindBy(id = "street2")
	WebElementFacade street2Label;

	@FindBy(id = "city")
	WebElementFacade cityLabel;

	@FindBy(id = "stateProvince")
	WebElementFacade stateOrProvinceLabel;

	@FindBy(id = "postalCode")
	WebElementFacade postalCodeLabel;

	@FindBy(id = "country")
	WebElementFacade countryLabel;

	@Override
	protected ContactDetailsPage self() {
		return this;
	}

	@Override
	protected String pageTitle() {
		return PAGE_TITLE;
	}

	@Override
	public Boolean isOpen() {
		return getDriver().getCurrentUrl().contains("/contactDetails");
	}

	/**
	 * Clicks on "Edit Contact" button.
	 */
	public void clickEditContactButton() {
		editContactButton.click();
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
