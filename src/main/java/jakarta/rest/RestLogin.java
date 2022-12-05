package jakarta.rest;

import domain.service.ServiceLogin;
import domain.service.ServiceNewspaper;
import domain.service.impl.ServiceLoginImpl;
import domain.service.impl.ServiceNewspaperImpl;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import utils.PathsConstants;

import java.util.concurrent.atomic.AtomicReference;

@Path(PathsConstants.PATH_USER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestLogin {

    private final ServiceLogin serviceLogin;

    @Inject
    public RestLogin(ServiceLoginImpl serviceLoginImpl) {
        this.serviceLogin = serviceLoginImpl;
    }

    @GET
    public Response findAll() {
        AtomicReference<Response> r = new AtomicReference<>();
        serviceLogin.findAll()
                .peek(list -> r.set(Response.ok(list).build()))
                .peekLeft(apiError -> r.set(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(apiError)
                        .build()));
        return r.get();
    }
}
