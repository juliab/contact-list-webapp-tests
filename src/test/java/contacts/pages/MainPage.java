package contacts.pages;

import org.openqa.selenium.support.FindBy;

import contacts.model.User;
import net.serenitybdd.core.pages.WebElementFacade;

public class MainPage extends BasePage {
	
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
	
	public void login(User user) {
		emailField.sendKeys(user.email());
		passwordField.sendKeys(user.password());
		submitButton.click();
	}
}
