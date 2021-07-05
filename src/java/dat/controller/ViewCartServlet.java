/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dat.controller;

import dat.cart.CartObject;
import dat.product.ProductDAO;
import dat.product.ProductDTO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Admin
 */
public class ViewCartServlet extends HttpServlet {

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
        response.setHeader("Cache-Control", "no-cache");
        Map<String,String> roadmap = (Map<String,String>)request.getServletContext().getAttribute("ROAD_MAP");
        String url = roadmap.get("viewItem");
        try{
            HttpSession session = request.getSession(false);
            if(session != null){
                CartObject cart = (CartObject) session.getAttribute("CART");
                if(cart == null || cart.getItems() == null){
                    String error = "No items in your cart or No cart is existed";
                    request.setAttribute("error", error);
                    session.setAttribute("LIST_ITEM_IN_VIEW", null );
                    }//end if cart or item in cart is null
                if(cart.getItems() != null){
                    ProductDAO dao = new ProductDAO();
                    Map<String,Integer> itemsInCart =  cart.getItems();
                    dao.loadTotalPriceOfItemToViewCart(itemsInCart);
                    List<ProductDTO> listItemsInViewCart = dao.getListItemInViewCart();
                    session.setAttribute("LIST_ITEM_IN_VIEW", listItemsInViewCart);
                    }//end if cart is existed
                }//end if session is timeout
            else{
                String error = "No items in your cart or No cart is existed";
                request.setAttribute("error", error);
            }
        }catch(Exception ex){
            
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
