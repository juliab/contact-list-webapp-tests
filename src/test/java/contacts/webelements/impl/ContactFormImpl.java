/**
 * This class represents the implementation of the ContactForm widget.
 * It provides methods to interact with the contact form elements.
 */

package contacts.webelements.impl;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import contacts.model.Contact;
import contacts.webelements.ContactForm;
import contacts.webelements.TextFormField;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WidgetObjectImpl;

public class ContactFormImpl extends WidgetObjectImpl implements ContactForm {

	/**
	 * Constructs a new ContactFormImpl object.
	 *
	 * @param page                  The PageObject to which this widget belongs.
	 * @param locator               The ElementLocator that locates this widget on
	 *                              the page.
	 * @param timeoutInMilliseconds The timeout for finding elements in
	 *                              milliseconds.
	 */
	public ContactFormImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
		super(page, locator, timeoutInMilliseconds);
	}

	@FindBy(id = "firstName")
	private TextFormField firstNameField;

	@FindBy(id = "lastName")
	private TextFormField lastNameField;

	@FindBy(id = "birthdate")
	private TextFormField dateOfBirthField;

	@FindBy(id = "email")
	private TextFormField emailField;

	@FindBy(id = "phone")
	private TextFormField phoneField;

	@FindBy(id = "street1")
	private TextFormField streetAddress1Field;

	@FindBy(id = "street2")
	private TextFormField streetAddress2Field;

	@FindBy(id = "city")
	private TextFormField cityField;

	@FindBy(id = "stateProvince")
	private TextFormField stateOrProvinceField;

	@FindBy(id = "postalCode")
	private TextFormField postalCodeField;

	@FindBy(id = "country")
	private TextFormField countryField;

	/**
	 * Fills the contact details into the corresponding form fields.
	 *
	 * @param contact The contact object containing the details to be filled.
	 * @return The PageObject representing the page where the contact form is
	 *         located.
	 */
	@Override
	public PageObject fillContactDetails(Contact contact) {
		firstNameField.fill(contact.getFirstName());
		lastNameField.fill(contact.getLastName());
		dateOfBirthField.fill(contact.getBirthdate());
		emailField.fill(contact.getEmail());
		phoneField.fill(contact.getPhone());
		streetAddress1Field.fill(contact.getStreet1());
		streetAddress2Field.fill(contact.getStreet2());
		cityField.fill(contact.getCity());
		stateOrProvinceField.fill(contact.getStateProvince());
		postalCodeField.fill(contact.getPostalCode());
		countryField.fill(contact.getCountry());
		return getPage();
	}
}
