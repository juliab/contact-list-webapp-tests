package ui.pages;

import org.openqa.selenium.support.FindBy;

import model.Contact;
import net.serenitybdd.annotations.DefaultUrl;
import ui.webelements.ReadOnlyFIeld;
import utils.AppUrls;

/**
 * Page Object class representing the "Contact Details" page in the application.
 * This page contains contact details and allows user to edit or delete contact.
 */
@DefaultUrl(AppUrls.BASE_URL + AppUrls.CONTACT_DETAILS_WEB_PATH)
public class ContactDetailsPage extends BasePage<ContactDetailsPage> {

	public static final String PAGE_TITLE = "";

	@FindBy(id = "edit-contact")
	private ReadOnlyFIeld editContactButton;

	@FindBy(id = "delete")
	private ReadOnlyFIeld deleteContactButton;

	@FindBy(id = "firstName")
	private ReadOnlyFIeld firstNameData;

	@FindBy(id = "lastName")
	private ReadOnlyFIeld lastNameData;

	@FindBy(id = "birthdate")
	private ReadOnlyFIeld dateOfBirthData;

	@FindBy(id = "email")
	private ReadOnlyFIeld emailData;

	@FindBy(id = "phone")
	private ReadOnlyFIeld phoneData;

	@FindBy(id = "street1")
	private ReadOnlyFIeld street1Data;

	@FindBy(id = "street2")
	private ReadOnlyFIeld street2Data;

	@FindBy(id = "city")
	private ReadOnlyFIeld cityData;

	@FindBy(id = "stateProvince")
	private ReadOnlyFIeld stateOrProvinceData;

	@FindBy(id = "postalCode")
	private ReadOnlyFIeld postalCodeData;

	@FindBy(id = "country")
	private ReadOnlyFIeld countryData;

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
		String firstName = firstNameData.read();
		String lastName = lastNameData.read();
		String dateOfBirth = dateOfBirthData.read();
		String email = emailData.read();
		String phone = phoneData.read();
		String address1 = street1Data.read();
		String address2 = street2Data.read();
		String city = cityData.read();
		String stateOrProvince = stateOrProvinceData.read();
		String postalCode = postalCodeData.read();
		String country = countryData.read();

		return new Contact(firstName, lastName, dateOfBirth, email, phone, address1, address2, city, stateOrProvince,
				postalCode, country);
	}
}
