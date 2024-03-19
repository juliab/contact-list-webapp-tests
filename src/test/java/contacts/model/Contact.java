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
	private String firstName;
	private String lastName;
	private Optional<String> dob;
	private Optional<String> email;
	private Optional<String> phone;
	private Optional<String> address1;
	private Optional<String> address2;
	private Optional<String> city;
	private Optional<String> stateOrProvince;
	private Optional<String> postalCode;
	private Optional<String> country;

	/**
	 * Constructs a contact with the specified first name and last name.
	 *
	 * @param firstName The first name of the contact.
	 * @param lastName  The last name of the contact.
	 */
	public Contact(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
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
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = Optional.of(dob);
		this.email = Optional.of(email.toLowerCase());
		this.phone = Optional.of(phone);
		this.address1 = Optional.of(address1);
		this.address2 = Optional.of(address2);
		this.city = Optional.of(city);
		this.stateOrProvince = Optional.of(stateOrProvince);
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
	public Contact(String firstName, String lastName, Optional<String> dob, Optional<String> email,
			Optional<String> phone, Optional<String> address1, Optional<String> address2, Optional<String> city,
			Optional<String> stateOrProvince, Optional<String> postalCode, Optional<String> country) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.stateOrProvince = stateOrProvince;
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
		Optional<String> dob = Optional.ofNullable(data.get("Date of Birth"));
		Optional<String> email = Optional.ofNullable(data.get("Email").toLowerCase());
		Optional<String> phone = Optional.ofNullable(data.get("Phone"));
		Optional<String> address1 = Optional.ofNullable(data.get("Street Address 1"));
		Optional<String> address2 = Optional.ofNullable(data.get("Street Address 2"));
		Optional<String> city = Optional.ofNullable(data.get("City"));
		Optional<String> stateOrProvince = Optional.ofNullable(data.get("State or Province"));
		Optional<String> postalCode = Optional.ofNullable(data.get("Postal Code"));
		Optional<String> country = Optional.ofNullable(data.get("Country"));

		return new Contact(data.get("First Name"), data.get("Last Name"), dob, email, phone, address1, address2, city,
				stateOrProvince, postalCode, country);
	}

	/**
	 * Gets the first name of the contact.
	 *
	 * @return The first name of the contact.
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Gets the last name of the contact.
	 *
	 * @return The last name of the contact.
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * Gets the date of birth of the contact.
	 *
	 * @return The date of birth of the contact.
	 */
	public String getDateOfBirth() {
		return dob.orElse("");
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
	public String getPhoneNumber() {
		return phone.orElse("");
	}

	/**
	 * Gets the first line of the address of the contact.
	 *
	 * @return The first line of the address of the contact.
	 */
	public String getAddressLine1() {
		return address1.orElse("");
	}

	/**
	 * Gets the second line of the address of the contact.
	 *
	 * @return The second line of the address of the contact.
	 */
	public String getAddressLine2() {
		return address2.orElse("");
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
	public String getStateOrProvince() {
		return stateOrProvince.orElse("");
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
				+ getDateOfBirth() + '\'' + ", email='" + getEmail() + '\'' + ", phone='" + getPhoneNumber() + '\''
				+ ", address1='" + getAddressLine1() + '\'' + ", address2='" + getAddressLine2() + '\'' + ", city='"
				+ getCity() + '\'' + ", stateOrProvince='" + getStateOrProvince() + '\'' + ", postalCode='"
				+ getPostalCode() + '\'' + ", country='" + getCountry() + '\'' + '}';
	}
	
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return Objects.equals(firstName, contact.firstName) &&
                Objects.equals(lastName, contact.lastName) &&
                Objects.equals(dob, contact.dob) &&
                Objects.equals(email, contact.email) &&
                Objects.equals(phone, contact.phone) &&
                Objects.equals(address1, contact.address1) &&
                Objects.equals(address2, contact.address2) &&
                Objects.equals(city, contact.city) &&
                Objects.equals(stateOrProvince, contact.stateOrProvince) &&
                Objects.equals(postalCode, contact.postalCode) &&
                Objects.equals(country, contact.country);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, dob, email, phone, address1, address2, city, stateOrProvince, postalCode, country);
    }
}
