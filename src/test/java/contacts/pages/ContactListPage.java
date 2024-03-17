package contacts.pages;

import java.util.List;
import java.util.Objects;

import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

import contacts.model.Contact;

@DefaultUrl(BasePage.BASE_URL + "/contactList")
public class ContactListPage extends BasePage<ContactListPage> {
	
	public static final String PAGE_TITLE = "My Contacts";

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
	
	public void logout() {
		logoutButton.click();
	}

	public ContactListPage clickAddNewContactButton() {
		addContactButton.click();
		return this;
	}

	private ContactData contactToTableData(Contact contact) {
		String name = contact.firstName() + " " + contact.lastName();
		
		String dob = contact.dob() != null ? contact.dob() : "";
		
		String email = contact.email().toLowerCase();
		
		String phone = contact.phoneNumber() != null ? contact.phoneNumber() : "";
		
		String address = contact.addressLine1() != null ? contact.addressLine1() : "";
		address += contact.addressLine2() != null ? " " + contact.addressLine2() : "";
		
		String cityStatePostalCode = contact.city() != null ? contact.city() : "";
		cityStatePostalCode += contact.stateOrProvince() != null ? " " + contact.stateOrProvince() : "";
		cityStatePostalCode += contact.postalCode() != null ? " " + contact.postalCode() : "";
		
		String country = contact.country() != null ? contact.country() : "";
		
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

	public Boolean containsContact(Contact contact) {
		
		if (contactsTableRows.isEmpty()) {
			return false;
		}
		
		List<ContactData> contactsInTable = contactsTableRows.stream().map((row) -> readContactData(row)).toList();
		
		System.out.println("Contacts read from contact table:");
		contactsInTable.forEach((c) -> System.out.println(c));
		
		System.out.println("Searched contact:");
		System.out.println(contactToTableData(contact));
		
		return contactsInTable.contains(contactToTableData(contact));
	}

	@Override
	protected String pageTitle() {
		return PAGE_TITLE;
	}

	@Override
	protected ContactListPage self() {
		return this;
	}
}
