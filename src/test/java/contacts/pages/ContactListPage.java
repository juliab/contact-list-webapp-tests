package contacts.pages;

import java.util.List;
import java.util.Objects;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import contacts.model.Contact;


/**
 * Page object representing the Contact List page in the application under test.
 * This page displays a list of contacts and provides functionality to interact with them.
 */
@DefaultUrl(BasePage.BASE_URL + "/contactList")
public class ContactListPage extends BasePage<ContactListPage> {
	
	/**
     * The title of the page.
     */
	public static final String PAGE_TITLE = "My Contacts";

	// Inner class to represent contact data information that can be read from the contact list.
	class ContactData {
		private String name;
		private String birthdate;
		private String email;
		private String phone;
		private String address;
		private String cityStatePostalCode;
		private String country;

		public ContactData(String name, String birthdate, String email, String phone, String address,
				String cityStatePostalCode, String country) {
			this.name = name;
			this.birthdate = birthdate;
			this.email = email;
			this.phone = phone;
			this.address = address;
			this.cityStatePostalCode = cityStatePostalCode;
			this.country = country;
		}
		
		@Override
	    public boolean equals(Object o) {
	        if (this == o) return true;
	        if (o == null || getClass() != o.getClass()) return false;
	        ContactData that = (ContactData) o;
	        return Objects.equals(name, that.name) &&
	                Objects.equals(birthdate, that.birthdate) &&
	                Objects.equals(email, that.email) &&
	                Objects.equals(phone, that.phone) &&
	                Objects.equals(address, that.address) &&
	                Objects.equals(cityStatePostalCode, that.cityStatePostalCode) &&
	                Objects.equals(country, that.country);
	    }
		
		@Override
	    public int hashCode() {
	        return Objects.hash(name, birthdate, email, phone, address, cityStatePostalCode, country);
	    }
		
		@Override
	    public String toString() {
	        return "ContactData{" +
	                "name='" + name + '\'' +
	                ", birthdate='" + birthdate + '\'' +
	                ", email='" + email + '\'' +
	                ", phone='" + phone + '\'' +
	                ", address='" + address + '\'' +
	                ", cityStatePostalCode='" + cityStatePostalCode + '\'' +
	                ", country='" + country + '\'' +
	                '}';
	    }
	}
	
	public final static By TABLE_CELL_LOCATOR = By.cssSelector("td");

	@FindBy(id = "add-contact")
	WebElementFacade addContactButton;

	@FindBy(css = ".contactTable .contactTableBodyRow")
	List<WebElementFacade> contactsTableRows;
	
	@FindBy(id = "logout")
	WebElementFacade logoutButton;
	
	/**
     * Clicks on the "Add a New Contact" button.
     *
     * @return The ContactListPage instance.
     */
	public ContactListPage clickAddNewContactButton() {
		addContactButton.click();
		return this;
	}

	/**
     * Logs out of the application by clicking on the logout button.
     */
	public void logout() {
		logoutButton.click();
	}
	
	private ContactData contactToTableData(Contact contact) {
		String name = contact.getFirstName() + " " + contact.getLastName();
		
		String dob = contact.getDateOfBirth() != null ? contact.getDateOfBirth() : "";
		
		String email = contact.getEmail().toLowerCase();
		
		String phone = contact.getPhoneNumber() != null ? contact.getPhoneNumber() : "";
		
		String address = contact.getAddressLine1() != null ? contact.getAddressLine1() : "";
		address += contact.getAddressLine2() != null ? " " + contact.getAddressLine2() : "";
		
		String cityStatePostalCode = contact.getCity() != null ? contact.getCity() : "";
		cityStatePostalCode += contact.getStateOrProvince() != null ? " " + contact.getStateOrProvince() : "";
		cityStatePostalCode += contact.getPostalCode() != null ? " " + contact.getPostalCode() : "";
		
		String country = contact.getCountry() != null ? contact.getCountry() : "";
		
		return new ContactData(name.trim(), 
				dob,
				email, 
				phone, 
				address.trim(),
				cityStatePostalCode.trim(), 
				country);
	}

	private ContactData readContactData(WebElementFacade row) {
		List<WebElementFacade> dataCells = row.thenFindAll(TABLE_CELL_LOCATOR);

		if (dataCells.size() != 8) {
			throw new RuntimeException("Unable to read contact data from provided table row element");
		}
		
		List<String> data = dataCells.stream().map((cell) -> cell.getText().trim()).toList();
		
		String name = data.get(1);
		String birthdate = data.get(2);
		String email = data.get(3);
		String phone = data.get(4);
		String address = data.get(5);
		String cityStatePostalCode = data.get(6);
		String country = data.get(7);
		
		ContactData contactData = new ContactData(name, birthdate, email, phone, address, cityStatePostalCode, country);
		
		return contactData;
	}

	/**
     * Checks if the specified contact exists in the contacts table on the page.
     *
     * @param contact The contact to be checked for existence.
     * @return True if the contact exists in the table, otherwise false.
     */
	public Boolean containsContact(Contact contact) {
		
		if (contactsTableRows.isEmpty()) {
			return false;
		}
		
		List<ContactData> contactsInTable = contactsTableRows.stream().map((row) -> readContactData(row)).toList();
		
		return contactsInTable.contains(contactToTableData(contact));
	}

	/**
     * Returns the title of the page.
     *
     * @return The title of the page.
     */
	@Override
	protected String pageTitle() {
		return PAGE_TITLE;
	}

	/**
     * Returns the instance of ContactListPage.
     *
     * @return The instance of ContactListPage.
     */
	@Override
	protected ContactListPage self() {
		return this;
	}
}
