/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.registration.RegistrationDAO;
import dat.registration.RegistrationInsertError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author Admin
 */
public class CreateNewAccount extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(CreateNewAccount.class);
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
        String confirmPassword = request.getParameter("txtConfirmPassword");
        String fullname = request.getParameter("txtFullName");
        RegistrationInsertError errors = new RegistrationInsertError();
        Map<String,String> roadmap = (Map<String, String>) request.getServletContext().getAttribute("ROAD_MAP");
        String url = roadmap.get("createNewAccountError");
        boolean foundError = false;
        try{
            //1 check errors
            if(username.trim().length() < 6 || username.trim().length() > 20 ){
                foundError = true;
                errors.setUsernameLengthError("Username is required 6 to 20 characters");
            }//end if username length is error
            if(password.trim().length() <6 || password.trim().length() > 30){
                foundError = true;
                errors.setPasswordLengthError("Password is required 6 to 30 characters");
            }else if(!confirmPassword.trim().equals(password.trim())){
                foundError = true;
                errors.setConfirmNotMatch("Confirm password is not matched");
            }// end if confirm password does not match
            if(fullname.trim().length() < 2 || fullname.trim().length() > 50){
                foundError = true;
                errors.setFullnameLengthError("Full name is required 2 to 50 characters");
            }//end if fullname length is errors
            // 2 process
            if(foundError){
                //send error to user
                request.setAttribute("errors", errors);
            }//end if found Error is true
            else{
                RegistrationDAO dao = new RegistrationDAO();
                boolean result =  dao.createNewAccount(username, password, fullname);
                if(result){
                    url = "login.html";
                }
            }
            
        }catch(SQLException ex){
            LOGGER.error(ex);
            String msg = ex.getMessage();
            log(("CreateAccountServlet_SQL ") + msg);
            if(msg.contains("duplicate")){
                errors.setDuplicatedUsername(username + " is existed");
                request.setAttribute("errors", errors);
            }
        } catch (NamingException ex) {
            LOGGER.error(ex);
        }finally{
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
