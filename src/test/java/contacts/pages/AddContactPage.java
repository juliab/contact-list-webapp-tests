package contacts.pages;

import contacts.model.Contact;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.support.FindBy;

@DefaultUrl(BasePage.BASE_URL + "/addContact")
public class AddContactPage extends BasePage<AddContactPage> {
	
	public static final String PAGE_TITLE = "Add Contact";
	
	@FindBy(id="firstName")
	WebElementFacade firstNameField;
	
	@FindBy(id="lastName")
	WebElementFacade lastNameField;
	
	@FindBy(id="birthdate")
	WebElementFacade dateOfBirthField;
	
	@FindBy(id="email")
	WebElementFacade emailField;
	
	@FindBy(id="phone")
	WebElementFacade phoneField;
	
	@FindBy(id="street1")
	WebElementFacade streetAddress1Field;
	
	@FindBy(id="street2")
	WebElementFacade streetAddress2Field;
	
	@FindBy(id="city")
	WebElementFacade cityField;
	
	@FindBy(id="stateProvince")
	WebElementFacade stateOrProvinceField;
	
	@FindBy(id="postalCode")
	WebElementFacade postalCodeField;
	
	@FindBy(id="country")
	WebElementFacade countryField;
	
	@FindBy(id="submit")
	WebElementFacade submitButton;
	
	public AddContactPage clickSubmitButton() {
		submitButton.click();
		return this;
    }
	
	public AddContactPage waitForContactToBeAdded() {
		waitFor((d) -> !isOpen());
		return this;
	}

    public AddContactPage fillContactDetails(Contact contact) {
    	firstNameField.sendKeys(contact.firstName());
    	lastNameField.sendKeys(contact.lastName());
    	dateOfBirthField.sendKeys(contact.dob());
    	emailField.sendKeys(contact.email());
    	phoneField.sendKeys(contact.phoneNumber());
    	streetAddress1Field.sendKeys(contact.addressLine1());
    	streetAddress2Field.sendKeys(contact.addressLine2());
    	cityField.sendKeys(contact.city());
    	stateOrProvinceField.sendKeys(contact.stateOrProvince());
    	postalCodeField.sendKeys(contact.postalCode());
    	countryField.sendKeys(contact.country());
    	return this;
    }

	@Override
	protected String pageTitle() {
		return PAGE_TITLE;
	}

	@Override
	protected AddContactPage self() {
		return this;
	}
}
