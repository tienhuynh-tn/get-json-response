/*
 * © 2022 tienhuynh-tn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.user;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import tienhlt.utils.DBHelper;

/**
 *
 * @author Tien Huynh - Tien Huynh TN - Huynh Le Thuy Tien
 */
public class UserDAO implements Serializable {
    public boolean checkLogin(String username, String password)
            throws SQLException, NamingException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
    
        try {
            //1. make connection to DB
            con = DBHelper.makeConnection();
            if (con != null) {
                //2. create SQL String
                String sql = "Select username "
                        + "From [User] "
                        + "Where username = ? And password = ?";
                //3. create statement object to load SQL String
                //and set value to parameters
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                stm.setString(2, password);
                //4. execute query
                rs = stm.executeQuery();
                //5. process result
                if (rs.next()) {
                    return true;
                }
            }//end if con is opened
        } finally { 
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return false;
    }
    
    public UserDTO showProfile(String username) 
        throws SQLException, NamingException{
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBHelper.makeConnection();
            if (con != null) {
                String sql = "Select username, password, lastname, isAdmin "
                        + "From [User] "
                        + "Where username = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, username);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String userName = rs.getString("username");
                    String password = rs.getString("password");
                    String fullname = rs.getString("lastname");
                    boolean role = rs.getBoolean("isAdmin");
                    
                    UserDTO dto = new UserDTO(userName, password, fullname, role);
                    return dto;
                }
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return null;
    } 
}
