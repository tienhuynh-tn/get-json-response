/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tienhlt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.RequestContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;
import org.json.simple.JSONObject;
import tienhlt.user.UserDAO;
import tienhlt.user.UserDTO;

/**
 *
 * @author tienhltse151104
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();

        String url = "invalid.html";

        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        RequestContext rqc = new ServletRequestContext(request);
        
        String username = "";
        String password = "";

        try {
            //Store request form data in a list
            List list = sfu.parseRequest(rqc);
            for (Object o : list) {
                //Treat each form data as FileItem
                FileItem item = (FileItem) o;
                //Branch depending on whether FileItem is a field or a file
                if (item.isFormField()) {
                    //For fields, get the field name and value
                    //(In the form<input name="Field name" value="concents inputted")
                    String fieldName = item.getFieldName();
                    String value = item.getString();
                    if (fieldName.equals("txtUsername")) {
                        //Username processing
                        username = (String)value;
                        System.out.println("username : " + value);
                    } else if (fieldName.equals("txtPassword")) {
                        //Password processing
                        password = (String)value;
                        System.out.println("password : " + value);
                    }
                }
            }

            UserDAO dao = new UserDAO();
            boolean result = dao.checkLogin(username, password);

            if (result) {
                UserDTO dto = dao.showProfile(username);
                JSONObject jobject = new JSONObject();
                jobject.put("username", dto.getUsername());
                jobject.put("password", dto.getPassword());
                jobject.put("lastname", dto.getLastname());
                jobject.put("role", dto.isRole());
                System.out.println("jo: " + jobject);
                out.print(jobject);
                response.flushBuffer();
                out.flush();
            }
        } catch (NamingException ex) {
            log("LoginServlet_Naming: " + ex.getMessage());
        } catch (SQLException ex) {
            log("LoginServlet_SQL: " + ex.getMessage());
        } catch (FileUploadException ex) {
            log("LoginServlet_FileUploadException: " + ex.getMessage());
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
