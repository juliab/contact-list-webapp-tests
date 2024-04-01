package tests;

import net.serenitybdd.junit5.SerenityJUnit5Extension;

import java.util.stream.Stream;

import org.apache.http.HttpException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import service.UserService;
import tests.datasource.DataSource;
import tests.datasource.SuccessContactDataSource;
import model.Contact;
import model.User;

/**
 * Positive tests for adding a contact using REST API.
 */
@TestInstance(Lifecycle.PER_CLASS)
@ExtendWith(SerenityJUnit5Extension.class)
public class AddContactRestPositiveTest {

	private ContactApiActions actions;
	private User user = User.generateTestUser();
	private DataSource datasource = new SuccessContactDataSource();

	@BeforeAll
	public void createUser() throws HttpException {
		UserService.add(user);
	}

	@ParameterizedTest(name = "Should be able to add a {0}")
	@MethodSource("contactsProvider")
	public void addContactSuccessfully(String name, Contact contact) throws HttpException {
		actions.iAmRegisteredInTheSystemAsUser(user);
		actions.iAddNewContactIntoMyContactList(contact);
		actions.iGetSuccessInReturnAndResponseBodyContainsValidData();
	}

	private Stream<Arguments> contactsProvider() {
		return datasource.get();
	}

	@AfterAll
	public void deleteUser() throws HttpException {
		UserService.delete(user);
	}
}
