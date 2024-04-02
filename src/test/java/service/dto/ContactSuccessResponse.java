package service.dto;

import model.Contact;

import java.util.List;
import java.util.ArrayList;

/**
 * Represents a successful response from the Contact service.
 */
public class ContactSuccessResponse {

	private int statusCode;
	private List<ContactDto> contactDtos;

	public ContactSuccessResponse(int statusCode, ContactDto contactDto) {
		this.statusCode = statusCode;
		this.contactDtos = List.of(contactDto);
	}

	public ContactSuccessResponse(int statusCode, List<ContactDto> contactDtos) {
		this.statusCode = statusCode;
		this.contactDtos = contactDtos;
	}

	public int getStatusCode() {
		return statusCode;
	}

	/**
	 * Returns the first ContactDto object from the web service response.
	 * 
	 * @return the ContactDto object.
	 */
	public ContactDto getContactDto() {
		if (contactDtos.isEmpty()) {
			return null;
		}
		return contactDtos.get(0);
	}

	/**
	 * Returns the list of ContactDto objects from the web service response.
	 * 
	 * @return the list of ContactDto objects.
	 */
	public List<ContactDto> getContactDtos() {
		return new ArrayList<>(contactDtos);
	}

	/**
	 * Returns the the first Contact object from the web service response.
	 * 
	 * @return the Contact object.
	 */
	public Contact getContact() {
		if (contactDtos.isEmpty()) {
			return null;
		}
		return contactDtos.get(0).toContact();
	}

	/**
	 * Returns the list of Contact objects from the web service response.
	 * 
	 * @return the list of Contact objects.
	 */
	public List<Contact> getContacts() {
		List<Contact> contacts = new ArrayList<>();
		for (ContactDto contactDto : contactDtos) {
			contacts.add(contactDto.toContact());
		}
		return contacts;
	}
}