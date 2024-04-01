package tests.datasource;

import java.util.stream.Stream;

import org.junit.jupiter.params.provider.Arguments;

import data.ContactDataGenerator;
import data.FallbackContactDataGenerator;
import model.Contact;
import utils.AppContstants;

import static org.junit.jupiter.params.provider.Arguments.arguments;

/**
 * Data source for a valid contact data.
 */
public class SuccessContactDataSource implements DataSource {

    @Override
    public Stream<Arguments> get() {
        ContactDataGenerator contactDataGenerator = new FallbackContactDataGenerator();
        Contact validContact = contactDataGenerator.nextRandomContact()
                .setStreet2("Apt. 100");
        Contact emptyBirthdateContact = contactDataGenerator
                .nextRandomContact().setBirthdate("");
        Contact emptyEmailContact = contactDataGenerator.nextRandomContact()
                .setEmail("");
        Contact emptyPhoneContact = contactDataGenerator.nextRandomContact()
                .setPhone("");
        Contact emptyStreet1Contact = contactDataGenerator.nextRandomContact()
                .setStreet1("");
        Contact emptyCityContact = contactDataGenerator.nextRandomContact()
                .setCity("");
        Contact emptyStateContact = contactDataGenerator.nextRandomContact()
                .setStateProvince("");
        Contact emptyPostCodeContact = contactDataGenerator.nextRandomContact()
                .setPostalCode("");
        Contact emptyCountryContact = contactDataGenerator.nextRandomContact()
                .setCountry("");
        Contact shortFirstNameContact = contactDataGenerator.nextRandomContact()
                .setFirstName("A");
        Contact maxLengthFirstNameContact = contactDataGenerator.nextRandomContact()
                .setFirstName("A".repeat(AppContstants.MAX_NAME_LENGTH));
        Contact firstNameIncludesNumbersContact = contactDataGenerator.nextRandomContact()
                .setFirstName("John123");
        Contact firstNameIncludesSpecialCharsContact = contactDataGenerator.nextRandomContact()
                .setFirstName("John!@#$%^&*()");
        Contact shortLastNameContact = contactDataGenerator.nextRandomContact()
                .setLastName("A");
        Contact maxLengthLastNameContact = contactDataGenerator.nextRandomContact()
                .setLastName("A".repeat(AppContstants.MAX_NAME_LENGTH));
        Contact lastNameIncludesNumbersContact = contactDataGenerator.nextRandomContact()
                .setLastName("Doe123");
        Contact lastNameIncludesSpecialCharsContact = contactDataGenerator.nextRandomContact()
                .setLastName("Doe!@#$%^&*()");
        Contact upperCaseLettersEmailContact = contactDataGenerator.nextRandomContact()
                .setEmail("Test@Example.Com");
        Contact subdomainEmailContact = contactDataGenerator.nextRandomContact()
                .setEmail("test@subdomain.example.com");
        Contact numericEmailContact = contactDataGenerator.nextRandomContact()
                .setEmail("123@example.com");
        Contact emailIncludesDotContact = contactDataGenerator.nextRandomContact()
                .setEmail("test.email@example.com");
        Contact emailIncludesHyphenContact = contactDataGenerator.nextRandomContact()
                .setEmail("test-email@example.com");

        return Stream.of(
                arguments("valid contact", validContact),
                arguments("contact with an empty birthdate", emptyBirthdateContact),
                arguments("contact with an empty email", emptyEmailContact),
                arguments("contact with an empty phone", emptyPhoneContact),
                arguments("contact with an empty street line 1", emptyStreet1Contact),
                arguments("contact with an empty city", emptyCityContact),
                arguments("contact with an empty state/province", emptyStateContact),
                arguments("contact with an empty postal code", emptyPostCodeContact),
                arguments("contact with an empty country", emptyCountryContact),
                arguments("contact with a short first name", shortFirstNameContact),
                arguments("contact with a maximum length first name", maxLengthFirstNameContact),
                arguments("contact whose first name includes numbers", firstNameIncludesNumbersContact),
                arguments("contact whose first name includes special characters", firstNameIncludesSpecialCharsContact),
                arguments("contact with a short last name", shortLastNameContact),
                arguments("contact with a maximum length last name", maxLengthLastNameContact),
                arguments("contact whose last name includes numbers", lastNameIncludesNumbersContact),
                arguments("contact whose last name includes special characters", lastNameIncludesSpecialCharsContact),
                arguments("contact with uppercase letters in the email", upperCaseLettersEmailContact),
                arguments("contact with a subdomain in the email", subdomainEmailContact),
                arguments("contact whose email includes numbers", numericEmailContact),
                arguments("contact whose email includes a dot", emailIncludesDotContact),
                arguments("contact whose email includes a hyphen", emailIncludesHyphenContact));
    }
}
