package contacts;

import org.apache.http.HttpException;

import contacts.model.User;
import contacts.service.UserService;

public class Tester {
	public static void main(String... args) throws HttpException {
		User user1 = new User("Nancy", "Jones", "NancyTJones@teleworm.us", "aeGh8dae2t");
		
		UserService userService = new UserService();
		
		
		System.out.println("Deleting user1");
		userService.loginUser(user1);
		userService.deleteUser(user1);
	}
}