/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.registration.RegistrationDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
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
public class DeleteUserByUserNameServlet extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(DeleteUserByUserNameServlet.class);
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
        String deleteUsername = request.getParameter("txtUsername");
        String lastSearchValue = request.getParameter("txtLastSearch");
        Map<String,String> roadmap = (Map<String,String>) request.getServletContext().getAttribute("ROAD_MAP");
        String url = roadmap.get("deleteError");
        try{
            RegistrationDAO dao = new RegistrationDAO();
            HttpSession session = request.getSession(false);
            String currentUser = (String) session.getAttribute("username");
            if(deleteUsername.equals(currentUser)){
                String deleteError = "You can not delete your account";
                session.setAttribute("deleteError", deleteError);
                url = "searchButton?txtLastSearch="+lastSearchValue;
            }
            else{
                boolean result = dao.deleteUserByUsername(deleteUsername);
                if(result){
                    session.removeAttribute("deleteError");
                    url = "searchButton?txtLastSearch="+lastSearchValue;
                }//end if result is true
            }
        }catch(SQLException | NamingException ex){
            LOGGER.error(ex);
        }finally{
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
