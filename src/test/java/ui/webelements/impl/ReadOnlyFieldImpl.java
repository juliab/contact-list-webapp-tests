package ui.webelements.impl;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.pagefactory.ElementLocator;

import net.serenitybdd.core.pages.WebElementFacadeImpl;
import ui.webelements.ReadOnlyFIeld;

/**
 * This class represents the implementation of a data field element.
 * It extends the WebElementFacadeImpl provided by Serenity and implements the
 * DataField interface.
 * It provides method to read a value of a data field.
 */
public class ReadOnlyFieldImpl extends WebElementFacadeImpl implements ReadOnlyFIeld {

	/**
	 * Constructs a new DataFieldImpl object.
	 *
	 * @param driver                        The WebDriver instance controlling the
	 *                                      browser.
	 * @param locator                       The ElementLocator that locates this
	 *                                      element on the page.
	 * @param implicitTimeoutInMilliseconds The implicit timeout for element
	 *                                      interactions in milliseconds.
	 */
	public ReadOnlyFieldImpl(WebDriver driver, ElementLocator locator, long implicitTimeoutInMilliseconds) {
		super(driver, locator, implicitTimeoutInMilliseconds);
	}

	/**
	 * Reads the text from the field.
	 *
	 * @return The text read from the field.
	 */
	@Override
	public String read() {
		return isVisible() ? getText() : "";
	}
}
