package model;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;

import utils.Logger;

/**
 * Represents a contact with various details such as name, date of birth, email,
 * address, etc. First and last name fields are mandatory, while other details
 * are optional.
 */
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Contact {
	private Optional<String> firstName = Optional.empty();
	private Optional<String> lastName = Optional.empty();
	private Optional<String> birthdate = Optional.empty();
	private Optional<String> email = Optional.empty();
	private Optional<String> phone = Optional.empty();
	private Optional<String> street1 = Optional.empty();
	private Optional<String> street2 = Optional.empty();
	private Optional<String> city = Optional.empty();
	private Optional<String> stateProvince = Optional.empty();
	private Optional<String> postalCode = Optional.empty();
	private Optional<String> country = Optional.empty();

	/**
	 * Constructs a contact with the specified first name and last name.
	 *
	 * @param firstName The first name of the contact.
	 * @param lastName  The last name of the contact.
	 */
	public Contact(String firstName, String lastName) {
		this.firstName = Optional.ofNullable(firstName);
		this.lastName = Optional.ofNullable(lastName);
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
	@JsonCreator
	public Contact(@JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName,
			@JsonProperty("birthdate") String birthdate,
			@JsonProperty("email") String email,
			@JsonProperty("phone") String phone,
			@JsonProperty("street1") String street1,
			@JsonProperty("street2") String street2,
			@JsonProperty("city") String city,
			@JsonProperty("stateProvince") String stateProvince,
			@JsonProperty("postalCode") String postalCode,
			@JsonProperty("country") String country) {
		this.firstName = Optional.ofNullable(firstName);
		this.lastName = Optional.ofNullable(lastName);
		this.birthdate = Optional.ofNullable(birthdate);
		this.email = Optional.ofNullable(email.toLowerCase());
		this.phone = Optional.ofNullable(phone);
		this.street1 = Optional.ofNullable(street1);
		this.street2 = Optional.ofNullable(street2);
		this.city = Optional.ofNullable(city);
		this.stateProvince = Optional.ofNullable(stateProvince);
		this.postalCode = Optional.ofNullable(postalCode);
		this.country = Optional.ofNullable(country);
	}

	/**
	 * Creates a contact object from the given map of data.
	 *
	 * @param data The map containing contact details.
	 * @return A new Contact object created from the provided data.
	 */
	public static Contact fromMap(Map<String, String> data) {
		return new Contact(data.get("First Name"), data.get("Last Name"), data.get("Date of Birth"), data.get("Email"),
				data.get("Phone"), data.get("Street Address 1"), data.get("Street Address 2"), data.get("City"),
				data.get("State or Province"), data.get("Postal Code"), data.get("Country"));
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
	 * Set the first name of the contact.
	 * 
	 * @param firstName The first name of the contact.
	 * @return This contact object.
	 */
	public Contact setFirstName(String firstName) {
		this.firstName = Optional.ofNullable(firstName);
		return this;
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
	 * Set the last name of the contact.
	 * 
	 * @param lastName The last name of the contact.
	 * @return This contact object.
	 */
	public Contact setLastName(String lastName) {
		this.lastName = Optional.ofNullable(lastName);
		return this;
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
	 * Set the date of birth of the contact.
	 * 
	 * @param birthdate The date of birth of the contact.
	 * @return This contact object.
	 */
	public Contact setBirthdate(String birthdate) {
		this.birthdate = Optional.ofNullable(birthdate);
		return this;
	}

	/**
	 * Gets the email address of the contact.
	 *
	 * @return The email address of the contact.
	 */
	public String getEmail() {
		return email.isPresent() ? email.get().toLowerCase() : "";
	}

	/**
	 * Sets the email address of the contact.
	 * 
	 * @param email The email address of the contact.
	 * @return This contact object.
	 */
	public Contact setEmail(String email) {
		this.email = Optional.ofNullable(email);
		return this;
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
	 * Sets the phone number of the contact.
	 * 
	 * @param phone The phone number of the contact.
	 * @return This contact object.
	 */
	public Contact setPhone(String phone) {
		this.phone = Optional.ofNullable(phone);
		return this;
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
	 * Sets the first line of the address of the contact.
	 * 
	 * @param street1 The first line of the address of the contact.
	 * @return This contact object.
	 */

	public Contact setStreet1(String street1) {
		this.street1 = Optional.ofNullable(street1);
		return this;
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
	 * Sets the second line of the address of the contact.
	 * 
	 * @param street2 The second line of the address of the contact.
	 * @return This contact object.
	 */
	public Contact setStreet2(String street2) {
		this.street2 = Optional.ofNullable(street2);
		return this;
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
	 * Sets the city of the contact's address.
	 * 
	 * @param city The city of the contact's address.
	 * @return This contact object.
	 */
	public Contact setCity(String city) {
		this.city = Optional.ofNullable(city);
		return this;
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
	 * Sets the state or province of the contact's address.
	 * 
	 * @param stateProvince The state or province of the contact's address.
	 * @return This contact object.
	 */
	public Contact setStateProvince(String stateProvince) {
		this.stateProvince = Optional.ofNullable(stateProvince);
		return this;
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
	 * Sets the postal code of the contact's address.
	 * 
	 * @param postalCode The postal code of the contact's address.
	 * @return This contact object.
	 */
	public Contact setPostalCode(String postalCode) {
		this.postalCode = Optional.ofNullable(postalCode);
		return this;
	}

	/**
	 * Gets the country of the contact's address.
	 *
	 * @return The country of the contact's address.
	 */
	public String getCountry() {
		return country.orElse("");
	}

	/**
	 * Sets the country of the contact's address.
	 * 
	 * @param country The country of the contact's address.
	 * @return This contact object.
	 */
	public Contact setCountry(String country) {
		this.country = Optional.ofNullable(country);
		return this;
	}

	/**
	 * Returns a string representation of the contact.
	 *
	 * @return A string representation of the contact.
	 */
	@Override
	public String toString() {
		String contactData = "first name: " + getFirstName();
		contactData += "\nlast name: " + getLastName();
		contactData += "\nbirth date: " + getBirthdate();
		contactData += "\nemail: " + getEmail();
		contactData += "\nphone: " + getPhone();
		contactData += "\nstreet address 1: " + getStreet1();
		contactData += "\nstreet address 2: " + getStreet2();
		contactData += "\ncity: " + getCity();
		contactData += "\nstate or province: " + getStateProvince();
		contactData += "\npostal code: " + getPostalCode();
		contactData += "\ncountry: " + getCountry();

		return contactData;
	}

	/**
	 * Returns a JSON representation of the contact.
	 *
	 * @return A JSON representation of the contact.
	 */
	public String toJsonString() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			return objectMapper.writeValueAsString(this);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Compares this contact to the specified object. The result is true if and only
	 * if the argument is not null and is a Contact object that has the same first
	 * name, last name, date of birth, email, phone number, street address, city,
	 * state or province, postal code, and country as this contact.
	 *
	 * @param o The object to compare this contact against.
	 * @return true if the given object represents a Contact equivalent to this
	 *         contact, false otherwise.
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		Contact contact = (Contact) o;

		boolean firstNameEquals = Objects.equals(getFirstName(), contact.getFirstName());
		boolean lastNameEquals = Objects.equals(getLastName(), contact.getLastName());
		boolean birthdateEquals = Objects.equals(getBirthdate(), contact.getBirthdate());
		boolean emailEquals = Objects.equals(getEmail(), contact.getEmail());
		boolean phoneEquals = Objects.equals(getPhone(), contact.getPhone());
		boolean street1Equals = Objects.equals(getStreet1(), contact.getStreet1());
		boolean street2Equals = Objects.equals(getStreet2(), contact.getStreet2());
		boolean cityEquals = Objects.equals(getCity(), contact.getCity());
		boolean stateProvinceEquals = Objects.equals(getStateProvince(), contact.getStateProvince());
		boolean postalCodeEquals = Objects.equals(getPostalCode(), contact.getPostalCode());
		boolean countryEquals = Objects.equals(getCountry(), contact.getCountry());

		boolean result = firstNameEquals && lastNameEquals && birthdateEquals && emailEquals && phoneEquals
				&& street1Equals && street2Equals && cityEquals && stateProvinceEquals && postalCodeEquals
				&& countryEquals;

		if (!result) {
			String equalityInfo = firstNameEquals ? "" : "first name, ";
			equalityInfo += lastNameEquals ? "" : "last name, ";
			equalityInfo += birthdateEquals ? "" : "birthdate, ";
			equalityInfo += emailEquals ? "" : "email, ";
			equalityInfo += phoneEquals ? "" : "phone, ";
			equalityInfo += street1Equals ? "" : "street1, ";
			equalityInfo += street2Equals ? "" : "street2, ";
			equalityInfo += cityEquals ? "" : "city, ";
			equalityInfo += stateProvinceEquals ? "" : "stateProvince, ";
			equalityInfo += postalCodeEquals ? "" : "postalCode, ";
			equalityInfo += countryEquals ? "" : "country";

			Logger.log("Contacts are not equal", "Properties that differ: " + equalityInfo);
		}

		return result;
	}

	/**
	 * Returns the hash code value for this contact.
	 *
	 * @return The hash code value for this contact.
	 */
	@Override
	public int hashCode() {
		return Objects.hash(firstName, lastName, birthdate, email, phone, street1, street2, city, stateProvince,
				postalCode, country);
	}
}
