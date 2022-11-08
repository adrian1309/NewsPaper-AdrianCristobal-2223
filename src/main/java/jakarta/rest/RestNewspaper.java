package jakarta.rest;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import model.Newspaper;
import org.modelmapper.ModelMapper;
import service.ServiceNewspaper;
import service.impl.ServiceNewspaperSpring;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Path("/newspaper")
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
    @Path("/findOneNewspaper")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNewspaper(@QueryParam("id") String id,
                               @Context HttpServletRequest request) {

        AtomicReference<Response> r = new AtomicReference();
        serviceNewspaper.findOne(Integer.parseInt(id))
                .peek(newspaper -> r.set(Response.ok(newspaper).build()))
                .peekLeft(apiError -> r.set(Response.status(Response.Status.NOT_FOUND)
                        .entity(apiError)
                        .build()));
        return r.get();
    }

    @GET
    @Path("/findAllNewspaper")
    public List<Newspaper> getAllNewspaper() {
        List<Newspaper> newspaperList = serviceNewspaper.findNewspapers().get();
        return newspaperList;
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
