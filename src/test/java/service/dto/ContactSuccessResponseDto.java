package service.dto;

import model.Contact;

/**
 * Represents a successful response from the Contact service.
 */
public class ContactSuccessResponseDto {

	private int statusCode;
	private ContactDto contactDto;

	public ContactSuccessResponseDto(int statusCode, ContactDto contactDto) {
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