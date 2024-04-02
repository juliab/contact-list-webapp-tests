package tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;

import java.util.stream.Stream;

import tests.datasource.DataSource;
import tests.datasource.ErrorContactDataSource;

import org.apache.http.HttpException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.internal.Either;

import service.UserService;
import service.dto.ContactErrorResponse;
import service.dto.ContactSuccessResponse;
import model.Contact;
import model.User;

/**
 * Negative tests for adding contacts using REST API.
 */
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SerenityJUnit5Extension.class)
public class AddContactRestNegativeTest {

	private ContactApiActions actions;
	private User user = User.generateTestUser();
	private DataSource datasource = new ErrorContactDataSource();
	private Either<ContactSuccessResponse, ContactErrorResponse> response;

	@BeforeAll
	public void createUser() throws HttpException {
		UserService.add(user);
	}

	@ParameterizedTest(name = "Should NOT be able to add a {0}")
	@MethodSource("contactsProvider")
	public void addContactFailure(String name, Contact contact, String message) throws HttpException {
		actions.iAmRegisteredInTheSystemAsUser(user);
		response = actions.iSendPostContactRequest(user, contact);
		actions.verifyThatResponseIsUnsuccessful(response, "Contact validation failed: " + message);
	}

	private Stream<Arguments> contactsProvider() {
		return datasource.get();
	}

	@AfterAll
	public void deleteUser() throws HttpException {
		UserService.delete(user);
	}
}
