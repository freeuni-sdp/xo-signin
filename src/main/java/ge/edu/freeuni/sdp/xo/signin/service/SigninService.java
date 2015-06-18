package ge.edu.freeuni.sdp.xo.signin.service;

import java.net.URI;
import java.util.*;

import ge.edu.freeuni.sdp.xo.signin.data.*;
import ge.edu.freeuni.sdp.xo.signin.data.json.SigninInfo;
import ge.edu.freeuni.sdp.xo.signin.data.json.UserInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.Response.Status;

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
	public Response registerUser(SigninInfo info) {
		return null;
	}

	@GET
	@Path("/confirm_email/{token}")
	public Response confirmEmail(@PathParam("token") String token) {
		return null;
	}
	
	@POST
	@Path("/recover_username")
	public Response recoverUsername(@PathParam("email") String email) {
		return null;
	}

	@POST
	@Path("/recover_password")
	public Response recoverPassword(UserInfo info) {
		return null;
	}

}
