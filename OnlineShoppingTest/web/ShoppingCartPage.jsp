<%-- 
    Document   : ShoppingCart
    Created on : Oct 26, 2023, 11:38:42 PM
    Author     : LENOVO
--%>

<%@page import="model.ShoppingcartTable"%>
<%@page import="java.util.Iterator"%>
<%@page import="model.Shoppingcart"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add To Shopping Cart</title>
    </head>
    <<jsp:useBean id="shop" class="model.Shoppingcart" scope="request"/>
     <%
                       
            List<Shoppingcart> proList =ShoppingcartTable.findAllShoppingcart();
            Iterator<Shoppingcart> itr = proList.iterator();
            
     %>
    <body>
        <center>
        <h1>DVD Catalog</h1>
        <table border="1">
          <tr>
            <th>DVD Name</th>
            <th>Rate</th>
            <th>Year</th>
            <th>Price/unit</th>
            <th>Quantity</th>
            <th>Price</th>
          </tr>
          <%
              
               while(itr.hasNext()) {
                   shop = itr.next();
                   out.println("<tr>");
                   out.println("<td> "+ shop.getProducts().getMovie() + "</td>");
                   out.println("<td> "+ shop.getProducts().getRating() + "</td>");
                   out.println("<td> "+ shop.getProducts().getYearcreate() + "</td>");
                   out.println("<td> "+ shop.getProducts().getPrice() + "</td>");
                   out.println("<td> "+ shop.getQuantity() + "</td>");
                   out.println("<td> "+ (shop.getQuantity()*shop.getProducts().getPrice()) + "</td>");
                   out.println("<tr>");
               }
          %>
          <tr>
              <td>Total</td>
              <%
                  
                  //int total = 0;
                  //List<Shoppingcart> shoplist = shop.findShoppingcartBycartId();
              %>
          </tr>
        </table>
        
    <form action="ShoppingCart.jsp" method="POST">
        <input type="submit" value="Add to Cart"/>
    </form>
    
    
 </center>
    </body>
</html>
