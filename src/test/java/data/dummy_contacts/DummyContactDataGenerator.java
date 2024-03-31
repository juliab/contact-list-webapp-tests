package data.dummy_contacts;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import data.ContactDataGenerator;
import model.Contact;

/**
 * This class is responsible for loading contacts from a JSON file and providing
 * a random contact by request.
 * This class is a singleton.
 */
public class DummyContactDataGenerator implements ContactDataGenerator {

    // Path to the JSON file with contacts
    private final static String CONTACTS_FILE_PATH = "src/test/java/data/dummy_contacts/dummy_contacts.json";

    // List of contacts loaded from the JSON file
    private List<Contact> contacts;

    // Singleton instance of the class
    private static DummyContactDataGenerator instance;

    // Private constructor to prevent instantiation
    private DummyContactDataGenerator() {
        try {
            contacts = loadContactsFromFile(CONTACTS_FILE_PATH);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Can't load contacts from " + CONTACTS_FILE_PATH + " file.");
        }
    }

    /**
     * Returns the singleton instance of the class.
     * 
     * @return the singleton instance of the class.
     */
    public static DummyContactDataGenerator getInstance() {
        if (instance == null) {
            synchronized (DummyContactDataGenerator.class) {
                if (instance == null) {
                    instance = new DummyContactDataGenerator();
                }
            }
        }
        return instance;
    }

    /**
     * Loads contacts from a JSON file.
     * 
     * @param filePath the name of the file to load contacts from.
     * @return the list of contacts loaded from the file.
     * @throws IOException if an I/O error occurs.
     */
    private static List<Contact> loadContactsFromFile(String filePath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File(filePath), new TypeReference<List<Contact>>() {
        });
    }

    /**
     * Generates a random contact from the list of contacts.
     * After generating a contact, it is removed from the list.
     * 
     * @return a random contact.
     * @throws RuntimeException if the list of contacts is empty.
     */
    @Override
    public Contact nextRandomContact() {
        if (contacts == null || contacts.isEmpty()) {
            throw new RuntimeException("Unable to generate unique contact");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(contacts.size());
        return contacts.remove(randomIndex);
    }
}