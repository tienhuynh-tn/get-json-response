/*
 * © 2022 tienhuynh-tn
 * All rights reserved!
 * For more information, please contact via my email: tien.huynhlt.tn@gmail.com
 */
package tienhlt.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author Tien Huynh - Tien Huynh TN - Huynh Le Thuy Tien
 */
public class DBHelper implements Serializable {

    public static Connection makeConnection() 
            throws NamingException, SQLException {
        Context context = new InitialContext();
        Context tomcatContext = (Context)context.lookup("java:comp/env");
        DataSource ds = (DataSource)tomcatContext.lookup("tienhuynhtn");
        Connection con = ds.getConnection();
        
        return con;
    }
}
