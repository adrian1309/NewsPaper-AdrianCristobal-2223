package jakarta.rest;

import domain.model.Reader;
import domain.service.ServiceReader;
import domain.service.impl.ServiceReaderImpl;
import jakarta.filtro.Secure;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import utils.PathsConstants;
import utils.QueryParamsConstants;

import java.util.concurrent.atomic.AtomicReference;

@Path(PathsConstants.PATH_READER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestReader {

    private final ServiceReader serviceReader;

    @Inject
    public RestReader(ServiceReaderImpl serviceReaderImpl) {
        this.serviceReader = serviceReaderImpl;
    }

    @GET
    @Secure
    public Response getAll() {
        AtomicReference<Response> r = new AtomicReference<>();
        serviceReader.findAll()
                .peek(list -> r.set(Response.ok(list).build()))
                .peekLeft(apiError -> r.set(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(apiError)
                        .build()));
        return r.get();
    }

    @GET
    @Path(PathsConstants.PATH_FIND_ONE)
    public Reader getOne(@PathParam(QueryParamsConstants.ID) String id) {

        return serviceReader.findOne(Integer.parseInt(id));
    }

    @POST
    public Response add(Reader reader) {
        serviceReader.add(reader);
        return Response.status(Response.Status.CREATED).entity(reader.getId()).build();
    }

    @DELETE
    public Response delete(@QueryParam(QueryParamsConstants.ID) String id) {
        serviceReader.delete(Integer.parseInt(id));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    public Reader update(Reader reader) {
        return serviceReader.update(reader);
    }
}
