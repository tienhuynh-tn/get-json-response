/*
 * © 2022 tienhuynh-tn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.restFul;

import java.sql.SQLException;
import javax.naming.NamingException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import tienhlt.user.UserDAO;
import tienhlt.user.UserDTO;

/**
 * REST Web Service
 *
 * @author Tien Huynh - Tien Huynh TN - Huynh Le Thuy Tien
 */
@Path("user")
public class UserResource {

    @Context
    private UriInfo context;

    public UserResource() {
    }

    @Path("/showprofile")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public UserDTO showProfile(
            @QueryParam("username") String username) 
            throws SQLException, NamingException{
        UserDAO userDAO = new UserDAO();
        return userDAO.showProfile(username);
    }
}
