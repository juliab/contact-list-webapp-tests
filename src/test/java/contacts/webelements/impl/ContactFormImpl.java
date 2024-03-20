package contacts.webelements.impl;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import contacts.model.Contact;
import contacts.webelements.ContactForm;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;
import net.serenitybdd.core.pages.WidgetObjectImpl;

public class ContactFormImpl extends WidgetObjectImpl implements ContactForm {

	public ContactFormImpl(PageObject page, ElementLocator locator, long timeoutInMilliseconds) {
		super(page, locator, timeoutInMilliseconds);
	}

	@FindBy(id = "firstName")
	WebElementFacade firstNameField;

	@FindBy(id = "lastName")
	WebElementFacade lastNameField;

	@FindBy(id = "birthdate")
	WebElementFacade dateOfBirthField;

	@FindBy(id = "email")
	WebElementFacade emailField;

	@FindBy(id = "phone")
	WebElementFacade phoneField;

	@FindBy(id = "street1")
	WebElementFacade streetAddress1Field;

	@FindBy(id = "street2")
	WebElementFacade streetAddress2Field;

	@FindBy(id = "city")
	WebElementFacade cityField;

	@FindBy(id = "stateProvince")
	WebElementFacade stateOrProvinceField;

	@FindBy(id = "postalCode")
	WebElementFacade postalCodeField;

	@FindBy(id = "country")
	WebElementFacade countryField;

	@Override
	public PageObject fillContactDetails(Contact contact) {
		fillFieldValue(firstNameField, contact.getFirstName());
		fillFieldValue(lastNameField, contact.getLastName());
		fillFieldValue(dateOfBirthField, contact.getBirthdate());
		fillFieldValue(emailField, contact.getEmail());
		fillFieldValue(phoneField, contact.getPhone());
		fillFieldValue(streetAddress1Field, contact.getStreet1());
		fillFieldValue(streetAddress2Field, contact.getStreet2());
		fillFieldValue(cityField, contact.getCity());
		fillFieldValue(stateOrProvinceField, contact.getStateProvince());
		fillFieldValue(postalCodeField, contact.getPostalCode());
		fillFieldValue(countryField, contact.getCountry());
		return getPage();
	}

	private void fillFieldValue(WebElementFacade field, String value) {
		if (value != null && !field.getAttribute("value").equals(value)) {
			field.clear();
			field.sendKeys(value);
		}
	}
}
