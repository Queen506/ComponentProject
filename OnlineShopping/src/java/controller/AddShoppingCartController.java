/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProductTable;
import model.Products;
import model.ShoppingCartTable;
import model.Shoppingcart;
import model.ShoppingcartPK;

/**
 *
 * @author ntpsm
 */
public class AddShoppingCartController extends HttpServlet {

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
            throws ServletException, IOException{
        String[] selectedCheckboxes = request.getParameterValues("select");//[5,6]
        String[] qtySelected = request.getParameterValues("qty");//[,,,,2,5,]
        int cartId = getLastCartId();
        int j = 0;
        //if (selectedCheckboxes != null) {
            for (int i = 0; i<selectedCheckboxes.length;i++ ) {
                String movieIdstr = selectedCheckboxes[i];//.replaceAll("/", "");
                //if ((movieIdstr!= null) && (!movieIdstr.isEmpty())) {
                    //try {
                        int movieId = Integer.parseInt(movieIdstr);
                        Shoppingcart shop = new Shoppingcart();
                        ShoppingcartPK spk = new ShoppingcartPK(cartId, movieId);
                        shop.setShoppingcartPK(spk);
                        Integer qty = Integer.parseInt(qtySelected[movieId-1]);
                        shop.setQuantity(qty);
                        int rowInserted = ShoppingCartTable.insertShoppingcart(shop);
                        request.setAttribute("rowInserted", rowInserted);
                    
                //}
            }
        //}
        
        request.setAttribute("cartId", cartId);
        request.getRequestDispatcher("add_result.jsp").forward(request, response);
    }

    public int getLastCartId(){
        ShoppingCartTable st = new ShoppingCartTable();
        List<Shoppingcart> shop = st.findAllShoppingCart();
        Shoppingcart last = shop.get(shop.size()-1);
        int lasted = last.getShoppingcartPK().getCartId();
        return lasted+1;
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
