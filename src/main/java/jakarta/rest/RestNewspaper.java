package jakarta.rest;

import jakarta.enterprise.inject.New;
import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import domain.model.Newspaper;
import org.modelmapper.ModelMapper;
import domain.service.ServiceNewspaper;
import domain.service.impl.ServiceNewspaperSpring;
import utils.PathsStrings;
import utils.QueryParamsStrings;

import java.util.concurrent.atomic.AtomicReference;

@Path(PathsStrings.PATH_NEWSPAPER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestNewspaper {

    private ServiceNewspaper serviceNewspaper;

    private ModelMapper mapper;

    @Inject
    public RestNewspaper(ServiceNewspaperSpring serviceNewspaperSpring, ModelMapper mapper) {
        this.serviceNewspaper = serviceNewspaperSpring;
        this.mapper = mapper;
    }


    @GET
    @Path(PathsStrings.PATH_NEWSPAPER_FIND_ALL)
    public Response getAll() {
        AtomicReference<Response> r = new AtomicReference();
        serviceNewspaper.findAll()
                .peek(list -> r.set(Response.ok(list).build()))
                .peekLeft(apiError -> r.set(Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                        .entity(apiError)
                        .build()));
        return r.get();
    }


    @GET
    @Path(PathsStrings.PATH_NEWSPAPER_FIND_ONE)
    @Produces(MediaType.APPLICATION_JSON)
    public Newspaper getOne(@QueryParam(QueryParamsStrings.ID) String id,
                               @Context HttpServletRequest request) {

        Newspaper newspaper = serviceNewspaper.findOne(Integer.parseInt(id));

        return newspaper;
    }

    @POST
    public Newspaper add(Newspaper newspaper) {
        return serviceNewspaper.add(newspaper);
    }

    @DELETE
    public boolean delete(@QueryParam("id") String id) {
        return serviceNewspaper.delete(Integer.parseInt(id));
    }

/*
    @GET
    @RolesAllowed("ADMIN")
    @Path("/{id}")
    public Response getUnUsuario(@PathParam("id") String id,
                                @HeaderParam("kk") String head) {
        AtomicReference<Response> r = new AtomicReference();
        su.dameUno(id)
                .peek(usuario -> r.set(Response.ok().entity(usuario).build()))
                .peekLeft(apiError -> r.set(Response.status(Response.Status.NOT_FOUND)
                        .entity(new ApiError(apiError.getMessage(), LocalDateTime.now()))
//
//                                ApiError.builder()
//                                .message("error not found")
//                                .fecha(LocalDateTime.now())
//                                .build())
                        .build()));

        return r.get();
    }

    @GET
    public List<Usuario> getAllUsuario() {
        return su.dameTodos();
    }

    @GET
    @Path("/hib")
    public List<UsuarioEntity> getAllUsuarioHibernate() {
        return su.dameTodosHibernate();
    }


    @POST
    public Usuario addUsuario(Usuario user) {
        return su.addUser(user);
    }

    @DELETE
    public Response delUsuario(@QueryParam("id") String id) {
        if (su.borrar(id))
            return Response.status(Response.Status.NO_CONTENT).build();
        else
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(ApiError.builder()
                            .message("usuario no encontrado")
                            .fecha(LocalDateTime.now())
                            .build())
                    .build();
    }

 */
}
