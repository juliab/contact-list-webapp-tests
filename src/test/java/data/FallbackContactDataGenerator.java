package data;

import data.dummy_contacts.DummyContactDataGenerator;
import data.random_user_api.RandomUserApiDataGenerator;
import model.Contact;
import utils.Logger;

/**
 * This class is responsible for providing a random contact by request. It uses
 * the primary generator to get a random contact. If the primary generator fails
 * to provide a contact, the fallback generator is used.
 */
public class FallbackContactDataGenerator implements ContactDataGenerator {

    private ContactDataGenerator primaryGenerator;
    private ContactDataGenerator fallbackGenerator;

    public FallbackContactDataGenerator() {
        this.primaryGenerator = RandomUserApiDataGenerator.getInstance();
        this.fallbackGenerator = DummyContactDataGenerator.getInstance();
    }

    /**
     * Returns a random contact using the primary generator. If the primary
     * generator fails to provide a contact, the fallback generator is used.
     *
     * @return a random contact.
     */
    @Override
    public Contact nextRandomContact() {
        try {
            return primaryGenerator.nextRandomContact();
        } catch (Exception e) {
            Logger.log("Test data info", "Primary generator failed to provide a contact. Using fallback generator.");
            return fallbackGenerator.nextRandomContact();
        }
    }
}
