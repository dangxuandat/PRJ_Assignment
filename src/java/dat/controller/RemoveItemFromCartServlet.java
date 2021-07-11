/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.cart.CartObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
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
public class RemoveItemFromCartServlet extends HttpServlet {
    private final Logger LOGGER = Logger.getLogger(RemoveItemFromCartServlet.class);
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
        Map<String,String> roadmap = (Map<String,String>) request.getServletContext().getAttribute("ROAD_MAP");
        String url = roadmap.get("viewItemInCart");
        try {
            //get session false in case session is time-out
            HttpSession session = request.getSession(false);
            if(session != null){
                CartObject cart = (CartObject) session.getAttribute("CART");
                if(cart != null){
                   Map<String,Integer> items = cart.getItems();
                   if(items != null){
                       String[] removedItem = request.getParameterValues("checkedItem");
                       if(removedItem != null){
                           for (String itemName : removedItem) {
                               cart.removeItemToCart(itemName);
                           }//end traverse array
                           session.setAttribute("CART", cart);
                       }//end Remove Items
                   }//end item has existed
                }//end if cart has existed
            }// end if ses
        }catch(Exception ex){
            LOGGER.error(ex);
        }
        finally{
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
