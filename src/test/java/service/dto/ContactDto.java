package service.dto;

import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import model.Contact;

public class ContactDto {

	private Optional<String> _id;
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
	private Optional<String> owner;
	private Optional<String> __v;

	@JsonCreator
	public ContactDto(@JsonProperty("_id") String _id, @JsonProperty("firstName") String firstName,
			@JsonProperty("lastName") String lastName, @JsonProperty("birthdate") String birthdate,
			@JsonProperty("email") String email, @JsonProperty("phone") String phone,
			@JsonProperty("street1") String street1, @JsonProperty("street2") String street2,
			@JsonProperty("city") String city, @JsonProperty("stateProvince") String stateProvince,
			@JsonProperty("postalCode") String postalCode, @JsonProperty("country") String country,
			@JsonProperty("owner") String owner, @JsonProperty("__v") String __v) {
		this._id = Optional.ofNullable(_id);
		this.firstName = Optional.ofNullable(firstName);
		this.lastName = Optional.ofNullable(lastName);
		this.birthdate = Optional.ofNullable(birthdate);
		this.email = Optional.ofNullable(email);
		this.phone = Optional.ofNullable(phone);
		this.street1 = Optional.ofNullable(street1);
		this.street2 = Optional.ofNullable(street2);
		this.city = Optional.ofNullable(city);
		this.stateProvince = Optional.ofNullable(stateProvince);
		this.postalCode = Optional.ofNullable(postalCode);
		this.country = Optional.ofNullable(country);
		this.owner = Optional.ofNullable(owner);
		this.__v = Optional.ofNullable(__v);
	}

	public String getId() {
		return _id.orElse("");
	}

	public String getFirstName() {
		return firstName.orElse("");
	}

	public String getLastName() {
		return lastName.orElse("");
	}

	public String getBirthdate() {
		return birthdate.orElse("");
	}

	public String getEmail() {
		return email.orElse("");
	}

	public String getPhone() {
		return phone.orElse("");
	}

	public String getStreet1() {
		return street1.orElse("");
	}

	public String getStreet2() {
		return street2.orElse("");
	}

	public String getCity() {
		return city.orElse("");
	}

	public String getStateProvince() {
		return stateProvince.orElse("");
	}

	public String getPostalCode() {
		return postalCode.orElse("");
	}

	public String getCountry() {
		return country.orElse("");
	}

	public String getOwner() {
		return owner.orElse("");
	}

	public String getVersion() {
		return __v.orElse("");
	}

	public Contact toContact() {
		return new Contact(firstName.orElse(""), lastName.orElse(""), birthdate.orElse(""), email.orElse(""),
				phone.orElse(""), street1.orElse(""), street2.orElse(""), city.orElse(""),
				stateProvince.orElse(""), postalCode.orElse(""), country.orElse(""));
	}
}
