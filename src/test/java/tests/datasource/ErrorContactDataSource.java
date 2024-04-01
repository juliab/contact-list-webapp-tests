package tests.datasource;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import data.ContactDataGenerator;
import data.FallbackContactDataGenerator;
import model.Contact;
import utils.AppContstants;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Data source for contact data with errors.
 */
public class ErrorContactDataSource implements DataSource {

    @Override
    public Stream<Arguments> get() {
        ContactDataGenerator contactDataGenerator = new FallbackContactDataGenerator();
        Contact emptyFirstNameContact = contactDataGenerator.nextRandomContact()
				.setFirstName("");
		Contact emptyLastNameContact = contactDataGenerator.nextRandomContact()
				.setLastName("");
		Contact longFirstNameContact = contactDataGenerator.nextRandomContact()
				.setFirstName("a".repeat(AppContstants.MAX_NAME_LENGTH + 1));
		Contact longLastNameContact = contactDataGenerator.nextRandomContact()
				.setLastName("a".repeat(AppContstants.MAX_NAME_LENGTH + 1));
		Contact invalidBirthdateContact = contactDataGenerator.nextRandomContact()
				.setBirthdate("2020-02-30");
		Contact wrongFormatBirthdateContact = contactDataGenerator.nextRandomContact()
				.setBirthdate("2020/02/30");
        Contact invalidEmailContact = contactDataGenerator.nextRandomContact()
                .setEmail("test.example.com");
        Contact invalidPhoneContact = contactDataGenerator.nextRandomContact()
                .setPhone("543-667-324234");
        Contact longStateContact = contactDataGenerator.nextRandomContact()
                .setStateProvince("a".repeat(AppContstants.MAX_STATE_LENGTH + 1));

		return Stream.of(
				arguments("contact with an empty first name", emptyFirstNameContact,
						"firstName: Path `firstName` is required."),
				arguments("contact with an empty last name", emptyLastNameContact,
						"lastName: Path `lastName` is required."),
				arguments("contact with a long first name", longFirstNameContact,
						"firstName: Path `firstName` (`" + longFirstNameContact.getFirstName()
								+ "`) is longer than the maximum allowed length (" + AppContstants.MAX_NAME_LENGTH
								+ ")."),
				arguments("contact with a long last name", longLastNameContact,
						"lastName: Path `lastName` (`" + longLastNameContact.getLastName()
								+ "`) is longer than the maximum allowed length (" + AppContstants.MAX_NAME_LENGTH
								+ ")."),
				arguments("contact with an invalid birthdate", invalidBirthdateContact, "birthdate: Birthdate is invalid"),
				arguments("contact with a wrong format birthdate", wrongFormatBirthdateContact,
						"birthdate: Birthdate is invalid"),
                arguments("contact with an invalid email", invalidEmailContact,
                        "email: Email is invalid"),
                arguments("contact with an invalid phone number", invalidPhoneContact,
                        "phone: Phone number is invalid"),
                arguments("contact with a long state", longStateContact,
                        "stateProvince: Path `stateProvince` (`" + longStateContact.getStateProvince()
                                + "`) is longer than the maximum allowed length (" + AppContstants.MAX_STATE_LENGTH
                                + ").")
			);
    }
}