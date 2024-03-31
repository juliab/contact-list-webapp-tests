package data.random_user_api;

import java.io.IOException;
import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

import data.ContactDataGenerator;
import model.Contact;

/**
 * This class is responsible for generating random contacts using the Random
 * User API.
 * This class is a singleton.
 */
public class RandomUserApiDataGenerator implements ContactDataGenerator {

    // URL of the Random User API
    private static String RANDOM_USER_API_URL = "https://randomuser.me/api";

    // Singleton instance
    private static RandomUserApiDataGenerator instance;

    // Private constructor to prevent instantiation
    private RandomUserApiDataGenerator() {
        // Initialize any necessary variables or resources
    }

    /**
     * Returns the singleton instance of RandomUserApiDataGenerator.
     *
     * @return the singleton instance of RandomUserApiDataGenerator.
     */
    public static RandomUserApiDataGenerator getInstance() {
        if (instance == null) {
            synchronized (RandomUserApiDataGenerator.class) {
                if (instance == null) {
                    instance = new RandomUserApiDataGenerator();
                }
            }
        }
        return instance;
    }

    /**
     * Returns a random contact using the Random User API.
     *
     * @return a random contact.
     */
    @Override
    public Contact nextRandomContact() {
        ObjectMapper mapper = new ObjectMapper();
        RandomUserResponseDto response;
        try {
            response = mapper.readValue(new URL(RANDOM_USER_API_URL), RandomUserResponseDto.class);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Random User Api is not available");
        }
        RandomUserDto randomUser = response.getResults()[0];
        return randomUser.toContact();
    }

}
