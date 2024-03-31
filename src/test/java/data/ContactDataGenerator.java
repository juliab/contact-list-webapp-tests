package data;

import model.Contact;

/**
 * This interface is responsible for generating contact test data.
 */
public interface ContactDataGenerator {

	/**
	 * Returns a random contact.
	 * 
	 * @return a random contact.
	 */
	Contact nextRandomContact();

}
