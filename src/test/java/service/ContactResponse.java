package service;

import model.Contact;
import service.dto.ContactDto;

/**
 * Represents a response from the Contact service.
 */
public class ContactResponse {

	private int statusCode;
	private ContactDto contactDto;

	public ContactResponse(int statusCode, ContactDto contactDto) {
		this.statusCode = statusCode;
		this.contactDto = contactDto;
	}

	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Returns the ContactDto object that represents the contact data.
	 * 
	 * @return the ContactDto object.
	 */
	public ContactDto getContactDto() {
		return contactDto;
	}

	/**
	 * Returns the Contact object that represents the contact data.
	 * 
	 * @return the Contact object.
	 */
	public Contact getContact() {
		return contactDto.toContact();
	}
}