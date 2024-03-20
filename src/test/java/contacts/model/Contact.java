package contacts.model;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * Represents a contact with various details such as name, date of birth, email,
 * address, etc. First and last name fields are mandatory, while other details
 * are optional.
 */
public class Contact {
	private Optional<String> firstName;
	private Optional<String> lastName;
	private Optional<String> birthdate;
	private Optional<String> email;
	private Optional<String> phone;
	private Optional<String> street1;
	private Optional<String> street2;
	private Optional<String> city;
	private Optional<String> stateProvince;
	private Optional<String> postalCode;
	private Optional<String> country;

	/**
	 * Constructs a contact with the specified first name and last name.
	 *
	 * @param firstName The first name of the contact.
	 * @param lastName  The last name of the contact.
	 */
	public Contact(String firstName, String lastName) {
		this.firstName = Optional.of(firstName);
		this.lastName = Optional.of(lastName);
	}

	/**
	 * Constructs a contact with the specified details.
	 *
	 * @param firstName       The first name of the contact.
	 * @param lastName        The last name of the contact.
	 * @param dob             The date of birth of the contact.
	 * @param email           The email address of the contact.
	 * @param phone           The phone number of the contact.
	 * @param address1        The first line of the address of the contact.
	 * @param address2        The second line of the address of the contact.
	 * @param city            The city of the contact's address.
	 * @param stateOrProvince The state or province of the contact's address.
	 * @param postalCode      The postal code of the contact's address.
	 * @param country         The country of the contact's address.
	 */
	public Contact(String firstName, String lastName, String dob, String email, String phone, String address1,
			String address2, String city, String stateOrProvince, String postalCode, String country) {
		this.firstName = Optional.of(firstName);
		this.lastName = Optional.of(lastName);
		this.birthdate = Optional.of(dob);
		this.email = Optional.of(email.toLowerCase());
		this.phone = Optional.of(phone);
		this.street1 = Optional.of(address1);
		this.street2 = Optional.of(address2);
		this.city = Optional.of(city);
		this.stateProvince = Optional.of(stateOrProvince);
		this.postalCode = Optional.of(postalCode);
		this.country = Optional.of(country);
	}

