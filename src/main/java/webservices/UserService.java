package webservices;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import media.User;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;

/**
 * Created by alexa on 17/06/2017.
 */

@WebService
@Path("/user")
@Produces("application/json; charset=UTF-8")
public class UserService
{
    @Inject
    private services.UserService userService;

    @Transactional
    @GET
    @Path("/find/{id}")
    public String getUser(@PathParam("id") final Integer id)
    {
        User user = userService.getById(id);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try
        {
            return ow.writeValueAsString(user);
        }
        catch (JsonProcessingException e)
        {
            e.printStackTrace();
            return "error";
        }
    }
}
