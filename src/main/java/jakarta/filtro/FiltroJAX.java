package jakarta.filtro;


import jakarta.errores.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;
import utils.ErrorConstants;

import java.io.IOException;

@Provider
@Secure
public class FiltroJAX implements ContainerRequestFilter {

    @Context
    private HttpServletRequest request;


    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        if (request.getSession().getAttribute("LOGIN")==null || request.getSession().getAttribute("LOGIN").equals(false)) {

            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN)
                    .entity(new ApiError(ErrorConstants.ERROR_LOGIN))
                    .type(MediaType.APPLICATION_JSON_TYPE).build());
        }
    }
}
