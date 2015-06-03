package ge.edu.freeuni.sdp.xo.signin;

import ge.edu.freeuni.sdp.xo.signin.data.Repository;
import ge.edu.freeuni.sdp.xo.signin.data.RepositoryFactory;
import ge.edu.freeuni.sdp.xo.signin.data.TaskEntity;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("ping")
@Consumes( { MediaType.APPLICATION_JSON})
@Produces( { MediaType.APPLICATION_JSON})
public class PingService {

  @GET
  public Response get() {
    return Response.ok().build();
  }

}
