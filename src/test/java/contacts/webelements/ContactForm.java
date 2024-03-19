package contacts.webelements;

import contacts.model.Contact;
import contacts.webelements.impl.ContactFormImpl;
import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WidgetObject;

/**
 * This interface represents Add/Edit Contact from.
 */
@ImplementedBy(ContactFormImpl.class)
public interface ContactForm extends WidgetObject {
	
	/**
     * Fills in the contact details in the corresponding fields on the page.
     * 
     * @param contact The Contact object containing the details to be filled in.
     */
	PageObject fillContactDetails(Contact contact);
}
