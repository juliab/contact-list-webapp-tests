package contacts.pages;

import net.serenitybdd.core.pages.PageObject;

public abstract class BasePage<T extends BasePage<T>> extends PageObject {
	
	public final static String BASE_URL = "https://thinking-tester-contact-list.herokuapp.com";
	
	protected abstract T self();
	
	protected abstract String pageTitle();

    public String title() {
        return getTitle();
    }
	
	public Boolean isOpen() {
		return getTitle().equals(pageTitle());
	}
	
	public T navigate() {
		open();
		return self();
	}
}
