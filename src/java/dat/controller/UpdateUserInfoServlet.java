/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.error.updateError;
import dat.registration.RegistrationDAO;
import java.io.IOException;
import java.sql.SQLException;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class UpdateUserInfoServlet extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(UpdateUserInfoServlet.class);
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
        response.setContentType("text/html;charset=UTF-8");
        String username = request.getParameter("txtUsername");
        String password = request.getParameter("txtPassword");
        String admin = request.getParameter("chkAdmin");
        boolean isAdmin = false;
        String lastSearchValue = request.getParameter("txtLastSearch");
        String url="";
        HttpSession session = request.getSession();
        try{
            if(password.trim().length() >= 6 && password.trim().length() <= 30){
                RegistrationDAO dao = new RegistrationDAO();
                if(admin != null){
                    if(admin.equals("ON")){
                        isAdmin = true;
                    }//end if admin is ON 
                }//end if admin is not null
                boolean result = dao.updateUserPasswordAndRole(username, password, isAdmin);
                if(result){
                    session.removeAttribute("deleteError");
                    session.removeAttribute("error");
                    url = "searchButton?txtLastSearch="+lastSearchValue;
                }// end if result is true
            }else{
                updateError error = new updateError();
                error.setPasswordLengthError("Password is required 6 to 30 characters");
                session.setAttribute("error", error);
                session.removeAttribute("deleteError");
                url = "searchButton?txtLastSearch="+lastSearchValue;
            }
        }catch(SQLException | NamingException ex){
            LOGGER.error(ex);
        }
        finally{
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);
            response.sendRedirect(url);
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
