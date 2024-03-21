/**
 * This interface represents a text form field element on a web page.
 * It extends the WebElementFacade interface provided by Serenity.
 * Implementations of this interface should provide functionality to interact with text form fields.
 */

package contacts.webelements;

import contacts.webelements.impl.TextFormFieldImpl;
import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;

@ImplementedBy(TextFormFieldImpl.class)
public interface TextFormField extends WebElementFacade {

	/**
	 * Fills the text form field with the specified value.
	 *
	 * @param value The value to be filled into the text form field.
	 */
	void fill(String value);
}
