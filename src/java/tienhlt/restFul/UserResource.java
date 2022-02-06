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
    
    @Path("/checkLogin")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String checkLogin(
            @QueryParam("username") String username,
            @QueryParam("password") String password) 
            throws SQLException, NamingException {
        UserDAO userDAO = new UserDAO();
        boolean result = userDAO.checkLogin(username, password);
        if (result)
            return "true";
        else 
            return "false";
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
    
    @GET // Mặc định GET sẽ gọi method này
    @Produces(MediaType.APPLICATION_JSON)    
    public UserDTO getAll() {         
        UserDTO dto = new UserDTO("tien", "tientien", "Tiên", true);
        return dto;
    }
}
