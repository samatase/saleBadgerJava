package app.saleBadger;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;
import static org.hamcrest.CoreMatchers.*;
import static org.mockito.Mockito.*;

import javax.ws.rs.core.MediaType;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.BeforeClass;
import org.junit.Test;

import app.model.User;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class UserResourceTest {

	private static HttpServer httpServer;
	private final String BASE_URL = "http://localhost:8181/api/v1/";

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		// run the httpserver
		httpServer = Main.startServer();

	}

	protected void tearDown() throws Exception {

		httpServer.stop();
	}

	private void assertResponseCode(String requestURL, int responseCode) {
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(requestURL);
			ClientResponse response = webResource.accept(
					MediaType.APPLICATION_JSON).get(ClientResponse.class);

			assertThat(response.getStatus(), is(responseCode));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void getUserResourceAndCheckResponseCode() {
		String requestURL = BASE_URL + "/users/lunayo";
		assertResponseCode(requestURL, 200);

	}

	@Test
	public void getUserResourceWithInvalidUsername() {
		String requestURL = BASE_URL + "/users/lun";
		assertResponseCode(requestURL, 404);
	}

	@Test
	public void addUserToResourceAndCheckResponseCode() {
		String requestURL = BASE_URL + "/users";
		try {
			User user = mock(User.class);
			Client client = Client.create();
			WebResource webResource = client.resource(requestURL);
			ClientResponse response = webResource.accept(
					MediaType.APPLICATION_JSON).put(ClientResponse.class);

			assertThat(response.getStatus(), is(200));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void deleteUserFromResourceAndCheckResponseCode() {
		String requestURL = BASE_URL + "/users/lunayo";
		this.assertResponseCode(requestURL, 204);
	}

	@Test
	public void updateUserInResourceAndCheckResponseCode() {
		String requestURL = BASE_URL + "/users/lunayo";
		try {

			Client client = Client.create();
			WebResource webResource = client.resource(requestURL);
			ClientResponse response = webResource.accept(
					MediaType.APPLICATION_JSON).put(ClientResponse.class);

			assertThat(response.getStatus(), is(200));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}