/**
 * Page object representing the Contact List page in the application under test.
 * This page displays a list of contacts and provides functionality to interact
 * with them.
 */

package ui.pages;

import java.util.List;
import java.util.Objects;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;
import utils.AppUrls;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import model.Contact;

@DefaultUrl(AppUrls.BASE_URL + AppUrls.CONTACT_LIST_WEB_PATH)
public class ContactListPage extends BasePage<ContactListPage> {

	public static final String PAGE_TITLE = "My Contacts";

	// Inner class to represent contact data information that can be read from the
	// contact list.
	private class ContactData {
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
			if (this == o)
				return true;
			if (o == null || getClass() != o.getClass())
				return false;
			ContactData that = (ContactData) o;
			return Objects.equals(name, that.name) && Objects.equals(birthdate, that.birthdate)
					&& Objects.equals(email, that.email) && Objects.equals(phone, that.phone)
					&& Objects.equals(address, that.address)
					&& Objects.equals(cityStatePostalCode, that.cityStatePostalCode)
					&& Objects.equals(country, that.country);
		}

		@Override
		public int hashCode() {
			return Objects.hash(name, birthdate, email, phone, address, cityStatePostalCode, country);
		}

		@Override
		public String toString() {
			return "ContactData{" + "name='" + name + '\'' + ", birthdate='" + birthdate + '\'' + ", email='" + email
					+ '\'' + ", phone='" + phone + '\'' + ", address='" + address + '\'' + ", cityStatePostalCode='"
					+ cityStatePostalCode + '\'' + ", country='" + country + '\'' + '}';
		}
	}

	private By tableCellLocator = By.cssSelector("td");

	@FindBy(id = "add-contact")
	private WebElementFacade addContactButton;

	@FindBy(css = ".contactTable .contactTableBodyRow")
	private List<WebElementFacade> contactsTableRows;

	@FindBy(id = "logout")
	private WebElementFacade logoutButton;

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
	public void clickLogoutButton() {
		logoutButton.click();
	}

	private ContactData contactToTableData(Contact contact) {
		String name = contact.getFirstName() + " " + contact.getLastName();

		String dob = contact.getBirthdate() != null ? contact.getBirthdate() : "";

		String email = contact.getEmail();

		String phone = contact.getPhone() != null ? contact.getPhone() : "";

		String address = contact.getStreet1() != null ? contact.getStreet1() : "";
		address += contact.getStreet2() != null ? " " + contact.getStreet2() : "";

		String cityStatePostalCode = contact.getCity() != null ? contact.getCity() : "";
		cityStatePostalCode += contact.getStateProvince() != null ? " " + contact.getStateProvince() : "";
		cityStatePostalCode += contact.getPostalCode() != null ? " " + contact.getPostalCode() : "";

		String country = contact.getCountry() != null ? contact.getCountry() : "";

		return new ContactData(name.trim(), dob, email, phone, address.trim(), cityStatePostalCode.trim(), country);
	}

	private ContactData readContactData(WebElementFacade row) {
		List<WebElementFacade> dataCells = row.thenFindAll(tableCellLocator);

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
	protected String getPageTitle() {
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

	/**
	 * Clicks on the row corresponding to the given contact in the contacts table.
	 * 
	 * This method searches for the specified contact record within the contact
	 * list. For the contact to be successfully found, all contact details provided
	 * must match the details in the contact list.
	 * 
	 * @param contact The Contact object representing the contact to be clicked.
	 * @throws RuntimeException if the contact is not found in the contact list
	 *                          table.
	 */
	public void clickOnContactRow(Contact contact) {
		WebElementFacade foundRecord = contactsTableRows.stream()
				.filter((row) -> readContactData(row).equals(contactToTableData(contact))).findAny().orElseThrow(
						() -> new RuntimeException("Contact " + contact + " was not found in the contact list table"));
		foundRecord.click();
	}

	public Boolean isLogoutButtonVisible() {
		return logoutButton.isVisible();
	}
}
