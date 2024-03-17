package contacts.model;

import java.util.Map;

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

    public Contact(String firstName, String lastName) {
    	this.firstName = firstName;
        this.lastName = lastName;
    }
    
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

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    public String dob() {
        return dob;
    }

    public String email() {
        return email;
    }

    public String phoneNumber() {
        return phone;
    }

    public String addressLine1() {
        return address1;
    }

    public String addressLine2() {
        return address2;
    }

    public String city() {
        return city;
    }

    public String stateOrProvince() {
        return stateOrProvince;
    }

    public String postalCode() {
        return postalCode;
    }

    public String country() {
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
