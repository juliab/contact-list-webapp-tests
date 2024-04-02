package ui.webelements;

import net.serenitybdd.core.annotations.ImplementedBy;
import net.serenitybdd.core.pages.WebElementFacade;
import ui.webelements.impl.ReadOnlyFieldImpl;

/**
 * This interface represents a field that contains text data. These fields are read-only.
 * It extends the WebElementFacade interface provided by Serenity.
 * Implementations of this interface should provide functionality to interact with the fields.
 */
@ImplementedBy(ReadOnlyFieldImpl.class)
public interface ReadOnlyFIeld extends WebElementFacade {

	/**
	 * Reads the text from the field.
	 *
	 * @return The text read from the field.
	 */
	String read();
}
