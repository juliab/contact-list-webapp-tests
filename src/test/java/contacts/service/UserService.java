package contacts.service;

import contacts.model.User;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertNotNull;

import org.apache.http.HttpException;
import org.apache.http.HttpStatus;

public class UserService {
	
	public void addUser(User user) throws HttpException {
		
		baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		basePath = "/users";
		
		Response response = given()
				.contentType("application/json")
				.body(user).
			when().post();
		
		int statusCode = response.statusCode();
		ResponseBody<?> body = response.body();
		
		if (statusCode != HttpStatus.SC_CREATED) {
			throw new HttpException("Could not create user. Status code: " + statusCode +  ". Response body: " + body.asPrettyString());
		}
		
		String userToken = body.jsonPath().get("token").toString();
		user.setToken(userToken);
	}
	
	public void deleteUser(User user) throws HttpException {
		
		assertNotNull(user.getToken(), "User must have a token");
		
		baseURI = "https://thinking-tester-contact-list.herokuapp.com";
		basePath = "/users/me";
		
		Response response = given()
				.contentType("application/json")
				.header("Authorization", "Bearer " + user.getToken()).
			when().delete();
		
		int statusCode = response.statusCode();
		ResponseBody<?> body = response.body();
		
		if (statusCode != HttpStatus.SC_OK) {
			throw new HttpException("Could not delete user. Status code: " + statusCode +  ". Response body: " + body.asPrettyString());
		}
	}
}
