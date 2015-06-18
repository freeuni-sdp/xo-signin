package ge.edu.freeuni.sdp.xo.signin.service;

import java.net.URI;
import java.util.*;

import ge.edu.freeuni.sdp.xo.signin.data.*;
import ge.edu.freeuni.sdp.xo.signin.data.json.SigninInfo;

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
		if(info.getEmail() == null || info.getUsername() == null || info.getPassword() == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		return Response.status(Status.CREATED).build();
	}
}
