package jakarta.rest;

import domain.service.ServiceNewspaper;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import domain.model.Newspaper;
import domain.service.impl.ServiceNewspaperImpl;
import utils.PathsConstants;
import utils.QueryParamsConstants;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path(PathsConstants.PATH_NEWSPAPER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestNewspaper {

    private final ServiceNewspaper serviceNewspaper;

    @Inject
    public RestNewspaper(ServiceNewspaperImpl serviceNewspaperImpl) {
        this.serviceNewspaper = serviceNewspaperImpl;

    }


    @GET
    //No se pone path al getAll porque se llama cuando se llame al path PathsConstants.PATH_NEWSPAPER
    public Response getAll() {
        AtomicReference<Response> r = new AtomicReference<>();
        serviceNewspaper.findAll()
                .peek(list -> r.set(Response.ok(list).build()))
                .peekLeft(apiError -> r.set(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(apiError)
                        .build()));
        return r.get();
    }


    @GET
    @Path(PathsConstants.PATH_FIND_ONE)
    public Newspaper getOne(@PathParam(QueryParamsConstants.ID) String id) {

        return serviceNewspaper.findOne(Integer.parseInt(id));
    }



    @POST
    public Response add(Newspaper newspaper) {
        serviceNewspaper.add(newspaper);
        //devolvemos un response en vez de solo el objeto newspaper para que el status sea 201
        return Response.status(Response.Status.CREATED).entity(newspaper.getId()).build();
    }

    @DELETE
    public Response delete(@QueryParam(QueryParamsConstants.ID) String id) {
        serviceNewspaper.delete(Integer.parseInt(id));
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @PUT
    public Newspaper update(Newspaper newspaper) {
        return serviceNewspaper.update(newspaper);
    }

    @GET
    @Path(PathsConstants.PATH_NEWSPAPER_FIND_ALL_LESS_THAN_DATE)
    public List<String> findAllNewspaperLessThanDate(@QueryParam(QueryParamsConstants.DATE) String date) {
        return serviceNewspaper.findAllNewspaperLessThanDate(date);
    }


}
