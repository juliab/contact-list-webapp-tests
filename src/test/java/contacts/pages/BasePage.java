package contacts.pages;

import net.serenitybdd.core.pages.PageObject;

/**
 * Base class for page objects in the contact list application.
 * Provides common functionality and methods shared among page objects.
 * 
 * @param <T> The type of the page object extending BasePage.
 */
public abstract class BasePage<T extends BasePage<T>> extends PageObject {
	
	/**
     * The base URL of the contact list application.
     */
	public final static String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";
	
	/**
     * Abstract method to return the instance of the current page.
     * Each subclass must implement this method to return its own instance.
     * 
     * @return The instance of the current page.
     */
	protected abstract T self();
	
	/**
     * Abstract method to get the expected title of the current page.
     * Each subclass must implement this method to provide its own page title.
     * 
     * @return The title of the current page.
     */
	protected abstract String pageTitle();
	
    /**
     * Checks if the current page is open by comparing its title with the expected page title.
     * 
     * @return True if the current page is open, otherwise false.
     */
	public Boolean isOpen() {
		return getTitle().equals(pageTitle());
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
}
