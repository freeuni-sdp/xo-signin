package ge.edu.freeuni.sdp.xo.signin.service;

import java.security.NoSuchAlgorithmException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.microsoft.azure.storage.StorageException;

import ge.edu.freeuni.sdp.xo.signin.data.Repository;
import ge.edu.freeuni.sdp.xo.signin.data.RepositoryFactory;
import ge.edu.freeuni.sdp.xo.signin.data.Util;
import ge.edu.freeuni.sdp.xo.signin.data.entity.SignInInfoEntity;
import ge.edu.freeuni.sdp.xo.signin.data.entity.TokenEntity;
import ge.edu.freeuni.sdp.xo.signin.data.json.EmailInfo;
import ge.edu.freeuni.sdp.xo.signin.data.json.SigninInfo;
import ge.edu.freeuni.sdp.xo.signin.data.json.Token;
import ge.edu.freeuni.sdp.xo.signin.data.json.UserInfo;
import ge.edu.freeuni.sdp.xo.signin.data.json.UsernameInfo;

@Path("")
@Consumes({ MediaType.APPLICATION_JSON })
@Produces({ MediaType.APPLICATION_JSON })
public class SigninService {

	@Context
	UriInfo uriInfo;

	public Repository getRepository() {
		return RepositoryFactory.getInMemoryRepository();
	}

	@POST
	@Path("signup")
	public UserInfo registerUser(SigninInfo info) throws StorageException, NoSuchAlgorithmException {
		/* If user has not entered required data */
		if (info.getEmail() == null || info.getUsername() == null || info.getPassword() == null)
			throw new WebApplicationException(Status.BAD_REQUEST);

		/* Checking if following user credentials are already used */
		boolean emailUsed = (getRepository().findByEmail(info.getEmail()) != null);
		boolean usernameUsed = (getRepository().findByUsername(info.getUsername()) != null);

		/*
		 * If returns true, user with following e-mail and/or username already
		 * exists
		 */
		if (emailUsed || usernameUsed)
			throw new WebApplicationException(Status.CONFLICT);

		/* Register new user */
		SignInInfoEntity entity = SignInInfoEntity.fromSignInfo(info);
		getRepository().insertOrUpdateSignInfo(entity);

		/* Save e-mail activation token */
		Token tok = new Token();
		tok.setToken(Util.generateToken(info.getEmail(), info.getUsername()));

		UsernameInfo uinfo = new UsernameInfo();
		uinfo.setUsername(info.getUsername());

		TokenEntity tokEntity = TokenEntity.fromToken(tok, uinfo);
		getRepository().insertOrUpdateToken(tokEntity);

		/* Returning registered user info */
		UserInfo uInfo = new UserInfo();
		uInfo.setEmail(info.getEmail());
		uInfo.setUsername(info.getUsername());

		return uInfo;
	}

	@GET
	@Path("confirm_email/{token}")
	public UserInfo confirmEmail(@PathParam("token") String token) {
		/* Check if such e-mail verifier token exists */
		if (!getRepository().hasToken(token))
			throw new WebApplicationException(Status.NOT_FOUND);

		/* Get associated account to be activated */
		SignInInfoEntity entity = getRepository().findForToken(token);

		/* Verifying e-mail */
		getRepository().deleteToken(token);

		/* Returning verified user info */
		SigninInfo sinfo = entity.signInfo();
		UserInfo info = new UserInfo();
		info.setUsername(sinfo.getUsername());
		info.setEmail(sinfo.getEmail());
		return info;
	}

	@POST
	@Path("recover_password")
	public Response recoverPassword(UsernameInfo info) throws StorageException {
		SignInInfoEntity entity = getRepository().findByUsername(info.getUsername());
		if (entity == null)
			return Response.status(Status.BAD_REQUEST).build();

		// SEND EMAIL WITH PASSWORD HERE

		return Response.ok().build();
	}

	@POST
	@Path("recover_username")
	public Response recoverUsername(EmailInfo info) {
		SignInInfoEntity entity = getRepository().findByEmail(info.getEmail());
		if (entity == null)
			return Response.status(Status.BAD_REQUEST).build();

		// SEND EMAIL WITH USERNAME HERE

		return Response.ok().build();
	}

}
