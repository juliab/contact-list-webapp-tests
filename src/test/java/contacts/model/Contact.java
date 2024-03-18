package contacts.model;

import java.util.Map;

/**
 * Represents a contact with various details such as name, date of birth, email, address, etc.
 */
public class Contact {
    private String firstName;
    private String lastName;
    private String dob = "";
    private String email = "";
    private String phone = "";
    private String address1 = "";
    private String address2 = "";
    private String city = "";
    private String stateOrProvince = "";
    private String postalCode = "";
    private String country = "";

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
    	return new Contact(data.get("First Name"), 
            data.get("Last Name"), 
            data.get("Date of Birth"), 
            data.get("Email"),
            data.get("Phone"), 
            data.get("Street Address 1"), 
            data.get("Street Address 2"), 
            data.get("City"),
            data.get("State or Province"), 
            data.get("Postal Code"), 
            data.get("Country"));
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
        return dob;
    }

    /**
     * Gets the email address of the contact.
     *
     * @return The email address of the contact.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Gets the phone number of the contact.
     *
     * @return The phone number of the contact.
     */
    public String getPhoneNumber() {
        return phone;
    }

    /**
     * Gets the first line of the address of the contact.
     *
     * @return The first line of the address of the contact.
     */
    public String getAddressLine1() {
        return address1;
    }

    /**
     * Gets the second line of the address of the contact.
     *
     * @return The second line of the address of the contact.
     */
    public String getAddressLine2() {
        return address2;
    }

    /**
     * Gets the city of the contact's address.
     *
     * @return The city of the contact's address.
     */
    public String getCity() {
        return city;
    }

    /**
     * Gets the state or province of the contact's address.
     *
     * @return The state or province of the contact's address.
     */
    public String getStateOrProvince() {
        return stateOrProvince;
    }

    /**
     * Gets the postal code of the contact's address.
     *
     * @return The postal code of the contact's address.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * Gets the country of the contact's address.
     *
     * @return The country of the contact's address.
     */
    public String getCountry() {
        return country;
    }
    
    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", city='" + city + '\'' +
                ", stateOrProvince='" + stateOrProvince + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
