<%-- 
    Document   : shoppingcartPages
    Created on : Oct 27, 2023, 2:31:36 PM
    Author     : LENOVO
--%>

<%@page import="java.util.Iterator"%>
<%@page import="model.ShoppingcartTable"%>
<%@page import="java.util.List"%>
<%@page import="model.Shoppingcart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shopping cart</title>
    </head>
    <<jsp:useBean id="shop" class="model.Shoppingcart" scope="request"/>
     <%
            //Object cartIdObject = request.getAttribute("cartId");
            Integer cartIdInteger = (Integer) session.getAttribute("cartId");
            int cartId = 0;
            if (cartIdInteger != null) {

                try {
                     cartId = cartIdInteger.intValue();
                     
                      
                } catch (NumberFormatException e) {
                }
            }
            List<Shoppingcart> shopList =ShoppingcartTable.findShoppingcartBycartId(cartId);
            Iterator<Shoppingcart> itr = shopList.iterator();
            //session.removeAttribute("cartId");
     %>
    <body>
        <%int total = 0;%>
        <center>
        <h1>Shopping cart</h1>
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
                   int pricess = shop.getQuantity()*shop.getProducts().getPrice();
                   out.println("<td> "+ (pricess) + "</td>");
                   out.println("<tr>");
                   total += pricess;
               }
          %>
          <tr>
              <td>Total</td>
              <%
                 out.println("<td> "+ total + "</td>");
              %>
          </tr>
        </table>
        
    <form action="ConfirmInformation.jsp" method="POST" >
        <% session.setAttribute("total", total); %>
        <input type="submit" value="checkout"/>
    </form>
    
    
 </center>
    </body>
</html>
