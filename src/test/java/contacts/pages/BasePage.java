package contacts.pages;

import net.serenitybdd.core.pages.PageObject;

public abstract class BasePage extends PageObject {
	
	public final static String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";
	
	protected abstract String pageTitle();

    public String title() {
        return getTitle();
    }
	
	public Boolean isOpen() {
		return getTitle().equals(pageTitle());
	}
}
