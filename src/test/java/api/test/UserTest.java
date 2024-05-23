package api.test;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoints.UserEndPoints;
import api.payloads.User;
import io.restassured.response.Response;

public class UserTest {

	Faker faker;
	User userpayload;
	@BeforeClass
	public void setup() {
		faker=new Faker();
		userpayload=new User();
		
		userpayload.setId(faker.idNumber().hashCode());
		userpayload.setUsername(faker.name().username());
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		userpayload.setEmail(faker.internet().safeEmailAddress());
		userpayload.setPassword(faker.internet().password(5, 10));
		userpayload.setPhone(faker.phoneNumber().cellPhone());
	}
	
	@Test(priority = 1)
	public void createUserTest() {
		Response response= UserEndPoints.createUser(userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 2)
	public void getUserDetailsTest() {
		Response response=UserEndPoints.readUser(this.userpayload.getUsername());
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Test(priority = 3)
	public void updateUserDetailsTest() {
		//update user details
		userpayload.setFirstName(faker.name().firstName());
		userpayload.setLastName(faker.name().lastName());
		
		Response response=UserEndPoints.updateUser(this.userpayload.getUsername(),userpayload);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);  //testng assertion
		System.out.println("------------------");
		Response updateresponse=UserEndPoints.readUser(this.userpayload.getUsername());
		//response.then().log().body().statusCode(200);  //chai assertion by restassured
		response.then().log().body();
		Assert.assertEquals(updateresponse.getStatusCode(), 200);
		
	}
	
	@Test(priority=4)
	public void deleteUserTest() {
		System.out.println("-----------delete user-----");
		Response response=UserEndPoints.deleteUser(this.userpayload.getUsername());
		Assert.assertEquals(response.getStatusCode(), 200);
		response.then().log().all();
		
	}
}
