package contacts;

import org.apache.http.HttpException;

import contacts.model.User;
import contacts.service.UserService;

public class Tester {
	public static void main(String[] args) throws HttpException {
		UserService service = new UserService();
		
		User user = new User("", "");
		user.setToken("blsdlkfjskdf");
		service.deleteUser(user);
	}
}
