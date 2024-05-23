package api.endpoints;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payloads.User;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//created for perform create,read,update,delete requests for user API
public class UserEndPoints2 {

	static ResourceBundle getURL(){
		ResourceBundle route=ResourceBundle.getBundle("routes");
		return  route;
	}
	public static Response createUser(User payload)
	{
		Response response=	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.body(payload)
			.when()
		.post(getURL().getString("post_url"));
		return response;
	}
	public static Response readUser(String userName)
	{
		Response response=	given()
				.pathParam("username", userName)
			.when()
		.get(getURL().getString("get_url"));
		return response;
	}
	public static Response updateUser(String userName,User payload)
	{
		Response response=	given()
		.contentType(ContentType.JSON)
		.accept(ContentType.JSON)
		.pathParam("username", userName)
		.body(payload)
			.when()
		.put(getURL().getString("update_url"));
		return response;
	}
	public static Response deleteUser(String username)
	{
		Response response=	given()
				.pathParam("username", username)
			.when()
		.delete(getURL().getString("delete_url"));
		return response;
	}
}
