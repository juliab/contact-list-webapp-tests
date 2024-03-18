package contacts.pages;

import contacts.model.Contact;
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
	
	@FindBy(id="error")
	WebElementFacade errorMessage;
	
	/**
     * Clicks the submit button to add the contact.
     * 
     * @return The instance of the AddContactPage.
     */
	public AddContactPage clickSubmitButton() {
		submitButton.click();
		return this;
    }
	
	/**
	 * Waits until the contact is successfully added.
	 * This method waits until the Add Contact page is no longer open,
	 * indicating that the contact has been added.
	 * 
	 * @return The instance of the AddContactPage.
	 */
	public AddContactPage waitForContactToBeAdded() {
		waitFor((d) -> !isOpen());
		return this;
	}

	/**
     * Fills in the contact details in the corresponding fields on the page.
     * 
     * @param contact The Contact object containing the details to be filled in.
     * @return The instance of the AddContactPage.
     */
    public AddContactPage fillContactDetails(Contact contact) {
    	firstNameField.sendKeys(contact.getFirstName());
    	lastNameField.sendKeys(contact.getLastName());
    	dateOfBirthField.sendKeys(contact.getDateOfBirth());
    	emailField.sendKeys(contact.getEmail());
    	phoneField.sendKeys(contact.getPhoneNumber());
    	streetAddress1Field.sendKeys(contact.getAddressLine1());
    	streetAddress2Field.sendKeys(contact.getAddressLine2());
    	cityField.sendKeys(contact.getCity());
    	stateOrProvinceField.sendKeys(contact.getStateOrProvince());
    	postalCodeField.sendKeys(contact.getPostalCode());
    	countryField.sendKeys(contact.getCountry());
    	return this;
    }
    
    public String readValidationMessage() {
    	return errorMessage.getText();
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
