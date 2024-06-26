package ui.pages;

import org.openqa.selenium.support.FindBy;

import net.serenitybdd.core.pages.PageObject;
import net.serenitybdd.core.pages.WebElementFacade;

/**
 * Base class for page objects in the contact list application. Provides common
 * functionality and methods shared among page objects.
 * 
 * @param <T> The type of the page object extending BasePage.
 */
public abstract class BasePage<T extends BasePage<T>> extends PageObject {
	
	@FindBy(id = "error")
	protected WebElementFacade errorMessage;

	/**
	 * Abstract method to return the instance of the current page. Each subclass
	 * must implement this method to return its own instance.
	 * 
	 * @return The instance of the current page.
	 */
	protected abstract T self();

	/**
	 * Abstract method to get the expected title of the current page. Each subclass
	 * must implement this method to provide its own page title.
	 * 
	 * @return The title of the current page.
	 */
	protected abstract String getPageTitle();

	/**
	 * Checks if the current page is open by comparing its title with the expected
	 * page title.
	 * 
	 * @return True if the current page is open, otherwise false.
	 */
	public Boolean isOpen() {
		return getTitle().equals(getPageTitle());
	}

	/**
	 * Navigates to the URL of the current page.
	 * 
	 * @return The instance of the current page.
	 */
	public T navigate() {
		open();
		return self();
	}

	/**
	 * Waits until the page is loaded.
	 * 
	 * @return The instance of the current page.
	 */
	public T waitForLoad() {
		waitFor((d) -> self().isOpen());
		return self();
	}

	/**
	 * Accepts the currently displayed alert dialog.
	 * 
	 * @return An instance of the current page.
	 */
	public T acceptAlert() {
		getAlert().accept();
		return self();
	}
	

	/**
	 * Reads error message on the page.
	 * 
	 * @return Error message text.
	 */
	public String readErrorMessage() {
		return errorMessage.getText();
	}
}