	/**
	 * Constructs a contact with the specified details.
	 *
	 * @param firstName       The first name of the contact.
	 * @param lastName        The last name of the contact.
	 * @param dob             The date of birth of the contact.
	 * @param email           The email address of the contact.
	 * @param phone           The phone number of the contact.
	 * @param address1        The first line of the address of the contact.
	 * @param address2        The second line of the address of the contact.
	 * @param city            The city of the contact's address.
	 * @param stateOrProvince The state or province of the contact's address.
	 * @param postalCode      The postal code of the contact's address.
	 * @param country         The country of the contact's address.
	 */
	public Contact(Optional<String> firstName, Optional<String> lastName, Optional<String> dob, Optional<String> email,
			Optional<String> phone, Optional<String> address1, Optional<String> address2, Optional<String> city,
			Optional<String> stateOrProvince, Optional<String> postalCode, Optional<String> country) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthdate = dob;
		this.email = email;
		this.phone = phone;
		this.street1 = address1;
		this.street2 = address2;
		this.city = city;
		this.stateProvince = stateOrProvince;
		this.postalCode = postalCode;
		this.country = country;
	}

	/**
	 * Creates a contact object from the given map of data.
	 *
	 * @param data The map containing contact details.
	 * @return A new Contact object created from the provided data.
	 */
	public static Contact fromMap(Map<String, String> data) {
		Optional<String> firstName = Optional.ofNullable(data.get("First Name"));
		Optional<String> lastName = Optional.ofNullable(data.get("Last Name"));
		Optional<String> dob = Optional.ofNullable(data.get("Date of Birth"));
		Optional<String> email = Optional.ofNullable(data.get("Email").toLowerCase());
		Optional<String> phone = Optional.ofNullable(data.get("Phone"));
		Optional<String> address1 = Optional.ofNullable(data.get("Street Address 1"));
		Optional<String> address2 = Optional.ofNullable(data.get("Street Address 2"));
		Optional<String> city = Optional.ofNullable(data.get("City"));
		Optional<String> stateOrProvince = Optional.ofNullable(data.get("State or Province"));
		Optional<String> postalCode = Optional.ofNullable(data.get("Postal Code"));
		Optional<String> country = Optional.ofNullable(data.get("Country"));

		return new Contact(firstName, lastName, dob, email, phone, address1, address2, city, stateOrProvince,
				postalCode, country);
	}

	/**
	 * Gets the first name of the contact.
	 *
	 * @return The first name of the contact.
	 */
	public String getFirstName() {
		return firstName.orElse("");
	}

	/**
	 * Gets the last name of the contact.
	 *
	 * @return The last name of the contact.
	 */
	public String getLastName() {
		return lastName.orElse("");
	}

	/**
	 * Gets the date of birth of the contact.
	 *
	 * @return The date of birth of the contact.
	 */
	public String getBirthdate() {
		return birthdate.orElse("");
	}

	/**
	 * Gets the email address of the contact.
	 *
	 * @return The email address of the contact.
	 */
	public String getEmail() {
		return email.orElse("");
	}

	/**
	 * Gets the phone number of the contact.
	 *
	 * @return The phone number of the contact.
	 */
	public String getPhone() {
		return phone.orElse("");
	}

	/**
	 * Gets the first line of the address of the contact.
	 *
	 * @return The first line of the address of the contact.
	 */
	public String getStreet1() {
		return street1.orElse("");
	}

	/**
	 * Gets the second line of the address of the contact.
	 *
	 * @return The second line of the address of the contact.
	 */
	public String getStreet2() {
		return street2.orElse("");
	}

	/**
	 * Gets the city of the contact's address.
	 *
	 * @return The city of the contact's address.
	 */
	public String getCity() {
		return city.orElse("");
	}

	/**
	 * Gets the state or province of the contact's address.
	 *
	 * @return The state or province of the contact's address.
	 */
	public String getStateProvince() {
		return stateProvince.orElse("");
	}

	/**
	 * Gets the postal code of the contact's address.
	 *
	 * @return The postal code of the contact's address.
	 */
	public String getPostalCode() {
		return postalCode.orElse("");
	}

	/**
	 * Gets the country of the contact's address.
	 *
	 * @return The country of the contact's address.
	 */
	public String getCountry() {
		return country.orElse("");
	}

	@Override
	public String toString() {
		return "Contact{" + "firstName='" + getFirstName() + '\'' + ", lastName='" + getLastName() + '\'' + ", dob='"
				+ getBirthdate() + '\'' + ", email='" + getEmail() + '\'' + ", phone='" + getPhone() + '\''
				+ ", address1='" + getStreet1() + '\'' + ", address2='" + getStreet2() + '\'' + ", city='" + getCity()
				+ '\'' + ", stateOrProvince='" + getStateProvince() + '\'' + ", postalCode='" + getPostalCode() + '\''
				+ ", country='" + getCountry() + '\'' + '}';
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Contact contact = (Contact) o;
		return Objects.equals(firstName, contact.firstName) && Objects.equals(lastName, contact.lastName)
				&& Objects.equals(birthdate, contact.birthdate) && Objects.equals(email, contact.email)
				&& Objects.equals(phone, contact.phone) && Objects.equals(street1, contact.street1)
				&& Objects.equals(street2, contact.street2) && Objects.equals(city, contact.city)
				&& Objects.equals(stateProvince, contact.stateProvince)
				&& Objects.equals(postalCode, contact.postalCode) && Objects.equals(country, contact.country);
	}

	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, birthdate, email, phone, street1, street2, city, stateProvince,
				postalCode, country);
	}
}
