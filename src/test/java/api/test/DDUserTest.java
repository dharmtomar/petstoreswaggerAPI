package api.test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import api.endpoints.UserEndPoints;
import api.logger.Log;
import api.payloads.User;
import api.utilities.ExcelUtils;
import io.restassured.response.Response;

public class DDUserTest {

	@DataProvider
	public Object[][] getUserData() {
		return ExcelUtils.getUserData("userdata");
	}

	@DataProvider
	public Object[] getUserNames() {
		return ExcelUtils.getUserName("userdata");
	}

	@Test(dataProvider = "getUserData")
	public void createUserByExlfileTest(String userId,String userName,String firstName,String lastName,String emailId,String password,String phone) {
	
		User userpayload=new User();
		userpayload.setId(Integer.parseInt(userId));
		userpayload.setUsername(userName);
		userpayload.setFirstName(firstName);
		userpayload.setLastName(lastName);
		userpayload.setEmail(emailId);
		userpayload.setPassword(password);
		userpayload.setPhone(phone);
		
		Response response=UserEndPoints.createUser(userpayload);
		
		response.then().log().all();
		Log.info(response.asString());
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}
	@Test(priority = 2,dataProvider = "getUserNames")
	public void deleteUserByExlTest(String userName) {
		Response response=UserEndPoints.deleteUser(userName);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
	}

}
