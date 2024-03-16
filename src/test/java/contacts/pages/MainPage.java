package contacts.pages;

import org.openqa.selenium.support.FindBy;

import contacts.model.User;
import net.serenitybdd.annotations.DefaultUrl;
import net.serenitybdd.core.pages.WebElementFacade;

@DefaultUrl(BasePage.BASE_URL)
public class MainPage extends BasePage<MainPage> {
	
	public static final String PAGE_TITLE = "Contact List App";
	
	@FindBy(id = "email")
	WebElementFacade emailField;
	
	@FindBy(id = "password")
	WebElementFacade passwordField;
	
	@FindBy(id = "submit")
	WebElementFacade submitButton;

	@Override
	protected String pageTitle() {
		return PAGE_TITLE;
	}
	
	public MainPage login(User user) {
		emailField.sendKeys(user.getEmail());
		passwordField.sendKeys(user.getPassword());
		submitButton.click();
		return this;
	}

	@Override
	protected MainPage self() {
		return this;
	}
}
