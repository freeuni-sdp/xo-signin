import static org.junit.Assert.*;

import java.security.NoSuchAlgorithmException;

import ge.edu.freeuni.sdp.xo.signin.data.Util;
import ge.edu.freeuni.sdp.xo.signin.data.json.EmailInfo;
import ge.edu.freeuni.sdp.xo.signin.data.json.SigninInfo;
import ge.edu.freeuni.sdp.xo.signin.data.json.UserInfo;
import ge.edu.freeuni.sdp.xo.signin.data.json.UsernameInfo;
import ge.edu.freeuni.sdp.xo.signin.service.SigninService;

import javax.ws.rs.core.Application;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.Test;

public class SigninServiceTest extends JerseyTest {

	@Override
	protected Application configure() {
		return new ResourceConfig(SigninService.class);
	}

	@Test
	public void testSignupWithUniqueGoodData() {
		/* Submitting data */
		SigninInfo signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com1");
		signInfo.setUsername("username1");
		signInfo.setPassword("password1");

		/* Returning response - should be OK */
		Response actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
		UserInfo returnedInfo = (UserInfo) actual.readEntity(UserInfo.class);
		assertEquals(signInfo.getEmail(), returnedInfo.getEmail());
		assertEquals(signInfo.getUsername(), returnedInfo.getUsername());

		/* --------------------------------------------------------- */

		/* Submitting data */
		signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com2");
		signInfo.setUsername("username2");
		signInfo.setPassword("password2");

		/* Returning response - should be OK */
		actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
		returnedInfo = (UserInfo) actual.readEntity(UserInfo.class);
		assertEquals(signInfo.getEmail(), returnedInfo.getEmail());
		assertEquals(signInfo.getUsername(), returnedInfo.getUsername());

		/* --------------------------------------------------------- */

		/* Submitting data */
		signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com3");
		signInfo.setUsername("username3");
		signInfo.setPassword("password3");

		/* Returning response - should be OK */
		actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
		returnedInfo = (UserInfo) actual.readEntity(UserInfo.class);
		assertEquals(signInfo.getEmail(), returnedInfo.getEmail());
		assertEquals(signInfo.getUsername(), returnedInfo.getUsername());
	}

	@Test
	public void testSignupWithMissingData() {
		/* Submitting data */
		SigninInfo signInfo = new SigninInfo();
		signInfo.setUsername("username");
		signInfo.setPassword("password");

		/* Returning response - should be BAD REQUEST */
		Response actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());

		/* --------------------------------------------------------- */

		/* Submitting data */
		signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com");
		signInfo.setPassword("password");

		/* Returning response - should be BAD REQUEST */
		actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());

		/* --------------------------------------------------------- */

		/* Submitting data */
		signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com");
		signInfo.setUsername("username");

		/* Returning response - should be BAD REQUEST */
		actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testSignupUserAlreadyRegistered() {
		/* Submitting data */
		SigninInfo signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com4");
		signInfo.setUsername("username4");
		signInfo.setPassword("password4");

		/* Returning response - should be OK */
		Response actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());

		/* User with given credentials should already exist, CONFLICT returned */
		actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.CONFLICT.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testConfirmEmailWithGoodToken() throws NoSuchAlgorithmException {
		/* Registering user */
		/* Submitting data */
		SigninInfo signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com5");
		signInfo.setUsername("username5");
		signInfo.setPassword("password5");

		/* Returning response - should be OK */
		Response actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());

		String realToken = Util.generateToken(signInfo.getEmail(),
				signInfo.getUsername());
		actual = target("/confirm_email/" + realToken).request().get();
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());

		UserInfo returnedInfo = (UserInfo) actual.readEntity(UserInfo.class);
		assertEquals(signInfo.getEmail(), returnedInfo.getEmail());
		assertEquals(signInfo.getUsername(), returnedInfo.getUsername());
	}

	@Test
	public void testConfirmEmailWithBadToken() throws NoSuchAlgorithmException {
		/* Registering user */
		/* Submitting data */
		SigninInfo signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com6");
		signInfo.setUsername("username6");
		signInfo.setPassword("password6");

		/* Returning response - should be OK */
		Response actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());

		/* Generating WRONG token */
		String wrongToken = Util.generateToken(signInfo.getUsername(),
				signInfo.getEmail());
		actual = target("/confirm_email/" + wrongToken).request().get();
		assertEquals(Response.Status.NOT_FOUND.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testPasswordRecoveryWithCorrectUsername() {
		/* Registering user */
		/* Submitting data */
		SigninInfo signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com7");
		signInfo.setUsername("username7");
		signInfo.setPassword("password7");

		/* Returning response - should be OK */
		Response actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());

		/* Asking for password recovery with given registered username */
		UsernameInfo usernameInfo = new UsernameInfo();
		usernameInfo.setUsername(signInfo.getUsername());

		actual = target("/recover_password").request().post(
				Entity.entity(usernameInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
	}

	@Test
	public void testPasswordRecoveryWithWrongUsername() {
		/* Registering user */
		/* Submitting data */
		SigninInfo signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com8");
		signInfo.setUsername("username8");
		signInfo.setPassword("password8");

		/* Returning response - should be OK */
		Response actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());

		/* Asking for password recovery with given wrong username */
		UsernameInfo usernameInfo = new UsernameInfo();
		usernameInfo.setUsername(signInfo.getUsername() + signInfo.getEmail());

		actual = target("/recover_password").request().post(
				Entity.entity(usernameInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

	@Test
	public void testUsernameRecoveryWithCorrectEmail() {
		/* Registering user */
		/* Submitting data */
		SigninInfo signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com9");
		signInfo.setUsername("username9");
		signInfo.setPassword("password9");

		/* Returning response - should be OK */
		Response actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());

		/* Asking for username recovery with given registered email */
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setEmail(signInfo.getEmail());

		actual = target("/recover_username").request().post(
				Entity.entity(emailInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());
	}

	@Test
	public void testUsernameRecoveryWithWrongEmail() {
		/* Registering user */
		/* Submitting data */
		SigninInfo signInfo = new SigninInfo();
		signInfo.setEmail("name@domain.com10");
		signInfo.setUsername("username10");
		signInfo.setPassword("password10");

		/* Returning response - should be OK */
		Response actual = target("/signup").request().post(
				Entity.entity(signInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.OK.getStatusCode(), actual.getStatus());

		/* Asking for username recovery with given wrong email */
		EmailInfo emailInfo = new EmailInfo();
		emailInfo.setEmail(signInfo.getEmail() + signInfo.getUsername());

		actual = target("/recover_username").request().post(
				Entity.entity(emailInfo, MediaType.APPLICATION_JSON));
		assertEquals(Response.Status.BAD_REQUEST.getStatusCode(),
				actual.getStatus());
	}

}
