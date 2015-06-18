package ge.edu.freeuni.sdp.xo.signin.service;

import java.security.NoSuchAlgorithmException;

import ge.edu.freeuni.sdp.xo.signin.data.*;
import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.UserInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.json.SigninInfo;
import ge.edu.freeuni.sdp.xo.signin.data.json.UserInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

import com.microsoft.azure.storage.StorageException;

@Path("")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class SigninService {

	@Context
	UriInfo uriInfo;

	public Repository getRepository() {
		return FakeRepositoryFactory.createInMemoryRepository();
	}

	@POST
	@Path("/signup")
	public UserInfo registerUser(SigninInfo info) throws StorageException,
			NoSuchAlgorithmException {
		/* If user has not entered required data */
		if (info.getEmail() == null || info.getUsername() == null
				|| info.getPassword() == null)
			throw new WebApplicationException(Status.BAD_REQUEST);

		/* Checking if following user credentials are already used */
		boolean emailUsed = (getRepository().findByEmail(info.getEmail()) != null);
		boolean usernameUsed = (getRepository().findByUsername(
				info.getUsername()) != null);

		/*
		 * If returns true, user with following e-mail and/or username already
		 * exists
		 */
		if (emailUsed || usernameUsed)
			throw new WebApplicationException(Status.CONFLICT);

		/* Register new user */
		SignInInfoEntity entity = SignInInfoEntity.fromSignInfo(info);
		getRepository().insertOrUpdate(entity);

		/* Save e-mail activation token */
		getRepository().insertToken(Util.generateToken(info.getEmail()),
				info.getEmail());

		/* Returning registered user info */
		UserInfo uInfo = new UserInfo();
		uInfo.setEmail(info.getEmail());
		uInfo.setUsername(info.getUsername());

		return uInfo;
	}

	@GET
	@Path("/confirm_email/{token}")
	public UserInfo confirmEmail(@PathParam("token") String token) {
		/* Check if such e-mail verifier token exists */
		if (!getRepository().hasToken(token))
			throw new WebApplicationException(Status.NOT_FOUND);

		/* Get associated account to be activated */
		UserInfoEntity entity = getRepository().findForToken(token);

		/* Verifying e-mail */
		getRepository().deleteToken(token);

		/* Returning verified user info */
		return entity.userInfo();
	}

	@POST
	@Path("/recover_password")
	public Response recoverPassword(@PathParam("username") String username) {
		UserInfoEntity entity = getRepository().findByUsername(username);
		if (entity == null)
			return Response.status(Status.BAD_REQUEST).build();

		// SEND EMAIL WITH PASSWORD HERE

		return Response.ok().build();
	}

}
