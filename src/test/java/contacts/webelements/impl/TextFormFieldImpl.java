/**
 * This class represents the implementation of a text form field element.
 * It extends the WebElementFacadeImpl provided by Serenity and implements the TextFormField interface.
 * It provides methods to interact with and fill text form fields.
 */

package contacts.webelements.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import contacts.webelements.TextFormField;
import net.serenitybdd.core.pages.WebElementFacadeImpl;

public class TextFormFieldImpl extends WebElementFacadeImpl implements TextFormField {

	/**
	 * Constructs a new TextFormFieldImpl object.
	 *
	 * @param driver                        The WebDriver instance controlling the
	 *                                      browser.
	 * @param locator                       The ElementLocator that locates this
	 *                                      element on the page.
	 * @param implicitTimeoutInMilliseconds The implicit timeout for element
	 *                                      interactions in milliseconds.
	 */
	public TextFormFieldImpl(WebDriver driver, ElementLocator locator, long implicitTimeoutInMilliseconds) {
		super(driver, locator, implicitTimeoutInMilliseconds);
	}

	/**
	 * Fills the text form field with the specified value.
	 *
	 * @param value The value to be filled into the text form field.
	 */
	@Override
	public void fill(String value) {
		if (value != null && !getAttribute("value").equals(value)) {
			clear();
			sendKeys(value);
		}
	}
}
